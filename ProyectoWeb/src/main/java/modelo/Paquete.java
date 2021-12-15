/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


public class Paquete {
      
    private int idPaquete;
    private String desc;
    private int peso;
    private String origen;
    private String estado;
    private int valor;

    public Paquete() {
    }

    public Paquete(String desc, int peso, String origen, String estado, int valor) {
        this.desc = desc;
        this.peso = peso;
        this.origen = origen;
        this.estado = estado;
        this.valor = valor;
    }

    public Paquete(int idPaquete, String desc, int peso, String origen, String estado, int valor) {
        this.idPaquete = idPaquete;
        this.desc = desc;
        this.peso = peso;
        this.origen = origen;
        this.estado = estado;
        this.valor = valor;
    }

    public int getIdPaquete() {
        return idPaquete;
    }

    public void setIdPaquete(int idPaquete) {
        this.idPaquete = idPaquete;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
    
}
