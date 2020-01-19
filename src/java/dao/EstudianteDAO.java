package dao;

import conexion.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import logicadenegocios.Estudiante;

/**
 *
 * @author María Paula
 */
public class EstudianteDAO {
    
  public static Connection conexion; 
  
  
  /**
   * Metodo donde se llama el procedimiento para agregar el estudiante
   * @param estudiante objeto de tipo estudiante para almacenar 
   * @return estudiante
   * @throws SQLException 
   */
  public boolean agregarEstudiante(Estudiante estudiante) throws SQLException{
    if(storeProcedureAgregarEstudiante(estudiante.getCarnet(),estudiante.getNombreCompleto(),estudiante.getCarrera(),
      estudiante.getEmail(),estudiante.getCalificacion(),estudiante.getTelefono())>0){
        
      return true;
    } else{
      return false;
    }
  }
  
  
/**
 * Metodo que ejecuta el procedimiento almacenado de agregar estudiante
 * @param pCarnet numero de carnet del estudiante
 * @param pNombreCompleto nombre del estudiante
 * @param pCarrera carrera del estudiante
 * @param pEmail correo electronico del estudiante
 * @param pCalificacion calificacion, por omision en 100
 * @param pTelefono telefono del estudiante
 * @return 1 si se inserto el estudiante y 0 si no se inserto
 * @throws SQLException si el procedimiento no se ejecuto
 */
  public int storeProcedureAgregarEstudiante(int pCarnet,String pNombreCompleto,String pCarrera,
    String pEmail, int pCalificacion ,String pTelefono) throws SQLException{
      
    CallableStatement cstmt = null;
    int rs = 0;
    conexion = Conexion.conectarMySQL();
    /*String  query = " execute dbo.createSala @identificador = ? ,"
        + "@ubicacion = ? ,@capacidadMax = ? ,@estado = ?,@calificacion = ?";
    CallableStatement consulta = conexion.prepareCall(query);
    consulta.setInt(1, "pCarnet");
    consulta.setString(2,"pNombreCompleto");
    consulta.setString(3,pCarrera);
    consulta.setString(4,"pEmail");
    consulta.execute();*/
    cstmt = conexion.prepareCall("{call agregarEstudiante(?,?,?,?,?)}");
    cstmt.setInt(1,pCarnet);
    cstmt.setString(2,pNombreCompleto);
    cstmt.setString(3,pCarrera);
    cstmt.setString(4,pEmail);
    cstmt.setString(5,pTelefono);
    //se utiliza executeUpdate porque retorna un 1 si se inserto el objeto, 0 en caso contrario
    rs = cstmt.executeUpdate();
    return rs;   
  }
 
  
  /**
   * Metodo que ejecuta el procedimiento de consultar estudiante
   * @param pCarnet carnet del estudiante
   * @return el estudiante
   */
  public ResultSet storeProcedureConsultarEstudiante(int pCarnet){
    ResultSet rs = null;
    try{
      conexion = Conexion.conectarMySQL();
      Statement ejecutor = conexion.createStatement();
      rs = ejecutor.executeQuery("{call dbo.consultarEstudiante(pCarnet)}");
    }catch(SQLException e){
      System.out.println(e);  
    }
    return rs;
  }
  
  
  /**
   * Metodo para obtener las reservas de un estudiante
   * @param pCarnet carnet del estudiante
   * @return las reservas
   */
  public ResultSet getReservasEstudiante(int pCarnet){
    ResultSet rs = null;
    try{
      conexion = Conexion.conectarMySQL();
      Statement ejecutor = conexion.createStatement();
      rs = ejecutor.executeQuery("select * from esquema.Reserva where organizador = " + pCarnet );
    }catch(SQLException e){
      System.out.println(e);  
    }
    return rs;
  }
  

/**
 * Metodo que obtiene los incidentes segun la reserva
 * @param pIdReserva numero de identifiacion que posee la reserva
 * @return los incidentes
 */  
  public ResultSet getIncidentesReserva(int pIdReserva){
    ResultSet rs = null;
    try{
      conexion = Conexion.conectarMySQL();
      Statement ejecutor = conexion.createStatement();
      rs = ejecutor.executeQuery("select numIncidente,detalle,valor,fecha from esquema.Incidente, esquema.IncidenteReserva where idIncidente = numIncidente and idReserva = " + pIdReserva );
    }catch(SQLException e){
      System.out.println(e);  
    }
    return rs;
  }
  
  
  }

