
package modelo;


public class Producto {
    
    private int id;
    private String idProd;
    private String nombreProd;
    private String descripcionProd;
    private float precioProd;
    private String imgProd;
    private int cantidadProd;
    private int idCategoria;

    public Producto() {
    }

    public Producto(String idProd, String nombreProd, String descripcionProd, float precioProd, String imgProd, int cantidadProd, int idCategoria) {
        this.idProd = idProd;
        this.nombreProd = nombreProd;
        this.descripcionProd = descripcionProd;
        this.precioProd = precioProd;
        this.imgProd = imgProd;
        this.cantidadProd = cantidadProd;
        this.idCategoria = idCategoria;
    }

    public Producto(int id, String idProd, String nombreProd, String descripcionProd, float precioProd, String imgProd, int cantidadProd, int idCategoria) {
        this.id = id;
        this.idProd = idProd;
        this.nombreProd = nombreProd;
        this.descripcionProd = descripcionProd;
        this.precioProd = precioProd;
        this.imgProd = imgProd;
        this.cantidadProd = cantidadProd;
        this.idCategoria = idCategoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdProd() {
        return idProd;
    }

    public void setIdProd(String idProd) {
        this.idProd = idProd;
    }

    public String getNombreProd() {
        return nombreProd;
    }

    public void setNombreProd(String nombreProd) {
        this.nombreProd = nombreProd;
    }

    public String getDescripcionProd() {
        return descripcionProd;
    }

    public void setDescripcionProd(String descripcionProd) {
        this.descripcionProd = descripcionProd;
    }

    public float getPrecioProd() {
        return precioProd;
    }

    public void setPrecioProd(float precioProd) {
        this.precioProd = precioProd;
    }

    public String getImgProd() {
        return imgProd;
    }

    public void setImgProd(String imgProd) {
        this.imgProd = imgProd;
    }

    public int getCantidadProd() {
        return cantidadProd;
    }

    public void setCantidadProd(int cantidadProd) {
        this.cantidadProd = cantidadProd;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
    
    
    
}
