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
    public cuentaController() {
    }

    public List<CuentasRQ> obtenerPrestamos(String cedula) {
        Client client = Client.create();
        System.out.println("jajaja http://192.168.0.196:9090/Modulo-Cuentas-Pll-web/api/cuenta/");
        WebResource resource = client.resource("http://192.168.0.196:9090/Modulo-Cuentas-Pll-web/api/cuenta/");
        //System.out.println("http://192.168.0.196:9090/Modulo-Cuentas-Pll-web/webresources/cuenta/");
        //WebResource resource = client.resource("http://192.168.0.196:9090/Modulo-Cuentas-Pll-web/webresources/cuenta/");
        List<CuentasRQ> cuentas = resource.path("1004456891")
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
            items = obtenerPrestamos(ar.getCi());
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
