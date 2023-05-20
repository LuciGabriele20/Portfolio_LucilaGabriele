package com.portfoliolg.mgb.Security.Dto;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;


public class JwDto {
     private String token;
     private String bearer="Bearer";
     private String nombreUsuario;
     private Collection<? extends GrantedAuthority> authorities;
     
     //constructor
     
     public JwDto(String token,String nombreUsuario, Collection<? extends GrantedAuthority> authorities){
         this.token=token;
         this.nombreUsuario= nombreUsuario;
         this.authorities= authorities;
     }
     
     //getter setter

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBearer() {
        return bearer;
    }

    public void setBearer(String bearer) {
        this.bearer = bearer;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
     
}
