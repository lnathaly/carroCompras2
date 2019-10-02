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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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

    private int subtotal;

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
        DetalleVenta det = new DetalleVenta();
        det.setCantidad(cantidad);
        det.setProducto(producto);
        det.setSubtotal((int) (cantidad * producto.getPrecio()));

        for (Producto listaProd1 : listaProd) {
            if (listaProd1.getCodigo() == producto.getCodigo()) {
                int cant = listaProd1.getStock() - cantidad;
                if (cant <0) {
                    System.out.println("IF CANT");
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Lo sentimos", "El stok del producto se ha agotado"));

                } else {
                    listaProd1.setStock(cant);
                    System.out.println("ELSE");
                    this.listaVenta.add(det);

                }

            }
        }

    }

    public void realizarCompra() {

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

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }

}
