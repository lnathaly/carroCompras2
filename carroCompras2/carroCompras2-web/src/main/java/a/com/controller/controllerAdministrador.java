/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a.com.controller;

import a.com.Entity.Producto;
import a.com.interfaces.ProductoFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author LauraDesarrollo
 */
@Named
@ViewScoped
public class controllerAdministrador implements Serializable {

    @EJB
    ProductoFacadeLocal productoFacade;
    List<Producto> listaProd;

    private String nombre;

    private double precio;

    private int stock;
    
    private Producto selectedProducto;

    @PostConstruct
    public void iniciar() {

        listaProd = productoFacade.findAll();

    }

    public void agregarProducto() {

        Producto producto = new Producto(nombre, precio, stock);
        productoFacade.create(producto);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Producto Agregado con exito"));

    }

    public List<Producto> ListarProducto() {

        listaProd = productoFacade.findAll();
        return listaProd;
    }

    public void onRowEdit(RowEditEvent event) {
        Producto p = (Producto) event.getObject();
        int cod = p.getCodigo();
        Producto prod = productoFacade.find(cod);
        prod.setNombre(nombre);
        prod.setPrecio(precio);
        prod.setStock(stock);
        productoFacade.edit(prod);
        FacesMessage msg = new FacesMessage("Producto Editado", ((Producto) event.getObject()).getNombre());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edicion Cancelada", ((Producto) event.getObject()).getNombre());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void EliminarProducto(Producto p) {
      
        Producto prod = productoFacade.find(p.getCodigo());
        productoFacade.remove(p);
      
    
    }

    public List<Producto> getListaProd() {
        return listaProd;
    }

    public void setListaProd(List<Producto> listaProd) {
        this.listaProd = listaProd;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Producto getSelectedProducto() {
        return selectedProducto;
    }

    public void setSelectedProducto(Producto selectedProducto) {
        this.selectedProducto = selectedProducto;
    }
    
 }
