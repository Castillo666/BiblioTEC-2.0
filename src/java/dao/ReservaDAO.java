/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import logicadenegocios.Participante;
import logicadenegocios.Reserva;
import util.EnviarCorreo;
import util.EnviarSMS;

/**
 *
 * @author María Paula
 */
public class ReservaDAO {
  public static Connection conexion; 
  
  
  /**
   * Metodo para agregar reserva
   * @param reserva reserva para ser insertada en la base de datos
   * @return reserva 
   * @throws SQLException si el procedimiento no se ejecuta con exito
   */
  /*public Reserva agregarReserva(Reserva reserva) throws SQLException{
    if(storeProcedureAgregarReserva(reserva.getEstado(),reserva.getFecha(),reserva.getHoraInicio(), reserva.getHoraFin(),reserva.getCodigoCalificacion(),reserva.getAsunto(),reserva.getOrganizador(),reserva.getIdSala()>0){
      return reserva;    
    }
    else{
      return null;   
    }
  }*/
  
  
  /**
   * Metodo para la consulta de la sala
     * @param pCapacidadMin capacidad minima que se requiere
     * @param pNombreRecurso nombre de un recurso que se require
   * @return salas que cumplen con los filtros
   * @throws SQLException si el procedimiento no se ejecuta con exito
   */
  public ResultSet consultarSalas(int pCapacidadMin,String pNombreRecurso) throws SQLException{
    ResultSet rs = null;
    conexion = Conexion.getConexion();
    if("".equals(pNombreRecurso)){
      CallableStatement cstmt = conexion.prepareCall("{call consultarSalas(?)}");
    cstmt.setInt(1, pCapacidadMin);
    rs = cstmt.executeQuery();
    return rs;  
    } else {
      CallableStatement cstmt = conexion.prepareCall("{call verificarRecursoSala(?,?)}");
      cstmt.setString(1, pNombreRecurso);
      cstmt.setInt(2, pCapacidadMin);
      rs = cstmt.executeQuery();
      return rs;
    }
  }
  
  /**
   * Metodo que ejecuta el procedimiento almacenado para agregar la reserva
   * @param pEstado estado de la reserva, por omision activado
   * @param pFecha  fecha de la reserva
   * @param pHoraInicio hora de inicio de la reserva
   * @param pHoraFin hora de finalizacion de la reserva
   * @param pCodigoCalificacion codigo de calificacion de la reserva
   * @param pAsunto detalle de la reserva
   * @param pOrganizador carnet del organizador de la reserva
   * @param pIdSala numero de identificacion de la sala
   * @return 1 si se inserto correctamente, 0 si no
   * @throws SQLException si el procedimiento no se ejecuta con exito
   */
  public int storeProcedureAgregarReserva(String pEstado ,Date pFecha,String pHoraInicio,
    String pHoraFin,String pCodigoCalificacion,String pAsunto,int pOrganizador,String pIdSala) throws SQLException{
    CallableStatement cstmt = null;
    int rs = 0;
    conexion = Conexion.getConexion();
    cstmt = conexion.prepareCall("{call agregarReserva(?,?,?,?,?,?,?,?)}");
    cstmt.setString(1,pEstado);
    java.sql.Date fecha = new java.sql.Date(pFecha.getTime());
    cstmt.setDate(2,fecha);
    cstmt.setString(3,pHoraInicio);
    cstmt.setString(4,pHoraFin);
    cstmt.setString(5,pCodigoCalificacion);
    cstmt.setString(6,pAsunto);
    cstmt.setInt(7,pOrganizador);
    cstmt.setString(8,pIdSala);
    //se utiliza executeUpdate porque retorna un 1 si se inserto el objeto, 0 en caso contrario
    rs = cstmt.executeUpdate();
    return rs;
  }
  
