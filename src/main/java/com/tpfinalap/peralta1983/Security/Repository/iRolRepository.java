
package com.tpfinalap.peralta1983.Security.Repository;

import com.tpfinalap.peralta1983.Security.Entity.Rol;
import com.tpfinalap.peralta1983.Security.Enumns.RolNombre;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iRolRepository extends JpaRepository<Rol, Integer> {
    
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
