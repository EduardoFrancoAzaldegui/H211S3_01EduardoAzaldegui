/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import modelo.Cliente;
import dao.daoClientes;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;
import service.Reporte;

@Named(value ="clienteC")
@SessionScoped
public class ClienteC implements Serializable{

    private List<Cliente> listaCliente;
    private Cliente cliente;
    private Cliente selectCliente;
    private String cass="1";
    private Boolean estadoInactivo=false;
    
    
    public ClienteC() {
        this.cliente = new Cliente();
    }
    
    
    
    
    //CRUD

    public void listar() {
        if(cass.equals("2")){
            setEstadoInactivo((Boolean) true);
        }else{
            setEstadoInactivo((Boolean) false);
        }
        daoClientes dao = new daoClientes();
        this.listaCliente = dao.ObtenerTodo(cass);
    }
    
    public void limpiar(){
        this.cliente = new Cliente();
    }

    public void nuevoCliente() {
        System.out.println("paso por agregar clientes");
        daoClientes dao = new daoClientes();
        dao.addCliente(cliente);
         FacesContext.getCurrentInstance().addMessage("myform:newPassword1",
                 new FacesMessage("Se agrego correctamente un cliente"));
    }

    public void modificarCliente() {
        daoClientes dao = new daoClientes();
        dao.editCliente(cliente);
    }

    public void eliminarCliente() {
        daoClientes dao = new daoClientes();
        dao.eliminarCliente(cliente);
    }
    
    public void restaurarCliente(){
        daoClientes dao = new daoClientes();
        dao.restaurarCliente(cliente);
    }
    
    
    
    //FUNCIONES ADICIONALES
    
    public void reportePersona() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        Reporte Cliente = new Reporte();
        FacesContext facescontext = FacesContext.getCurrentInstance();
        ServletContext servletcontext = (ServletContext) facescontext.getExternalContext().getContext();
        String root = servletcontext.getRealPath("reports/Cliente.jasper");
        Cliente.getReportePdf(root, "A","Cliente");
        FacesContext.getCurrentInstance().responseComplete();
    }

    
    
    
    
    
    
    //GETTERS AND SETTERS
    
    
    public List<Cliente> getListaCliente() {
        return listaCliente;
    }

    public void setListaCliente(List<Cliente> listaCliente) {
        this.listaCliente = listaCliente;
    }
    
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    
    public Cliente getSelectCliente() {
        return selectCliente;
    }

    public void setSelectCliente(Cliente selectCliente) {
        this.selectCliente = selectCliente;
    }

    
    public String getCass() {
        return cass;
    }

    public void setCass(String cass) {
        this.cass = cass;
    }

    
    public Boolean getEstadoInactivo() {
        return estadoInactivo;
    }

    public void setEstadoInactivo(Boolean estadoInactivo) {
        this.estadoInactivo = estadoInactivo;
    }


}
