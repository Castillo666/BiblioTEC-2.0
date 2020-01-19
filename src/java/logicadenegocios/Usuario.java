package logicadenegocios;

/**
 *
 * @author Maria Paula Rodriguez y Kevin Castillo
 */
public class Usuario {
    
  private String nombreUsuario;
  private String contraseña;
  private String tipo;
  
  /**
   * Constructor
   */
  public Usuario() {}
  
  
  /**
   * Constructor
   * @param pNombre
   * @param pContraseña 
   */
  public Usuario(String pNombre, String pContraseña, String pTipo) {
    this.nombreUsuario = pNombre;
    this.contraseña = pContraseña;
    this.tipo = pTipo;
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
  

  public String getTipo(){
    return tipo; 
  }
  
  public void setTipo(String pTipo){
    this.tipo = pTipo;
  }
     
   /**
   * Método para convertir en String toda la información del Usuario
   * 
   * @return msg, con todos los datos del Usuario
   */
  @Override
  public String toString(){
    String msg;
    msg = "Nombre:  " + nombreUsuario + "Contraseña: " + contraseña + "Tipo: " + tipo;
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
