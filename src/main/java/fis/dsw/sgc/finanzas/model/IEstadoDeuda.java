package fis.dsw.sgc.finanzas.model;

public interface IEstadoDeuda {
    void procesarPago(Deuda deuda);
    void aplicarMora(Deuda deuda);
    void anular(Deuda deuda);

    String getNombreEstadoBD(); // Devuelve: "EN_MORA", "ANULADA"
    String getNombreEstadoUI(); // Devuelve: "En Mora", "Eliminada"
}
