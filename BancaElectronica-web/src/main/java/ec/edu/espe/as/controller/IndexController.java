package ec.edu.espe.as.controller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import ec.edu.espe.as.controller.msg.LoginRQ;
import ec.edu.espe.as.controller.msg.UsuarioRQ;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.ws.rs.core.MediaType;

/**
 * @author jhona
 */
@Named
@ViewScoped
public class IndexController implements Serializable {

    private LoginRQ loginRQ;
    private UsuarioRQ usuarioRQ;

    @PostConstruct
    public void init() {
        loginRQ = new LoginRQ();
    }

    public String iniciarSesion() {
        String redireccion = "";
        try {
            UsuarioRQ lg = new UsuarioRQ();
            URL url = new URL(
                    "http://localhost:8080/Banca-web/api/usuario/");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");
            String in = "{\"clave\":\"" + this.loginRQ.getClave() + "\",\"usuario\":\"" + this.loginRQ.getUsuario() + "\"}";
            OutputStream os = conn.getOutputStream();
            os.write(in.getBytes());
            os.flush();
            System.out.println("input: " + in);
            System.out.println("Mensaje de respuesta: " + conn.getResponseCode());

            if (conn.getResponseCode() == 204) {
                redireccion = "/main?faces-redirect=true";
                lg=this.obtenerUsuario();
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", lg);
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Credenciales Incorrectas"));
                System.out.println("Fallamos prro :( ");
            }
        } catch (Exception e) {
            System.out.println("Ex:" + e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "ERROR!"));

        }
        return redireccion;
    }

    public void cerrarSesion() {
        System.out.println("cerrando session....");
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }

    public UsuarioRQ obtenerUsuario() throws MalformedURLException, IOException {
        Client client = Client.create();
        WebResource resource = client.resource("http://localhost:8080/ServicioPersona/api/persona/");
        // Get response as String
        UsuarioRQ us = resource.path("1004456891")
                .accept(MediaType.APPLICATION_JSON)
                .get(UsuarioRQ.class);
        System.out.println("nombre:"+us.getNombres());
        System.out.println("apellido:"+us.getApellidos());
        return us;
    }

    public LoginRQ getLoginRQ() {
        return loginRQ;
    }

    public void setLoginRQ(LoginRQ loginRQ) {
        this.loginRQ = loginRQ;
    }

    public UsuarioRQ getUsuarioRQ() {
        return usuarioRQ;
    }

    public void setUsuarioRQ(UsuarioRQ usuarioRQ) {
        this.usuarioRQ = usuarioRQ;
    }
    
     public String getActual() {
        UsuarioRQ as = (UsuarioRQ) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        System.out.println("Bienvenido>> : " + as.getNombres()+as.getApellidos());
        return as.getNombres()+" "+as.getApellidos();
    }

}
