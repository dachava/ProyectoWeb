
package controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import gestion.ClienteGestion;
import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
        
        

@Named(value = "clienteController")
@SessionScoped
public class ClienteController extends Cliente implements Serializable {

    ArrayList<Cliente> cart= new ArrayList<>();
    
    public ClienteController() {
    }
    
     public List<Cliente> getProductos(){
        return ClienteGestion.getClientes();
    }
     
    
     
     //CRUD
     //Insert
     
     public String inserta (){
        
        if (ClienteGestion.insertar(this)){
            return "ProductoList.xhtml";
        }else{
            FacesMessage mensaje= new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error","Posible Identificación Duplicada");
            FacesContext.getCurrentInstance().addMessage("editaProductoForm:identificacion", mensaje);
            return "ProductoEdit.xhtml";
        }
    }
     
     //Edita lista
     public String edita (String idProd){
        
        Producto producto=  ProductoGestion.getProducto(idProd);
        
        if (producto !=null){
            
            this.setIdProd(producto.getIdProd());
            this.setNombreProd(producto.getNombreProd());
            this.setDescripcionProd(producto.getDescripcionProd());
            this.setPrecioProd(producto.getPrecioProd());
            this.setImgProd(producto.getImgProd());
            this.setCantidadProd(producto.getCantidadProd());
            this.setIdCategoria(producto.getIdCategoria());
            return "ProductoEdit.xhtml";
        }else{
            
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
            "Error","Posiblemente el id no exista");
            FacesContext.getCurrentInstance().addMessage("listForm", mensaje);
            return "ProductoList.xhtml";
        }
        
    }
     //Update
     public String modifica (){
        
        if (ProductoGestion.actualiza(this)){
            return "ProductoList.xhtml";
            
        }else{
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
            "Error","Posible Identificación Duplicada");
            FacesContext.getCurrentInstance().addMessage("editaProductoForm:idProd", mensaje);
            return "ProductoEdit.xhtml";
        }
        
    }
    //Delete
    public String elimina (){
        
        if (ProductoGestion.eliminar(this)){
            return "ProductoList.xhtml";
        }else{
            FacesMessage mensaje= new FacesMessage (FacesMessage.SEVERITY_ERROR,
            "Error", "Posible que el id no exista");
            FacesContext.getCurrentInstance().addMessage("editaProductoForm:identificacion", mensaje);
            return "ProductoEdit.xhtml";
        }
        
    }
     
     
     //Agregar al carrito
     public String addToCart (String id){

        Producto producto=  ProductoGestion.getProducto(id);
        
        if (producto !=null){
            cart.add(producto);
            FacesContext.getCurrentInstance()
                    .addMessage("form:dvgi", new FacesMessage(FacesMessage.SEVERITY_INFO, "Confirmación", "Agregó el producto "
                            + producto.getNombreProd()+" al carrito."));        }
        
        else{
            FacesContext.getCurrentInstance()
                    .addMessage("form:dvgi", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El producto no se "
                            + "pudo agregar al carrito. Intentelo de nuevo más tarde."));
        }
        return null;
        
    }

    public ArrayList<Producto> getCart() {
        return cart;
    }
    
  
    public void limpiarCart(){
        cart = new ArrayList<>();
    }
 
    public String isCartEmpty() {
        if (cart.size() > 0) {
            return "lleno";
        } else {
            return "vacio";
        }
    }
    
    
    public float sumaPrecio() {

        float total = 0;
        for (int i = 0; i <= (cart.size()-1); i++) {
            total = total + cart.get(i).getPrecioProd();
        }

        return total;
    }
    
    public String checkout (){
        
        sumaPrecio();
        return "checkout.xhtml";
        
    }
    
    public boolean transaccion() {
        try {
            for (int i = 0; i <= (cart.size() - 1); i++) {
                String idProd = cart.get(i).getIdProd();
                ProductoGestion.transaccion(idProd);
            }
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    
    public String compra() {
        if (transaccion() == true) {
            insertaOrden();
            return "factura.xhtml";

        } else {
            return null;
        }
    }
    

    public void insertaOrden() {

        if (ProductoGestion.insertarOrden(new Gson().toJson(cart)) == true) {
            limpiarCart();
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Completado", "Articulos enviados");
            FacesContext.getCurrentInstance().addMessage("editaProductoForm:identificacion", mensaje);
        } 
        else{
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Posible Identificación Duplicada");
            FacesContext.getCurrentInstance().addMessage("editaProductoForm:identificacion", mensaje);
        }
    }
    

}
