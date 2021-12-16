
package gestion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Conexion;
import modelo.Producto;
import modelo.Orden;
import java.util.ArrayList;
import controller.ProductoController;
import java.sql.Statement;

public class OrdenGestion {
    
    private static final String SQL_SELECT_ORDEN = "select * from orden where id_orden=?";
    
    public static Orden getOrden(String id_orden){
        
        Orden orden = null; 
        
        try{
            PreparedStatement consulta= Conexion.getConexion().prepareStatement(SQL_SELECT_ORDEN);
            consulta.setString(1, id_orden);
            ResultSet datos=  consulta.executeQuery();
            
            if (datos.next()){
                orden = new Orden(
                datos.getInt(2),
                datos.getInt(3),
                datos.getInt(4),
                datos.getString(5),
                datos.getString(6));
            }
            
        }catch (SQLException ex){
                Logger.getLogger(OrdenGestion.class.getName()).log(Level.SEVERE,null,ex);
        }
            
        return orden;
    } 
    //Lista
    private static final String SQL_SELECT_ORDENES= "select id_orden, id_cliente, id_paquete, tarifa, estado from orden";
    
    public static ArrayList<Orden> getOrdens (){
        
        ArrayList<Orden> ordenList= new ArrayList<>();
        
        try{
            
            PreparedStatement consulta=Conexion.getConexion().prepareStatement(SQL_SELECT_ORDENES);
            ResultSet rs= consulta.executeQuery();
            
            while (rs!=null && rs.next()){
                ordenList.add(new Orden (
                            rs.getInt(1),
                            rs.getInt(2),
                            rs.getInt(3),
                            rs.getString(4),
                            rs.getString(5)));
            }
            
        }catch(SQLException ex){
            Logger.getLogger(OrdenGestion.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        return ordenList;
    }
    
    //CRUD
    //Insert
    
    private static final String SQL_INSERT_ORDEN = "insert into orden (id_orden, id_cliente, id_paquete, tarifa, estado) values (?,?,?,?,?)";
    
    public static boolean insertar (Orden orden){
        Statement nom = null;
        try{
                PreparedStatement sentencia=  Conexion.getConexion().prepareCall(SQL_INSERT_ORDEN);
            sentencia.setInt(1,orden.getIdOrden());
            sentencia.setInt(2,orden.getIdCliente());
            sentencia.setInt(3,orden.getIdPaquete());
            sentencia.setString(4,orden.getTarifa());
            sentencia.setString(5,orden.getEstado());
            
            
            
            return sentencia.executeUpdate()>0;//Devuelve un true en caso de que sea posible insertar el registro
            
        }catch (SQLException ex){
            Logger.getLogger(OrdenGestion.class.getName()).log(Level.SEVERE,null, ex);
        }
        
        return false;
    }
    //UPDATE
    private static final String SQL_UPDATE_ORDEN = "update orden set id_cliente=?, id_paquete=?, tarifa=?, estado=? where id_orden=?";
    
    public static boolean actualiza (Orden orden){
        
        try{
            PreparedStatement sentencia= Conexion.getConexion().prepareCall(SQL_UPDATE_ORDEN);
            
            sentencia.setInt(1,orden.getIdCliente());
            sentencia.setInt(2,orden.getIdPaquete());
            sentencia.setString(3,orden.getTarifa());
            sentencia.setString(4,orden.getEstado());
            sentencia.setInt(5,orden.getIdOrden());
            
            
            return sentencia.executeUpdate()>0;
            
        }catch (Exception ex){
            Logger.getLogger(OrdenGestion.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        return false; 
        
    }
    //DELETE
    private static final String SQL_DELETE_ORDEN = "delete from orden where id_orden=?";
    
    public static boolean eliminar (Orden orden){
        
        try{
            PreparedStatement consulta= Conexion.getConexion().prepareStatement(SQL_DELETE_ORDEN);
            consulta.setInt(1, orden.getIdOrden());
            
            return consulta.executeUpdate()>0;
        }catch (SQLException ex){
            Logger.getLogger(OrdenGestion.class.getName()).log(Level.SEVERE,null, ex);
        }
        return false;
    }
    
   
    
}
