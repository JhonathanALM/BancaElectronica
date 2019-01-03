package ec.edu.espe.as.controller.msg;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jhona
 */
public class PrestamoRQ {

    private String estado;
    private String fecha;
    private int id;
    private double monto;
    private int numero;
    private double saldo;
    private String tipo;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFecha() throws ParseException {
        StringBuilder crunchifyBuilder = new StringBuilder();
        crunchifyBuilder.append(fecha);
        return crunchifyBuilder.substring(0,10);
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
