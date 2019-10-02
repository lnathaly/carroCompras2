/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a.com.controller;

import a.com.Dto.ProductoDto;
import a.com.Entity.Cliente;
import a.com.Entity.DetalleVenta;
import a.com.Entity.Producto;
import a.com.Entity.Venta;
import a.com.interfaces.DetalleVentaFacadeLocal;
import a.com.interfaces.ProductoFacadeLocal;
import a.com.interfaces.VentaFacadeLocal;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author LauraDesarrollo
 */
@Named
@ViewScoped
public class controllerIndex implements Serializable {

    private Cliente clie;

    @EJB
    ProductoFacadeLocal productoFacade;
    List<Producto> listaProd;

    @EJB
    VentaFacadeLocal ventaFacade;

    @EJB
    DetalleVentaFacadeLocal detalleFacade;

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
                if (cant < 0) {
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
        FacesContext fCtx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false);
        clie = (Cliente) session.getAttribute("usuario");
        System.out.println("ENtre al boton");
        DetalleVenta detalle = new DetalleVenta();

        Venta venta = new Venta();
        venta.setCodPersona(clie);
        venta.setFecha(Date.from(Instant.EPOCH));
        venta.setTotal(BigDecimal.ZERO);
        venta.setDetalleventaList(listaVenta);
        detalle.setVenta(venta);

        ventaFacade.create(venta);
        detalleFacade.create(detalle);  

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

    public String validarSession() {
        String redireccion = "cliente";
        FacesContext fCtx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

        try {
            clie = (Cliente) session.getAttribute("usuario");
            System.out.println("entro al cliente");

        } catch (Exception e) {
        }

        if (clie == null) {
            try {
                ec.redirect(ec.getRequestContextPath() + "/faces/index.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(controllerIndex.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println(redireccion);
        return redireccion;
    }

    /**
     * Metodo el cual se encarga de destruir la session y redirigir
     *
     * @return Retorna un String con el nombre de la pagina a la caul se va a
     * redirigir
     */
    public String cerrarSession() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        System.out.println("Session terminada");
        return "index";

    }

}
