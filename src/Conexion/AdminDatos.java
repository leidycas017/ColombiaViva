/*
 * La clase conexio, contiene los metodos para conectarnos a la base de datod
 * Ventas
 */
package Conexion;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class AdminDatos {
    public Connection con=null;
    public String usuario = "root";
    public String clave = "";
    public String url = "jdbc:mysql://localhost:3306/sistemavivacolombia"; //Nombre de la base de datos
    public AdminDatos(){}
    
    public Connection conectar() throws SQLException{
        Connection link =null;
        try{
              Class.forName("com.mysql.cj.jdbc.Driver"); //Carga el driver de conexión
              link = DriverManager.getConnection(this.url, this.usuario, this.clave);//Link hacia la base de datos
              
        }
        catch(ClassNotFoundException e){
           JOptionPane.showConfirmDialog(null,e);
        }
        return link;
    }
}
    
