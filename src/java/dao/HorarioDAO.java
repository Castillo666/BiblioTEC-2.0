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
import logicadenegocios.Horario;

/**
 *
 * @author María Paula
 */
public class HorarioDAO {

    
  /**
   * Metodo que retorna todos los horarios disponibles
   * @return ResultSet, con todos los horarios disponibles  
   */  
  public ResultSet getHorarios(){
    ResultSet rs = null;
    try{
      Connection conexion = Conexion.getConexion();
      Statement ejecutor = conexion.createStatement();
      rs = ejecutor.executeQuery("select *from Horario");
    }catch(SQLException e){
      System.out.println(e);  
    }
    return rs;
  }
  
  
  
    /**
   * Metodo para obtener un horario por su id 
   * 
   * @param pId id del horario 
   * @return ResultSet con el horario que corresponde
   */  
  public ResultSet getHorarioID(int pId){
    ResultSet rs = null;
    try{
      Connection conexion = Conexion.getConexion();
      Statement ejecutor = conexion.createStatement();
      rs = ejecutor.executeQuery("select *from Horario where idHorario = " + pId );
    }catch(SQLException e){
      System.out.println(e);  
    }
    return rs;
  }
  
  
    /**
   * Metodo para obtener el horario de una sala en particular
   * 
   * @param pIdSala id de la sala particualr
   * @return ResultSet con el horario que corresponde a la sala
   */  
  public ResultSet getHorarioSala(String pIdSala){
    ResultSet res = null;
    try{
      String idSala = pIdSala;
      Connection conexion = Conexion.getConexion();
      Statement ejecutor = conexion.createStatement();
      res = ejecutor.executeQuery("dbo.getHorarioSala '" + idSala +"'");
    }catch(SQLException e){
      System.out.println(e);
    } return res;
  }
}
 
