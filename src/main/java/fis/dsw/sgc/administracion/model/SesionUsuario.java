package fis.dsw.sgc.administracion.model;

public class SesionUsuario {

    private static SesionUsuario instancia;

    private Usuario usuarioActual;

    private SesionUsuario() {
    }

    public static SesionUsuario obtenerInstancia() {
        if (instancia == null) {
            instancia = new SesionUsuario();
        }
        return instancia;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public void cerrarSesion() {
        this.usuarioActual = null;
    }
}
