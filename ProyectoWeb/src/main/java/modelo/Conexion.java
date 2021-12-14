
package modelo;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Conexion {
    
    private static Conexion conexion;
    //String de conexión a la base de datos
    private static final String DBURL = "jdbc:oracle:thin:@localhost:1521:proyecto";
    private static Connection conn=null;
    
    private Conexion(){
        
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver").getDeclaredConstructor().newInstance();
            conn= DriverManager.getConnection(DBURL,"dchavarria","12345");
        }catch (ClassNotFoundException | SQLException | NoSuchMethodException | SecurityException | 
                InstantiationException | IllegalAccessException | IllegalArgumentException 
                | InvocationTargetException ex){
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null, ex);
        }
        
    }
    
    public static synchronized Connection getConexion(){
        
        if (conexion==null){
            conexion= new Conexion();
        }
        return conn;
    }
    
    
}
