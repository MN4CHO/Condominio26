package fis.dsw.sgc.comunicacion.dao;

import fis.dsw.sgc.comunicacion.dto.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface IComunicacionDAO {

    long obtenerIdEmisorPredeterminado()
            throws SQLException;

    long guardarMensaje(
            EnviarComunicacionDTO dto
    ) throws SQLException;

    long guardarAnuncio(
            PublicarAnuncioDTO dto
    ) throws SQLException;

    List<MensajeResumenDTO> listarMensajesRecientes(
            int limite
    ) throws SQLException;

    List<AnuncioResumenDTO> listarAnunciosRecientes(
            int limite
    ) throws SQLException;

    /*
     * Consulta administrativa:
     * permite buscar notificaciones de todos los usuarios.
     */
    List<NotificacionDTO> buscarNotificaciones(
            String tipo,
            String estado,
            String criterio
    ) throws SQLException;

    /*
     * Consulta personal:
     * devuelve solamente las notificaciones del usuario autenticado.
     */
    List<NotificacionDTO> buscarNotificacionesPorUsuario(
            long idUsuario,
            String tipo,
            String estado,
            String criterio
    ) throws SQLException;

    /*
     * Operación administrativa.
     */
    void marcarNotificacionLeida(
            long idNotificacion
    ) throws SQLException;

    /*
     * Operación personal segura:
     * verifica que la notificación pertenezca al usuario.
     */
    void marcarNotificacionLeidaPorUsuario(
            long idNotificacion,
            long idUsuario
    ) throws SQLException;

    /*
     * Operación administrativa.
     */
    void eliminarNotificacion(
            long idNotificacion
    ) throws SQLException;

    /*
     * Operación personal segura:
     * verifica que la notificación pertenezca al usuario.
     */
    void eliminarNotificacionPorUsuario(
            long idNotificacion,
            long idUsuario
    ) throws SQLException;

    List<HistorialDTO> buscarHistorial(
            LocalDate desde,
            LocalDate hasta,
            String tipo,
            String estado,
            String criterio
    ) throws SQLException;

    List<ResumenReporteDTO> generarResumen(
            LocalDate inicio,
            LocalDate fin,
            String tipo
    ) throws SQLException;
}