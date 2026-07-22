package fis.dsw.sgc.reservas.model;

import fis.dsw.sgc.reservas.service.IServicioReservas;

public class EstadoCancelada implements IEstadoReserva {

    @Override
    public String getNombreEstado() {
        return "CANCELADA";
    }

    @Override
    public boolean puedeCancelar() {
        return false;
    }

    @Override
    public boolean puedeAgregarObservacion() {
        return false;
    }

    @Override
    public void evaluarVencimiento(Reserva reserva, IServicioReservas servicio) {
        // Una reserva cancelada no vence, no se hace nada
    }
}
