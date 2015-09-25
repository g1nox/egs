/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.egs.webapp.entities;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author EduardoAlexis
 */
@Entity
@Table(name = "detalleventa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detalleventa.findAll", query = "SELECT d FROM Detalleventa d"),
    @NamedQuery(name = "Detalleventa.findByIdDetalle", query = "SELECT d FROM Detalleventa d WHERE d.idDetalle = :idDetalle"),
    @NamedQuery(name = "Detalleventa.findByCantArt", query = "SELECT d FROM Detalleventa d WHERE d.cantArt = :cantArt"),
    @NamedQuery(name = "Detalleventa.findByPrecioUni", query = "SELECT d FROM Detalleventa d WHERE d.precioUni = :precioUni"),
    @NamedQuery(name = "Detalleventa.findByPrecioTotal", query = "SELECT d FROM Detalleventa d WHERE d.precioTotal = :precioTotal")})
public class Detalleventa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_detalle")
    private Long idDetalle;
    @Column(name = "cant_art")
    private BigInteger cantArt;
    @Column(name = "precio_uni")
    private BigInteger precioUni;
    @Column(name = "precio_total")
    private BigInteger precioTotal;
    @JoinColumn(name = "id_venta", referencedColumnName = "id_venta")
    @ManyToOne
    private Venta idVenta;
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    @ManyToOne
    private Producto idProducto;

    public Detalleventa() {
    }

    public Detalleventa(Long idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Long getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Long idDetalle) {
        this.idDetalle = idDetalle;
    }

    public BigInteger getCantArt() {
        return cantArt;
    }

    public void setCantArt(BigInteger cantArt) {
        this.cantArt = cantArt;
    }

    public BigInteger getPrecioUni() {
        return precioUni;
    }

    public void setPrecioUni(BigInteger precioUni) {
        this.precioUni = precioUni;
    }

    public BigInteger getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(BigInteger precioTotal) {
        this.precioTotal = precioTotal;
    }

    public Venta getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Venta idVenta) {
        this.idVenta = idVenta;
    }

    public Producto getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Producto idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalle != null ? idDetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalleventa)) {
            return false;
        }
        Detalleventa other = (Detalleventa) object;
        if ((this.idDetalle == null && other.idDetalle != null) || (this.idDetalle != null && !this.idDetalle.equals(other.idDetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.egs.webapp.entities.Detalleventa[ idDetalle=" + idDetalle + " ]";
    }
    
}
