/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.egs.webapp.sessionBeans;

import com.egs.webapp.entities.Venta;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author EduardoAlexis
 */
@Stateless
public class VentaFacade extends AbstractFacade<Venta> {
    @PersistenceContext(unitName = "com.egs.webapp_HabanaSalsa_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VentaFacade() {
        super(Venta.class);
    }
    
    
//     public List<Usuario> usuariosActivos(String usuario) {
//        // me tiene que devolver una lista con los usuarios activos
//        final Estado estado = ef.find(1);
//        final String admin = "admin";
//        return getEntityManager().createQuery("Select u FROM Usuario u Where u.username <> ?1 AND u.username <> ?2 AND u.idEstado = ?3")
//                .setParameter(1, usuario)
//                .setParameter(2, admin)
//                .setParameter(3, estado)
//                .getResultList();
//
//    }
//    
    public Venta findLastVenta (){
    Venta venta = (Venta)getEntityManager().createQuery("SELECT v FROM Venta v Where v.idVenta = (SELECT MAX (vv.idVenta) FROM Venta vv)").getSingleResult();
    return venta;
    }
    
    
    
    
    
    
}
