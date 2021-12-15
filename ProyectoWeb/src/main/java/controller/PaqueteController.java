
package controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import gestion.PaqueteGestion;
import java.util.ArrayList;
import java.util.List;
import modelo.Paquete;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
        
        

@Named(value = "paqueteController")
@SessionScoped
public class PaqueteController extends Paquete implements Serializable {

    
    
    public PaqueteController() {
    }
    
     public List<Paquete> getPaquetes(){
        return PaqueteGestion.getPaquetes();
    }
     
    
     
     //CRUD
     //Insert
     
     public String inserta (){
        
        if (PaqueteGestion.insertar(this)){
            return "PaqueteList.xhtml";
        }else{
            FacesMessage mensaje= new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error","Posible Identificación Duplicada");
            FacesContext.getCurrentInstance().addMessage("editaPaqueteForm:idPaquete", mensaje);
            return "PaqueteEdit.xhtml";
        }
    }
     
     //Edita lista
     public String edita (String idPaquete){
        
        Paquete paquete=  PaqueteGestion.getPaquete(idPaquete);
        
        if (paquete !=null){
            
            this.setIdPaquete(paquete.getIdPaquete());
            this.setDesc(paquete.getDesc());
            this.setPeso(paquete.getPeso());
            this.setOrigen(paquete.getOrigen());
            this.setEstado(paquete.getEstado());
            this.setValor(paquete.getValor());
         
            return "PaqueteEdit.xhtml";
        }else{
            
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
            "Error","Posiblemente el id no exista");
            FacesContext.getCurrentInstance().addMessage("listForm", mensaje);
            return "PaqueteList.xhtml";
        }
        
    }
     //Update
     public String modifica (){
        
        if (PaqueteGestion.actualiza(this)){
            return "PaqueteList.xhtml";
            
        }else{
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
            "Error","Posible Identificación Duplicada");
            FacesContext.getCurrentInstance().addMessage("editaPaqueteForm:idPaquete", mensaje);
            return "PaqueteEdit.xhtml";
        }
        
    }
    //Delete
    public String elimina (){
        
        if (PaqueteGestion.eliminar(this)){
            return "PaqueteList.xhtml";
        }else{
            FacesMessage mensaje= new FacesMessage (FacesMessage.SEVERITY_ERROR,
            "Error", "Posible que el id no exista");
            FacesContext.getCurrentInstance().addMessage("editaPaqueteForm:idPaquete", mensaje);
            return "PaqueteEdit.xhtml";
        }
        
    }
     
     
    
    
 
    
  
    

}
