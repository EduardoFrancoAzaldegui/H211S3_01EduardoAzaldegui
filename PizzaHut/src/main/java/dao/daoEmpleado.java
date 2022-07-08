/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Empleado;

/**
 *
 * @author PC-EDUARDO
 */
public class daoEmpleado extends Conexion implements ICRUD<Empleado>{

    @Override
    public void add(Empleado item) {
    }

    @Override
    public void edit(Empleado item) {
    }

    @Override
    public void delete(Empleado item) {
    }

    @Override
    public List<Empleado> getAll() {
        List<Empleado> allEmpleado = new ArrayList<Empleado>();
        String query = "SELECT * FROM EMPLEADO AS EM "
                        +" WHERE ESTEMP='A' ";
        
        rs = consultar(query);
        try {
            do {
                Empleado empleado = new Empleado();
                empleado.setIDEMP(rs.getInt("IDEMP"));
                empleado.setNOMEMP(rs.getString("NOMEMP"));
                empleado.setCELEMP(rs.getString("CELEMP"));
                empleado.setCOREMP(rs.getString("COREMP"));
                empleado.setIDCAR(rs.getInt("IDCAR"));
                allEmpleado.add(empleado);
            } while (rs.next());
        } catch (SQLException e) {
            System.out.println("Ya se rellenaron todo los datos");
        }
        closeConeccion();
        return allEmpleado;
    }
    
}
