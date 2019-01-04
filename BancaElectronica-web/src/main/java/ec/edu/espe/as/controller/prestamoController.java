/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.as.controller;

import ec.edu.espe.as.controller.msg.PrestamoRQ;
import javax.inject.Named;
//import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import ec.edu.espe.as.controller.msg.UsuarioRQ;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.MediaType;
import javax.faces.view.ViewScoped;

/**
 *
 * @author jhona
 */
@Named(value = "prestamoController")
@ViewScoped
public class prestamoController implements Serializable {

    private List<PrestamoRQ> items;
    //private final String urlPrestamos = "http://40.121.87.240:8086/ServicioPrestamo/api/prestamo/";
    private final String urlPrestamos = "http://13.82.133.134:8084/Prestamo-web/api/verPrestamo/";
    private PrestamoRQ prq;

    public prestamoController() {
    }

    @PostConstruct
    public void init() {
        obtenerPrestamos();
    }

    public void obtenerPrestamos() {
        Client client = Client.create();
        WebResource resource = client.resource(urlPrestamos);
        UsuarioRQ ar = (UsuarioRQ) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        items= new ArrayList<>();
       // prq = resource.path("1004456891")
       // prq = resource.path(ar.getIdentificacion())
        prq = resource.path("1")
                .accept(MediaType.APPLICATION_JSON)
                .get(PrestamoRQ.class);
        items.add(prq);
        System.out.println(prq.getSaldo());
        FacesMessage msg = new FacesMessage("Lista de Prestamos Actializada",items.size()+" prestamos");          
        FacesContext.getCurrentInstance().addMessage(null, msg);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("prestamo", prq);
    }

    public List<PrestamoRQ> getItems() {
        if (items == null) {
            obtenerPrestamos();
        }
        return items;
    }

    public void setItems(List<PrestamoRQ> items) {
        this.items = items;
    }

}
