/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a.com.controller;

import a.com.Dto.ProductoDto;
import a.com.Entity.DetalleVenta;
import a.com.Entity.Producto;
import a.com.Entity.Venta;
import a.com.interfaces.ProductoFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
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
public class controllerIndex implements Serializable {

    @EJB
    ProductoFacadeLocal productoFacade;
    List<Producto> listaProd;
    
    private List<DetalleVenta> listaVenta = new ArrayList();

    private int cantidad;

    private Producto producto = new Producto();

    public void hacerAlgo() {
        listarProductos();
    }

    @PostConstruct
    public void iniciar() {

        listaProd = productoFacade.findAll();

    }

    public void guardarProducto() {
        Producto producto = new Producto("leche", 1500, 3);
        productoFacade.create(producto);
    }

    public void anadirCarrito() {
        System.out.println("ENtre a agregar");
        DetalleVenta det = new DetalleVenta();
        det.setCantidad(cantidad);
        det.setProducto(producto);
        this.listaVenta.add(det);
        
    }

    public List<Producto> listarProductos() {

        listaProd = productoFacade.findAll();
        return listaProd;
    }

    public List<Producto> getListaProd() {
        return listaProd;
    }

    public void setListaProd(List<Producto> listaProd) {
        this.listaProd = listaProd;
    }

    public ProductoFacadeLocal getProductoFacade() {
        return productoFacade;
    }

    public void setProductoFacade(ProductoFacadeLocal productoFacade) {
        this.productoFacade = productoFacade;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public List<DetalleVenta> getListaVenta() {
        return listaVenta;
    }

    public void setListaVenta(List<DetalleVenta> listaVenta) {
        this.listaVenta = listaVenta;
    }

}
