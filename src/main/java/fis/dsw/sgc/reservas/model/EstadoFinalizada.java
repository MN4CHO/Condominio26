package fis.dsw.sgc.reservas.model;

import fis.dsw.sgc.reservas.service.IServicioReservas;

public class EstadoFinalizada implements IEstadoReserva {

    @Override
    public String getNombreEstado() {
        return "FINALIZADA";
    }

    @Override
    public boolean puedeCancelar() {
        return false;
    }

    @Override
    public boolean puedeAgregarObservacion() {
        return true; // Solo en estado finalizado se pueden agregar observaciones de auditoría
    }

    @Override
    public void evaluarVencimiento(Reserva reserva, IServicioReservas servicio) {
        // Una reserva finalizada ya no vence, no se hace nada
    }
}
