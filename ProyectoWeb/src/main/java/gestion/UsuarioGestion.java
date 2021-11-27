
package gestion;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Conexion;
import modelo.Usuario;


public class UsuarioGestion {
    
    private static final String SQL_VALIDA="select nombreUsuario,idRol from usuario where emailUsuario=? and pwUsuario=MD5(?)";
    
    public static Usuario Valida (String emailUsuario, String password){
        
        Usuario usuario = null;
        
        try{
            
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_VALIDA);
            sentencia.setString(1, emailUsuario);
            sentencia.setString(2, password);
            ResultSet rs= sentencia.executeQuery();
            
            if (rs.next()){//Si tiene la posibilidad de hacer next es porque tiene datos
                
                usuario= new Usuario (rs.getString(1),rs.getString(2));
            }
            
        }catch (SQLException ex){
            
            Logger.getLogger(UsuarioGestion.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        return usuario;
    }
    
}
