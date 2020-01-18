package conexion;

import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException; 

/**
 *
 * @author Maria Paula
 */
public class Conexion{

    // Librería de MySQL
    public static String driver = "com.mysql.jdbc.Driver";

    // Nombre de la base de datos
    public static String database = "BiblioTEC";

    // Host
    public static String hostname = "localhost";

    // Puerto
    public static String port = "3306";

    // Ruta de nuestra base de datos (desactivamos el uso de SSL con "?useSSL=false")
    public static String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useSSL=false";

    // Nombre de usuario
    public static String username = "root";

    // Clave de usuario
    public static String password = "pass123";

    public static Connection conectarMySQL() {
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

}
