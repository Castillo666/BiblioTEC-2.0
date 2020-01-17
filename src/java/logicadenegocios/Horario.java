package logicadenegocios;

/**
 *
 * @author María Paula
 */
public class Horario {
  private int idHorario;
  private String horaInicio;
  private String horaFin;
  private String dias;

  public Horario(int pIdHorario,String pHoraInicio, String pHoraFin, String pDias) {
    this.idHorario = pIdHorario;
    this.horaInicio = pHoraInicio;
    this.horaFin = pHoraFin;
    this.dias = pDias;
  }

    
  public String getHoraInicio() {
    return horaInicio;
  }

    
  public String getHoraFin() {
    return horaFin;
  }

    
  public String getDias() {
    return dias;
  }
  
  
  public int getIdHorario() {
    return idHorario;
  }

  public void setIdHorario(int idHorario) {
    this.idHorario = idHorario;
  }
  
  
   /**
   * Método para convertir en String toda la información del Horario
   * 
   * @return msg, con todos los datos del Horario
   */
  @Override
  public String toString(){
    String idHorarioS = Integer.toString(idHorario);
    String msg = "Id del Horario: " + idHorarioS + " hora de Apertura: " + horaInicio
            + "Hora de cierre: " + horaFin + "Dias hábiles: " + dias;
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
    Horario horario = (Horario) o;
    return idHorario == horario.getIdHorario();
    }  
}
