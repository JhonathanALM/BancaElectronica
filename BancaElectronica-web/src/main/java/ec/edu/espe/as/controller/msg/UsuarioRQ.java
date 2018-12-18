package ec.edu.espe.as.controller.msg;

/**
 * @author jhona
 */
public class UsuarioRQ {
    
    private String ci;
    
    private String nombres;
    private String apellidos;
   
    private String correo;

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }



    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }


    
    
    

}
