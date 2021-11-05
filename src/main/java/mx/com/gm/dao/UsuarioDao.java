package mx.com.gm.dao;

import mx.com.gm.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

//extendemos de JpaRepository para usar seguridad

public interface UsuarioDao extends JpaRepository<Usuario, Long>{
    
    //es necesario definir el metodo findByUsername para trabajar con la seguridad en spring
    //le pasamos por parametro el username y lo que hace spring en automatico es recuperar
    //un usuario de la base de datos por medio del parametro username
    Usuario findByUsername(String username);
}
