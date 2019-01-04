/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.as.controller;

import com.banco.Externo_Service;
import com.banco.Transaccion;
import ec.edu.espe.arquitectura.soap.ws.TransferenciaWs_Service;
import ec.edu.espe.as.controller.msg.UsuarioRQ;
import java.io.OutputStream;
import javax.inject.Named;
//import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
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

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/40.87.45.204_9090/Modulo-Cuentas-Pll-web/TransferenciaWs.wsdl")
    private TransferenciaWs_Service service;
    private final String urlSeg = "http://137.135.107.221:8080/SegNotP2v1-web/api/notificacion";

    //  @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/ExternoWS/externo.wsdl")
    //  private Externo_Service service;
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
        /* try { // Call Web Service Operation
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
        } */

        try {
            ec.edu.espe.arquitectura.soap.ws.TransferenciaWs port = service.getTransferenciaWsPort();
            double monto = 0.0d;
            // TODO process result here
            System.out.println("EXs:" + this.transaccionExt.getCuentaOrigen());
            System.out.println("EXo:" + this.transaccionExt.getCuentaDestino());
            System.out.println("EXd:" + this.transaccionExt.getMonto());
            java.lang.String result = port.transferenciaE(this.transaccionExt.getCuentaOrigen(), this.transaccionExt.getCuentaDestino(), this.transaccionExt.getMonto());
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

    public Transaccion getTransaccionExt() {
        return transaccionExt;
    }

    public void setTransaccionExt(Transaccion transaccionExt) {
        this.transaccionExt = transaccionExt;
    }

    public void reset() {
        System.out.println("Reset campos...");
        transaccionExt.setMonto(0);
        transaccionExt.setBanco(null);
        transaccionExt.setConcepto(null);
        transaccionExt.setCuentaDestino(null);
        transaccionExt.setCuentaOrigen(null);
        transaccionExt.setIdentificacion(null);
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
