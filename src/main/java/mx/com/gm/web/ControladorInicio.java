package mx.com.gm.web;

import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import mx.com.gm.domain.Persona;
import mx.com.gm.servicio.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

//esta anotacion se pone para que Spring reconozca a esta clase dentro del contenedor de Spring
@Controller
@Slf4j //con esta anotacion vamos a manejar el logger para desplegar info en la consola
public class ControladorInicio {
    
    @Autowired //inyecto la clase PersonaService
    private PersonaService personaService;
    
    //con esta anotacion estamos indicando el path que va a utilizar y a trabajar, haciendo lo que indique el metodo
    //la anotacion AuthenticationPrincipal es para traer el usuario que hizo login
    @GetMapping("/")
    //recibimos el Model. Antes en un servlet se recibia el HttpServletResponse y el HttpServletRequest, ahora
    //no es neceario, solo se recibe el Model
    public String inicio(Model model, @AuthenticationPrincipal User user){
        
        //recibimos la lista de todos los registros de la base de datos de tipo persona
        //haciendo uso de la interfaz y la clase que la implementa que definimos para este evento
        var personas = personaService.listarPersonas();
        
        //mostramos por consola el usuario que hizo el login
        log.info("usuario que hizo login: " + user);
        
        //este es el logger o la info que se va a mostrar por consola
        log.info("ejecutando el controlador Spring MVC");
        //enviamos un atributo a la vista por medio del metodo addAttribute enviando la clave y el valor
        //usando el model enviamos el objeto a la interfaz o pagina index
        //model.addAttribute("persona", persona);
        model.addAttribute("personas", personas); //compartimos el array o lista de personas
        var saldoTotal = 0.D;
        for(var p: personas){
            saldoTotal += p.getSaldo();
        }
        model.addAttribute("saldoTotal", saldoTotal);
        model.addAttribute("totalClientes", personas.size());
        return "index";
    }
    
    //este metodo returna una pagina donde se va a llenar los datos del objeto ya que lo vamos a crear
    //entonces spring envia un objeto de tipo Persona vació para que este sea llenado en el front o vista
    @GetMapping("/agregar")
    public String agregar(Persona persona){
        return "modificar";
    }
    
    //este elemento recibe los datos del formulario quye se envia desde la vista o el front
    //este lo recibe con los parametros procesados en la variable persona
    //recibe los valores del formulario es decir sin el idPersona
    @PostMapping("/guardar")
    public String guardar(@Valid Persona persona, Errors errores){
        //preguntamos si hemos recibido algun error
        if(errores.hasErrors()){
            return "modificar";
        }else{
            //utilizamos el objeto recibido y e insertamos la informacion en la database
            personaService.guardar(persona);
            //redireccionamos a la pagina de inicio
            return "redirect:/";  
        }
    }
    
    //este metodo recibe por parametro el idPersona, lo que hace es que lo asocia o crea un objeto
    //nuevo de tipo persona, automaticamente crea un obejeto de tipo persona solo con el idPersona
    //temnbien recibimos la variable modelo para poderla compartir en la siguiente peticion
    @GetMapping("/editar/{idPersona}")
    public String editar(Persona persona, Model model){
        //busco la persona por medio del id en la base de datos
        persona = personaService.encontrarPersona(persona);
        //envio la informacion completa de la persona a la vista modificar
        model.addAttribute("persona", persona);
        return "modificar";
    }
    
    @GetMapping("/eliminar/{idPersona}")
    public String eliminar(Persona persona, Model model){
        //elimino la persona por medio del id en la base de datos
        personaService.eliminar(persona);
        //redireccionamos a la pagina de inicio
        return "redirect:/";
    }

}
