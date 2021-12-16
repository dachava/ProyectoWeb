
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
import java.sql.SQLException;
        
        

@Named(value = "clienteController")
@SessionScoped
public class ClienteController extends Cliente implements Serializable {

    
    
    public ClienteController() {
    }
    
     public List<Cliente> getClientes(){
        return ClienteGestion.getClientes();
    }
     
        public List<Cliente> getVistaClientes(){
        return ClienteGestion.getVista();
    }
        
    //DBMS
       public String dbms(){
           return "ClienteOutput.xhtml";
       }
     
    
     
     //CRUD
     //Insert
     
     public String inserta (){
        
        if (ClienteGestion.insertar(this)){
            return "ClienteList.xhtml";
        }else{
            FacesMessage mensaje= new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error","Posible Identificación Duplicada");
            FacesContext.getCurrentInstance().addMessage("editaClienteForm:idCliente", mensaje);
            return "ClienteEdit.xhtml";
        }
    }
     
     //Edita lista
     public String edita (String idCliente){
        
        Cliente cliente=  ClienteGestion.getCliente(idCliente);
        
        if (cliente !=null){
            
            this.setIdCliente(cliente.getIdCliente());
            this.setNombre(cliente.getNombre());
            this.setApellidos(cliente.getApellidos());
            this.setDireccion(cliente.getDireccion());
            this.setCorreo(cliente.getCorreo());
            this.setTelefono(cliente.getTelefono());
         
            return "ClienteEdit.xhtml";
        }else{
            
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
            "Error","Posiblemente el id no exista");
            FacesContext.getCurrentInstance().addMessage("listForm", mensaje);
            return "ClienteList.xhtml";
        }
        
    }
     //Update
     public String modifica (){
        
        if (ClienteGestion.actualiza(this)){
            return "ClienteList.xhtml";
            
        }else{
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
            "Error","Posible Identificación Duplicada");
            FacesContext.getCurrentInstance().addMessage("editaClienteForm:idCliente", mensaje);
            return "ClienteEdit.xhtml";
        }
        
    }
    //Delete
    public String elimina (){
        
        if (ClienteGestion.eliminar(this)){
            return "ClienteList.xhtml";
        }else{
            FacesMessage mensaje= new FacesMessage (FacesMessage.SEVERITY_ERROR,
            "Error", "Posible que el id no exista");
            FacesContext.getCurrentInstance().addMessage("editaClienteForm:idCliente", mensaje);
            return "ClienteEdit.xhtml";
        }
        
    }
    
    public void startDbmsOutput() throws SQLException{
        getDbmsOutput();
    }
     
    public String getDbmsOutput() throws SQLException{
        
    return ClienteGestion.dbmsOutput();
    }

    
     
    
    
 
    
  
    

}
