
package controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import gestion.ProductoGestion;
import java.util.ArrayList;
import java.util.List;
import modelo.Producto;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
        
        

@Named(value = "productoController")
@SessionScoped
public class ProductoController extends Producto implements Serializable {

    ArrayList<Producto> cart= new ArrayList<>();
    
    public ProductoController() {
    }
    
     public List<Producto> getProductos(){
        return ProductoGestion.getProductos();
    }
     
    
     //Muestra la categoria segun el ID
     public String getCategoria(int cat){
         
         String categoria=new String();
     
         switch(cat) {
            case 1:
                categoria="Acetato";
                break;
            case 2:
                categoria="CD";
                 break;
            case 3:
                categoria="DVD";
                 break;
            case 4:
                categoria="Casette";
                 break;
            case 5:
                categoria="Digipak";
                 break;
            case 6:
                categoria="T-Shirt";
                break; 
            case 7:
                categoria="Accesorios";
                break; 
            default:
                 categoria="Desconocido";
                 break;
        }
    
         return categoria;
     }
     
     //Muestra cuanto stock queda dependiendo de la cantidad en la BD
     public String getStock(int cantidad){
         
         String stockStatus = new String();
         
         if(cantidad <= 0){
             stockStatus = "Agotado";
         }
         if(cantidad < 10){
             stockStatus = "Pocas Unidades";
         } else {
             stockStatus = "Disponible";
         }
         return stockStatus;
     }
     
     //CRUD
     //Insert
     
     public String inserta (){
        
        if (ProductoGestion.insertar(this)){
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
                    .addMessage("form:cartAction", new FacesMessage(FacesMessage.SEVERITY_INFO, "Info Message", "Message Content"));        }
        
        else{
            FacesContext.getCurrentInstance()
                    .addMessage("form:cartAction", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Message Content."));
        }
        return null;
        
    }

    public ArrayList<Producto> getCart() {
        return cart;
    }
    
  
    public void limpiarCart(){
        cart = new ArrayList<>();
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