  /**
   * Metodo para obtener el identificador de la reserva
   * @return idReserva
   * @throws SQLException si el procedimiento no se ejecuta con exito
   */
  public int obtenerIdReserva() throws SQLException{
    ResultSet rs = null;
    int idReserva =0;
    CallableStatement cstmt = null;
    conexion = Conexion.getConexion();
    cstmt = conexion.prepareCall("{call obtenerIdReserva()}");
    rs = cstmt.executeQuery();
    while(rs.next()){
      idReserva = rs.getInt(1);
    }
    return idReserva+1;
  }
  
  
  /**
   * Metodo para obtener las proximas reservas
   * @param pIdentificador identificador de la reserva
   * @return reservas
   * @throws SQLException si el procedimiento no se ejecuta con exito
   */
  public ResultSet getProxReservasSala(String pIdentificador) throws SQLException{
    ResultSet res = null;
    String identificador = pIdentificador;
    Connection conexion = Conexion.getConexion();
    Statement ejecutor = conexion.createStatement();
    res = ejecutor.executeQuery("execute dbo.getProxReservas '" + identificador +"'");
    return res;
  }
  
  
  /**
   * Metodo para consultar las reservas
   * @return reservas
   * @throws SQLException si el procedimiento no se ejecuta con exito
   */
  public ResultSet consultarReservas() throws SQLException{
    ResultSet rs = null;
    conexion = Conexion.getConexion();
    Statement ejecutor = conexion.createStatement();
    rs = ejecutor.executeQuery("{call esquema.consultarReservas}");
    return rs;
  }
  
  
  /**
   * Metodo para cancelar una reserva
   * @param pNumero numero de identificacion de l reserva
   * @return 1 si fue exitoso, 0 en caso contrario
   * @throws SQLException si el procedimiento no se ejecuta con exito
   */ 
  public int cancelarReserva(int pNumero) throws SQLException{
    int rs = 0;
    CallableStatement cstmt = null;        
    conexion = Conexion.getConexion();
    cstmt = conexion.prepareCall("{call cancelarReserva(?)}");
    cstmt.setInt(1, pNumero);
    rs = cstmt.executeUpdate();
    return rs;
  }
  
 
   /**
   * Metodo para verificar el código para calificar una sala y obtener el idSala de la reserva, si existiese. 
   * 
   * @param pCodigo código a validar
   * @return ResultSet con el idSala de la reserva a la que corresponde el codigo
     * @throws java.sql.SQLException si el procedimiento no se ejecuta con exito
   */ 
   public ResultSet verificarCodigo(String pCodigo) throws SQLException{
    ResultSet res = null;
    Connection conexion = Conexion.getConexion();
    Statement ejecutor = conexion.createStatement();
    res = ejecutor.executeQuery("execute codigoUsado '" + pCodigo +"'");
    return res;
  }
  
   
     /**
   * Metodo para utilizar el código al calificar la sala
   * 
   * @param pReserva el número de reserva en donde se utilizará el código
   * @return True si se logra con éxito, False en caso contrario
   */ 
  public boolean usarCodigo(int pReserva){
    boolean res = false;
    try{
      conexion = Conexion.getConexion();
      String query = "Execute usarCodigo @idReserva = ?";
      CallableStatement consulta = conexion.prepareCall(query);
      consulta.setInt(1,pReserva);
      consulta.execute();
      res = true;
    }catch(SQLException e){
      System.out.println(e);
    }return res;
  }

  
   /**
   * Metodo que ejecuta el procedimiento para obtener el correo de los participantes
   * 
   * @param pNumero numero de identificacion de la reserva
   * @param pIdSala numero de identificacion de la sala
     * @throws java.sql.SQLException si el procedimiento no se ejecuta con exito
   */ 
  public void notificarParticipantes(int pNumero,String pIdSala) throws SQLException{
    ResultSet rs = null;
    String msg = "Ha sido cancelada la reserva de la sala: "+pIdSala;
    String asunto = "Notificación de cancelacion de reserva";
    CallableStatement cstmt = null;
    conexion = Conexion.getConexion();
    cstmt = conexion.prepareCall("{call obtenerEmailParticipantes(?)}");
    cstmt.setInt(1, pNumero);
    rs = cstmt.executeQuery();
    while(rs.next()){
      EnviarCorreo.enviarCorreo(rs.getString("email"),asunto,msg);
    }
  }
  
  
  /**
   * Metodo que obtiene el correo del organizador
   * @param pOrganizador numero de carnet del organizador
   * @param pIdSala numero de identificacion de la sala
   * @throws SQLException si el procedimiento no se ejecuta con exito
   */
  public void notificarOrganizador(int pOrganizador,String pIdSala) throws SQLException{
    ResultSet rs = null;
    String msg = "Ha sido cancelada la reserva de la sala: "+pIdSala;
    String asunto = "Notificación de cancelacion de reserva";
    CallableStatement cstmt = null;
    conexion = Conexion.getConexion();
    cstmt = conexion.prepareCall("{call obtenerCorreoEstudiante(?)}");
    cstmt.setInt(1, pOrganizador);
    rs = cstmt.executeQuery();
    while(rs.next()){
      EnviarCorreo.enviarCorreo(rs.getString("email"),asunto,msg);
    }
  }
  
