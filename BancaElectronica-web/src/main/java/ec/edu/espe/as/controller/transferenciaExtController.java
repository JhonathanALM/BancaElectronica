/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.as.controller;

import com.banco.Externo_Service;
import com.banco.Transaccion;
import javax.inject.Named;
//import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.xml.ws.WebServiceRef;
import javax.faces.view.ViewScoped;

/**
 *
 * @author jhona
 */
@Named(value = "transferenciaExtController")
@ViewScoped
public class transferenciaExtController implements Serializable {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/ExternoWS/externo.wsdl")
    private Externo_Service service;

    /**
     * Creates a new instance of transferenciaExtController
     */
    private Transaccion transaccionExt;

    public transferenciaExtController() {

    }

    @PostConstruct
    public void init() {
        transaccionExt = new Transaccion();
        
    }

    public void enviar() {
        try { // Call Web Service Operation
            com.banco.Externo port = service.getExternoPort();
            String result = port.transferir(this.transaccionExt);
            System.out.println(result);
            if (result.equals("error")) {
                System.out.println("err");
                FacesMessage msg = new FacesMessage("Error", "Verifique los datos y vuelva a intentar");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                System.out.println("noEr");
                FacesMessage msg = new FacesMessage("Realizado", "Transaccion Realizada con exito");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                 reset();
            }
        } catch (Exception ex) {
            System.out.println("algo salio mal :[ " + ex);
        } 
    }

    public Transaccion getTransaccionExt() {
        return transaccionExt;
    }

    public void setTransaccionExt(Transaccion transaccionExt) {
        this.transaccionExt = transaccionExt;
    }
    public void reset(){
        System.out.println("Reset campos...");
        transaccionExt.setMonto(0);
        transaccionExt.setBanco(null);
        transaccionExt.setConcepto(null);
        transaccionExt.setCuentaDestino(null);
        transaccionExt.setCuentaOrigen(null);
        transaccionExt.setIdentificacion(null);        
    }

}
