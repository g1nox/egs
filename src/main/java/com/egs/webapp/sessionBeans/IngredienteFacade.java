/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.egs.webapp.sessionBeans;

import com.egs.webapp.entities.Ingrediente;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author EduardoAlexis
 */
@Stateless
public class IngredienteFacade extends AbstractFacade<Ingrediente> {
    @PersistenceContext(unitName = "MyCrudHabanaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IngredienteFacade() {
        super(Ingrediente.class);
    }
    
}
