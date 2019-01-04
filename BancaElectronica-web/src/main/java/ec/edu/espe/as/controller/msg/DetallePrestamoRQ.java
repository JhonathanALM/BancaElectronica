/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.as.controller.msg;

/**
 *
 * @author jorge
 */
public class DetallePrestamoRQ {

    private int id;
    private double capital;
    private double interes;
    private double valorCuota;
    private double saldo;
    private String fechaAmortizacion;
    private String estado;
    private int numero;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCapital() {
        return capital;
    }

    public void setCapital(double capital) {
        this.capital = capital;
    }

    public double getInteres() {
        return interes;
    }

    public void setInteres(double interes) {
        this.interes = interes;
    }

    public double getValorCuota() {
        return valorCuota;
    }

    public void setValorCuota(double valorCuota) {
        this.valorCuota = valorCuota;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getFechaAmortizacion() {
        StringBuilder crunchifyBuilder = new StringBuilder();
        crunchifyBuilder.append(fechaAmortizacion);
        return crunchifyBuilder.substring(0, 10);
    }

    public void setFechaAmortizacion(String fechaAmortizacion) {
        this.fechaAmortizacion = fechaAmortizacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

}
