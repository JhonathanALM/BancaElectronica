/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.as.controller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import ec.edu.espe.as.controller.msg.DetallePrestamoRQ;
import ec.edu.espe.as.controller.msg.UsuarioRQ;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author jorge
 */
@Named(value = "detallePrestamoController")
@ViewScoped
public class detallePrestamoController implements Serializable{
    
    private List<DetallePrestamoRQ> items = null;
    private DetallePrestamoRQ dprq = new DetallePrestamoRQ();
    private final String urlDetallePrestamo="http://40.121.87.240:8086/ServicioDetallePrestamo/api/detalleprestamo";

    public detallePrestamoController() {
    }
    @PostConstruct
    public void init() {
        obtenerDetallePrestamos();
    }
    
    public void obtenerDetallePrestamos(){
        UsuarioRQ ar = (UsuarioRQ) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");        
        Client client = Client.create();    
        System.out.println("");
        WebResource resource = client.resource(urlDetallePrestamo);
        System.out.println("HASTA AQUI30");
        items = resource.path("")
                .accept(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<DetallePrestamoRQ>>() {
                });
        System.out.println("ENTRAMOS 80");
        System.out.println("tiene "+items.size()+" detalle de prestamos");
    }
    
    public List<DetallePrestamoRQ> getItems() {
         if (items == null) {
           obtenerDetallePrestamos();
        }
        return items;
    }

    public void setItems(List<DetallePrestamoRQ> items) {
        this.items = items;
    }

    public DetallePrestamoRQ getDprq() {
        return dprq;
    }

    public void setDprq(DetallePrestamoRQ dprq) {
        this.dprq = dprq;
    }
    
    
    
}
