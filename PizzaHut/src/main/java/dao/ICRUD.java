/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

/**
 *
 * @author PC-EDUARDO
 */
public interface ICRUD <T>{
    
    public void add (T item);
    
    public void edit(T item);
    
    public void delete(T item);
    
    public List<T> getAll();
    
}
