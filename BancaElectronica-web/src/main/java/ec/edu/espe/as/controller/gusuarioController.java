/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.as.controller;

import com.mongodb.MongoClient;
import ec.edu.espe.as.controller.msg.GUsuarioRQ;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

/**
 *
 * @author jorge
 */
@Named(value = "gusuarioController")
@ViewScoped
public class gusuarioController implements Serializable{
    private List<GUsuarioRQ> items = null;
    private GUsuarioRQ usrq = new GUsuarioRQ();
    private final String urlUsuario="http://kyc.strangled.net:8080/KYC-mongo-rest-web/api/cliente/cedula/1004456891";
    private String usuario1="";
    private String usuario2="";
    private String usuario3="";
    private String contras1="";
    private String contras2="";
    public gusuarioController() {
    }
   
    public void generarContraseña(){
        try {
            URL url = new URL(urlUsuario);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            BufferedReader inp = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = null;
            StringBuilder crunchifyBuilder = new StringBuilder();
            while ((line = inp.readLine()) != null) {
                crunchifyBuilder.append(line);                    
            }
            System.out.println("Data Received: " + crunchifyBuilder.toString()); 
            System.out.println("substring is = " + crunchifyBuilder.substring(126, 127));
            if(conn.getResponseCode() == 200){
                usuario1=crunchifyBuilder.substring(126, 127);
                usuario2=crunchifyBuilder.substring(135, 137);
                usuario3=crunchifyBuilder.substring(14, 20);
                usuario1=usuario1.concat(usuario2).concat(usuario3);
                usuario1=usuario1.replace(" ","");
                usuario1=usuario1.toLowerCase();
                contras1=crunchifyBuilder.substring(20,26);
                contras2=crunchifyBuilder.substring(37,40);
                contras1=contras1.concat(contras2);
                contras1=contras1.replace(" ","");
                contras1=contras1.toLowerCase();
                System.out.println("USUARIO=:>"+usuario1);
                System.out.println("CONTRASEÑA=:>"+contras1);
                IngresarUsuario(usuario1, contras1, "1004456891");
            }else{
                System.out.println("Fallamos perro :'(");
            }
            
        }catch(Exception e){
            System.out.println("Ex:" + e);
        }
    }
    
    public void IngresarUsuario(String usser, String contra, String ced){
        System.out.println("DATOS LLEGA=:>"+usser+"/"+contra+"/"+ced);
        Morphia morphia = new Morphia();
        morphia.mapPackage("ec.edu.espe.arquitectura.msg");
        Datastore ds = morphia.createDatastore(new MongoClient(), "Usuario");
        System.out.println("Conexion establecida");
        
        usrq.setUsuario(usser);
        usrq.setContrasenia(contra);
        usrq.setCedula(ced);
        ds.save(usrq);
        ds.ensureIndexes();
        
    }

    public List<GUsuarioRQ> getItems() {
        return items;
    }

    public void setItems(List<GUsuarioRQ> items) {
        this.items = items;
    }

    public GUsuarioRQ getUsrq() {
        return usrq;
    }

    public void setUsrq(GUsuarioRQ usrq) {
        this.usrq = usrq;
    }

    public String getUsuario1() {
        return usuario1;
    }

    public void setUsuario1(String usuario1) {
        this.usuario1 = usuario1;
    }

    public String getUsuario2() {
        return usuario2;
    }

    public void setUsuario2(String usuario2) {
        this.usuario2 = usuario2;
    }

    public String getUsuario3() {
        return usuario3;
    }

    public void setUsuario3(String usuario3) {
        this.usuario3 = usuario3;
    }

    public String getContras1() {
        return contras1;
    }

    public void setContras1(String contras1) {
        this.contras1 = contras1;
    }

    public String getContras2() {
        return contras2;
    }

    public void setContras2(String contras2) {
        this.contras2 = contras2;
    }
    
}
