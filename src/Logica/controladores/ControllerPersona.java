/*
 * Trabajo presentado Por:
 * Leidy Castaño
 * Omar Torres
*/
package Logica.controladores;

//import Logica.accesodatos.PersonaDao;
import Logica.accesodatos.PersonaDao;
import Modelo.Vpersona;
import Modelo.Vpnatural;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class ControllerPersona {
    private  PersonaDao persona;
    private String buscar;
    public ControllerPersona() throws SQLException {
        persona = new PersonaDao();
        buscar = "";
    }
    public boolean insertarController(Vpersona dtspersona, Vpnatural dtspnatural){
          boolean flag = true;
          flag = persona.insertar(dtspersona, dtspnatural);
          return flag;
    } 
    public boolean editarController(Vpersona dtspersona, Vpnatural dtspnatural){
        boolean flag=true;
        flag = persona.editar(dtspersona, dtspnatural);
        return flag;
    }
    public boolean eliminarController(Vpersona dtspersona){
        boolean flag;
        flag = persona.eliminar(dtspersona);
        return flag;
    }
    public DefaultTableModel mostrarController(String buscar){
          DefaultTableModel modelo = new DefaultTableModel();
          modelo = persona.mostrar(buscar);
          return modelo;
    }
    
    public boolean existePersonaController(int numdocumento){
        boolean noexiste = false;
        return  noexiste;
    }
}
