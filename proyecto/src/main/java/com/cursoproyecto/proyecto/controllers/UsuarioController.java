package com.cursoproyecto.proyecto.controllers;

import com.cursoproyecto.proyecto.dao.UsuarioDao;
import com.cursoproyecto.proyecto.models.Usuario;
import com.cursoproyecto.proyecto.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController

public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.GET)
    public Usuario getUsuario(@PathVariable Long id){
        Usuario usuario = new  Usuario();
        usuario.setId(id);
        usuario.setNombre("Jefree");
        usuario.setApellido("Rucal");
        usuario.setEmail("jefreealejandro@gmail.com");
        usuario.setTelefono("33060733");
        usuario.setPassword("Jefree1109Rucal");

        return usuario;

    }


    @RequestMapping(value = "api/usuarios" , method = RequestMethod.GET)
    public List<Usuario> getUsuarios(@RequestHeader(value = "Authorization") String token) {

        if(!validarToken(token)){
        return null;}



        return usuarioDao.getUsuarios();

    }

    private boolean validarToken(String token){

        String usuarioId = jwtUtil.getKey(token);
        return usuarioId != null;
        }



    @RequestMapping(value = "api/usuarios" , method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody Usuario usuario){
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, usuario.getPassword());
        usuario.setPassword(hash);
        usuarioDao.registrar(usuario);


    }
    @RequestMapping(value = "usuario789")
    public Usuario editar(){
        Usuario usuario = new  Usuario();
        usuario.setNombre("Jefree");
        usuario.setApellido("Rucal");
        usuario.setEmail("jefreealejandro@gmail.com");
        usuario.setTelefono("33060733");
        usuario.setPassword("Jefree1109Rucal");

        return usuario;

    }


    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)
    public void eliminar(@RequestHeader(value = "Authorization") String token, @PathVariable Long id) {
        if(!validarToken(token)){
        return;}
        usuarioDao.eliminar(id);


    }


}