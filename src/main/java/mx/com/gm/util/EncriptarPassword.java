package mx.com.gm.util;

//esta clase se va a encargar de encriptar las contraseñas

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncriptarPassword {
    public static void main(String[] args) {
        String password="123";
        System.out.println("password Normal = " + password);
        System.out.println("password Encriptada = " + encriptarPassword(password));
    }
    
    //este metodo va a encriptar las contraseñas
    public static String encriptarPassword(String password){
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            return encoder.encode(password);
        }
}
