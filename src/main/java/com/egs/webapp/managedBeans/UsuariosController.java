/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.egs.webapp.managedBeans;

import com.egs.webapp.entities.Usuario;
import com.egs.webapp.sessionBeans.UsuarioFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author EduardoAlexis
 */
@Named(value = "usuariosController")
@SessionScoped
public class UsuariosController implements Serializable {

    // EJB sessionBeans 
    @EJB
    private UsuarioFacade usuarioFacade;
    
    //attribute
    private String username;
    private Usuario selectedUser;
    private Usuario currentUser;
    private String pass;
    private List<Usuario> items = null;
    
    public UsuariosController() {
    }

    public UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }
    
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Usuario getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(Usuario selectedUser) {
        this.selectedUser = selectedUser;
    }
// current user in session
    public Usuario getCurrentUser() {
        if (currentUser==null){
        currentUser = new Usuario();
        currentUser = getUsuarioFacade().findUsuarioByUsername(getUsername());
    
        }
        
        return currentUser;
    }

    public void setCurrentUser(Usuario currentUser) {
        this.currentUser = currentUser;
    }

    public List<Usuario> getItems() {
        if (items == null) {

            // no tiene que encontrarlos a todos   
            items = getUsuarioFacade().usuariosActivos(getUsername());
        } 
    
     return items;
    
    }
    public void setItems(List<Usuario> items) {
        this.items = items;
    }
    

    
        public String login() {

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context
                                     .getExternalContext().getRequest();
        try {
            request.login(username, pass);

        } catch (ServletException e) {

            FacesContext facesContext = FacesContext.getCurrentInstance();
            Flash flash = facesContext.getExternalContext().getFlash();
            flash.setKeepMessages(true);
            flash.setRedirect(true);
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario y/o password incorrectos!", null));
            return "login";
        }

        if (request.isUserInRole("administrador")) {

//            FacesContext facesContext = FacesContext.getCurrentInstance();
//            Flash flash = facesContext.getExternalContext().getFlash();
//            flash.setKeepMessages(true);
//            flash.setRedirect(true);
//            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "HOLA " + usuario.toUpperCase() + "!", "¿ Por donde empiezas hoy ?"));
             
            return "welcome-admin";
        } else if (request.isUserInRole("vendedor")) {

            return "login";
        } else {

            return "login";
        }

    }

    public String logout() {
        String result = "login";

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
            request.logout();
            
        } catch (ServletException e) {
            System.out.println("Error al intentar salir");
            result = "login";

        }

        return result;
    }
    //Navigation methods
    
    public String goWelcomeAdmin(){
    
    return "welcome-admin";
    }
   public String goUsuariosList(){
    
    return "usuarios-list";
    }
    
   public String goUsuarioEdit(){
    
    return "usuario-edit";
    }
   
   public String goUsuarioDelete(){
    
    return "usuario-delete";
    }
   
   public String goUsuarioCreate(){
    
    return "usuario-create";
    }
   
   public String goUsuarioView(){
    
    return "usuario-view";
    }
   
   public String goUsuariosDashboard(){
    
    return "usuarios-dashboard";
    }
   
}
