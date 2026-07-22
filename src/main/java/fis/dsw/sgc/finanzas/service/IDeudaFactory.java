package fis.dsw.sgc.finanzas.service;

import fis.dsw.sgc.finanzas.model.Deuda;
import java.util.Date;

public interface IDeudaFactory {

    // Método original (perfecto para MULTAS y RESERVAS)
    Deuda crearDeuda(String motivo, int idResidente, double valor, Date fecha);

    // NUEVO MÉTODO: Exclusivo para ALICUOTAS
    Deuda crearDeudaAlicuota(int idResidente, double valorBase, Date fecha, double tamanoDepto, double tamanoCondominio);
}