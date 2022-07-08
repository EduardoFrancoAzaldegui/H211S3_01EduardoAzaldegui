/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Producto;

/**
 *
 * @author PC-EDUARDO
 */
public class daoProducto extends Conexion implements ICRUD<Producto>{

    @Override
    public void add(Producto item) {
    }

    @Override
    public void edit(Producto item) {
    }

    @Override
    public void delete(Producto item) {
    }

    @Override
    public List<Producto> getAll() {
        
        List<Producto> allProductos = new ArrayList<Producto>();
        String query = "SELECT * FROM PRODUCTO "
                        + " WHERE ESTPRO='A' ";

      
        rs = consultar(query);
        try {
            do {
                Producto producto = new Producto();
                producto.setIDPRO(rs.getInt("IDPRO"));
                producto.setNOMPRO(rs.getString("NOMPRO"));
                producto.setCANPRO(rs.getInt("CANPRO"));
                producto.setPREPRO(rs.getDouble("PREPRO"));
                allProductos.add(producto);
            } while (rs.next());
        } catch (SQLException e) {
            System.out.println("Ya se rellenaron todo los datos");
        }
        
        closeConeccion();
        return allProductos;
    }
    
}
