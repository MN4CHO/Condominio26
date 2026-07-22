package fis.dsw.sgc.finanzas.service;

import fis.dsw.sgc.finanzas.dao.IDeudaDAO;
import fis.dsw.sgc.finanzas.dao.IPagoDAO;
import fis.dsw.sgc.finanzas.dto.PagoTarjetaDTO;
import fis.dsw.sgc.finanzas.exception.DeudaEnProcesoDePagoException;
import fis.dsw.sgc.finanzas.exception.DeudaNoExisteException;
import fis.dsw.sgc.finanzas.exception.DeudaYaFuePagadaException;
import fis.dsw.sgc.finanzas.exception.TarjetaVencidaException;
import fis.dsw.sgc.finanzas.model.*;

import java.time.LocalDate;

public class PagoServiceImpl implements IPagoService {

    private IPagoFactory pagoFactory;
    private IPagoDAO pagoDAO;
    private IDeudaDAO deudaDAO;

    // Inyección de dependencias reales
    public PagoServiceImpl(IPagoFactory pagoFactory, IPagoDAO pagoDAO, IDeudaDAO deudaDAO) {
        this.pagoFactory = pagoFactory;
        this.pagoDAO = pagoDAO;
        this.deudaDAO = deudaDAO;
    }

    @Override
    public void registrarPagoEfectivoTransferenciaResidente(Integer idDeuda) {
        // 1. Buscar la deuda en la base de datos real[cite: 20]
        Deuda deudaBD = deudaDAO.buscarPorId(idDeuda);

        // 2. SI NO existe, lanza error[cite: 20]
        if (deudaBD == null) {
            throw new DeudaNoExisteException("No existe una deuda con el identificador proporcionado");
        }

        // 3. Verificamos contra el estado exacto de la BD[cite: 20]
        String estadoActual = deudaBD.getEstado().getNombreEstadoBD();

        // 4. SI el estado es 'PAGADA'[cite: 20]
        if (estadoActual.equals("PAGADA")) {
            throw new DeudaYaFuePagadaException("Esta deuda ya ha sido pagada");
        }

        // 5. SI el estado es 'EN_PROCESO' (Esperando validación contable)[cite: 20]
        if (estadoActual.equals("EN_PROCESO")) {

            // Delegamos al modelo rico procesar el pago final
            deudaBD.setEstado(new EstadoPagada());
            deudaDAO.actualizar(deudaBD);

            // Generamos la entidad Pago a través del Factory y guardamos
            Pago nuevoPago = pagoFactory.crearPago("EFECTIVO", deudaBD, deudaBD.getSaldo(), null);
            nuevoPago.ejecutarCobro();
            nuevoPago.setEstado("VALIDADO");

            pagoDAO.guardar(nuevoPago);

            System.out.println("Pago registrado exitosamente");
        } else {
            throw new DeudaEnProcesoDePagoException("La deuda no se encuentra 'EN PROCESO' para ser validada.");
        }
    }

    @Override
    public void pagarDeuda(Integer idDeuda, String metodoPago) {
        Deuda deudaBD = deudaDAO.buscarPorId(idDeuda);

        if (deudaBD == null) {
            throw new DeudaNoExisteException("No existe la deuda.");
        }

        if (metodoPago.equalsIgnoreCase("EFECTIVO") || metodoPago.equalsIgnoreCase("TRANSFERENCIA")) {
            // Cambiamos el estado de la deuda y actualizamos en base de datos[cite: 20]
            deudaBD.setEstado(new EstadoEnProceso());
            deudaDAO.actualizar(deudaBD);

            if (metodoPago.equalsIgnoreCase("EFECTIVO")) {
                System.out.println("Acerquese a oficinas de contabilidad para efectuar el pago");
            } else {
                System.out.println("Se revisara el deposito y se actualizara el estado de su deuda en las próximas horas");
            }
        }
    }

    @Override
    public void pagarDeudaTarjeta(PagoTarjetaDTO dto) {
        Deuda deudaBD = deudaDAO.buscarPorId(dto.getIdDeuda());

        if (deudaBD == null) {
            throw new DeudaNoExisteException("No existe la deuda.");
        }

        if (dto.getFechaVencimiento().isBefore(LocalDate.now())) {
            throw new TarjetaVencidaException("La tarjeta ingresada se encuentra caducada.");
        }

        // Usamos el Factory para orquestar el pago con tarjeta[cite: 20]
        Pago pagoTarjeta = pagoFactory.crearPago("TARJETA", deudaBD, deudaBD.getSaldo(), dto.getNumeroTarjeta());

        // Si la pasarela de pagos aprueba (simulado en el Strategy)[cite: 20]
        if (pagoTarjeta.ejecutarCobro()) {
            deudaBD.procesarPago(deudaBD.getSaldo());
            deudaBD.setEstado(new EstadoPagada());

            deudaDAO.actualizar(deudaBD);
            pagoDAO.guardar(pagoTarjeta);

            System.out.println("Deuda cancelada exitosamente con tarjeta a nombre de: " + dto.getNombreTitular());
        }
    }
}