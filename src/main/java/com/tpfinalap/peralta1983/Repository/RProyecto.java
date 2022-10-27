package com.tpfinalap.peralta1983.Repository;

import com.tpfinalap.peralta1983.Entity.Proyecto;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RProyecto extends JpaRepository<Proyecto, Integer> {

    public Optional<Proyecto> findByNombreP(String nombre);
    public boolean existsByNombreP(String nombre);
}
