package ec.edu.espe.as.controller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import ec.edu.espe.as.controller.msg.LoginRQ;
import ec.edu.espe.as.controller.msg.UsuarioRQ;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.ws.rs.core.MediaType;

/**
 * @author jhona
 */
@Named
@SessionScoped
public class IndexController implements Serializable {

    private LoginRQ loginRQ;
    private UsuarioRQ usuarioRQ;
    //private final String urlSeg="http://40.121.87.240:8086/Banca-web/api/usuario";
    private final String urlSeg = "http://137.135.107.221:8080/SegNotP2v1-web/api/validarLogin";
    //private final String urlKyc="http://40.121.87.240:8086/ServicioPersona/api/persona/";
    private final String urlKyc = "http://kyc.strangled.net:8080/KYC-mongo-rest-web/api/cliente/cedula/";
    private String c;

    @PostConstruct
    public void init() {
        loginRQ = new LoginRQ();
    }

    public String iniciarSesion() {
        String redireccion = "";
        try {
            UsuarioRQ lg = new UsuarioRQ();
            URL url = new URL(urlSeg);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            String in = "{\"clave\":\"" + this.loginRQ.getClave() + "\",\"usuario\":\"" + this.loginRQ.getUsuario() + "\",\"rol\":\"UsuarioBancaElectronica\"}";
            //String in = "{\"clave\":\"" + this.loginRQ.getClave() + "\",\"usuario\":\"" + this.loginRQ.getUsuario() + "\"}";
            OutputStream os = conn.getOutputStream();
            os.write(in.getBytes());
            os.flush();
            System.out.println("input: " + in);
            System.out.println("Mensaje de respuesta: " + conn.getResponseCode());
            String ss = conn.getResponseCode() + "";
            StringBuilder crunchifyBuilder = new StringBuilder();
            if (!ss.equals("401")) {
                BufferedReader inp = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line = null;
                while ((line = inp.readLine()) != null) {
                    crunchifyBuilder.append(line);
                }

                System.out.println("Data Received: " + crunchifyBuilder.toString());
                System.out.println("substring is5= " + crunchifyBuilder.substring(7, 17));
            }

            if (conn.getResponseCode() == 202) {
                c = crunchifyBuilder.substring(7, 17);
                redireccion = "/main?faces-redirect=true";
                lg = this.obtenerUsuario();
                Client client = Client.create();
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

    public void nada() {
        System.out.println("No hice nada");
    }

    public UsuarioRQ obtenerUsuario() throws MalformedURLException, IOException {
        Client client = Client.create();
        WebResource resource = client.resource(urlKyc);
        // Get response as String
        UsuarioRQ us = resource.path(c)
                .accept(MediaType.APPLICATION_JSON)
                .get(UsuarioRQ.class);
        System.out.println("usuario:" + us.toString());
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
        return as.getNombres() + " " + as.getApellidos();
    }

}
