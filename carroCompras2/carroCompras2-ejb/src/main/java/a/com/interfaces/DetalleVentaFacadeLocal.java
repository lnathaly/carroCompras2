/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a.com.interfaces;

import a.com.Entity.DetalleVenta;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author LauraDesarrollo
 */
@Local
public interface DetalleVentaFacadeLocal {

    void create(DetalleVenta detalleVenta);

    void edit(DetalleVenta detalleVenta);

    void remove(DetalleVenta detalleVenta);

    DetalleVenta find(Object id);

    List<DetalleVenta> findAll();

    List<DetalleVenta> findRange(int[] range);

    int count();
    
}
