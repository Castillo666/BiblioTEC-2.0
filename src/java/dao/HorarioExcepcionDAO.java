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
import java.util.Date;

/**
 *
 * @author María Paula
 */
public class HorarioExcepcionDAO {
    
    
    /**
   * Metodo que retorna todos los horarios de excepción 
   * @return ResultSet, con todos los horarios   
   */  
    public ResultSet getHorarios(){
    ResultSet rs = null;
    try{
      Connection conexion = Conexion.getConexion();
      Statement ejecutor = conexion.createStatement();
      rs = ejecutor.executeQuery("select *from esquema.HorarioExcepcion");
    }catch(SQLException e){
      System.out.println(e);  
    }
    return rs;
  }
}
