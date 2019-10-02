/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a.com.controller;

import a.com.Entity.Producto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yesid
 */
public class ControllerProductoSession implements Serializable {

    private List<Producto> listaProductoSession;

    public ControllerProductoSession() {
    }

    public ControllerProductoSession(List<Producto> listaProductoSession) {
        this.listaProductoSession = listaProductoSession;
    }

    public List<Producto> getListaProductoSession() {
        return listaProductoSession;
    }

    public void setListaProductoSession(List<Producto> listaProductoSession) {
        this.listaProductoSession = listaProductoSession;
    }


}
