package com.portfoliolg.mgb.Security.Controller;

public class Mensaje {
    private String mensaje;
    
    //constructor
    
    public Mensaje(){
   }
    
    public Mensaje (String mensaje){
       this.mensaje= mensaje;
    }
    
    //getter setter

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
