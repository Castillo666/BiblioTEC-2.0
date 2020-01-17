package dao;
import conexion.Conexion; 
import java.sql.CallableStatement; 
import java.sql.Connection; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import logicadenegocios.Usuario;

/**
 *
 * @author Maria Paula Rodriguez y Kevin Cstillo
 */
public class UsuarioDAO {
  public static Connection conexion;
  
  /**
   * Metodo donde se comprueba el usuario
   * @param usuario usuario que quiere iniciar sesion
   * @return usuario 
   * @throws SQLException si el procedimiento no se realizo con exito 
   */
  /*
  public Usuario iniciarSesion(Usuario usuario) throws SQLException{
    if(comprobarContraseña(usuario.getNombreUsuario(),usuario.getContraseña())==1){
      return usuario;
    } else{
      return null;
    }
  }*/
  
  
  /**
   * Metodo donde se comprueba la contraseña
   * @param pUsuario nombre de usuario
   * @param pContraseña contraseña del usuario
   * @return 1 si funciona, 0 si no
   * @throws SQLException si el procedimiento no se realizo con exito 
   */
  public int comprobarContraseña(String pUsuario, String pContraseña) throws SQLException{
    int resultado = 2;
    CallableStatement cstmt = null;
    ResultSet rs = null;
    Conexion cn = new Conexion();
    conexion = cn.getConexion();
    cstmt = conexion.prepareCall("{call dbo.consultarUsuario(?,?)}");
    cstmt.setString(1, pUsuario);
    cstmt.setString(2, pContraseña);
    cstmt.executeQuery();
    rs = cstmt.getResultSet();
    //si rs.next funciona quiere decir que el valor si coincide 
    if(rs.next()){
      resultado = 1;
    } else{
      resultado = 0;
    }
    return resultado;
  }
  
}
