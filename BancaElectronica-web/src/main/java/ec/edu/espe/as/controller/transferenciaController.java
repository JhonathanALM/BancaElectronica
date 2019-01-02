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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author jhona
 */
@Named(value = "transferenciaController")
@SessionScoped
public class transferenciaController implements Serializable {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/192.168.0.196_9090/Modulo-Cuentas-Pll-web/TransferenciaWs.wsdl")
    private ec.edu.espe.arquitectura.soap.ws.TransferenciaWs_Service service;

    public transferenciaController() {
    }
    private Transaccion transaccionExt;

    @PostConstruct
    public void init() {
        transaccionExt = new Transaccion();
    }

    public void enviar() {

        try {
            ec.edu.espe.arquitectura.soap.ws.TransferenciaWs port = service.getTransferenciaWsPort();
            double monto = 0.0d;
            // TODO process result here
            System.out.println("s:" + this.transaccionExt.getCuentaOrigen());
            System.out.println("o:" + this.transaccionExt.getCuentaDestino());
            System.out.println("d:" + this.transaccionExt.getMonto());
            java.lang.String result = port.transferencia(this.transaccionExt.getCuentaOrigen(), this.transaccionExt.getCuentaDestino(), this.transaccionExt.getMonto());
            System.out.println("Result = " + result);
            String cabecera = "";
            String mensaje = "";
            switch (result) {
                case "201":
                    System.out.println("201");
                    cabecera = "Realizado";
                    mensaje = "Transaccion Realizada con exito";
                    reset();
                    break;
                case "400":
                    System.out.println("400");
                    cabecera = "Error";
                    mensaje = "Datos de cuentas incorrectos o sintaxis incorrecta";
                    break;
                case "409":
                    System.out.println("409");
                    cabecera = "Error";
                    mensaje = "Conflicto en la transferencia falta de fondos";
                    break;
                case "500":
                    System.out.println("500");
                    cabecera = "Error";
                    mensaje = "Error dentro del sistema";
                    break;
                default:
                    cabecera = "Informacion Mal enviada";
                    mensaje = "Error no especificado";
                    break;
            }
            FacesMessage msg = new FacesMessage(cabecera, mensaje);
            FacesContext.getCurrentInstance().addMessage(null, msg);
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

    public void reset() {
        System.out.println("Reset campos...");
        transaccionExt.setMonto(0);
        transaccionExt.setCuentaDestino(null);
        transaccionExt.setCuentaOrigen(null);
    }

}
