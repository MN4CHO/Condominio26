package fis.dsw.sgc.reservas.model;

import fis.dsw.sgc.reservas.service.IServicioReservas;

/**
 * Interfaz para implementar el patrón State en la Reserva.
 */
public interface IEstadoReserva {
    
    /** Retorna el nombre del estado para persistencia y presentación. */
    String getNombreEstado();
    
    /** Indica si en este estado es posible cancelar la reserva. */
    boolean puedeCancelar();
    
    /** Indica si en este estado es posible añadir observaciones (Auditoría). */
    boolean puedeAgregarObservacion();
    
    /**
     * Evalúa si el tiempo de la reserva ya pasó. 
     * Si expiró, debe transicionar a EstadoFinalizada y notificar al servicio.
     */
    void evaluarVencimiento(Reserva reserva, IServicioReservas servicio);
}
