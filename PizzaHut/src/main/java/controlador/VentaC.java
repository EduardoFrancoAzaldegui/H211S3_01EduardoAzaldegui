/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.daoCliente;
import dao.daoEmpleado;
import dao.daoProducto;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;
import modelo.Cliente;
import modelo.Empleado;
import modelo.Producto;
import service.Reporte;

/**
 *
 * @author PC-EDUARDO
 */

@Named(value ="ventaC")
@SessionScoped

public class VentaC implements Serializable{

    
    
  
    private Producto producto;
    private Cliente cliente;
    private Empleado empleado;
   
    //COMBOBOX CARGO VARIABLE
    private Map<String, String> listEmpleado = new HashMap<>();
    private String empleadoSelect;
    
    //COMBOBOX UBIGE VARIABLE
    private Map<String, String> listCliente = new HashMap<>();
    private String clienteSelect;
    
    //COMBOBOX UBIGE VARIABLE
    private Map<String, String> listProducto = new HashMap<>();
    private String productoSelect;
    
 
    public VentaC() {
        this.producto = new Producto();
    }

    
    //CRUD
    public void listarAll() {
        this.rellenarListEmpleado();
        this.rellenarListProducto();
        this.rellenarListCliente();
    }
    
    
    //FUNCIONES COMBOBOX
    
    public void rellenarListProducto() {
        productoSelect= String.valueOf(producto.getIDPRO());
        daoProducto dao = new daoProducto();
        List<Producto> listaProducto = dao.getAll();
        setListProducto(new HashMap<>());

        for (int i = 0; i < listaProducto.size(); i++) {
            String id = String.valueOf(listaProducto.get(i).getIDPRO());
            getListProducto().put(listaProducto.get(i).getNOMPRO(), id);
        }
    }

    
    public void rellenarListCliente() {
        clienteSelect = String.valueOf(cliente.getIDCLI());
        daoCliente dao = new daoCliente();
        List<Cliente> listaCliente = dao.getAll();
        this.setListCliente(new HashMap<>());

        for (int i = 0; i < listaCliente.size(); i++) {
            String id = String.valueOf(listaCliente.get(i).getIDCLI());
            this.getListCliente().put(listaCliente.get(i).getNOMCLI(), id);
        }
    }
    
    
    
    public void rellenarListEmpleado() {
        empleadoSelect = String.valueOf(empleado.getIDEMP());
        daoEmpleado dao = new daoEmpleado();
        List<Empleado> listaEmpleado = dao.getAll();
        this.setListEmpleado(new HashMap<>());

        for (int i = 0; i < listaEmpleado.size(); i++) {
            String id = String.valueOf(listaEmpleado.get(i).getIDEMP());
            this.getListEmpleado().put(listaEmpleado.get(i).getNOMEMP(), id);
        }
    }

    
        public void reporteEmpleado() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        Reporte Cliente = new Reporte();
        FacesContext facescontext = FacesContext.getCurrentInstance();
        ServletContext servletcontext = (ServletContext) facescontext.getExternalContext().getContext();
        String root = servletcontext.getRealPath("reports/Empleado.jasper");
        System.out.println("este es el root "+ root);
        //String numeroinformesocial = String.valueOf(modelo.getESTPER());
        //System.out.println("La Persona es: " + numeroinformesocial);
        Cliente.getReportePdf(root, "A");
        FacesContext.getCurrentInstance().responseComplete();
    }
    
    
   
        
     /**
     * @return the producto
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * @param producto the producto to set
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the empleado
     */
    public Empleado getEmpleado() {
        return empleado;
    }

    /**
     * @param empleado the empleado to set
     */
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    /**
     * @return the listEmpleado
     */
    public Map<String, String> getListEmpleado() {
        return listEmpleado;
    }

    /**
     * @param listEmpleado the listEmpleado to set
     */
    public void setListEmpleado(Map<String, String> listEmpleado) {
        this.listEmpleado = listEmpleado;
    }

    /**
     * @return the empleadoSelect
     */
    public String getEmpleadoSelect() {
        return empleadoSelect;
    }

    /**
     * @param empleadoSelect the empleadoSelect to set
     */
    public void setEmpleadoSelect(String empleadoSelect) {
        this.empleadoSelect = empleadoSelect;
    }

    /**
     * @return the listCliente
     */
    public Map<String, String> getListCliente() {
        return listCliente;
    }

    /**
     * @param listCliente the listCliente to set
     */
    public void setListCliente(Map<String, String> listCliente) {
        this.listCliente = listCliente;
    }

    /**
     * @return the clienteSelect
     */
    public String getClienteSelect() {
        return clienteSelect;
    }

    /**
     * @param clienteSelect the clienteSelect to set
     */
    public void setClienteSelect(String clienteSelect) {
        this.clienteSelect = clienteSelect;
    }

    /**
     * @return the listProducto
     */
    public Map<String, String> getListProducto() {
        return listProducto;
    }

    /**
     * @param listProducto the listProducto to set
     */
    public void setListProducto(Map<String, String> listProducto) {
        this.listProducto = listProducto;
    }

    /**
     * @return the productoSelect
     */
    public String getProductoSelect() {
        return productoSelect;
    }

    /**
     * @param productoSelect the productoSelect to set
     */
    public void setProductoSelect(String productoSelect) {
        this.productoSelect = productoSelect;
    }
    

    
}
