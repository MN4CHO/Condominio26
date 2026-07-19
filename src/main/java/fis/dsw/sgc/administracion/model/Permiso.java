package fis.dsw.sgc.administracion.model;

import java.util.UUID;

public class Permiso {
    private UUID idPermiso;
    private String nombre;
    private String recurso;

    public UUID getIdPermiso() { return idPermiso; }
    public void setIdPermiso(UUID idPermiso) { this.idPermiso = idPermiso; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getRecurso() { return recurso; }
    public void setRecurso(String recurso) { this.recurso = recurso; }
}