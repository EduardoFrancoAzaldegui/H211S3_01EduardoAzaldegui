/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.daoProducto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import modelo.Producto;



@Named(value ="productoC")
@SessionScoped

public class ProductoC implements Serializable{

    private Producto producto;

    //COMBOBOX UBIGE VARIABLE
    private Map<String, String> listProducto = new HashMap<>();
    private String productoSelect;
    
    
    public ProductoC() {
        this.producto = new Producto();
    }

    
    //CRUD
    
    public List<Producto> listarAll() {
        this.rellenarListProducto();
        List<Producto> lista=new ArrayList();
        daoProducto dao = new daoProducto();
        lista=dao.getAll();
        return lista;
    }
        
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
