
package com.tpfinalap.peralta1983.Controller;

import com.tpfinalap.peralta1983.Dto.DtoProyecto;
import com.tpfinalap.peralta1983.Entity.Proyecto;
import com.tpfinalap.peralta1983.Security.Controller.Mensaje;
import com.tpfinalap.peralta1983.Service.SProyecto;
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
@RequestMapping("/proyectos")
@CrossOrigin(origins = "https://frontportfoliofp.web.app")
public class CProyecto {
    
    @Autowired
    SProyecto sProyecto;
    
       @GetMapping("/lista")
    public ResponseEntity<List<Proyecto>> list(){
    
        List<Proyecto> list = sProyecto.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Proyecto> getById(@PathVariable("id") int id){
        if(!sProyecto.existsById(id)){
            return new ResponseEntity(new Mensaje("Ese id no existe"), HttpStatus.BAD_REQUEST);
        }
        
        Proyecto proyecto = sProyecto.getOne(id).get();
        return new ResponseEntity(proyecto, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
    
        if(!sProyecto.existsById(id))
            return new ResponseEntity(new Mensaje("No existe el id"), HttpStatus.NOT_FOUND);
        
        sProyecto.delete(id);
        return new ResponseEntity(new Mensaje("Proyecto eliminada"), HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoProyecto dtoProyecto){
    
        if(StringUtils.isBlank(dtoProyecto.getNombreP()) || StringUtils.isBlank(dtoProyecto.getDescripcionP()))
            return new ResponseEntity(new Mensaje("Los campos son obligatorios"), HttpStatus.BAD_REQUEST);
        
        if(sProyecto.existsByNombreP(dtoProyecto.getNombreP()))
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        
        Proyecto proyecto = new Proyecto(dtoProyecto.getNombreP(), dtoProyecto.getDescripcionP(), dtoProyecto.getImgP());

        Object proyectoCreado = sProyecto.save(proyecto);
        
        return new ResponseEntity(proyectoCreado, HttpStatus.OK);
                            
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoProyecto dtoProyecto){
    
        if(!sProyecto.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el id"), HttpStatus.NOT_FOUND);
        }
        
        if(sProyecto.existsByNombreP(dtoProyecto.getNombreP()) && sProyecto.getByNombreP(dtoProyecto.getNombreP()).get().getId() != id){
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        
        if(StringUtils.isBlank(dtoProyecto.getNombreP()) || StringUtils.isBlank(dtoProyecto.getDescripcionP())){
            return new ResponseEntity(new Mensaje("Los campos son obligatorios"), HttpStatus.BAD_REQUEST);
        }
        
        Proyecto proyecto = sProyecto.getOne(id).get();
        
        proyecto.setNombreP(dtoProyecto.getNombreP());
        proyecto.setDescripcionP(dtoProyecto.getDescripcionP());
        proyecto.setImgP(dtoProyecto.getImgP());
        
        sProyecto.save(proyecto);
        
        return new ResponseEntity(new Mensaje("Proyecto actualizada"), HttpStatus.OK);
    }
      
}
