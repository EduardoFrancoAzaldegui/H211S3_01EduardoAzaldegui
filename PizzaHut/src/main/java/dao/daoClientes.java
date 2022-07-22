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
 * @author azald
 */
public class daoClientes extends Conexion {
    
    public daoClientes(){}
        
    public void addCliente(Cliente cliente){
        
        conexion = getConnection();
        String query = "INSERT INTO CLIENTE(NOMCLI,CELCLI,CORCLI,DIRCLI,IDUBI) VALUES(?,?,?,?,?)";
        
        try {
            ps = conexion.prepareStatement(query);
            ps.setString(1, cliente.getNOMCLI());
            ps.setString(2, cliente.getCELCLI());
            ps.setString(3, cliente.getCORCLI());
            ps.setString(4, cliente.getDIRCLI());
            ps.setInt(5, 2);
            ps.executeUpdate();
            closeConeccion();
        } catch (SQLException e) {
        }
    }
  
    
    public void editCliente(Cliente cliente){
        
        conexion = getConnection();
        String query = "UPDATE CLIENTE SET NOMCLI=?,CELCLI=?,CORCLI=?,DIRCLI=? WHERE IDCLI=?";
        try {
            System.out.print("Este es el id "+cliente.getIDCLI());
            ps = conexion.prepareStatement(query);
            ps.setString(1, cliente.getNOMCLI());
            ps.setString(2, cliente.getCELCLI());
            ps.setString(3, cliente.getCORCLI());
            ps.setString(4, cliente.getDIRCLI());
            ps.setInt(5, cliente.getIDCLI());
            ps.executeUpdate();
            closeConeccion();
            System.out.println("se edito correctyamente");
        } catch (SQLException e) {
            System.out.println("se edito mal");
            e.printStackTrace();
        }
    }
    
    
    public void eliminarCliente(Cliente cliente){
        
        conexion = getConnection();
        String query = "UPDATE CLIENTE SET ESTCLI=? WHERE IDCLI=?";
        try {
            ps = conexion.prepareStatement(query);
            ps.setString(1, "I");
            ps.setInt(2, cliente.getIDCLI());
            ps.executeUpdate();
            closeConeccion();
            System.out.println("se hizo en update");
        } catch (SQLException e) {
            System.out.println("fallo en update");
            e.printStackTrace();
        }
    }
    
    
    public void restaurarCliente(Cliente cliente) {

        conexion = getConnection();
        String query = "UPDATE CLIENTE SET ESTCLI=? WHERE IDCLI=?";
        try {
            ps = conexion.prepareStatement(query);
            ps.setString(1, "A");
            ps.setInt(2, cliente.getIDCLI());
            ps.executeUpdate();
            closeConeccion();
        } catch (SQLException e) {
        }
    }

    
    public List<Cliente> ObtenerTodo(String cass) {
        List<Cliente> allCliente = new ArrayList<Cliente>();
        
        String query="";
        switch (cass) {
            case "1":
                query = "SELECT * FROM CLIENTE WHERE ESTCLI = 'A'";
                break;
            case "2":
                query = "SELECT * FROM CLIENTE WHERE ESTCLI = 'I'";
                break;
            case "3":
                query = "SELECT * FROM CLIENTE";
        }
        
        rs = consultar(query);

        try {
            do {
                Cliente cliente = new Cliente();
                cliente.setIDCLI(rs.getInt("IDCLI"));
                cliente.setNOMCLI(rs.getString("NOMCLI"));
                cliente.setDIRCLI(rs.getString("DIRCLI"));
                cliente.setCELCLI(rs.getString("CELCLI"));
                cliente.setCORCLI(rs.getString("CORCLI"));
                allCliente.add(cliente);
            } while (rs.next());
        } catch (SQLException e) {
        }
        closeConeccion();
        return allCliente;
    }

    
}
