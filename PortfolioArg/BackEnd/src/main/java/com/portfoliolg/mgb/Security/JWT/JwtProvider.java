package com.portfoliolg.mgb.Security.JWT;

import com.portfoliolg.mgb.Security.Entity.UsuarioPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.security.Key;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtProvider {
   private final static Logger logger= LoggerFactory.getLogger (JwtProvider.class);

        @Value("${jwt.secret)") 
        private String secret;
        @Value("${jwt.expiration)") 
        private int expiration;
        
        public String generatedToken(Authentication authentication){
            UsuarioPrincipal usuarioPrincipal =(UsuarioPrincipal) authentication.getPrincipal();
               // Generar una clave secreta aleatoria para HS256
            Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
            return Jwts.builder().setSubject(usuarioPrincipal.getUsername())
               .setIssuedAt(new Date())
               .setExpiration(new Date(new Date().getTime()+expiration*1000))
               .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
        }
        
        public String getNombreUsuarioFromToken(String token){
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
        }
       public boolean validateToken(String token){
           try{
               Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
               return true;
           }catch (MalformedJwtException e){
               logger.error("Token mal formado");
           }catch(UnsupportedJwtException e ){
               logger.error("Token mal formado");
           }catch(ExpiredException e ){
               logger.error("Token mal formado");  
           }catch (IlegalArgumentException e){
              logger.error("Token vacio");  
           }catch (SignatureExcepption e){
              logger.error("Token no valida");
       }
           return false;
     }
  }