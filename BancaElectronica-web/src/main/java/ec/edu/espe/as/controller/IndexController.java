package ec.edu.espe.as.controller;

import ec.edu.espe.as.controller.msg.LoginRQ;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 * @author jhona
 */
@Named
@ViewScoped
public class IndexController implements Serializable {

    private String usuario;
    private String clave;
    private String ci;

    public void init() {

    }

    public String iniciarSesion() {
         String redireccion = "";
        try {
            LoginRQ lg=new LoginRQ();
            URL url = new URL(
                    "https://9461f83f.ngrok.io/Banca-web/api/usuario/");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");
            String in = "{\"clave\":\"" + this.clave + "\",\"usuario\":\"" + this.usuario + "\"}";
            OutputStream os = conn.getOutputStream();
            os.write(in.getBytes());
            os.flush();
            System.out.println("input: " + in);
            System.out.println("Mensaje de respuesta: " + conn.getResponseCode());
           
            if (conn.getResponseCode() == 204) {
               /* throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());*/
              redireccion = "/main?faces-redirect=true";
              lg.setClave(this.usuario);
              FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", lg);                             
            } else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Credenciales Incorrectas"));
                System.out.println("Fallamos prro :( ");
            }
        } catch (Exception e) {
            System.out.println("Ex:"+e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "ERROR!"));
            
        }
        return redireccion;
    }

    public void cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

}
