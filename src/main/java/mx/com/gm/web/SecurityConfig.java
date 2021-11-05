package mx.com.gm.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
    //inyectamos la configuracion de usuarios que ya hemos implementado
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder build) throws Exception {
        build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    
    //al concepto de asegurar las urls se le llama autorizacion
    //este metodo permite configurar los accesos
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/editar/**", "/agregar/**", "/eliminar/**") //indicamos los path que van a estar restringidos
                    .hasRole("ADMIN") //indicamos el rol que si va a tener acceso a los path anteriores indicados
                .antMatchers("/")
                    .hasAnyRole("USER","ADMIN") 
                .and() //indicamos ue vamos a dar mas parametros
                    .formLogin() //indicamos el formLogin que vamos a utilizar personalizado
                    .loginPage("/login") //iindicamos cual es la pagina de login que vamos a manejar
                .and()
                    //si no tiene acceso a alguna pagina entonces muestra el html de errores/403
                    .exceptionHandling().accessDeniedPage("/errores/403")
                ;
    }
}