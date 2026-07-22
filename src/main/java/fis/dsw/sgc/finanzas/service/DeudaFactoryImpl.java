package fis.dsw.sgc.finanzas.service;

import fis.dsw.sgc.finanzas.model.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DeudaFactoryImpl implements IDeudaFactory {

    @Override
    public Deuda crearDeuda(String motivo, int idResidente, double valor, Date fecha) {
        Deuda nuevaDeuda = armarDeudaBase(idResidente, valor, fecha);

        switch (motivo.toUpperCase()) {
            case "MULTA":
                nuevaDeuda.setTipoDeuda(new DeudaMulta());
                break;
            case "RESERVA":
                nuevaDeuda.setTipoDeuda(new DeudaReserva());
                break;
            case "ALICUOTA":
                throw new IllegalArgumentException("Para crear una alícuota, utilice el método crearDeudaAlicuota().");
            default:
                throw new IllegalArgumentException("Motivo de deuda no reconocido: " + motivo);
        }

        if (nuevaDeuda.getTipoDeuda() != null) {
            nuevaDeuda.calcularValorTotal();
        }
        return nuevaDeuda;
    }

    // --- NUESTRA NUEVA MAGIA PARA LAS ALÍCUOTAS ---
    @Override
    public Deuda crearDeudaAlicuota(int idResidente, double valorBase, Date fecha, double tamanoDepto, double tamanoCondominio) {
        Deuda nuevaDeuda = armarDeudaBase(idResidente, valorBase, fecha);

        // Le inyectamos la estrategia con los datos REALES que nos mandó el Service
        nuevaDeuda.setTipoDeuda(new DeudaAlicuota(tamanoDepto, tamanoCondominio));
        nuevaDeuda.calcularValorTotal();

        return nuevaDeuda;
    }

    // --- Método auxiliar para no repetir código (Clean Code) ---
    private Deuda armarDeudaBase(int idResidente, double valor, Date fecha) {
        Deuda deuda = new Deuda();
        deuda.setIdUsuario(idResidente);
        deuda.setValorBase(valor);

        LocalDate fechaVencimiento = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        deuda.setFechaVencimiento(fechaVencimiento);
        deuda.setEstado(new EstadoPendiente());

        return deuda;
    }
}