package mx.com.gm.servicio;

import java.util.*;
import lombok.extern.slf4j.Slf4j;
import mx.com.gm.dao.UsuarioDao;
import mx.com.gm.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//esta marcacion se debe de poner si o si para que spring security en jpa lo pueda usar
@Service("userDetailsService")
@Slf4j
public class UsuarioService implements UserDetailsService{
    
    //inyectamos una clase
    @Autowired
    private UsuarioDao usuarioDao;

    @Override
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //recuperamos un usuario filtrando por username
        Usuario usuario = usuarioDao.findByUsername(username);
        
        if(usuario == null){
            throw new UsernameNotFoundException(username);
        }
        
        //la clase GrantedAuthority es la que usa spring para manejar los roles
        //los roles quedan manejados por la clase GrantedAuthority
        var roles = new ArrayList<GrantedAuthority>();
        
        for(Rol rol : usuario.getRoles()){
            roles.add(new SimpleGrantedAuthority(rol.getNombre()));
        }
        
        //retornamos un objeto de User de spring
        return new User(usuario.getUsername(), usuario.getPassword(), roles);
    }
    
}
