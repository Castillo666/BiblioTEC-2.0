package logicadenegocios;
import java.util.Date;

/**
 *
 * @author María Paula
 */
public class Incidente {
  private int idIncidente;
  private String detalle;
  private int valor;
  private Date fecha;

  public Incidente(String pDetalle, int pValor, Date pFecha) {
    this.detalle = pDetalle;
    this.valor = pValor;
    this.fecha = pFecha;
  }

  public Incidente() {}
  
  public String getDetalle() {
    return detalle;
  }

  public int getValor() {
    return valor;
  }

  public Date getFecha() {
    return fecha;
  }

  public int getIdIncidente() {
      return idIncidente;
  }
  
    
   /**
   * Método para convertir en String toda la información del Incidente
   * 
   * @return msg, con todos los datos del Incidente
   */
  @Override
  public String toString(){
    String msg;
    String incidentS = Integer.toString(idIncidente);
    String valorS = Integer.toString(valor);
    msg = "Incidente = " + incidentS + " valor: " + valorS + "Detalle: " + detalle;
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
    Incidente incidente = (Incidente) o;
    return incidente.idIncidente == idIncidente;
  } 
}
