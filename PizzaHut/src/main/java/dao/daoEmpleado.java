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
    
    

    
    public List<Empleado> getAll(String cass,String id) {
        List<Empleado> allEmpleado = new ArrayList<Empleado>();
        String query="";
        
        switch (cass) {
            case "Vendedor":
                query = "SELECT * FROM Vendedores WHERE IDSUC="+id;
                break;
            case "Despachador":
                query = "SELECT * FROM Despachador WHERE IDSUC="+id;
                break;
        }
        
        rs = consultar(query);
        try {
            do {
                Empleado empleado = new Empleado();
                empleado.setIDEMP(rs.getInt("IDEMP"));
                empleado.setNOMEMP(rs.getString("NOMEMP"));
                allEmpleado.add(empleado);
            } while (rs.next());
        } catch (SQLException e) {
            //System.out.println("Ya se rellenaron todo los datos en Empleado");
        }
        closeConeccion();
        return allEmpleado;
    }

    
    @Override
    public List<Empleado> getAll() {
        return null;
    }
    
    
    
}
