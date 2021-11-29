
package controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import gestion.ProductoGestion;
import java.util.List;
import modelo.Producto;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


@Named(value = "productoController")
@SessionScoped
public class ProductoController extends Producto implements Serializable {

    
    public ProductoController() {
    }
    
     public List<Producto> getProductos(){
        return ProductoGestion.getProductos();
    }
    
     
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
     
}
