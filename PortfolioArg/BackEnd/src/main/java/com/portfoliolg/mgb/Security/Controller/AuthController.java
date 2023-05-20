package com.portfoliolg.mgb.Security.Controller;

import com.portfoliolg.mgb.Security.Dto.LoginUsuario;
import com.portfoliolg.mgb.Security.Dto.NuevoUsuario;
import com.portfoliolg.mgb.Security.Entity.Rol;
import com.portfoliolg.mgb.Security.Entity.Usuario;
import com.portfoliolg.mgb.Security.Enums.RolNombre;
import com.portfoliolg.mgb.Security.JWT.JwtProvider;
import com.portfoliolg.mgb.Security.Service.RolService;
import com.portfoliolg.mgb.Security.Service.UsuarioService;
import java.util.HashSet;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@CrossOrigin
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    RolService reolService;
    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) 
            return new ResponseEntity(new Mensaje("Campo mal posicionado o email inválido"), HttpStatus.BAD_REQUEST);
      
        if (usuarioService.existsByNombreUsuario(nombreUsuario.getNombreUsuario())) 
            return new ResponseEntity(new Mensaje("Usuario Existente"), HttpStatus.BAD_REQUEST); 
  
        if(usuarioService.existsByEmail (nombreUsuario.getEmail()))               
            return new ResponseEntity(new Mensaje("Mail existente"),HttpStatus.BAD_REQUEST);
         
        Usuario usuario = new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(),
                nuevoUsuario.getEmail(), passwordEncoder.encode(nuevoUsuario.getPassword()));
          
         Set<Rol> roles = new HashSet<>();
          roles .add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
   
        if(nuevoUsuario.getRoles().contains("admin"))
           roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
             usuario.setRoles(roles); 
             usuarioService.save(usuario);

          return new ResponseEntity(new Mensaje("Usuario guardado"),HttpStatus.CREATED);
  }
     @PostMapping("/login")
       public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario,BindingResult bindingResult){
            if(bindingResult.hasErrors()) 
            return new ResponseEntity(new Mensaje("Campo mal ubicado"),HttpStatus.BAD_REQUEST);
            
       Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
         loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
    
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt= jwtProvider.generateToken(authentication);
        
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        JwtDto jwtDto= new JwtDto(jwt, userDetails.getUsername(),userDetails.getAuthorities());

        return new ResponseEntity(jwtDto,HttpStatus.OK);
   }
} //ctrl+shift+i para actualizar/activar la clase mensaje