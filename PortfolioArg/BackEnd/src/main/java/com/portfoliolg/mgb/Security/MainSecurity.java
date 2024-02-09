package com.portfoliolg.mgb.Security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.context.annotation.Configuration;

import com.portfoliolg.mgb.Security.JWT.JwtEntryPoint;
import com.portfoliolg.mgb.Security.JWT.JwtTokenFilter;
import com.portfoliolg.mgb.Security.Service.UserDetailsImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class MainSecurity {
     @Autowired
     UserDetailsImp userDetailsServicesImpl;
     @Autowired
     JwtEntryPoint jwtEntryPoint;
     
     @Bean
     public JwtTokenFilter jwtTokenFilter(){
         return new JwtTokenFilter();
     }
     
     @Bean
     public PasswordEncoder passwordEncoder(){
         return new BCryptPasswordEncoder();
     }
     
     @Override
     protected void configure(HttpSecurity http) throws Exception{
         http.cors().and().carf().disable().authorizeRequests().antMatchers("/auth/").permitAll()
                 .anyRequest().authenticated().and().exceptionHandling().authenticationEntryPoint(jwtEntryPoint
                 .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
         http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.claas);      
     }
     
      @Override
     public AuthenticationManager authenticationManager() throw Exception{
         return super.authenticationManager();
     }
     @Bean
      @Override
     protected AuthenticationManager authenticationManagerBean() throw Exception{
        return super.authenticationManagerBean() ;
     }
       @Override
     public void configure(AuthenticationManagerBuilder auth) throw Exception{
         auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
}
}