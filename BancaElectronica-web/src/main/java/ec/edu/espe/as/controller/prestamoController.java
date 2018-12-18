/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.as.controller;

import ec.edu.espe.as.controller.msg.PrestamoRQ;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import ec.edu.espe.as.controller.msg.UsuarioRQ;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author jhona
 */
@Named(value = "prestamoController")
@SessionScoped
public class prestamoController implements Serializable {

     private List<PrestamoRQ> items = null;
    public prestamoController() {
    }

    public List<PrestamoRQ> obtenerPrestamos(String cedula) {
        Client client = Client.create();
        WebResource resource = client.resource("http://localhost:8080/ServicioPrestamo/api/prestamo/");

        List<PrestamoRQ> prestamos = resource.path("")
                .accept(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<PrestamoRQ>>() {
                });
        System.out.println(prestamos.size());
        return prestamos;
    }

    public List<PrestamoRQ> getItems() {
        UsuarioRQ ar = (UsuarioRQ) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");        
        System.out.println("Estoy llenando los clientes para" + ar.getCi());
          if (items == null) {
            items = obtenerPrestamos(ar.getCi());
        }
        return items;
    }

    public void setItems(List<PrestamoRQ> items) {
        this.items = items;
    }
    
    
    
    

}
