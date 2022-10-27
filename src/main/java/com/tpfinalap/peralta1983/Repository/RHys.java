
package com.tpfinalap.peralta1983.Repository;

import com.tpfinalap.peralta1983.Entity.Hys;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RHys extends JpaRepository<Hys, Integer> {
    
    Optional<Hys> findByNombre(String nombre);
    public boolean existsByNombre(String nombre);
}
