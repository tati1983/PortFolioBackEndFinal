package com.tpfinalap.peralta1983.Controller;

import com.tpfinalap.peralta1983.Dto.DtoHys;
import com.tpfinalap.peralta1983.Entity.Hys;
import com.tpfinalap.peralta1983.Security.Controller.Mensaje;
import com.tpfinalap.peralta1983.Service.SHys;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "https://frontportfoliofp.web.app")
@RequestMapping("/skill")
public class CHys {

    @Autowired
    SHys sHys;

    @GetMapping("/detail/{id}")
    public ResponseEntity<Hys> getById(@PathVariable("id") int id) {
        if (!sHys.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Hys hys = sHys.getOne(id).get();
        return new ResponseEntity(hys, HttpStatus.OK);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<Hys>> list() {

        List<Hys> list = sHys.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoHys dtoHys) {

        if (StringUtils.isBlank(dtoHys.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (sHys.existsByNombre(dtoHys.getNombre())) {
            return new ResponseEntity(new Mensaje("Esa Skill existe"), HttpStatus.BAD_REQUEST);
        }

        Hys hys = new Hys(dtoHys.getNombre(), dtoHys.getPorcentaje());
        sHys.save(hys);
        return new ResponseEntity(new Mensaje("Skill agregada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoHys dtoHys) {

        if (!sHys.existsById(id)) {
            return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.BAD_REQUEST);
        }
        if (sHys.existsByNombre(dtoHys.getNombre()) && sHys.getByNombre(dtoHys.getNombre()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Esa Skill ya existe"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(dtoHys.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        Hys hys = sHys.getOne(id).get();
        hys.setNombre(dtoHys.getNombre());
        hys.setPorcentaje(dtoHys.getPorcentaje());

        sHys.save(hys);
        return new ResponseEntity(new Mensaje("Skills actualizadas"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {

        if (!sHys.existsById(id)) {
            return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.BAD_REQUEST);
        }

        sHys.delete(id);

        return new ResponseEntity(new Mensaje("Skill eliminda"), HttpStatus.OK);
    }
}
