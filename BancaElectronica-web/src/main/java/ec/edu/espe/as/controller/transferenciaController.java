/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.as.controller;

import com.banco.Transaccion;
import ec.edu.espe.as.controller.msg.UsuarioRQ;
import java.io.OutputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/40.87.45.204_9090/Modulo-Cuentas-Pll-web/TransferenciaWs.wsdl")
    private ec.edu.espe.arquitectura.soap.ws.TransferenciaWs_Service service;
    private final String urlSeg = "http://137.135.107.221:8080/SegNotP2v1-web/api/notificacion";

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
                    EnviarNotificacion(this.transaccionExt.getCuentaOrigen(),this.transaccionExt.getMonto());
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
            System.out.println("algo salio mal :[ " + ex);
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

    public void EnviarNotificacion(String c, Double d) {
        System.out.println("Enviando notificacion...");
          UsuarioRQ as = (UsuarioRQ) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");

        try {
            URL url = new URL(urlSeg);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            String in = "{\n"
                    + "\"userId\":\""+as.getIdentificacion()+"\",\n"
                    + "\"numCuenta\":\""+c+"\",\n"
                    + "\"tipo\":\"Transaccion Interna\",\n"
                    + "\"monto\":\""+d+"\"\n"
                    + "}";
            OutputStream os = conn.getOutputStream();
            os.write(in.getBytes());
            os.flush();
            System.out.println("input: " + in);
            System.out.println("Mensaje de respuesta: " + conn.getResponseCode());

        } catch (Exception e) {
            System.out.println("notificacion Error" + e);
        }
    }

}
