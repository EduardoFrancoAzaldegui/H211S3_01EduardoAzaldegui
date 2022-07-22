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
        String query = "INSERT INTO CARRITO_VENTA(CANTVEDET,IDSUC,IDPRO,IDCLI,IDEMVEN,IDEMDES) VALUES(?,?,?,?,?,?)";
        try {
            ps = conexion.prepareStatement(query);
            ps.setInt(1, venta.getCANTVEDET());
            ps.setInt(2, venta.getIDSUC());
            ps.setInt(3, venta.getIDPRO());
            ps.setInt(4, venta.getIDCLI());
            ps.setInt(5, venta.getIDEMVEN());
            ps.setInt(6, venta.getIDEMDES());
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
        conexion = getConnection();
        String query = "UPDATE CARRITO_VENTA SET CANTFACT=?,IDEMP=?,IDPRO=?,IDCLI=?,IDEMVEN=?,IDEMDES=? WHERE IDCARVEN=?";
        try {
            ps = conexion.prepareStatement(query);
            ps.setInt(1, item.getCANTVEDET());
            ps.setInt(2, item.getIDEMP());
            ps.setInt(3, item.getIDPRO());
            ps.setInt(4, item.getIDCLI());
            ps.setInt(5, item.getIDEMVEN());
            ps.setInt(6, item.getIDEMDES());
            ps.setInt(7, item.getIDCARVEN());
            ps.executeUpdate();
            closeConeccion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConeccion();
    }

    
    @Override
    public void delete(Venta item) {
    }

    
    @Override
    public List<Venta> getAll() {
        List<Venta> allBoleta = new ArrayList<Venta>();
        allBoleta = this.agregarVista(allBoleta);
        return allBoleta;
    }
    
    
      
    //vista de los item agregados que va a vender  

    public List<Venta> agregarVista(List<Venta> vistaVenta) {

        String query = "SELECT * FROM VistaCarritoVenta";
        rs = consultar(query);
        try {
            do {
                Venta venta = new Venta();
                venta.setNOMPRO(rs.getString("NOMPRO"));
                venta.setCANTVEDET(rs.getInt("CANTVEDET"));
                venta.setPREPRO(rs.getDouble("PREPRO"));
                venta.setSUBTOTAL(rs.getDouble("Subtotal"));
                vistaVenta.add(venta);
                //System.out.println("Vista de Carrito se obtuvo");
            } while (rs.next());
        } catch (SQLException e ) {
            //System.out.println("Ya se rellenaron todo los datos en AgregarVista");
        }catch( Exception ex){
            //System.out.println("Array esta vacio en AgregarVista");
        }
        return vistaVenta;

    }
    
    
    public List<Venta> listCarrito() {
        List<Venta> allVenta = new ArrayList<Venta>();
        String query = "SELECT *,Subtotal=CANTVEDET*PD.PREPRO FROM CARRITO_VENTA AS CV" +
                          " INNER JOIN PRODUCTO AS PD" +
                          " ON PD.IDPRO=CV.IDPRO";
        rs = consultar(query);
        try {
            do {
                Venta venta = new Venta();
                venta.setIDPRO(rs.getInt("IDPRO"));
                venta.setSUBVEDET(rs.getDouble("Subtotal"));
                venta.setCANTVEDET(rs.getInt("CANTVEDET"));
                venta.setIDCLI(rs.getInt("IDCLI"));
                venta.setIDEMVEN(rs.getInt("IDEMVEN"));
                venta.setIDEMDES(rs.getInt("IDEMDES"));
                allVenta.add(venta);
                //System.out.println("Vista de Carrito se obtuvo");
            } while (rs.next());
        } catch (SQLException e) {
            //System.out.println("Ya se rellenaron todo los datos en AgregarVista");
        } catch (Exception ex) {
            //System.out.println("Array esta vacio en AgregarVista");
        }
        return allVenta;

    }
    
    
    //OBTENER DATOS ESPECIFICOS
    
    public int obtenerStock(String id){

        String query = "SELECT CANPRO FROM PRODUCTO WHERE IDPRO="+id;
        int maxSpinner=10;
        rs = consultar(query);
        try {
            maxSpinner=rs.getInt("CANPRO");
        } catch (SQLException e) {
            //System.out.println("Ya se rellenaron todo los datos en ObetenerStock");
        }
        closeConeccion();
        return maxSpinner;
    }
    
    
    public int obtenerPrecio(String id) {

        String query = "SELECT PREPRO FROM PRODUCTO WHERE IDPRO=" + id;
        int precio = 1;
        rs = consultar(query);
        try {
            precio = rs.getInt("PREPRO");
        } catch (SQLException e) {
            //System.out.println("Ya se rellenaron todo los datos en ObetenerStock");
        }
        closeConeccion();
        return precio;
    }
    
    
    public void updateReporte(String id) {
        conexion = getConnection();
        String query = "UPDATE REPORTE SET IDVEN=" + id;
        try {
            ps = conexion.prepareStatement(query);  
            ps.executeUpdate();
            System.out.println("Se actualizao el reporte actual");
            closeConeccion();
        } catch (SQLException e) {
            //System.out.println("Ya se rellenaron todo los datos en ObetenerStock");
        }
        closeConeccion();
    }
    
    
    public Venta fillReport(Venta venta){
        String query = "SELECT * FROM REPORTESVISTA WHERE IDVEN=" + venta.getIDVEN();
        rs = consultar(query);
        try {
            venta.setCELCLI(rs.getString("CELCLI"));
            venta.setDIRCLI(rs.getString("DIRCLI"));
            venta.setCELEMP(rs.getString("CELEMP"));
            venta.setCOREMP(rs.getString("COREMP"));
            System.out.println("Se relleno fillreport satisfactoriamente");
        } catch (SQLException e) {
            //System.out.println("Ya se rellenaron todo los datos en ObetenerStock");
        }
        closeConeccion();
        return venta;
    }
    
    
    
    
    
    
    //TRANSACCIONALES
    public void addVenta(Venta venta) {

        conexion = getConnection();
        String query = "EXEC spUpdateVenta  ?";
        try {
            ps = conexion.prepareStatement(query);
            ps.setInt(1, venta.getIDCLI());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public void addVentaDetalle(Venta venta,int NROVENTA) {

        conexion = getConnection();
        String query = "EXEC spUpdateVentaDetalle ?,?,?,?";
        try {
            ps = conexion.prepareStatement(query);
            ps.setInt(1, venta.getCANTVEDET());
            ps.setDouble(2, venta.getSUBVEDET());
            ps.setInt(3, venta.getIDPRO());
            ps.setInt(4, NROVENTA);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public void addVentaEmpleado(int id,int NROVENTA) {
        conexion = getConnection();
        String query = "EXEC spUpdateVentaEmpleado ?,?";
        try {
            ps = conexion.prepareStatement(query);
            ps.setInt(1, id);
            ps.setInt(2, NROVENTA);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    
    
    public int getNroVenta() {
        String query = "SELECT COUNT(*) AS idVenta FROM VENTA";
        int nroFactura = 10;
        rs = consultar(query);
        try {
            nroFactura = rs.getInt("idVenta");

        } catch (SQLException e) {
        }
        return nroFactura;
    }
    
    
    
    public void limpiarCarrito() {

        conexion = getConnection();
        String query = "DELETE FROM CARRITO_VENTA";
        try {
            ps = conexion.prepareStatement(query);
            ps.executeUpdate();
            ps.close();
            System.out.println("Se limpio los registros Correctamente");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error en limpiar los registros");
        }
    }
    
    
      
    //--------------------------VENTA DETALLES --------------------------------
    
    public List<Venta> ObtenerTodoVenta() {
        
        List<Venta> allVenta = new ArrayList<Venta>();
        String query = "SELECT * FROM VistaVenta";
        rs = consultar(query);

        try {
            do {
                Venta venta = new Venta();

                //TABLA TEMPORAL
                venta.setIDVEN(rs.getInt("IDVEN"));
                venta.setNOMCLI(rs.getString("NOMCLI"));
                venta.setVENDEDOR(rs.getString("Vendedor"));
                venta.setTOTAL(rs.getDouble("TOTAL"));
                allVenta.add(venta);

                //DATOS DE VISTA
            } while (rs.next());
        } catch (SQLException e) {
            System.out.println("Ya se rellenaron todo los datos TodoVenta");
        } finally {
            allVenta = this.agregarVista(allVenta);
            return allVenta;
        }
        
    }

    
    public List<Venta> ObtenerTodoDetalle(int IDVEN) {
        List<Venta> allBoletaDetalle = new ArrayList<>();
        String query = "SELECT * FROM VistaVentaDetalle WHERE IDVEN=" + IDVEN;
        rs = consultar(query);

        try {
            do {
                Venta ventaDetalle = new Venta();

                //VISTA PRODUCTOS ENTREGADOS  
                ventaDetalle.setNOMPRO(rs.getString("NOMPRO"));
                ventaDetalle.setPREPRO(rs.getDouble("PREPRO"));
                ventaDetalle.setCANTVEDET(rs.getInt("CANTVEDET"));
                ventaDetalle.setSUBTOTAL(rs.getDouble("Subtotal"));
                allBoletaDetalle.add(ventaDetalle);

                //DATOS DE VISTA
            } while (rs.next());
        } catch (SQLException e) {
            System.out.println("Ya se rellenaron todo los datos VentaDetalle");
        } finally {
            allBoletaDetalle = this.agregarVista(allBoletaDetalle);
            return allBoletaDetalle;
        }
    }
    
    
    
    
    
}
