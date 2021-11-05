package mx.com.gm.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.Data;


//la anotacion Data del proyecto de lombok lo que hace es que me mapea los getters and setters
//de la clase y los metodos equals, toString, hashCode etc, eso lo podemos ver en la ventada Navigator

//la anotacion Entity convertimos a esta clase en una clase de entidad para mapearse en la base de datos
//la anotacion de Table es para inidicar que es una tabla y no tenga problemas con algunos sistemas operativos
//al momento de mapearse esta tabla o hacer consultas, ya que el nombre de la clase empieza por P mayuscula y
//en la tabla de MySQL está en minuscula
@Data
@Entity
@Table(name="persona") //indico el nombre tal cual como esta la tabla en la base de datos
public class Persona implements Serializable{ //implementamos la interfaz serializable
    
    private static final Long serialVersionUID = 1L;
    
    @Id //indicamos que la variable que tiene esta anotacion es la llave primaria de la database
    @GeneratedValue(strategy = GenerationType.IDENTITY) //indicamos la forma de generar esta llave primaria
    private Long idPersona;
    
    /*
        vamos a manejar las validaciones de los datos en el formulario
        para validar el tipo de dato o el dato que ingresan en el front
    */
    
    //la anotacion NotEmpty indica que no puede estar vacio
    @NotEmpty
    private String nombre;
    
    @NotEmpty
    private String apellido;
    
    @NotEmpty
    @Email //indicamos que este camopo debe de tener la validacion de email
    private String email;
    
    //en el telefono no vamos a agregar validaciones
    private String telefono;
    
    @NotNull
    private double saldo;
}