  /**Metodo que consulta las reservas validas
   * @return las reservas que sean despues de la fecha actual
   * @throws SQLException si el procedimiento no se ejecuta con exito 
   */
  public ResultSet consultarReservasValidas() throws SQLException{
    ResultSet rs = null;
    conexion = Conexion.getConexion();
    Statement ejecutor = conexion.createStatement();
    rs = ejecutor.executeQuery("{call comprobarFechaReservas}");
    return rs;
  }
  
  /**Metodo que baja la calificacion del estudiante
   * @param pOrganizador numero de carnet del estudiante
   * @param valor valor para restar en su calificacion
   * @throws SQLException  si el procedimiento no se ejecuta con exito
   */
  public void bajarCalificacionEstudiante(int pOrganizador, int valor) throws SQLException{
    CallableStatement cstmt = null;
    conexion = Conexion.getConexion();
    cstmt = conexion.prepareCall("{call bajarCalificacionEstudiante(?,?)}");
    cstmt.setInt(1,pOrganizador);
    cstmt.setInt(2,valor);
    cstmt.executeUpdate();
  }
  
  /**
   * Metodo que retorna la calificacion de un estudiante
   * @param pOrganizador numero de carnet del estudiante
   * @return
   * @throws SQLException si el procedimiento no se ejecuta con exito
   */
  public int comprobarCalificacionEstudiante(int pOrganizador) throws SQLException{
    ResultSet rs = null;
    int calificacion = 0;
    CallableStatement cstmt = null;        
    conexion = Conexion.getConexion();
    cstmt = conexion.prepareCall("{call obtenerCalificacionEstudiante(?)}");
    cstmt.setInt(1, pOrganizador);
    rs = cstmt.executeQuery();
    while(rs.next()){
      calificacion = rs.getInt("calificacion");
    }
    return calificacion;
  }
  
  /**
   * Metodo para comprobar cantidad de reservas de un estudiante en una semana
   * @param pFecha fecha que desea reservar
   * @param pOrganizador numero de carnet del organizador
   * @return numero de reservas realizadas en una semana
   * @throws SQLException si el procedimiento no se ejecuta con exito
   */
  public int comprobarCantidadReservas(Date pFecha, int pOrganizador) throws SQLException{
    ResultSet rs = null;
    int contador = 0;
    int numSemana = obtenerNumSemana(pFecha);
    CallableStatement cstmt = null;        
    conexion = Conexion.getConexion();
    cstmt = conexion.prepareCall("{call obtenerFechaReserva(?)}");
    cstmt.setInt(1,pOrganizador);
    rs = cstmt.executeQuery();
    while(rs.next()){
      if(obtenerNumSemana(rs.getDate("fecha"))==numSemana){
        contador++;
      }
    }
    return contador;
  }
  
