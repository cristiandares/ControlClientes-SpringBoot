package mx.com.gm.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

//creamos la relacion o el modelo de la tabla Usuario

@Entity
@Data
@Table(name="usuario")
public class Usuario implements Serializable{
    
    
    private static final long serialVersionUID = 1L;
    
    //indicamos el campo de la llave primaria e indicamos el modo de procesar
    //o de generar esta llave - ya que esta es autoincrementable
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUsuario;
    
    //agregamos la validacion del campo para que no se pueda vacio
    @NotEmpty
    private String username;
    
    //agregamos la validacion del campo para que no se pueda vacio
    @NotEmpty
    private String password;
    
    
    //indicamos la columna que va a manejar la relacion de las tablas de rol y de Usuario
    //relacion uno a muchos
    @OneToMany
    @JoinColumn(name="id_usuario")
    private List<Rol> roles;
    
}
