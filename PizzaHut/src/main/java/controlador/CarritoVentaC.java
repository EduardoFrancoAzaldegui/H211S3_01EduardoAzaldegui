/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import modelo.Cliente;
import modelo.Venta;
import modelo.Producto;
import dao.daoClientes;
import dao.daoEmpleado;
import dao.daoProducto;
import dao.daoVenta;
import java.io.IOException;
import java.sql.SQLException;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import modelo.Empleado;
import service.Reporte;



@ManagedBean(name="carritoVentaC")
@SessionScoped

public class CarritoVentaC {
    
    private List<Venta> listaVenta;
    private Venta articulo;
    private String TOTAL;



    //COMBOBOX VENDEDOR VARIABLE
    private Map<String, String> listVendedor = new HashMap<>();
    private String VendedorSelect;

    //COMBOBOX DESPACHADOR VARIABLE
    private Map<String, String> listDespachador = new HashMap<>();
    private String DespachadorSelect;

    //COMBOBOX CLIENTE VARIABLE
    private Map<String, String> listCliente = new HashMap<>();
    private String clienteSelect;

    //COMBOBOX PRODUCTO VARIABLE
    private Map<String, String> listProducto = new HashMap<>();
    private String productoSelect;

    //COMBOBOX SUCURSAL VARIABLE
    private Map<String, String> listSucursal = new HashMap<>();
    private String sucursalSelect="0";

    
    //SPINNER VALUE
    private String cantidadSelect;
    private int maxSpinner = 10;
    
    
    public CarritoVentaC() {
        this.articulo = new Venta();
        daoVenta dao = new daoVenta();
        this.listaVenta = dao.getAll();
        listarAll();
        
    }
    
    public void ActualizarCampos() {
        this.rellenarListProducto();
        this.rellenarListVendedor();
        this.rellenarListDespachador();
    }

    
    //---------------- CRUD ---------------- 
    
    public void listarAll() {
        this.rellenarListProducto();
        this.rellenarListCliente();
        this.rellenarListSucursal();
        this.rellenarListVendedor();
        this.rellenarListDespachador();
    }
        
    
    //FUNCIONES DE BOTONES
    
    public void agregarItem() {
        daoVenta dao = new daoVenta();
        Venta item = new Venta();

       
        articulo.setCANTVEDET(Integer.valueOf(this.getCantidadSelect()));
        articulo.setIDSUC(Integer.valueOf(this.sucursalSelect));
        articulo.setIDPRO(Integer.valueOf(this.productoSelect));
        articulo.setIDCLI(Integer.valueOf(this.clienteSelect));
        articulo.setIDEMVEN(Integer.valueOf(this.VendedorSelect));
        articulo.setIDEMDES(Integer.valueOf(this.DespachadorSelect));
        System.out.println(articulo.getCANTVEDET());
        System.out.println(articulo.getIDSUC());
        
        dao.add(articulo);

    }

    public void comprarItems() {
        daoVenta dao = new daoVenta();
        List<Venta> listaVenta2 = dao.listCarrito();
        Venta venta=listaVenta2.get(listaVenta2.size()-1);
        
        //rellenando Venta
        dao.addVenta(venta);
        
        
        //RELLENANDO VENTA DETALLE
        int NROVENTA = dao.getNroVenta();
        int indice = 0;
        for (int i = 0; i < listaVenta2.size(); i++) {
            dao.addVentaDetalle(listaVenta2.get(indice), NROVENTA);
            indice++;
        }
        
        //rellenando empleado
        dao.addVentaEmpleado(venta.getIDEMVEN(), NROVENTA);
        dao.addVentaEmpleado(venta.getIDEMDES(), NROVENTA);
        
        dao.limpiarCarrito();
    }

    
    public void eliminarItems() {
        daoVenta dao = new daoVenta();
        dao.delete(articulo);
        dao.limpiarCarrito();

    }
    
    //FUNCIONES COMBOBOX
    
    
    public void rellenarListProducto() {
        daoProducto dao = new daoProducto();
        List<Producto> listaProducto = dao.getAll(sucursalSelect);
        setListProducto(new HashMap<>());

        for (int i = 0; i < listaProducto.size(); i++) {
            String id = String.valueOf(listaProducto.get(i).getIDPRO());
            getListProducto().put(listaProducto.get(i).getNOMPRO(), id);
        }
    }

    public void rellenarListCliente() {
        daoClientes dao = new daoClientes();
        String cass = "1";
        List<Cliente> listaCliente = dao.ObtenerTodo(cass);
        this.setListCliente(new HashMap<>());

        for (int i = 0; i < listaCliente.size(); i++) {
            String id = String.valueOf(listaCliente.get(i).getIDCLI());
            this.getListCliente().put(listaCliente.get(i).getNOMCLI(), id);
        }
    }

