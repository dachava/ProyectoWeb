
package modelo;


public class Orden {
    
    private int idOrden;
    private int idCliente;
    private int idPaquete;
    private String tarifa;
    private String estado;

      public Orden() {
    }

    
    public Orden(int idOrden, int idCliente, int idPaquete, String tarifa, String estado) {
        this.idOrden = idOrden;
        this.idCliente = idCliente;
        this.idPaquete = idPaquete;
        this.tarifa = tarifa;
        this.estado = estado;
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdPaquete() {
        return idPaquete;
    }

    public void setIdPaquete(int idPaquete) {
        this.idPaquete = idPaquete;
    }

    public String getTarifa() {
        return tarifa;
    }

    public void setTarifa(String tarifa) {
        this.tarifa = tarifa;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

  
    
    
}
