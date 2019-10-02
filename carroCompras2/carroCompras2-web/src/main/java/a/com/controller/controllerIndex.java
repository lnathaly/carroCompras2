/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a.com.controller;

import a.com.Entity.Producto;
import a.com.interfaces.ProductoFacadeLocal;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author LauraDesarrollo
 */
@Named
@ViewScoped
public class controllerIndex implements Serializable{
    
    @EJB
    ProductoFacadeLocal productoFacade;
    List<Producto> listaProd;
    public void hacerAlgo() {
        listarProductos();
    }
    
    @PostConstruct
    public void iniciar (){
           
        listaProd= productoFacade.findAll();

    }
    
    public void guardarProducto(){
        Producto producto = new Producto ("leche", 1500, 3);
        productoFacade.create(producto);
    }
    public List<Producto> listarProductos(){
        
        listaProd= productoFacade.findAll();
       return listaProd;
    }

    public List<Producto> getListaProd() {
        return listaProd;
    }

    public void setListaProd(List<Producto> listaProd) {
        this.listaProd = listaProd;
    }
    
    
}
