/*
 * Trabajo presentado Por:
 * Leidy Castaño
 * Omar Torres
 */
package Logica.controladores;

import Logica.accesodatos.ActividadesDao;
import Modelo.Vactividades;
import Modelo.Vpersona;
import Modelo.Vpnatural;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class ControllerActividades {
    private  ActividadesDao actividad;
    private String buscar;
    public ControllerActividades() throws SQLException {
        actividad = new ActividadesDao();
        buscar = "";
    }
    public boolean insertarController(Vactividades dts){
          boolean flag = true;
          flag = actividad.insertar(dts);
          return flag;
    } 
    public boolean editarController(Vactividades dts){
        boolean flag=true;
        flag = actividad.editar(dts);
        return flag;
    }
    public boolean eliminarController(Vactividades dts){
        boolean flag;
        flag = actividad.eliminar(dts);
        return flag;
    }
    public DefaultTableModel mostrarController(String buscar){
          DefaultTableModel modelo = new DefaultTableModel();
          modelo = actividad.mostrar(buscar);
          return modelo;
    }
    
    public boolean existeController(int numdocumento){
        boolean noexiste = false;
        return  noexiste;
    }
}
