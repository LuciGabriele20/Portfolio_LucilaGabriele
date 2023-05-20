package com.portfoliolg.mgb.Security.Service;

import com.portfoliolg.mgb.Security.Entity.Usuario;
import com.portfoliolg.mgb.Security.Entity.UsuarioPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsImp implements UserDetailsService {
    @Autowired
    UsuarioService usuarioService;
    
    @Override
    public UserDetails loadUserByUsername(String nombreUsuario){
     Usuario usuario= usuarioService.getByNombreUsuario(nombreUsuario).get();
       return UsuarioPrincipal.build(usuario);
    }
}