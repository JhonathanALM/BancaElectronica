/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.as.controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author jhona
 */
@Named(value = "calendarView")
@SessionScoped
public class CalendarView implements Serializable {

    /**
     * Creates a new instance of CalendarView
     */
    private Date date3;
    public CalendarView() {
    }

    public Date getDate3() {
        return date3;
    }

    public void setDate3(Date date3) {
        this.date3 = date3;
    }
    
}
