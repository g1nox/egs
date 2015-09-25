/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.egs.webapp.managedBeans;

import com.egs.webapp.entities.Detalleventa;
import com.egs.webapp.sessionBeans.DetalleventaFacade;
import com.egs.webapp.util.JsfUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

/**
 *
 * @author EduardoAlexis
 */
@Named(value = "detalleController")
@SessionScoped
public class DetalleController implements Serializable {

    /**
     * Creates a new instance of DetalleController
     */
    
    @EJB
    private com.egs.webapp.sessionBeans.DetalleventaFacade ejbFacade;
    private List<Detalleventa> items = null;
    private Detalleventa selected;
    // This items are only available in shopping cart 
    private Detalleventa currentDetalleVenta; 
    private List<Detalleventa> currentItems = null;
    
    
    
    public DetalleController() {
    }
    
    
    public DetalleventaFacade getEjbFacade() {
        return ejbFacade;
    }
    
    public List<Detalleventa> getItems() {
        if (items == null) {
            items = getEjbFacade().findAll();
        }
        return items;
    }

    public Detalleventa getSelected() {
        return selected;
    }
    
     public Detalleventa prepareCreate() {
        selected = new Detalleventa();
        return selected;
    }
    
     public void init (){
         currentDetalleVenta = new Detalleventa();
         currentItems = new ArrayList <Detalleventa>();
     }
     public String reinit (){
         
         currentDetalleVenta = new Detalleventa();
         return null;
     }
     
     public void addShoppingCart() {
//        if(currentItems.contains(currentDetalleVenta)) {
//            FacesMessage msg = new FacesMessage("Duplicado", "This item has already been added");
//            FacesContext.getCurrentInstance().addMessage(null, msg);
//        } 
//        else {
//            currentItems.add(currentDetalleVenta);
//            currentDetalleVenta = new Detalleventa();
//        }
          currentItems.add(currentDetalleVenta);
          System.out.println(""+currentItems.contains(currentDetalleVenta));
          
         
          
          currentDetalleVenta = new Detalleventa();
         System.out.println("contiene lista?"+currentDetalleVenta.toString()+currentItems.contains(currentDetalleVenta));
         
    }
     
     
     public String create() {
        persist(JsfUtil.PersistAction.CREATE,  "La venta se ha concretado correctamente");
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
            FacesContext facesContext = FacesContext.getCurrentInstance();
             Flash flash = facesContext.getExternalContext().getFlash();
             flash.setKeepMessages(true);
             flash.setRedirect(true);
             prepareCreate();
             return goDetalleCreate();
        }
        return null;
    }

      public void update() {
        persist(JsfUtil.PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ProductoUpdated"));
    }

    public void destroy() {
        persist(JsfUtil.PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ProductoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
    private void persist(JsfUtil.PersistAction persistAction, String successMessage) {
        if (selected != null) {
           
            try {
                if (persistAction != JsfUtil.PersistAction.DELETE) {
                    getEjbFacade().edit(selected);
                } else {
                    getEjbFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }
    
    
    public Detalleventa getDetalleventa(java.lang.Long id) {
        return getEjbFacade().find(id);
    }

    public List<Detalleventa> getItemsAvailableSelectMany() {
        return getEjbFacade().findAll();
    }

    public List<Detalleventa> getItemsAvailableSelectOne() {
        return getEjbFacade().findAll();
    }

    @FacesConverter(forClass = Detalleventa.class)
    public static class DetalleControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ProductoController controller = (ProductoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "detalleController");
            return controller.getProducto(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Detalleventa) {
                Detalleventa o = (Detalleventa) object;
                return getStringKey(o.getIdDetalle());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Detalleventa.class.getName()});
                return null;
            }
        }

    }

    public Detalleventa getCurrentDetalleVenta() {
        return currentDetalleVenta;
    }

    public List<Detalleventa> getCurrentItems() {
        return currentItems;
    }
    
    
    public String goDetalleCreate(){
    prepareCreate();
    return "venta-create";
    }
    
    public String goDetalleList(){
    prepareCreate();
    return "venta-list";
    }
    
    
    
    
}
