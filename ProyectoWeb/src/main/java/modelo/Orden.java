
package modelo;


public class Orden {
    
    public int idOrden;
    public String descOrden;

    public Orden() {
    }

    public Orden(int idOrden, String descOrden) {
        this.idOrden = idOrden;
        this.descOrden = descOrden;
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public String getDescOrden() {
        return descOrden;
    }

    public void setDescOrden(String descOrden) {
        this.descOrden = descOrden;
    }
    
}
