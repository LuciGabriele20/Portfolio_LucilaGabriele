package com.portfoliolg.mgb.Security.JWT;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import com.portfoliolg.mgb.Security.Service.UserDetailsImp;



public class JwtTokenFilter extends OncePerRequestFilter{
   private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);
   
   @Autowired
   JwtProvider jwtProvider;
   @Autowired
   UserDetailsImp userDetailsServiceImpl;
   
   @Override
   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain){
       try{
       String token = getToken(request);
       if(token != null && jwtProvider.validateToken(token)){
       String nombreUsuario = jwtProvider.getNombreUsuarioFromToken(token);
       UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(nombreUsuario);
       UsernamePasswordAuthenticationToken auth= new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
       SecurityContextHolder.getContext().setAuthentication(auth);
           }
   }catch (Exception e){
        logger.error("Falló metodo doFilterInternal");        
      }
   }
   private String getToken(HttpServletRequest request){
       String header = request.getHeader("Authorization");
       if(header != null && header.startsWith("Bearer"))
           return header.replace("Bearer", "");
          return null;
   }
}
