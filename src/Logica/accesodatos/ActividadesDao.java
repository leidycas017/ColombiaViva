/*
 * Trabajo presentado Por:
 * Leidy Castaño
 * Omar Torres
 */
package Logica.accesodatos;

import Conexion.AdminDatos;
import Modelo.Vactividades;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class ActividadesDao {
    private AdminDatos mysql;
    private Connection cn;
    private String sSQL="";
    private DefaultTableModel modelo;
    private String cabecera[] ={"ID Actividad", "Descripción"};
    
   
    public ActividadesDao() throws SQLException{
         iniciarconnection();
    }
    private void iniciarconnection() throws SQLException{
         AdminDatos mysql=new AdminDatos();
         cn = mysql.conectar();
        
    }
    public boolean insertar(Vactividades dts){
        sSQL ="INSERT INTO actividad (descripcion)"+
                "VALUES (?)";
        try{
            PreparedStatement pst=cn.prepareStatement(sSQL); //Se prepara la sentencia sql para insertar
            pst.setString(1,dts.getDescripcion());
            int flag = pst.executeUpdate();
            if(flag != 0){
              return true;
            }
            else{
                return false;
            }
        }
        catch(Exception e){
            System.out.println("Verifica la lógica SQL " + e);
            return true;
        }
    }
    public boolean eliminar(Vactividades dts){
        sSQL = "DELETE FROM actividad WHERE idactividad = ?";
        try{
            PreparedStatement pst=cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getIdactividad()); 
            int flag = pst.executeUpdate();
            if(flag != 0){
              
                    return true;
            }        
            else{
                return false;
            }
        }
        catch(Exception e){
            System.out.println("Verifica la lógica SQL " + e); 
            return true;
        }
    }
    public boolean editar(Vactividades dts){
        sSQL = "UPDATE actividad set descripcion =? " +
               "WHERE idactividad = ?";
        try{
            PreparedStatement pst=cn.prepareStatement(sSQL); //Se prepara la sentencia sql para insertar
            pst.setString(1,dts.getDescripcion());
            pst.setInt(2, dts.getIdactividad());
                      
            int flag = pst.executeUpdate();
        
            if(flag != 0){
                return true;
            }
            else{
                return false;
            }
        }
        catch(Exception e){
            System.out.println("Verifica la lógica SQL " + e);
            return true;
        }
    }
    public DefaultTableModel mostrar(String buscar){
        String registro[] = new String[2];
        modelo = new DefaultTableModel(null, cabecera);
        sSQL = "SELECT idactividad, descripcion "
                + " FROM actividad "
                + " WHERE descripcion like '%"+buscar+"%' order by descripcion desc";
                
        try{
           
            Statement st = cn.createStatement();  //Crea variable para poder ejecutar el método executequery
            ResultSet rs = st.executeQuery(sSQL);   //Se ejecuta la sentencia query y retorna el resultado de la consulta
            while(rs.next()){
                registro[0] = rs.getString("idactividad");
                registro[1] = rs.getString("descripcion");
                modelo.addRow(registro);
            }
         
        }
        
        catch(Exception e){
             System.out.println("Verifica la lógica SQL " + e);
             return null;
        }
        return modelo;
     }
         
}
