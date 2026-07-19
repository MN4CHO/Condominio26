package fis.dsw.sgc.administracion.controller;

import fis.dsw.sgc.administracion.model.Cuenta;
import fis.dsw.sgc.administracion.model.EstadoCuenta;
import fis.dsw.sgc.administracion.model.NombreRol;
import fis.dsw.sgc.administracion.model.Rol;
import fis.dsw.sgc.administracion.model.Usuario;

import java.util.ArrayList;
import java.util.List;

/**
 * Datos de ejemplo compartidos entre las pantallas del módulo GRB
 */
final class DatosDemoGRB {

    private static final List<Usuario> USUARIOS = crearUsuariosDemo();

    private DatosDemoGRB() {
    }

    static List<Usuario> listarUsuarios() {
        return USUARIOS;
    }

    static Usuario buscarPorCorreo(String correo) {
        if (correo == null) return null;
        return USUARIOS.stream()
                .filter(u -> u.getCorreo().equalsIgnoreCase(correo.trim()))
                .findFirst()
                .orElse(null);
    }

    private static List<Usuario> crearUsuariosDemo() {
        List<Usuario> usuarios = new ArrayList<>();

        usuarios.add(crearUsuario("Andrea", "Administradora", "admin@condominio.local",
                EstadoCuenta.ACTIVA, NombreRol.ADMINISTRADOR));
        usuarios.add(crearUsuario("Carlos", "Residente", "carlos.residente@condominio.local",
                EstadoCuenta.ACTIVA, NombreRol.RESIDENTE));
        usuarios.add(crearUsuario("María", "Propietaria", "maria.propietaria@condominio.local",
                EstadoCuenta.ACTIVA, NombreRol.RESIDENTE, NombreRol.PROPIETARIO));
        usuarios.add(crearUsuario("Luis", "Guardia", "luis.guardia@condominio.local",
                EstadoCuenta.ACTIVA, NombreRol.PERSONAL_SEGURIDAD));
        usuarios.add(crearUsuario("Patricia", "Presidenta", "patricia.presidenta@condominio.local",
                EstadoCuenta.ACTIVA, NombreRol.PRESIDENTE, NombreRol.PROPIETARIO));
        usuarios.add(crearUsuario("Jorge", "Residente", "jorge.residente@condominio.local",
                EstadoCuenta.ACTIVA, NombreRol.RESIDENTE));
        usuarios.add(crearUsuario("Sofía", "Residente", "sofia.residente@condominio.local",
                EstadoCuenta.ACTIVA, NombreRol.RESIDENTE));

        return usuarios;
    }

    private static Usuario crearUsuario(String nombre, String apellido, String correo,
                                        EstadoCuenta estado, NombreRol... roles) {
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setCorreo(correo);

        Cuenta cuenta = new Cuenta();
        cuenta.setEstado(estado);
        for (NombreRol nombreRol : roles) {
            Rol rol = new Rol();
            rol.setNombre(nombreRol);
            cuenta.getRoles().add(rol);
        }
        usuario.setCuenta(cuenta);

        return usuario;
    }
}