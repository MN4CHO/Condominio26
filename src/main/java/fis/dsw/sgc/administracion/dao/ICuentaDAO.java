package fis.dsw.sgc.administracion.dao;

import fis.dsw.sgc.administracion.model.Cuenta;
import java.util.UUID;

public interface ICuentaDAO {
    void guardar(Cuenta cuenta);
    void actualizar(Cuenta cuenta);
    Cuenta buscarPorIdUsuario(UUID idUsuario);
    Cuenta autenticar(String correo, String contrasena);
}