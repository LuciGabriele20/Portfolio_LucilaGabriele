package com.portfoliolg.mgb.Security.Service;

import com.portfoliolg.mgb.Security.Entity.Rol;
import com.portfoliolg.mgb.Security.Enums.RolNombre;
import com.portfoliolg.mgb.Security.Repository.iRolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@Transactional
public class RolService {
    @Autowired
    iRolRepository irolRepository;
    
    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
        return irolRepository.findByRolNombre(rolNombre);
    }
    public void save(Rol rol){
        irolRepository.save(rol);
    }
}
