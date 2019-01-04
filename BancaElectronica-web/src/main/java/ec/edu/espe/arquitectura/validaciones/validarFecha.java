/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.validaciones;

import java.text.DecimalFormat;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author jorge
 */
@FacesValidator("validacion")
public class validarFecha implements Validator{
    private Date desde;
    private Date hasta;
    
    public void desde(FacesContext fc, UIComponent  uic, Object o){
        String dsd = o.toString().trim();
        boolean ok = false;
        
        if(dsd.length() == 0){
            throw new ValidatorException(new FacesMessage("Ingrese un valor adecuado en el campo fecha"));            
        }else if(!ok){
            throw new ValidatorException(new FacesMessage("fallo"));
        }
    }
    @Override
    public void validate(FacesContext fc, UIComponent  uic, Object o){
        String nmonto = o.toString().trim();
        double monto=0;
        boolean ok = false;
        DecimalFormat formato1 = new DecimalFormat("#.00");
        String val="";
        if(nmonto.length() == 0){
            throw new ValidatorException(new FacesMessage("Ingrese un valor adecuado entre 500 y 1500"));            
        }else{
            monto=Double.parseDouble(nmonto);
            val=formato1.format(monto);
            System.out.println(val);
            System.out.println("s=:>"+Double.parseDouble(val.replace(",",".").trim()));
            monto=0;
            monto=Double.parseDouble(val.replace(",",".").trim());
            System.out.println("monto=:>"+monto);
            if(monto>=500 && monto<=1500){
                nmonto=String.valueOf(monto);
                ok=true;
            }else if(!ok){
                throw new ValidatorException(new FacesMessage("fallo"));
            }
        }
    }

    public validarFecha() {
    }

    public Date getDesde() {
        return desde;
    }

    public void setDesde(Date desde) {
        this.desde = desde;
    }

    public Date getHasta() {
        return hasta;
    }

    public void setHasta(Date hasta) {
        this.hasta = hasta;
    }
    
    
    
}
