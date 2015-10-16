/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.egs.webapp.managedBeans;

import com.egs.webapp.entities.Detalleventa;
import com.egs.webapp.entities.Venta;
import com.egs.webapp.sessionBeans.VentaFacade;
import com.egs.webapp.util.JsfUtil;
import java.io.Serializable;
import java.math.BigInteger;
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
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author EduardoAlexis
 */
@Named(value = "ventaController")
@SessionScoped
public class VentaController implements Serializable {

    /**
     * Creates a new instance of VentaController
     */
    
     @EJB
    private com.egs.webapp.sessionBeans.VentaFacade ejbFacade;
    private List<Venta> items = null;
    private Venta selected;
    
    @Inject
    private DetalleController currentDetalle;
    @Inject
    private UsuariosController contextUsuario;
    public VentaController() {
    }

    public VentaFacade getEjbFacade() {
        return ejbFacade;
    }
    
    public List<Venta> getItems() {
        if (items == null) {
            items = getEjbFacade().findAll();
        }
        return items;
    }

    public Venta getSelected() {
        return selected;
    }
    
     public Venta prepareCreate() {
        selected = new Venta();
        currentDetalle.init();
        return selected;
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
             return goVentaCreate();
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
//                if (persistAction != JsfUtil.PersistAction.DELETE) {
                    
                    selected.setFecha(null);
                    selected.setHora(null);
                    selected.setIdUsuario(contextUsuario.getCurrentUser());
                   int contadorTotal = 0;
                   selected.setTotal(BigInteger.ZERO);
                   for (Detalleventa dv: currentDetalle.getCurrentItems()){
                   
                  //BigInteger totalParcial = BigInteger.valueOf(dv.getCantArt())* BigInteger.valueOf(dv.getIdProducto().getStockMaximo());
                  // contadorTotal = ;
                   
                   }
                    
                    selected.setTotal(BigInteger.ZERO);
//                    getEjbFacade().edit(selected);
//                } else {
//                    getEjbFacade().remove(selected);
//                }
//                JsfUtil.addSuccessMessage(successMessage);
                
                
                
                
                
                
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
    
    
    public Venta getVenta(java.lang.Long id) {
        return getEjbFacade().find(id);
    }

    public List<Venta> getItemsAvailableSelectMany() {
        return getEjbFacade().findAll();
    }

    public List<Venta> getItemsAvailableSelectOne() {
        return getEjbFacade().findAll();
    }

    @FacesConverter(forClass = Venta.class)
    public static class VentaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ProductoController controller = (ProductoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ventaController");
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
            if (object instanceof Venta) {
                Venta o = (Venta) object;
                return getStringKey(o.getIdVenta());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Venta.class.getName()});
                return null;
            }
        }

    }
    
    public String goVentaCreate(){
    prepareCreate();
    return "venta-create";
    }
    
    public String goVentaList(){
    return "venta-list";
    }

    
    
}
