package mx.com.gm.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

//creamos la relacion o el modelo de la tabla rol teniendo en cuenta que esta tiene
//una llave foranea

@Entity
@Data
@Table(name="rol")
public class Rol implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    //indicamos el campo de la llave primaria e indicamos el modo de procesar
    //o de generar esta llave - ya que esta es autoincrementable
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idRol;
    
    //agregamos la validacion del campo para que no se pueda vacio
    @NotEmpty
    private String nombre;
    
}
