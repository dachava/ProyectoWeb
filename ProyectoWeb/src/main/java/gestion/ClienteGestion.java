
package gestion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Conexion;
import modelo.Producto;
import modelo.Cliente;
import java.util.ArrayList;
import controller.ProductoController;

public class ClienteGestion {
    
    private static final String SQL_SELECT_CLIENTE = "select * from cliente where id_cliente=?";
    
    public static Cliente getCliente(String id_cliente){
        
        Cliente cliente = null; 
        
        try{
            PreparedStatement consulta= Conexion.getConexion().prepareStatement(SQL_SELECT_CLIENTE);
            consulta.setString(1, id_cliente);
            ResultSet datos=  consulta.executeQuery();
            
            if (datos.next()){
                cliente = new Cliente(
                datos.getString(2),
                datos.getString(3),
                datos.getString(4),
                datos.getString(5),
                datos.getString(6));
            }
            
        }catch (SQLException ex){
                Logger.getLogger(ClienteGestion.class.getName()).log(Level.SEVERE,null,ex);
        }
            
        return cliente;
    } 
    //Lista
    private static final String SQL_SELECT_CLIENTES= "select id_cliente, nombre, apellidos, direccion, correo, telefono from cliente";
    
    public static ArrayList<Cliente> getClientes (){
        
        ArrayList<Cliente> clienteList= new ArrayList<>();
        
        try{
            
            PreparedStatement consulta=Conexion.getConexion().prepareStatement(SQL_SELECT_CLIENTES);
            ResultSet rs= consulta.executeQuery();
            
            while (rs!=null && rs.next()){
                clienteList.add(new Cliente (
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6)));
            }
            
        }catch(SQLException ex){
            Logger.getLogger(ClienteGestion.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        return clienteList;
    }
    
    //CRUD
    //Insert
    
    private static final String SQL_INSERT_CLIENTE = "insert into cliente (id_cliente, nombre,apellidos,direccion,correo,telefono) values (?,?,?,?,?,?)";
    
    public static boolean insertar (Cliente cliente){
        
        try{
                PreparedStatement sentencia=  Conexion.getConexion().prepareCall(SQL_INSERT_CLIENTE);
            sentencia.setInt(1,cliente.getIdCliente());
            sentencia.setString(2,cliente.getNombre());
            sentencia.setString(3,cliente.getApellidos());
            sentencia.setString(4,cliente.getDireccion());
            sentencia.setString(5,cliente.getCorreo());
            sentencia.setString(6,cliente.getTelefono());
            
            
            return sentencia.executeUpdate()>0;//Devuelve un true en caso de que sea posible insertar el registro
            
        }catch (SQLException ex){
            Logger.getLogger(ClienteGestion.class.getName()).log(Level.SEVERE,null, ex);
        }
        
        return false;
    }
    //UPDATE
    private static final String SQL_UPDATE_CLIENTE = "update cliente set nombre=?,apellidos=?,direccion=?,correo=?,telefono=? where id_cliente=?";
    
    public static boolean actualiza (Cliente cliente){
        
        try{
            PreparedStatement sentencia= Conexion.getConexion().prepareCall(SQL_UPDATE_CLIENTE);
            
            sentencia.setString(1,cliente.getNombre());
            sentencia.setString(2,cliente.getApellidos());
            sentencia.setString(3,cliente.getDireccion());
            sentencia.setString(4,cliente.getCorreo());
            sentencia.setString(5,cliente.getTelefono());
            sentencia.setInt(6,cliente.getIdCliente());
            
            
            return sentencia.executeUpdate()>0;
            
        }catch (Exception ex){
            Logger.getLogger(ClienteGestion.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        return false; 
        
    }
    //DELETE
    private static final String SQL_DELETE_CLIENTE = "delete from cliente where id_cliente=?";
    
    public static boolean eliminar (Cliente cliente){
        
        try{
            PreparedStatement consulta= Conexion.getConexion().prepareStatement(SQL_DELETE_CLIENTE);
            consulta.setInt(1, cliente.getIdCliente());
            
            return consulta.executeUpdate()>0;
        }catch (SQLException ex){
            Logger.getLogger(ClienteGestion.class.getName()).log(Level.SEVERE,null, ex);
        }
        return false;
    }
    
   
    
}
