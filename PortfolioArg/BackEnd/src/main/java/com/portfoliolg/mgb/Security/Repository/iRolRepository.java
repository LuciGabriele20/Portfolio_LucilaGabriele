package com.portfoliolg.mgb.Security.Repository;

import com.portfoliolg.mgb.Security.Entity.Rol;
import com.portfoliolg.mgb.Security.Enums.RolNombre;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

@Repository
    public interface iRolRepository extends JpaRepository<Rol, Integer>{
      Optional<Rol> findByRolNombre(RolNombre rolNombre);
    }

 
 