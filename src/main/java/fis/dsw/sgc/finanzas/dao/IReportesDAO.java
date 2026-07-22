package fis.dsw.sgc.finanzas.dao;

import fis.dsw.sgc.finanzas.dto.DetalleGastoDTO;
import fis.dsw.sgc.finanzas.dto.DetallePagoDTO;
import fis.dsw.sgc.finanzas.dto.ReporteRendicionDTO;
import java.time.LocalDate;
import java.util.List;

public interface IReportesDAO {
    // Consultas para Pagos
    List<DetallePagoDTO> buscarPagosPorRangoFechas(LocalDate inicio, LocalDate fin);
    List<DetallePagoDTO> buscarPagosPorRangoFechasYUsuario(LocalDate inicio, LocalDate fin, int idUsuario);

    // Consulta para Gastos (¡Movida aquí para respetar CQRS!)
    List<DetalleGastoDTO> buscarGastosPorRangoFechas(LocalDate inicio, LocalDate fin);

    // Comandos y Consultas para el Reporte de Rendición
    void guardarReporteRendicion(ReporteRendicionDTO dto);
    ReporteRendicionDTO buscarReporteRendicion(LocalDate inicio, LocalDate fin);
    boolean existeReporteRendicion(LocalDate inicio, LocalDate fin);
}