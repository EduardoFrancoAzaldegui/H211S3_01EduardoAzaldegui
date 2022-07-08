/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Venta;

/**
 *
 * @author PC-EDUARDO
 */
public class daoVenta extends Conexion implements ICRUD<Venta>{

    @Override
    public void add(Venta venta) {
        
                conexion = getConnection();
        String query = "INSERT INTO CARRITO_VENTA(CANTFACT,IDEMP,IDPRO,IDCLI,IDMETPAG) VALUES(?,?,?,?,?)";
        try {
            ps = conexion.prepareStatement(query);
            //ps.setInt(1, venta.getCANTFACT());
            ps.setInt(2, 1);
            //ps.setInt(3, venta.getIDPRO());
            ps.setInt(4, venta.getIDCLI());
            ps.setInt(5, 1);
            System.out.println("Este es el idcliente "+venta.getIDCLI());
            ps.executeUpdate();
            closeConeccion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConeccion();
    }

    @Override
    public void edit(Venta item) {
    }

    @Override
    public void delete(Venta item) {
    }

    
    @Override
    public List<Venta> getAll() {
        
        List<Venta> allBoleta = new ArrayList<Venta>();
        String query = "SELECT * FROM VistaVentas";
        rs = consultar(query);

        try {
            do {
                Venta venta = new Venta();

                //TABLA TEMPORAL
                //venta.setNROBOLDET(rs.getInt("NROBOLDET"));
                //venta.setFECBOLDET(rs.getDate("FECBOLDET"));
                //venta.setNOMEMP(rs.getString("NOMEMP"));
                //venta.setNOMCLI(rs.getString("NOMCLI"));
                //venta.setTOTAL(rs.getDouble("TOTAL"));
                allBoleta.add(venta);

                //DATOS DE VISTA
            } while (rs.next());
        } catch (SQLException e) {
            System.out.println("Ya se rellenaron todo los datos");
        } finally {
            //allBoleta = this.agregarVista(allBoleta);
            //return allBoleta;
        }
        
        return null;
    }
    
    
}
