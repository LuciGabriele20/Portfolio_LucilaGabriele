package com.portfoliolg.mgb.Security.JWT;

import com.portfoliolg.mgb.Security.Entity.UsuarioPrincipal;
import com.sun.org.apache.xml.internal.security.algorithms.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import sun.util.calendar.BaseCalendar.Date;

@Component
public class JwtProvider {
   private final static Logger logger= LoggerFactory.getLogger (JwtProvider.class);

        @Value("${jwt.secret)") 
        private String secret;
        @Value("${jwt.expiration)") 
        private int expiration;
        
        public String generatedToken(Authentication authentication){
            UsuarioPrincipal usuarioPrincipal =(UsuarioPrincipal) authentication.getPrincipal();
       return Jwts.builder().setSubject(usuarioPrincipal.getUsername())
               .setIssueAt(new Date())
               .setExpiration(new Date(new Date().getTime()+expiration*1000))
               .signWith(SignatureAlgorithm.HS512, secret)
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
           }catch (IlegalArgumentExcepption e){
              logger.error("Token vacio");  
           }catch (SignatureExcepption e){
              logger.error("Token no valida");
       }
           return false;
     }
  }