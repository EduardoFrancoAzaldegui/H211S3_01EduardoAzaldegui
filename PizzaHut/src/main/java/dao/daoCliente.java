/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;

/**
 *
 * @author PC-EDUARDO
 */
public class daoCliente extends Conexion implements ICRUD <Cliente>{

    @Override
    public void add(Cliente item) {
    }

    @Override
    public void edit(Cliente item) {
    }

    @Override
    public void delete(Cliente item) {
    }

    @Override
    public List<Cliente> getAll() {
        List<Cliente> allCliente = new ArrayList<Cliente>();
        
        String query = "SELECT * FROM CLIENTE WHERE ESTCLI = 'A'";

        rs = consultar(query);

        try {
            do {
                Cliente cliente = new Cliente();
                cliente.setIDCLI(rs.getInt("IDCLI"));
                cliente.setNOMCLI(rs.getString("NOMCLI"));
                cliente.setDIRCLI(rs.getString("DIRCLI"));
                cliente.setCELCLI(rs.getString("CELCLI"));
                allCliente.add(cliente);
            } while (rs.next());
        } catch (SQLException e) {
        }
        closeConeccion();
        return allCliente;
    }

   
}