    public void rellenarListVendedor() {
        daoEmpleado dao = new daoEmpleado();
        List<Empleado> listaVendedor = dao.getAll("Vendedor", sucursalSelect);
        this.setListVendedor(new HashMap<>());

        for (int i = 0; i < listaVendedor.size(); i++) {
            String id = String.valueOf(listaVendedor.get(i).getIDEMP());
            this.getListVendedor().put(listaVendedor.get(i).getNOMEMP(), id);
        }
    }

    public void rellenarListDespachador() {
        daoEmpleado dao = new daoEmpleado();
        List<Empleado> listaDespachador =dao.getAll("Despachador", sucursalSelect);
        this.setListDespachador(new HashMap<>());

        for (int i = 0; i < listaDespachador.size(); i++) {
            String id = String.valueOf(listaDespachador.get(i).getIDEMP());
            this.getListDespachador().put(listaDespachador.get(i).getNOMEMP(), id);
        }
    }

    public void rellenarListSucursal() {
        this.setListSucursal(new HashMap<>());

        for (int i = 1; i < 4; i++) {
            String nombre = "Sucursal "+ i;
            String id= String.valueOf(i);
            this.getListSucursal().put(nombre, id);
        }
    }
    
    
    public void updateMaxSpinner() {
        daoVenta dao = new daoVenta();
        this.setMaxSpinner(dao.obtenerStock(productoSelect));
        System.out.println("Este es el stock " + this.getMaxSpinner());
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
        //this.updateMaxSpinner();
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

    /**
     * @return the listVendedor
     */
    public Map<String, String> getListVendedor() {
        return listVendedor;
    }

    /**
     * @param listVendedor the listVendedor to set
     */
    public void setListVendedor(Map<String, String> listVendedor) {
        this.listVendedor = listVendedor;
    }

    /**
     * @return the VendedorSelect
     */
    public String getVendedorSelect() {
        return VendedorSelect;
    }

    /**
     * @param VendedorSelect the VendedorSelect to set
     */
    public void setVendedorSelect(String VendedorSelect) {
        this.VendedorSelect = VendedorSelect;
    }

    /**
     * @return the listDespachador
     */
    public Map<String, String> getListDespachador() {
        return listDespachador;
    }

    /**
     * @param listDespachador the listDespachador to set
     */
    public void setListDespachador(Map<String, String> listDespachador) {
        this.listDespachador = listDespachador;
    }

    /**
     * @return the DespachadorSelect
     */
    public String getDespachadorSelect() {
        return DespachadorSelect;
    }

    /**
     * @param DespachadorSelect the DespachadorSelect to set
     */
    public void setDespachadorSelect(String DespachadorSelect) {
        this.DespachadorSelect = DespachadorSelect;
    }

    /**
     * @return the listSucursal
     */
    public Map<String, String> getListSucursal() {
        return listSucursal;
    }

    /**
     * @param listSucursal the listSucursal to set
     */
    public void setListSucursal(Map<String, String> listSucursal) {
        this.listSucursal = listSucursal;
    }

    /**
     * @return the sucursalSelect
     */
    public String getSucursalSelect() {
        return sucursalSelect;
    }

    /**
     * @param sucursalSelect the sucursalSelect to set
     */
    public void setSucursalSelect(String sucursalSelect) {
        this.sucursalSelect = sucursalSelect;
    }

    /**
     * @return the listaVenta
     */
    public List<Venta> getListaVenta() {
        System.out.println("si paso por obtener");
        daoVenta dao = new daoVenta();
        listaVenta=dao.getAll();
        return listaVenta;
    }

    /**
     * @param listaVenta the listaVenta to set
     */
    public void setListaVenta(List<Venta> listaVenta) {
        this.listaVenta = listaVenta;
        listarAll();
    }

    /**
     * @return the articulo
     */
    public Venta getArticulo() {
        return articulo;
    }

    /**
     * @param articulo the articulo to set
     */
    public void setArticulo(Venta articulo) {
        this.articulo = articulo;
    }

    /**
     * @return the TOTAL
     */
    public String getTOTAL() {
        return TOTAL;
    }

    /**
     * @param TOTAL the TOTAL to set
     */
    public void setTOTAL(String TOTAL) {
        this.TOTAL = TOTAL;
    }

    /**
     * @return the cantidadSelect
     */
    public String getCantidadSelect() {
        return cantidadSelect;
    }

    /**
     * @param cantidadSelect the cantidadSelect to set
     */
    public void setCantidadSelect(String cantidadSelect) {
        this.cantidadSelect = cantidadSelect;
    }

    /**
     * @return the maxSpinner
     */
    public int getMaxSpinner() {
        return maxSpinner;
    }

    /**
     * @param maxSpinner the maxSpinner to set
     */
    public void setMaxSpinner(int maxSpinner) {
        this.maxSpinner = maxSpinner;
    }
}
