package fis.dsw.sgc.administracion.model;

import java.util.Date;
import java.util.UUID;

public class TokenRestablecimiento {
    private UUID idToken;
    private String codigo;
    private Date fechaExpiracion;
    private Integer intentos;

    public UUID getIdToken() { return idToken; }
    public void setIdToken(UUID idToken) { this.idToken = idToken; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public Date getFechaExpiracion() { return fechaExpiracion; }
    public void setFechaExpiracion(Date fechaExpiracion) { this.fechaExpiracion = fechaExpiracion; }

    public Integer getIntentos() { return intentos; }
    public void setIntentos(Integer intentos) { this.intentos = intentos; }

    public boolean estaExpirado() {
        return fechaExpiracion != null && fechaExpiracion.before(new Date());
    }

    public boolean esValido() {
        return !estaExpirado() && intentos != null && intentos < 5;
    }
}