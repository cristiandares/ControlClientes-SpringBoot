package mx.com.gm.servicio;

import java.util.List;
import mx.com.gm.dao.PersonaDao;
import mx.com.gm.domain.Persona;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

//en esta clase se va a hacer la implementacion de los servicios para comunicarse con la database

//es necesario poner esta anotacion pa ra despues hacer la inyeccion de esta dependencia 
@Service
public class PersonaServiceImpl implements PersonaService {
    
    /*
        En esta clase se usa las transaciones de la base de datos, estas permiten 
        hacer commit si sale bien la consulta o rollback si hay algun problema
        dependiendo de la consulta se configura la transacion
    */
    
    //inyectamos una instancia de PersonaDao
    @Autowired
    private PersonaDao personaDao;

    //este metodo retorna todos los elementos
    @Override
    //indicamos que la transaction es de solo lectura ya que solo lee info de database
    @Transactional(readOnly = true) 
    public List<Persona> listarPersonas() {
        return (List<Persona>) personaDao.findAll();
    }

    //este metodo guarda un elemento en la database
    @Override
    //para este caso es de solo transactional porque este metodo va a hacer 
    //cambios en la base de datos y es necesario para hacer el commit o rollback
    @Transactional
    public void guardar(Persona persona) {
        personaDao.save(persona);
    }

    //este metodo elimina un elemento de la base de datos
    @Override
    //para este caso es de solo transactional porque este metodo va a hacer 
    //cambios en la base de datos y es necesario para hacer el commit o rollback
    @Transactional
    public void eliminar(Persona persona) {
        personaDao.delete(persona);
    }

    //este metodo busca una sola persona por medio de su id
    //si el metodo falla entonces retorna el valor null definido en .orElse(null)
    @Override
    //indicamos que la transaction es de solo lectura ya que solo lee info de database
    @Transactional(readOnly = true)
    public Persona encontrarPersona(Persona persona) {
        return personaDao.findById(persona.getIdPersona()).orElse(null);
    }
    
}
