package com.cursoproyecto.proyecto.dao;

import com.cursoproyecto.proyecto.models.Usuario;
import java.util.List;

public interface UsuarioDao {

    List<Usuario> getUsuarios();

    void eliminar(Long id);

    void registrar(Usuario usuario);


    Usuario obtenerUsuarioPorCredenciales (Usuario usuario);
}
