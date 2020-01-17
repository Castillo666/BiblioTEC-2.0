package logicadenegocios;

/**
 * Clase de los objetos de tipo Recurso
 * 
 * @author Kevin Castillo, Ma Paula Rodriguez y Raquel Rojas
 * @version 1.0
 */
public class Recurso {
  private String nombre;
  private String detalle;
  
  public Recurso(String pNombre, String pDetalle){
    nombre = pNombre;
    detalle = pDetalle;     
  }
  
  public String getNombre() {
    return nombre;
  }

    
  public void setNombre(String pNombre) {
    this.nombre = pNombre;
  }

    
  public String getDetalle() {
    return detalle;
  }

    
  public void setDetalle(String pDetalle) {
    this.detalle = pDetalle;
  }
  
  
   /**
   * Método para convertir en String toda la información del Recurso
   * 
   * @return  msg, informacion de Recurso
   */
    @Override
    public String toString(){
    String msg = "Nombre: " + getNombre() + "detalle: " + getDetalle();
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
    Recurso recurso = (Recurso) o;
    return recurso.nombre == nombre && recurso.detalle == detalle;
   } 
}
