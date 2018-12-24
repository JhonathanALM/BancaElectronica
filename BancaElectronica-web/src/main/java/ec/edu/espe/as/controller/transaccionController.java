/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.as.controller;

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
@Named(value = "transaccionController")
@SessionScoped
public class transaccionController implements Serializable {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/192.168.0.196_9090/Modulo-Cuentas-Pll-web/TransferenciaWs.wsdl")
    private ec.edu.espe.arquitectura.soap.ws.TransferenciaWs_Service service;

    public transaccionController() {
    }
     private  Transaccion transaccionExt ;
    
    @PostConstruct
    public void init() {
        transaccionExt = new Transaccion();
    }
    
    public void enviar(){
        
        
        try { 
            ec.edu.espe.arquitectura.soap.ws.TransferenciaWs port = service.getTransferenciaWsPort();
            double monto = 0.0d;
            // TODO process result here
            System.out.println("s:"+this.transaccionExt.getCuentaOrigen());
            System.out.println("o:"+this.transaccionExt.getCuentaDestino());
            System.out.println("d:"+this.transaccionExt.getMonto());
            java.lang.String result = port.transferencia(this.transaccionExt.getCuentaOrigen(), this.transaccionExt.getCuentaDestino(), this.transaccionExt.getMonto());
            System.out.println("Result = "+result);
        } catch (Exception ex) {
             System.out.println("algo salio mal :[ ");
        }
     


    }

    public ec.edu.espe.arquitectura.soap.ws.TransferenciaWs_Service getService() {
        return service;
    }

    public void setService(ec.edu.espe.arquitectura.soap.ws.TransferenciaWs_Service service) {
        this.service = service;
    }

    public Transaccion getTransaccionExt() {
        return transaccionExt;
    }

    public void setTransaccionExt(Transaccion transaccionExt) {
        this.transaccionExt = transaccionExt;
    }
    
}
