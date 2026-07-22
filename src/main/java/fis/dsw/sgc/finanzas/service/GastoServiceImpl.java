package fis.dsw.sgc.finanzas.service;

import fis.dsw.sgc.finanzas.dao.IGastoDAO;
import fis.dsw.sgc.finanzas.dto.NuevoGastoDTO;
import fis.dsw.sgc.finanzas.exception.DescripcionGastoInvalidoException;
import fis.dsw.sgc.finanzas.exception.FechasInvalidasException;
import fis.dsw.sgc.finanzas.exception.ValorPagadoInvalidoException;
import fis.dsw.sgc.finanzas.model.Gasto;
import java.time.LocalDate;

public class GastoServiceImpl implements IGastoService {

    private GastoFactoryImpl gastoFactory;
    private IGastoDAO gastoDAO;

    // Inyección de dependencias
    public GastoServiceImpl(IGastoDAO gastoDAO) {
        this.gastoFactory = new GastoFactoryImpl();
        this.gastoDAO = gastoDAO;
    }

    @Override
    public void registrarPagosCondominio(NuevoGastoDTO dto) {
        // 1. Validar reglas de negocio básicas del Caso de Uso
        if (dto.getFechaPago() == null || dto.getFechaPago().isAfter(LocalDate.now())) {
            throw new FechasInvalidasException("La fecha del pago debe ser menor o igual a la fecha actual.");
        }

        if (dto.getValorPagado() == null || dto.getValorPagado() <= 0) {
            throw new ValorPagadoInvalidoException("El valor pagado debe ser mayor a 0 y ser un decimal válido.");
        }

        // 2. Creación del objeto Gasto según su motivo
        Gasto nuevoGasto = gastoFactory.crearGasto(
                dto.getMotivo(),
                dto.getDescripcion(),
                dto.getValorPagado(),
                dto.getFechaPago()
        );

        // 3. Validar descripción delegando al modelo (Polimorfismo)
        if (!nuevoGasto.validarDetalle()) {
            if (nuevoGasto.getTipoGasto().equals("SERVICIO_BASICO")) {
                throw new DescripcionGastoInvalidoException("La descripción para servicios básicos debe ser exactamente AGUA, LUZ, TELEFONO o INTERNET.");
            } else {
                throw new DescripcionGastoInvalidoException("La descripción excede los 200 caracteres o contiene caracteres no permitidos.");
            }
        }

        // 4. Guardar en Base de Datos Real
        // OJO: Estos IDs (Condominio y Usuario) deberían venir de la sesión activa de tu sistema.
        // Por ahora enviamos IDs válidos (1 y 101) para que guarde correctamente.
        int idCondominioActual = 1;
        int idAdministradorActual = 101;

        gastoDAO.guardar(nuevoGasto, idCondominioActual, idAdministradorActual);

        // 5. Mensaje de éxito final
        System.out.println("Pago externo guardado exitosamente");
    }
}