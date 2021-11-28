
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
    
}
