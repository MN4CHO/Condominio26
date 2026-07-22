package fis.dsw.sgc.reservas.model;

import fis.dsw.sgc.reservas.service.IServicioReservas;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EstadoActiva implements IEstadoReserva {

    @Override
    public String getNombreEstado() {
        return "ACTIVA";
    }

    @Override
    public boolean puedeCancelar() {
        return true;
    }

    @Override
    public boolean puedeAgregarObservacion() {
        return false;
    }

    @Override
    public void evaluarVencimiento(Reserva reserva, IServicioReservas servicio) {
        if (reserva.getFechaReserva() == null || reserva.getHoraFin() == null) {
            return;
        }
        
        try {
            // Formatear la fecha y hora de fin de la reserva
            // Se asume formato de BD: "YYYY-MM-DD" y "HH:MM:SS" o "HH:MM"
            String fecha = reserva.getFechaReserva();
            if (fecha.length() > 10) {
                fecha = fecha.substring(0, 10);
            }
            
            String hora = reserva.getHoraFin();
            if (hora.length() == 5) {
                hora += ":00"; // Agregar segundos si no los tiene
            }
            
            String datetimeStr = fecha + "T" + hora;
            LocalDateTime finReserva = LocalDateTime.parse(datetimeStr);
            LocalDateTime ahora = LocalDateTime.now();
            
            if (ahora.isAfter(finReserva)) {
                // El tiempo ya pasó. Cambiar estado a FINALIZADA
                reserva.cambiarEstado(new EstadoFinalizada());
                
                // Actualizar en base de datos si se inyectó el servicio
                if (servicio != null) {
                    servicio.finalizarReservasVencidas();
                }
            }
        } catch (DateTimeParseException e) {
            // Ignorar errores de parseo, se asume que no está vencida
            System.err.println("Error parseando fecha/hora para vencimiento: " + e.getMessage());
        }
    }
}
