/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.as.controller;

import com.banco.Externo_Service;
import com.banco.Transaccion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author jhona
 */
@Named(value = "transaccionExtController")
@SessionScoped
public class transaccionExtController implements Serializable {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/ExternoWS/externo.wsdl")
    private Externo_Service service;

    /**
     * Creates a new instance of transaccionExtController
     */
    
    private  Transaccion transaccionExt ;
    
    public transaccionExtController() {      

    }
     @PostConstruct
    public void init() {
        transaccionExt = new Transaccion();
    }
    
    public void enviar(){
         try { // Call Web Service Operation
            com.banco.Externo port = service.getExternoPort();            
            String result = port.transferir(this.transaccionExt);
            System.out.println("Result = "+result);
        } catch (Exception ex) {
             System.out.println("algo salio mal :[ ");
        }
        
    }

    public Transaccion getTransaccionExt() {
        return transaccionExt;
    }

    public void setTransaccionExt(Transaccion transaccionExt) {
        this.transaccionExt = transaccionExt;
    }
    
    
    
    
}
