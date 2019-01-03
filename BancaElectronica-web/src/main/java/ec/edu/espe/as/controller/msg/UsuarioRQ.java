package ec.edu.espe.as.controller.msg;

/**
 * @author jhona
 */
public class UsuarioRQ {
    
    private String codigo;
    
    private String nombres;
    private String apellidos;
   
    private String correoElectronico;
    private String identificacion;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    @Override
    public String toString() {
        return "UsuarioRQ{" + "codigo=" + codigo + ", nombres=" + nombres + ", apellidos=" + apellidos + ", correoElectronico=" + correoElectronico + ", identificacion=" + identificacion + '}';
    }
    

}