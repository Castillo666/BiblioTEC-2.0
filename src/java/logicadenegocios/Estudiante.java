package logicadenegocios;

/**
 * Clase Estudiante
 * @author Maria Paula 
 */
public class Estudiante {
  private String nombreCompleto;
  private int carnet;
  private String carrera;
  private String email;
  private int calificacion = 100;
  private String telefono;

  
  /**
   * Constructor
   */
  public Estudiante() {}

  /**
   * Constructor
   * @param pNombreCompleto nombre del estudiante
   * @param pCarnet numero de carnet del estudiante
   * @param pCarrera carrera que cursa el estudiante
   * @param pEmail correo del estudiante
   * @param pTelefono telefono del estudiante
   */
  public Estudiante(int pCarnet,String pNombreCompleto, String pCarrera, String pEmail, String pTelefono) {
    this.nombreCompleto = pNombreCompleto;
    this.carnet = pCarnet;
    this.carrera = pCarrera;
    this.email = pEmail;
    this.telefono = pTelefono;
  }

  
  public int getCalificacion() {
    return calificacion;
  }

  
  public String getNombreCompleto() {
    return nombreCompleto;
  }

  public int getCarnet() {
    return carnet;
  }

  
  public String getCarrera() {
    return carrera;
  }

  
  public String getEmail() {
    return email;
  }

  
  public String getTelefono() {
    return telefono;
  }
  
     /**
   * Método para convertir en String toda la información del Estudiante
   * 
   * @return msg, con todos los datos del Estudiante
   */
  @Override
  public String toString(){
    String carnetS = Integer.toString(carnet);
    String calificacionS = Integer.toString(calificacion);
    String msg = "Nombre: " + getNombreCompleto() + "carnet: " + carnetS
            + "Calificacion: " + calificacionS + "Correo: " + getEmail()
            + "Telefono: " + getTelefono();
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
    Estudiante estudiante = (Estudiante) o;
    return carnet == estudiante.getCarnet();
    } 
  
}
