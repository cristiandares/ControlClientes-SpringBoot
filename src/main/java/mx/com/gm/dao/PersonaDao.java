package mx.com.gm.dao;

import mx.com.gm.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

//esta clase extiende de CrudRepository que tiene los metodos para hacer el crud hacia la base de datos 
//CrudRepository es de tipo generico y puede implementar con cualquier modelo, en este caso va a trabajar con Persona
//tambien debemos de especificar el tipo de dato que estemos utilizando para para la llave primaria de Persona
//para este caso es Long segun la variable idPersona de la clase o modelo Persona
public interface PersonaDao extends JpaRepository<Persona, Long> {
    
}
