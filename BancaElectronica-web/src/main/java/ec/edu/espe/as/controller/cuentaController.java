/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.as.controller;
import javax.inject.Named;
//import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import ec.edu.espe.as.controller.msg.CuentasRQ;
import ec.edu.espe.as.controller.msg.UsuarioRQ;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author jhona
 */
@Named(value = "cuentaController")
@ViewScoped
public class cuentaController implements Serializable {

    private List<CuentasRQ> items = null;
    private CuentasRQ selected= new CuentasRQ();
    private final String urlCuentas="http://40.87.45.204:9090/Modulo-Cuentas-Pll-web/api/cuenta/";
    //private final String urlCuentas="http://40.121.87.240:8086/ServicioCuenta/api/cuenta/";
    public cuentaController() {
    }
    @PostConstruct
    public void init() {
        obtenerCuentas();
    }

    public void obtenerCuentas() {
        UsuarioRQ ar = (UsuarioRQ) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");        
         Client client = Client.create();    
        System.out.println("2233 ");
        WebResource resource = client.resource(urlCuentas);
        items = resource.path(ar.getIdentificacion())              
       // items = resource.path("")
                .accept(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<CuentasRQ>>() {
                });
        System.out.println("tiene "+items.size()+" cuentas");
        FacesMessage msg = new FacesMessage("Lista de Cuentas Actializada", items.size()+" cuentas\n");  
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public List<CuentasRQ> getItems() {       
          if (items == null) {
           obtenerCuentas();
        }
        return items;
    }

    public void setItems(List<CuentasRQ> items) {
        this.items = items;
    }

    public CuentasRQ getSelected() {
        return selected;
    }

    public void setSelected(CuentasRQ selected) {
        this.selected = selected;
    }
    
    
    

}
