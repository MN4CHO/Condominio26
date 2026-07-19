package fis.dsw.sgc.administracion.model;

import java.util.Date;
import java.util.UUID;

public class Perfil {
    private UUID idPerfil;
    private String telefono;
    private String direccion;
    private String fotoPerfil;
    private Date fechaActualizacion;

    public UUID getIdPerfil() { return idPerfil; }
    public void setIdPerfil(UUID idPerfil) { this.idPerfil = idPerfil; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getFotoPerfil() { return fotoPerfil; }
    public void setFotoPerfil(String fotoPerfil) { this.fotoPerfil = fotoPerfil; }

    public Date getFechaActualizacion() { return fechaActualizacion; }
    public void setFechaActualizacion(Date fechaActualizacion) { this.fechaActualizacion = fechaActualizacion; }
}