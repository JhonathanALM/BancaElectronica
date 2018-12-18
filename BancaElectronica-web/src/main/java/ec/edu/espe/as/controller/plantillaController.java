package ec.edu.espe.as.controller;

import ec.edu.espe.as.controller.msg.UsuarioRQ;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 * @author jhona
 */
@Named
@ViewScoped
public class plantillaController implements Serializable {

    public void verificarSesion() throws IOException {
         FacesContext context = FacesContext.getCurrentInstance();
        try {
            
           
            UsuarioRQ u = (UsuarioRQ) context.getExternalContext().getSessionMap().get("usuario");
            System.out.println("Este usuario va -> " + u.getCi());
            if (u == null) {
                context.getExternalContext().redirect("./../permisos.xhtml");

            }
        } catch (Exception e) {
            System.out.println("error de plantilla: "+e);
            context.getExternalContext().redirect("permisos.xhtml");
        }

    }

}
