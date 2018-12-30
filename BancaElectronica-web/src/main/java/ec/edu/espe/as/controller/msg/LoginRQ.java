package ec.edu.espe.as.controller.msg;

/**
 * @author jhona
 */
public class LoginRQ {

    private String clave;
    private String usuario;
    private String ci;

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }
    
}
