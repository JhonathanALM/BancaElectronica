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
import com.sun.jersey.api.client.WebResource;
import ec.edu.espe.as.controller.msg.UsuarioRQ;
import java.util.ArrayList;
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
     private final String urlPrestamos="http://40.121.87.240:8086/ServicioPrestamo/api/prestamo/";
    public prestamoController() {
    }
    public List<PrestamoRQ> obtenerPrestamos(String cedula) {
        Client client = Client.create();
        List<PrestamoRQ> r = new ArrayList<>();
        WebResource resource = client.resource(urlPrestamos);
        PrestamoRQ us = resource.path(cedula)
                .accept(MediaType.APPLICATION_JSON)
                .get(PrestamoRQ.class);
        r.add(us);
        System.out.println(us.getSaldo());
        return r;
    }

    public List<PrestamoRQ> getItems() {
        UsuarioRQ ar = (UsuarioRQ) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");        
        System.out.println("Estoy llenando los prestamos para" + ar.getCi());
          if (items == null) {
            items = obtenerPrestamos(ar.getCi());
        }
        return items;
    }

    public void setItems(List<PrestamoRQ> items) {
        this.items = items;
    }
               
}
