/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a.com.controller;

import a.com.interfaces.ClienteFacadeLocal;
import a.com.Dto.ClienteDto;
import a.com.Entity.Cliente;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Yesid
 */
@Named(value = "controllerLogin")
@SessionScoped
public class ControllerLogin implements Serializable {
    /*
     Creamos la el objeto UsuarioFacadeLocal para realziar la concexion al EJB
     */

    @EJB(beanName = "ClienteFacade")
    private ClienteFacadeLocal clienteFacadeLocal;
    /**
     * Creamos el objeto Persona para enviar los datos al EJB
     */
    private ClienteDto cliente;
    /*
     Creamos el objeto persona para poder realizar las validaciones del rol con lo que viene del EJB
     */
    private Cliente clie;

    @PostConstruct
    public void init() {
        cliente = new ClienteDto();
        clie = new Cliente();
    }

    public ControllerLogin() {
    }

    /*
     Lectura del UsuarioFacadeLocal
     */
    public ClienteFacadeLocal getClienteFacadeLocal() {
        return clienteFacadeLocal;
    }

    public void setClienteFacadeLocal(ClienteFacadeLocal clienteFacadeLocal) {
        this.clienteFacadeLocal = clienteFacadeLocal;
    }

    public ClienteDto getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDto cliente) {
        this.cliente = cliente;
    }

    public Cliente getClie() {
        return clie;
    }

    public void setClie(Cliente clie) {
        this.clie = clie;
    }

    /**
     * Metodo el cual realiza el Login. Hacemos la concexion a base de datos por
     * medio del EJB el cual nos retorna un objeto de tipo Persona y se valida
     * si esta vacio. Al no ser asi creamos la session y validamos el rol para
     * redireccion del usuario como tal
     *
     * @return
     */
    public String empezarLogin() {
        String redireccion = "index";
        try {

            clie = clienteFacadeLocal.login(cliente);
            if (clie != null) {
                FacesContext fCtx = FacesContext.getCurrentInstance();
                HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false);
                session.setAttribute("usuario", clie);
                if (clie.getRol() == 1) {
                    redireccion = "administrador";
                } else {
                    redireccion = "crearProducto";
                }
            } else {
                System.out.println("usuario invalido");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Incorrecto", "Datos Incorrectos"));
            }
        } catch (Exception e) {
        }
        return redireccion;
    }

}
