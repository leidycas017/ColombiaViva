/*
 * Trabajo presentado Por:
 * Leidy Castaño
 * Omar Torres
 */
package Logica.accesodatos;

import Conexion.AdminDatos;
import Modelo.Vpersona;
import Modelo.Vpnatural;
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
public class PersonaDao {
    private AdminDatos mysql;
    private Connection cn;
    private String sSQL="";
    private String sSQL2 = "";
    private DefaultTableModel modelo;
    private String cabecera[] ={"ID Persona", "Nombres", "Direccion","Teléfono","Correo ","Id Actividad","ID PNatural","Numero Documento"};
    
   
    public PersonaDao() throws SQLException{
         iniciarconnection();
    }
    
    /*
     * El metodo instancia la clase conexión que contiene los metodos necesarios para conectarse a la bdd
    */
    private void iniciarconnection() throws SQLException{
         AdminDatos mysql=new AdminDatos();
         cn = mysql.conectar();
        
    }
    
    public DefaultTableModel mostrar(String buscar){
        String registro[] = new String[8];
        modelo = new DefaultTableModel(null, cabecera);
        sSQL = "Select p.idpersona, p.nombre, p.direccion, p.telefono, p.correo, p.idactividad, "
                + " n.idpnatural , n.numdocumento "
                + " FROM persona p  INNER JOIN pnatural n "
                + " On p.idpersona = n.idpnatural Where (p.nombre) like '%"+buscar+"%' order by p.idpersona desc";
                
        try{
           
            Statement st = cn.createStatement();  //Crea variable para poder ejecutar el método executequery
            ResultSet rs = st.executeQuery(sSQL);   //Se ejecuta la sentencia query y retorna el resultado de la consulta
            while(rs.next()){
                registro[0] = rs.getString("p.idpersona");
                registro[1] = rs.getString("p.nombre");
                registro[2] = rs.getString("p.direccion");
                registro[3] = rs.getString("p.telefono");
                registro[4] = rs.getString("p.correo");
                registro[5] = rs.getString("p.idactividad");
                registro[6] = rs.getString("n.idpnatural");
                registro[7] = rs.getString("n.numdocumento");
                modelo.addRow(registro);
            }
         
        }
        
        catch(Exception e){
             System.out.println("Verifica la lógica SQL " + e);
             return null;
        }
        return modelo;
     }
    public boolean insertar(Vpersona dtspersona, Vpnatural dtspnatural){
        sSQL ="INSERT INTO Persona (nombre, direccion, telefono, correo, idactividad)"+
                "VALUES (?,?,?,?,?)";
       
               
        sSQL2 = "INSERT INTO pnatural (idpnatural ,numdocumento)" +
                "values((SELECT idpersona FROM Persona ORDER BY idpersona desc limit 1),?)";
               
        try{
            PreparedStatement pst=cn.prepareStatement(sSQL); //Se prepara la sentencia sql para insertar
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);
            pst.setString(1,dtspersona.getNombre());
            pst.setString(2, dtspersona.getDirección());
            pst.setString(3,dtspersona.getTelefono());
            pst.setString(4, dtspersona.getCorreo());
            pst.setInt(5, dtspersona.getIdactividad());
            
            pst2.setString(1, dtspnatural.getNumdocumento());
            int flag = pst.executeUpdate();
            if(flag != 0){
                int n2 = pst2.executeUpdate();
                if(n2 !=0){
                  return true;
                }
                else return false;
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
    public boolean editar(Vpersona dtspersona, Vpnatural dtspnatural){
        sSQL = "UPDATE Persona set nombre =?, direccion =?, telefono = ?, " +
                " correo=? "+
                "WHERE idpersona = ?";
      
        sSQL2 = "UPDATE pnatural SET numdocumento =? "+
                "WHERE idpnatural =? ";
            
        try{
            PreparedStatement pst=cn.prepareStatement(sSQL); //Se prepara la sentencia sql para insertar
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);
           
            pst.setString(1,dtspersona.getNombre());
            pst.setString(2, dtspersona.getDirección());
            pst.setString(3,dtspersona.getTelefono());
            pst.setString(4, dtspersona.getCorreo());
            pst.setInt(5, dtspersona.getIdpersona());
           
            pst2.setString(1, dtspnatural.getNumdocumento());
            pst2.setDouble(2,dtspnatural.getIdpnatural());
           
           
            int flag = pst.executeUpdate();
        
            if(flag != 0){
                int n2 = pst2.executeUpdate();
                if(n2 !=0){
                    return true;
                }
                else return false;
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
     public DefaultTableModel login(String documento){
        String registro[] = new String[5];
        String cab[] = {"Id","Numero Documento"};
       
        modelo = new DefaultTableModel(null, cab);
        
        sSQL = "SELECT numdocumento "
                + " FROM pnatural "
                + " WHERE numdocumento = "+ documento;
              
        try{
            
            Statement st = cn.createStatement();  //Crea variable para poder ejecutar el método executequery
            ResultSet rs = st.executeQuery(sSQL);   //Se ejecuta la sentencia query y retorna el resultado de la consulta
            while(rs.next()){
                registro[0] = rs.getString("numdocumento");
                //registro[1] = rs.getString("p.nombre");
                //registro[2] = rs.getString("t.acceso");
                //registro[3] = rs.getString("usuario");
               // registro[4] = rs.getString("contrasena");
                
                modelo.addRow(registro);  //Se agrega registro a la tabla
            }
         
        }
        catch(Exception e){
             System.out.println("Verifica la lógica SQL " + e);
             return null;
        }
        return modelo;
    }
     
    public boolean eliminar(Vpersona dts){
        sSQL = "DELETE FROM pnatural WHERE idpnatural=?";
        sSQL2 ="DELETE FROM Persona WHERE idpersona=?";
        try{
            PreparedStatement pst=cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);
            pst.setInt(1, dts.getIdpersona()); 
            pst2.setInt(1,dts.getIdpersona());
            
            int flag = pst.executeUpdate();
            if(flag != 0){
                int n2 = pst2.executeUpdate();
                if(n2 !=0){
                    return true;
                }
                else return false;
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
}
