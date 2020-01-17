package logicadenegocios;
import java.text.DateFormat;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.Calendar; 

/**
 *
 * @author María Paula
 */
public class HorarioExcepcion {
  private String horaInicio;
  private String horaFin;
  private Date fecha;
  private String descripcion;

  public HorarioExcepcion(String pHoraInicio, String pHoraFin, Date pFecha, String pDescripcion) {
    this.horaInicio = pHoraInicio;
    this.horaFin = pHoraFin;
    this.fecha = pFecha;
    this.descripcion = pDescripcion;
  }

  
  public String getHoraInicio() {
    return horaInicio;
  }

  
  public String getHoraFin() {
    return horaFin;
  }

  
  public Date getFecha() {
    return fecha;
  }

  
  public String getDescripcion() {
    return descripcion;
  }
  
  
   /**
   * Método para convertir en String toda la información del Horario Excepcion
   * 
   * @return msg, con todos los datos del Horario Excepcion
   */
  @Override
  public String toString(){
    String msg;
    msg = "Hora de Inicio: " + getHoraInicio() + "Hora de Cierre: " + getHoraFin()
            +"Descripcion: " + getDescripcion();
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
    HorarioExcepcion horario = (HorarioExcepcion) o;
    return fecha == horario.getFecha() && horaInicio == horario.getHoraInicio() && horaFin == horario.getHoraFin();
    }
  
}
