/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.as.controller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import ec.edu.espe.as.controller.msg.HTransferenciaRQ;
import javax.inject.Named;
//import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author jhona
 */
@Named(value = "hTransferenciaController")
@ViewScoped
public class hTransferenciaController implements Serializable {

    /**
     * Creates a new instance of hTransferenciaController
     */
    private List<HTransferenciaRQ> items = null;
    private HTransferenciaRQ ht =  new HTransferenciaRQ();
    private String pa;
    private Date desde;
    private Date hasta;

    public hTransferenciaController() {
    }

    public void obtenerHTransferencias() {
        System.out.println("Estoy aqui10.0");
        Client client = Client.create();
        DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        DateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println("Deste: " + formatoFecha.format(this.desde) + " hasta: " + formatoFecha.format(this.hasta) + " a la cuenta: " + this.pa);
        WebResource resource = client.resource("http://40.87.45.204:9090/Modulo-Cuentas-Pll-web/api/transaccion/");
        //System.out.println(desde+"&"+hasta+"&"+this.cuenta);
        items = resource.path(formatoFecha.format(this.desde) + "&" + formatoFecha.format(this.hasta) + "&"+this.pa)
                .accept(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<HTransferenciaRQ>>() {
                });
        FacesMessage msg = new FacesMessage("Prestamos", items.size()+" registros\n");  
        FacesContext.getCurrentInstance().addMessage(null, msg);
        System.out.println(items.size());
    }

    public List<HTransferenciaRQ> getItems() {
        return items;
    }

    public void setItems(List<HTransferenciaRQ> items) {
        this.items = items;
    }

    public HTransferenciaRQ getHt() {
        return ht;
    }

    public void setHt(HTransferenciaRQ ht) {
        this.ht = ht;
    }

    public Date getDesde() {
        return desde;
    }

    public void setDesde(Date desde) {
        this.desde = desde;
    }

    public Date getHasta() {
        return hasta;
    }

    public void setHasta(Date hasta) {
        this.hasta = hasta;
    }


    public String getPa() {
        return pa;
    }

    public void setPa(String pa) {
        this.pa = pa;
    }

}