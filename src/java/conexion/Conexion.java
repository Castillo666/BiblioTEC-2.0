package conexion;
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException; 
import javax.swing.JOptionPane;

/**
 *
 * @author Maria Paula
 */
public class Conexion {
    
   /**
   * @return conexion 
   */
  public static Connection getConexion(){
    Connection contacto = null;
    String url = "jdbc:sqlserver://localhost\\MSSQLSERVER01:1433;databaseName=BiblioTEC";
    try {
      Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");   
    } catch (ClassNotFoundException e){
      System.out.println( "No se pudo establecer la conexión" + e.getMessage()+
      "Error de Conexion" + JOptionPane.ERROR_MESSAGE);
    }
    try {
      contacto = DriverManager.getConnection(url,"sa","sa");
    } catch (SQLException e){
      System.out.println( "Erro" + e.getMessage()+
      "Error de Conexion"+JOptionPane.ERROR_MESSAGE);
    }
      return contacto;
    }    
}
