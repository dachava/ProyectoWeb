
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

public class ProductoGestion {
    
    private static final String SQL_SELECT_PRODUCTO = "select * from producto where idProd=?";
    
    public static Producto getProducto(String idProd){
        
        Producto producto = null; 
        
        try{
            PreparedStatement consulta= Conexion.getConexion().prepareStatement(SQL_SELECT_PRODUCTO);
            consulta.setString(1, idProd);
            ResultSet datos=  consulta.executeQuery();
            
            if (datos.next()){
                producto = new Producto(
                datos.getString(2),
                datos.getString(3),
                datos.getString(4),
                datos.getFloat(5),
                datos.getString(6),
                datos.getInt(7),
                datos.getInt(8));
            }
            
        }catch (SQLException ex){
                Logger.getLogger(ProductoGestion.class.getName()).log(Level.SEVERE,null,ex);
        }
            
        return producto;
    } 
    //Lista
    private static final String SQL_SELECT_PRODUCTOS= "Select * from producto";
    
    public static ArrayList<Producto> getProductos (){
        
        ArrayList<Producto> productList= new ArrayList<>();
        
        try{
            
            PreparedStatement consulta=Conexion.getConexion().prepareStatement(SQL_SELECT_PRODUCTOS);
            ResultSet rs= consulta.executeQuery();
            
            while (rs!=null && rs.next()){
                productList.add(new Producto (
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getFloat(5),
                            rs.getString(6),
                            rs.getInt(7),
                            rs.getInt(8)) );
            }
            
        }catch(SQLException ex){
            Logger.getLogger(ProductoGestion.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        return productList;
    }
    
    //CRUD
    //Insert
    
    private static final String SQL_INSERT_PRODUCTO = "insert into producto (idProd, nombreProd,descripcionProd,precioProd,imgProd,cantidadProd,idCategoria) values (?,?,?,?,?,?,?)";
    
    public static boolean insertar (Producto producto){
        
        try{
            PreparedStatement sentencia=  Conexion.getConexion().prepareCall(SQL_INSERT_PRODUCTO);
            sentencia.setString(1,producto.getIdProd());
            sentencia.setString(2,producto.getNombreProd());
            sentencia.setString(3,producto.getDescripcionProd());
            sentencia.setFloat(4,producto.getPrecioProd());
            sentencia.setString(5,producto.getImgProd());
            sentencia.setInt(6,producto.getCantidadProd());
            sentencia.setInt(7,producto.getIdCategoria());
            
            return sentencia.executeUpdate()>0;//Devuelve un true en caso de que sea posible insertar el registro
            
        }catch (SQLException ex){
            Logger.getLogger(ProductoGestion.class.getName()).log(Level.SEVERE,null, ex);
        }
        
        return false;
    }
    //UPDATE
    private static final String SQL_UPDATE_PRODUCTO = "update producto set nombreProd=?,descripcionProd=?,precioProd=?,imgProd=?,cantidadProd=?,idCategoria=? where idProd=?";
    
    public static boolean actualiza (Producto producto){
        
        try{
            PreparedStatement sentencia= Conexion.getConexion().prepareCall(SQL_UPDATE_PRODUCTO);
            sentencia.setString(1,producto.getNombreProd());
            sentencia.setString(2,producto.getDescripcionProd());
            sentencia.setFloat(3,producto.getPrecioProd());
            sentencia.setString(4,producto.getImgProd());
            sentencia.setInt(5,producto.getCantidadProd());
            sentencia.setInt(6,producto.getIdCategoria());
            sentencia.setString(7,producto.getIdProd());
            
            return sentencia.executeUpdate()>0;
            
        }catch (SQLException ex){
            Logger.getLogger(ProductoGestion.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        return false; 
        
    }
    //DELETE
    private static final String SQL_DELETE_PRODUCTO = "delete from producto where idProd=?";
    
    public static boolean eliminar (Producto producto){
        
        try{
            PreparedStatement consulta= Conexion.getConexion().prepareStatement(SQL_DELETE_PRODUCTO);
            consulta.setString(1, producto.getIdProd());
            
            return consulta.executeUpdate()>0;
        }catch (SQLException ex){
            Logger.getLogger(ProductoGestion.class.getName()).log(Level.SEVERE,null, ex);
        }
        return false;
    }
    
    //Actualizar cant de items en DB
    private static final String SQL_UPDATE_CANT_CART = "update producto set cantidadProd = cantidadProd-1 where idProd=?";
    
    public static boolean transaccion (String idProd){
        
        try{
            PreparedStatement sentencia= Conexion.getConexion().prepareCall(SQL_UPDATE_CANT_CART);
            
            sentencia.setString(1,idProd);
            
            return sentencia.executeUpdate()>0;
            
        }catch (SQLException ex){
            Logger.getLogger(ProductoGestion.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        return false; 
        
    }
    //Insert Orden
    
     private static final String SQL_INSERT_ORDEN = "insert into orden (descOrden) values (?)";
    
    public static boolean insertarOrden (String orden){
        
        try{
            PreparedStatement sentencia=  Conexion.getConexion().prepareCall(SQL_INSERT_ORDEN);
         
            sentencia.setString(1,orden);
           
            
            return sentencia.executeUpdate()>0;//Devuelve un true en caso de que sea posible insertar el registro
            
        }catch (SQLException ex){
            Logger.getLogger(ProductoGestion.class.getName()).log(Level.SEVERE,null, ex);
        }
        
        return false;
    }
    
}
