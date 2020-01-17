/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.Conexion;
import static dao.EstudianteDAO.conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author María Paula, Raquel Rojas y Kevin Castillo
 * 
 */
public class GraficoDAO {

  
  /**
   * Metodo para obtener el top 5 de salas mas utilizadas
   * @return salas
   */
  public ResultSet getSalasUtilizadas(){
    ResultSet rs = null;
      try {
        conexion = Conexion.getConexion();
        Statement ejecutor;
        ejecutor = conexion.createStatement();
        rs = ejecutor.executeQuery("select idSala,count(*) as contador from Reserva group by idSala order by contador desc");
      } catch (SQLException ex) {
          Logger.getLogger(GraficoDAO.class.getName()).log(Level.SEVERE, null, ex);
      }
      System.out.println(rs);
      return rs;
    }
  
  
  /**
   * Metodo para obtener los horarios mas utilizados
   * @return horarios mas utilizados en reservas
   */
  public ResultSet getHorariosUtilizados(){
    ResultSet rs = null;
    try {
        conexion = Conexion.getConexion();
        Statement ejecutor;
        ejecutor = conexion.createStatement();
        rs = ejecutor.executeQuery("select top 5 .SalaHorario.idHorario, count(*) as contador from esquema.SalaHorario, esquema.Horario where esquema.SalaHorario.idHorario = esquema.Horario.idHorario group by esquema.SalaHorario.idHorario order by contador desc");
      } catch (SQLException ex) {
          Logger.getLogger(GraficoDAO.class.getName()).log(Level.SEVERE, null, ex);
      }
      System.out.println(rs);
      return rs;
  
  }
  
  
  /**
   * Metodo para obtener top 5 de las carreras de los estudiantes que mas reservan
   * @return top 5 carreras que mas reservan salas
   */
  public ResultSet getCarreras(){
    ResultSet rs = null;
    try {
        conexion = Conexion.getConexion();
        Statement ejecutor;
        ejecutor = conexion.createStatement();
        rs = ejecutor.executeQuery("select top 5 Estudiante.carrera, count(*) as contador from esquema.Reserva, esquema.Estudiante, esquema.Sala where esquema.Reserva.organizador = esquema.Estudiante.carnet and esquema.Sala.identificador = esquema.reserva.idSala group by carrera order by contador desc");
      } catch (SQLException ex) {
          Logger.getLogger(GraficoDAO.class.getName()).log(Level.SEVERE, null, ex);
      }
      System.out.println(rs);
      return rs;
  }
  
  
 /**
  * Metodo para obtener el top 5
  * @return 5 salas mejor calificadas
  */ 
  public ResultSet getCalificaciones(){
    ResultSet rs = null;
    try {
        conexion = Conexion.getConexion();
        Statement ejecutor;
        ejecutor = conexion.createStatement();
        rs = ejecutor.executeQuery("select top 5 Sala.identificador, esquema.Sala.calificacion from esquema.Sala order by calificacion desc");
      } catch (SQLException ex) {
          Logger.getLogger(GraficoDAO.class.getName()).log(Level.SEVERE, null, ex);
      }
      System.out.println(rs);
      return rs;
  }
}
  
