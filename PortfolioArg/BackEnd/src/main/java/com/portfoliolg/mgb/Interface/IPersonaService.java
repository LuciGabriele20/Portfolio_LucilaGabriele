package com.portfoliolg.mgb.Interface;

import com.portfoliolg.mgb.Entity.Persona;
import java.util.List;

  public interface IPersonaService{
    //traer lista de personas//
    public List<Persona> getPersona();
    
    //Guardar un objeto de tipo Persona
    public void savePersona(Persona persona);
    
    //Eliminar un objeto de tipo Persona
    public void deletePersona(Long id);
    
    //Buscar una persona por ID
    public Persona findPersona(Long id);
}