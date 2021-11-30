
package gestion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Conexion;
import modelo.Producto;
import java.util.ArrayList;

public class ProductoGestion {
    
    private static final String SQL_SELECT_PRODUCTO = "select * from producto where idProd=?";
    
    public static Producto getProducto(String id){
        
        Producto producto = null; 
        
        try{
            PreparedStatement consulta= Conexion.getConexion().prepareStatement(SQL_SELECT_PRODUCTO);
            consulta.setString(1, id);
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
    
}
