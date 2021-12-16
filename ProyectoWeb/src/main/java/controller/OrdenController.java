
package controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import gestion.OrdenGestion;
import java.util.ArrayList;
import java.util.List;
import modelo.Orden;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
        
        

@Named(value = "ordenController")
@SessionScoped
public class OrdenController extends Orden implements Serializable {

    
    
    public OrdenController() {
    }
    
     public List<Orden> getOrdens(){
        return OrdenGestion.getOrdens();
    }
     
    
     
     //CRUD
     //Insert
     
     public String inserta (){
        
        if (OrdenGestion.insertar(this)){
            return "OrdenList.xhtml";
        }else{
            FacesMessage mensaje= new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error","Posible Identificación Duplicada");
            FacesContext.getCurrentInstance().addMessage("editaOrdenForm:idOrden", mensaje);
            return "OrdenEdit.xhtml";
        }
    }
     
     //Edita lista
     public String edita (String idOrden){
        
        Orden orden=  OrdenGestion.getOrden(idOrden);
        
        if (orden !=null){
            
            this.setIdOrden(orden.getIdOrden());
            this.setIdCliente(orden.getIdCliente());
            this.setIdPaquete(orden.getIdPaquete());
            this.setTarifa(orden.getTarifa());
            this.setEstado(orden.getEstado());
            
         
            return "OrdenEdit.xhtml";
        }else{
            
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
            "Error","Posiblemente el id no exista");
            FacesContext.getCurrentInstance().addMessage("listForm", mensaje);
            return "OrdenList.xhtml";
        }
        
    }
     //Update
     public String modifica (){
        
        if (OrdenGestion.actualiza(this)){
            return "OrdenList.xhtml";
            
        }else{
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
            "Error","Posible Identificación Duplicada");
            FacesContext.getCurrentInstance().addMessage("editaOrdenForm:idOrden", mensaje);
            return "OrdenEdit.xhtml";
        }
        
    }
    //Delete
    public String elimina (){
        
        if (OrdenGestion.eliminar(this)){
            return "OrdenList.xhtml";
        }else{
            FacesMessage mensaje= new FacesMessage (FacesMessage.SEVERITY_ERROR,
            "Error", "Posible que el id no exista");
            FacesContext.getCurrentInstance().addMessage("editaOrdenForm:idOrden", mensaje);
            return "OrdenEdit.xhtml";
        }
        
    }
     
     
    
    
 
    
  
    

}
