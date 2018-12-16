package ec.edu.espe.as.controller.msg;

/**
 * @author jhona
 */
public class LoginRQ {
    
    private String cedula;
    private String clave;
    private String nombre;

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "LoginRQ{" + "cedula=" + cedula + ", clave=" + clave + ", nombre=" + nombre + '}';
    }
    
    
    

}
