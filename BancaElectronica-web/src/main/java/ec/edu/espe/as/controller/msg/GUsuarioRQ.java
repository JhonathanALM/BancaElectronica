/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.as.controller.msg;

import com.sun.corba.se.spi.ior.ObjectId;

import javax.persistence.Id;
import org.mongodb.morphia.annotations.Entity;

/**
 *
 * @author jorge
 */
@Entity(noClassnameStored = true, value="usuario")
public class GUsuarioRQ {
     @Id
    private ObjectId id;
    
    
    private String usuario;
    private String contrasenia;
    private String cedula;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    
}
