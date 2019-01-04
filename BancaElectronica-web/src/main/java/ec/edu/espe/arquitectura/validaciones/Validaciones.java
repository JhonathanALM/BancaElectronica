/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.validaciones;

/**
 *
 * @author jorge
 */
import java.text.DecimalFormat;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("validacion")
public class Validaciones implements Validator{
    
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
}
