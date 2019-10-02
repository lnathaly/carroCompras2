/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a.com.bean;

import a.com.Dto.ClienteDto;
import a.com.interfaces.AbstractFacade;
import a.com.interfaces.ClienteFacadeLocal;
import a.com.Entity.Cliente;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author LauraDesarrollo
 */
@Stateless
public class ClienteFacade extends AbstractFacade<Cliente> implements ClienteFacadeLocal {

    @PersistenceContext(unitName = "a.com_carroCompras2-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClienteFacade() {
        super(Cliente.class);
    }

    @Override
    public Cliente login(ClienteDto clie) {
        Cliente usuario = null;
        String consulta;
        System.out.println(clie.getUsername()+clie.getClave());
        try {
            consulta = "FROM Cliente c WHERE c.username = ?1 AND c.clave = ?2";
            Query query = em.createQuery(consulta);
            query.setParameter(1, clie.getUsername());
            query.setParameter(2, clie.getClave());
            List<Cliente> lista = query.getResultList();
            if (!lista.isEmpty()) {
                usuario = lista.get(0);
            }
        } catch (Exception e) {
        }
        return usuario;
    }
}
