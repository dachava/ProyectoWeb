
package modelo;


//Clase POJO relacionada a la tabla de base de datos
public class Usuario {
    
    //Atributos de la clase
    private int idUsuario;
    private String emailUsuario;
    private String pwUsuario;
    private String nombreUsuario;
    private String apellidosUsuario;
    private String provinciaUsuario;
    private String cantonUsuario;
    private String idRol;

    public Usuario() {
    }

    public Usuario(String nombreUsuario, String idRol) {
        
        this.nombreUsuario = nombreUsuario;
        this.idRol = idRol;
    }

    
    public Usuario(int idUsuario, String emailUsuario, String pwUsuario, String nombreUsuario, String apellidosUsuario, String provinciaUsuario, String cantonUsuario, String idRol) {
        this.idUsuario = idUsuario;
        this.emailUsuario = emailUsuario;
        this.pwUsuario = pwUsuario;
        this.nombreUsuario = nombreUsuario;
        this.apellidosUsuario = apellidosUsuario;
        this.provinciaUsuario = provinciaUsuario;
        this.cantonUsuario = cantonUsuario;
        this.idRol = idRol;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getPwUsuario() {
        return pwUsuario;
    }

    public void setPwUsuario(String pwUsuario) {
        this.pwUsuario = pwUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidosUsuario() {
        return apellidosUsuario;
    }

    public void setApellidosUsuario(String apellidosUsuario) {
        this.apellidosUsuario = apellidosUsuario;
    }

    public String getProvinciaUsuario() {
        return provinciaUsuario;
    }

    public void setProvinciaUsuario(String provinciaUsuario) {
        this.provinciaUsuario = provinciaUsuario;
    }

    public String getCantonUsuario() {
        return cantonUsuario;
    }

    public void setCantonUsuario(String cantonUsuario) {
        this.cantonUsuario = cantonUsuario;
    }

    public String getIdRol() {
        return idRol;
    }

    public void setIdRol(String idRol) {
        this.idRol = idRol;
    }

    
    
    
    
}
