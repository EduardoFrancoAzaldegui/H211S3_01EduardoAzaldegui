package dao;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class Conexion {

    public Connection conexion = null;
    public PreparedStatement ps = null;
    public ResultSet rs = null;

    private static final String config_user = "src/main/java/properties/db.properties";
    String db;
    String user;
    String password;
    String url;
    int port;

    public void fillProperties() {
        db = "PizzaHut";
        user = "sa";
        password = "123456789";
        url = "localhost";
    }

    public Connection getConnection() {
        try {
            fillProperties();
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            SQLServerDataSource ds = new SQLServerDataSource();
            ds.setUser(user);
            ds.setPassword(password);
            ds.setServerName(url);
            ds.setDatabaseName(db);
            
            conexion=ds.getConnection();
        } catch (SQLServerException | ClassNotFoundException error) {
            System.out.println("Error abrir: " + error.getMessage());
        }
        return conexion;
    }

    public ResultSet consultar(String query) {


        conexion = getConnection();
        rs = null;

        
        try {
            ps = conexion.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
            } else {
               // System.out.println("src\\dao\\Conexion.java -->consultar() Esta vacio este campo");
            }

        } catch (Exception e) {
            rs = null;
            System.out.println("Error " + e);
        }

        return rs;
    }

    
    public void closeConeccion() {
        try {
            rs.close();
        } catch (Exception e) {
            /* Ignored */ }
        try {
            ps.close();
        } catch (Exception e) {/* Ignored */ }
        try {
            conexion.close();
        } catch (Exception e) {/* Ignored */ }
    }

    
}
