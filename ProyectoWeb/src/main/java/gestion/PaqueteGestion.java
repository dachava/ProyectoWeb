
package gestion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Conexion;
import modelo.Producto;
import modelo.Paquete;
import java.util.ArrayList;
import controller.ProductoController;

public class PaqueteGestion {
    
    private static final String SQL_SELECT_PAQUETE = "select * from paquete where id_paquete=?";
    
    public static Paquete getPaquete(String id_paquete){
        
        Paquete paquete = null; 
        
        try{
            PreparedStatement consulta= Conexion.getConexion().prepareStatement(SQL_SELECT_PAQUETE);
            consulta.setString(1, id_paquete);
            ResultSet datos=  consulta.executeQuery();
            
            if (datos.next()){
                paquete = new Paquete(
                datos.getString(2),
                datos.getInt(3),
                datos.getString(4),
                datos.getString(5),
                datos.getInt(6));
            }
            
        }catch (SQLException ex){
                Logger.getLogger(PaqueteGestion.class.getName()).log(Level.SEVERE,null,ex);
        }
            
        return paquete;
    } 
    //Lista
    private static final String SQL_SELECT_PAQUETES= "select id_paquete, descripcion, peso, origen, estado, valor from paquete";
    
    public static ArrayList<Paquete> getPaquetes (){
        
        ArrayList<Paquete> paqueteList= new ArrayList<>();
        
        try{
            
            PreparedStatement consulta=Conexion.getConexion().prepareStatement(SQL_SELECT_PAQUETES);
            ResultSet rs= consulta.executeQuery();
            
            while (rs!=null && rs.next()){
                paqueteList.add(new Paquete (
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getInt(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getInt(6)));
            }
            
        }catch(SQLException ex){
            Logger.getLogger(PaqueteGestion.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        return paqueteList;
    }
    
    //CRUD
    //Insert
    
    private static final String SQL_INSERT_PAQUETE = "insert into paquete (id_paquete, descripcion, peso, origen, estado, valor) values (?,?,?,?,?,?)";
    
    public static boolean insertar (Paquete paquete){
        
        try{
                PreparedStatement sentencia=  Conexion.getConexion().prepareCall(SQL_INSERT_PAQUETE);
            sentencia.setInt(1,paquete.getIdPaquete());
            sentencia.setString(2,paquete.getDesc());
            sentencia.setInt(3,paquete.getPeso());
            sentencia.setString(4,paquete.getOrigen());
            sentencia.setString(5,paquete.getEstado());
            sentencia.setInt(6,paquete.getValor());
            
            
            return sentencia.executeUpdate()>0;//Devuelve un true en caso de que sea posible insertar el registro
            
        }catch (SQLException ex){
            Logger.getLogger(PaqueteGestion.class.getName()).log(Level.SEVERE,null, ex);
        }
        
        return false;
    }
    //UPDATE
    private static final String SQL_UPDATE_PAQUETE = "update paquete set descripcion=?, peso=?, origen=?, estado=?, valor=? where id_paquete=?";
    
    public static boolean actualiza (Paquete paquete){
        
        try{
            PreparedStatement sentencia= Conexion.getConexion().prepareCall(SQL_UPDATE_PAQUETE);
            
            sentencia.setString(1,paquete.getDesc());
            sentencia.setInt(2,paquete.getPeso());
            sentencia.setString(3,paquete.getOrigen());
            sentencia.setString(4,paquete.getEstado());
            sentencia.setInt(5,paquete.getValor());
            sentencia.setInt(6,paquete.getIdPaquete());
            
            
            return sentencia.executeUpdate()>0;
            
        }catch (Exception ex){
            Logger.getLogger(PaqueteGestion.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        return false; 
        
    }
    //DELETE
    private static final String SQL_DELETE_CLIENTE = "delete from paquete where id_paquete=?";
    
    public static boolean eliminar (Paquete paquete){
        
        try{
            PreparedStatement consulta= Conexion.getConexion().prepareStatement(SQL_DELETE_CLIENTE);
            consulta.setInt(1, paquete.getIdPaquete());
            
            return consulta.executeUpdate()>0;
        }catch (SQLException ex){
            Logger.getLogger(PaqueteGestion.class.getName()).log(Level.SEVERE,null, ex);
        }
        return false;
    }
    
   
    
}
