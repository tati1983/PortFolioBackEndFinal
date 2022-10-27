
package com.tpfinalap.peralta1983.Service;

import com.tpfinalap.peralta1983.Entity.Hys;
import com.tpfinalap.peralta1983.Repository.RHys;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SHys {
    
    @Autowired
    RHys rHys;
    
    public List<Hys> list(){
    
        return rHys.findAll();
    }
    
    public Optional<Hys> getOne(int id){
    
        return rHys.findById(id);
    }
    
    public Optional<Hys> getByNombre(String nombre){
    
        return rHys.findByNombre(nombre);
    }
    
    public void save(Hys hys){
    
        rHys.save(hys);
    }
    
    public void delete(int id){
    
        rHys.deleteById(id);
    }
    
    public boolean existsById(int id){
    
        return rHys.existsById(id);
    }
    
    public boolean existsByNombre(String nombre){
    
        return rHys.existsByNombre(nombre);
    }
}
