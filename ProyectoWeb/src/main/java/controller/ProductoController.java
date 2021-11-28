
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
}
