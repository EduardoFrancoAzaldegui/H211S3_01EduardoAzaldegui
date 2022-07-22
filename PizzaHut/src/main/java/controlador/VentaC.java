/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.daoClientes;
import dao.daoEmpleado;
import dao.daoProducto;
import dao.daoVenta;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;
import modelo.Venta;
import service.Reporte;

/**
 *
 * @author PC-EDUARDO
 */
@Named(value = "ventaC")
@SessionScoped

public class VentaC implements Serializable {
    
    
    //-----------------AGREGAR VENTA------------------//
    private List<Venta> listaVenta;
    private List<Venta> listaDetalles;
    private int NROVEN;
    private Venta venta;
    
    
    @PostConstruct
    public void init() {
        this.setVenta(new Venta());
    }

    
    public VentaC() {
        
        listaVenta = getListaVenta();
    }
    
    
    public void reporteVenta(Venta venta2) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        Reporte Cliente = new Reporte();
        FacesContext facescontext = FacesContext.getCurrentInstance();
        ServletContext servletcontext = (ServletContext) facescontext.getExternalContext().getContext();
        String root = servletcontext.getRealPath("reports/Transaccional.jasper");
        Cliente.getReportePdf(root, "A", "Venta",venta2);
        FacesContext.getCurrentInstance().responseComplete();
    }
    
    
    /**
     * @return the listaVenta
     */
    public List<Venta> getListaVenta() {
        daoVenta dao = new daoVenta();
        this.listaVenta = dao.ObtenerTodoVenta();
        return listaVenta;
    }

    /**
     * @param listaVenta the listaVenta to set
     */
    public void setListaVenta(List<Venta> listaVenta) {
        this.listaVenta = listaVenta;
    }

    /**
     * @return the listaDetalles
     */
    public List<Venta> getListaDetalles() {
        System.out.println("Entro por producto detalle");
        System.out.println(venta.getIDVEN());
        daoVenta dao = new daoVenta();
        listaDetalles = dao.ObtenerTodoDetalle(venta.getIDVEN());
        return listaDetalles;
    }
    
    

    /**
     * @param listaDetalles the listaDetalles to set
     */
    public void setListaDetalles(List<Venta> listaDetalles) {
        this.listaDetalles = listaDetalles;
    }

    /**
     * @return the venta
     */
    public Venta getVenta() {
        return venta;
    }

    /**
     * @param venta the venta to set
     */
    public void setVenta(Venta venta) {
        this.venta = venta;
    }


    
}
