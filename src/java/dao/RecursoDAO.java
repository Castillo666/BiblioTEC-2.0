/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author María Paula
 */
public class RecursoDAO {
  
  
    /**
   * Metodo que retorna todos los recursos disponibles
   * @return ResultSet, con todos los recusos disponibles  
   */  
  public ResultSet getRecursos(){
    ResultSet rs = null;
    try{
      Connection conexion = Conexion.getConexion();
      Statement ejecutor = conexion.createStatement();
      rs = ejecutor.executeQuery("select *from Recurso");
    }catch(SQLException e){
      System.out.println(e);  
    }
    return rs;
  }
  
  
   /**
   * Metodo para obtener los datos de un recurso por su nombre
   * 
   * @param pNombre nombre del recurso a consultar
   * @return ResultSet con los datos del recurso 
   */ 
  public ResultSet getRecursoID(String pNombre){
    ResultSet rs = null;
    try{
      Connection conexion = Conexion.getConexion();
      Statement ejecutor = conexion.createStatement();
      rs = ejecutor.executeQuery("select *from Recurso where nombre = " + pNombre );
    }catch(SQLException e){
      System.out.println(e);  
    }
    return rs;
  }
}
   
