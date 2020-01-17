package logicadenegocios;

/**
 *
 * @author Maria Paula Rodriguez y Kevin Castillo
 */
public class Usuario {
    
  private String nombreUsuario;
  private String contraseña;

  
  /**
   * Constructor
   */
  public Usuario() {}
  
  
  /**
   * Constructor
   * @param pNombre nombre de usuario
   * @param pContraseña  contraseña del usuario
   */
  public Usuario(String pNombre, String pContraseña) {
    this.nombreUsuario = pNombre;
    this.contraseña = pContraseña;
  }  

  
  public String getNombreUsuario() {      
    return nombreUsuario;
  }

  
  public void setNombreUsuario(String pNombreUsuario) {
    this.nombreUsuario = pNombreUsuario;
  }

  
  public String getContraseña() {
    return contraseña;
  }

  
  public void setContraseña(String pContraseña) {    
    this.contraseña = pContraseña;
  }
  
  
     
   /**
   * Método para convertir en String toda la información del Usuario
   * 
   * @return msg, con todos los datos del Usuario
   */
  @Override
  public String toString(){
    String msg;
    msg = "Nombre:  " + nombreUsuario + "Contraseña: " + contraseña;
    return msg;     
    } 
  
  
   /**
   * Método para comparar si un objeto es igual 
   * 
   * @param  o el objeto a comparar
   */
  public boolean equals(Object o){
    if(this == o)
      return true;  
    if(o==null)
      return false;
    if(getClass()!=o.getClass())
      return false;
    // convertir el objeto
    Usuario u = (Usuario) o;
    return nombreUsuario == u.nombreUsuario && contraseña == u.contraseña ;
  }
}
