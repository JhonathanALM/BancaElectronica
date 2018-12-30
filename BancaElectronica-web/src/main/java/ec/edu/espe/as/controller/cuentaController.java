/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.as.controller;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import ec.edu.espe.as.controller.msg.CuentasRQ;
import ec.edu.espe.as.controller.msg.UsuarioRQ;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author jhona
 */
@Named(value = "cuentaController")
@SessionScoped
public class cuentaController implements Serializable {

    private List<CuentasRQ> items = null;
    private CuentasRQ selected= new CuentasRQ();
    private final String urlCuentas="http://40.121.87.240:8086/ServicioCuenta/api/cuenta/";
    public cuentaController() {
    }

    public List<CuentasRQ> obtenerCuentas(String cedula) {
        Client client = Client.create();        
        WebResource resource = client.resource(urlCuentas);
        List<CuentasRQ> cuentas = resource.path("")
                .accept(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<CuentasRQ>>() {
                });
        System.out.println(cuentas.size());
        return cuentas;
    }

    public List<CuentasRQ> getItems() {
        UsuarioRQ ar = (UsuarioRQ) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");        
        System.out.println("Estoy llenando las cuentas para" + ar.getCi());
          if (items == null) {
            items = obtenerCuentas(ar.getCi());
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