  /**
   * Metodo para obtener el numero de la semana
   * @param pFecha fecha a conocer el numero de semana
   * @return el numero de semana en que esta la fecha
   * @throws SQLException si el procedimiento no se ejecuta con exito
   */
  public int obtenerNumSemana(Date pFecha) throws SQLException{
    ResultSet rs = null;
    int numSemana = 0;
    CallableStatement cstmt = null;        
    conexion = Conexion.getConexion();
    cstmt = conexion.prepareCall("{call obtenerNumSemana(?)}");
    java.sql.Date fecha = new java.sql.Date(pFecha.getTime());
    cstmt.setDate(1,fecha);
    rs = cstmt.executeQuery();
    while(rs.next()){
      numSemana = rs.getInt("numSemana");
    }
    return numSemana;
  }
  
  /**
   * Metodo para comprobar si existe estudiante
   * @param pOrganizador carnet el estudiante
   * @return 1 si el estudiante existe, 0 en caso contrario
   * @throws SQLException si el procedimiento no se ejecuta con exito
   */
  public int existeEstudiante(int pOrganizador) throws SQLException{
    CallableStatement cstmt = null;
    ResultSet rs = null;
    int resultado =0;
    Conexion cn = new Conexion();
    conexion = cn.getConexion();
    cstmt = conexion.prepareCall("{call existeEstudiante(?)}");
    cstmt.setInt(1, pOrganizador);
    cstmt.executeQuery();
    rs = cstmt.getResultSet();
    if(rs.next()){
      resultado = 1;
    } else{
      resultado = 0;
    }
    return resultado;
  }
  
  /**
   * Metodo para obtener el correo de un estudiante
   * @param pOrganizador carnet del estudiante
   * @return correo del estudiante
   * @throws SQLException si el procedimiento no se ejecuta con exito
   */
  public String obtenerCorreoEstudiante(int pOrganizador) throws SQLException{
    String correo = "";
    CallableStatement cstmt = null;
    ResultSet rs = null;
    conexion = Conexion.getConexion();
    cstmt = conexion.prepareCall("{call obtenerCorreoEstudiante(?)}"); 
    cstmt.setInt(1, pOrganizador);
    rs = cstmt.executeQuery();
    while(rs.next()){
      correo = rs.getString(1);
    }
    return correo;
  }
  
  /**
   * Metodo para enviar correo a estudiante organizador sobre reserva de sala
   * @param pCorreo correo del estudiante
   * @param pIdSala numero de identificacion de la sala
   * @param pHoraInicio hora de inicio
   * @param pHoraFin hora de finalizacion
   * @param pFecha  fecha
   */
  public void notificarOrganizador(String pCorreo,String pIdSala, String pHoraInicio, String pHoraFin,Date pFecha){
    String msg = "Identificador de la sala: " + pIdSala + "\nFecha: " +pFecha.toString()+"\nHora de inicio: "+pHoraInicio+"\nHora de finalización: "+pHoraFin;
      EnviarCorreo.enviarCorreo(pCorreo,"BiblioTEC - Invitación a reserva de sala",msg);
  }
  
  /**
   * Metodo para enviar un SMS a estudiante organizador sobre reserva de sala
   * @param pTelefono telefono del estudiante
   * @param pMensaje mensaje a enviar al estudiante
   */
  public void notificarOrganizadorSMS(String pTelefono,String pMensaje){
    //EnviarSMS.enviarSMS(pTelefono, pMensaje);
  }
  
  /**
   * Metodo para obtener telefono de un estudiante 
   * @param pOrganizador numero de carnet del estudiante
   * @return telefono del estudiante
   * @throws SQLException si el procedimiento no tuvo exito
   */
  public String obtenerTelefonoEstudiante(int pOrganizador) throws SQLException{
    String telefono = "";
    CallableStatement cstmt = null;
    ResultSet rs = null;
    conexion = Conexion.getConexion();
    cstmt = conexion.prepareCall("{call obtenerTelefonoEstudiante(?)}"); 
    cstmt.setInt(1, pOrganizador);
    rs = cstmt.executeQuery();
    while(rs.next()){
      telefono = rs.getString(1);
    }
    return telefono;
  }
  
  
  
}  
