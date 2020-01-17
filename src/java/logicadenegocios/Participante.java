package logicadenegocios;

/**
 * Clase de los objetos de tipo Participante
 * 
 * @author Kevin Castillo, Ma Paula Rodriguez y Raquel Rojas
 * @version 1.0
 */
public class Participante {
    private String nombre;
    private String email;
    
    public Participante(String pNombre, String pEmail){
      setNombre(pNombre);
      setEmail(pEmail);
    }

    
    public String getNombre() {
      return nombre;
    }

    
    public String getEmail() {
      return email;
    }

    
    private void setNombre(String pNonbre) {
      this.nombre = pNonbre;
    }

    
    private void setEmail(String pEmail) {
      this.email = pEmail;
    }
    
      
    /**
   * Método para convertir en String toda la información del Participante
   * 
   * @return  msg, informacion de Participante
   */
    @Override
    public String toString(){
    String msg = "Nombre: " + getNombre() + "email: " + getEmail();
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
    Participante participante = (Participante) o;
    return participante.getEmail() == email && participante.getNombre() == nombre;
   } 
}

