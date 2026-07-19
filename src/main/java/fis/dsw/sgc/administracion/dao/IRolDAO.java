package fis.dsw.sgc.administracion.dao;

import fis.dsw.sgc.administracion.model.Rol;
import java.util.List;
import java.util.UUID;

public interface IRolDAO {
    void guardar(Rol rol);
    void actualizar(Rol rol);
    List<Rol> listarTodos();
    void asignarRolAUsuario(UUID idUsuario, UUID idRol);
}