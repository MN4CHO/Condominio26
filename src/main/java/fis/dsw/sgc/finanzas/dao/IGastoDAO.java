package fis.dsw.sgc.finanzas.dao;

import fis.dsw.sgc.finanzas.model.Gasto;

public interface IGastoDAO {
    // Solo guarda, no sabe nada de reportes ni consultas masivas
    void guardar(Gasto gasto, int idCondominio, int idUsuarioRegistra);
}