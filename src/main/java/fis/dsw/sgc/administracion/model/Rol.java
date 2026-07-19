package fis.dsw.sgc.administracion.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Rol {
    private UUID idRol;
    private NombreRol nombre;
    private String descripcion;

    private List<Permiso> permisos = new ArrayList<>();

    public UUID getIdRol() { return idRol; }
    public void setIdRol(UUID idRol) { this.idRol = idRol; }

    public NombreRol getNombre() { return nombre; }
    public void setNombre(NombreRol nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public List<Permiso> getPermisos() { return permisos; }
    public void setPermisos(List<Permiso> permisos) { this.permisos = permisos; }

    public List<Permiso> obtenerPermisos() {
        return permisos;
    }
}