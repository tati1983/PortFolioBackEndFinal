package com.tpfinalap.peralta1983.Service;

import com.tpfinalap.peralta1983.Entity.Proyecto;
import com.tpfinalap.peralta1983.Repository.RProyecto;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class SProyecto {

    @Autowired
    RProyecto rProyecto;

    public List<Proyecto> list() {

        return rProyecto.findAll();
    }

    public Optional<Proyecto> getOne(int id) {

        return rProyecto.findById(id);
    }

    public Optional<Proyecto> getByNombreP(String nombreP) {

        return rProyecto.findByNombreP(nombreP);
    }

    public Object save(Proyecto proyecto) {

         return rProyecto.save(proyecto);
       
    }

    public void delete(int id) {

        rProyecto.deleteById(id);
    }

    public boolean existsById(int id) {

        return rProyecto.existsById(id);
    }

    public boolean existsByNombreP(String nombreP) {

        return rProyecto.existsByNombreP(nombreP);
    }

}
