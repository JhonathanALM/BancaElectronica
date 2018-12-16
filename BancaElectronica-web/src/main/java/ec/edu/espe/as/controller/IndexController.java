package ec.edu.espe.as.controller;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 * @author jhona
 */
@Named
@ViewScoped
public class IndexController implements Serializable {

    /*@EJB
    private AgenteFacadeLocal EJBAgente;
    private Agente agente;

    @EJB
    private CarteraFacadeLocal EJBCartera;
    private List<Cartera> cartera = null;
    private Cartera ca = new Cartera();
    private int cuantos;
    private float dinero=0;

    @PostConstruct
    public void init() {
        agente = new Agente();
        ca = new Cartera();
        contar();
    }

    public Agente getAgente() {
        return agente;
    }

    public void setAgente(Agente agente) {
        this.agente = agente;
    }

    public String iniciarSesion() {
        String redireccion = "";

        try {
            Agente us;
            System.out.println("Este agente va -> " + agente.getNombre());
            us = EJBAgente.iniciarSesion(agente, agente.getNombre());
            if (us != null) {
                // redireccion = "/Vista/Inicio?faces-redirect=true";
                redireccion = "/Vista/Inicio?faces-redirect=true";
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("agente", us);
                //llenarClientes();  
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Credenciales Incorrectas"));
                System.out.println("Fallamos prro :( ");
            }

        } catch (Exception e) {
            System.out.println("Error: " + agente.getClave());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "ERROR!"));
        }
        return redireccion;
    }

    public void cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }

    public String getActual() {
        Agente as = (Agente) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("agente");
        System.out.println("Bienvenido Agente>> : " + as.getNombre());
        return as.getNombre();
    }
    
    public void contar(){
        Agente ar = (Agente) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("agente");       
        cuantos=EJBCartera.CarteraFiltro(ar).size();
        
        for(Cartera p : EJBCartera.CarteraFiltro(ar)){
            this.dinero = this.dinero+ Float.parseFloat(p.getCodigopermora().getMontototal()+"");
         }
        System.out.println("Se t agregaran "+cuantos+"Un total de  : "+dinero+"$");
    }

    public Cartera getCa() {
        return ca;
    }

    public void setCa(Cartera ca) {
        this.ca = ca;
    }

    public int getCuantos() {
        return cuantos;
    }

    public void setCuantos(int cuantos) {
        this.cuantos = cuantos;
    }

    public float getDinero() {
        return dinero;
    }

    public void setDinero(float dinero) {
        this.dinero = dinero;
    }

*/
}
