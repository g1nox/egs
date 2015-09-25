/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.egs.webapp.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author EduardoAlexis
 */
@Entity
@Table(name = "producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p"),
    @NamedQuery(name = "Producto.findByIdProducto", query = "SELECT p FROM Producto p WHERE p.idProducto = :idProducto"),
    @NamedQuery(name = "Producto.findByNombre", query = "SELECT p FROM Producto p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Producto.findByUnidadCompra", query = "SELECT p FROM Producto p WHERE p.unidadCompra = :unidadCompra"),
    @NamedQuery(name = "Producto.findByStockIdeal", query = "SELECT p FROM Producto p WHERE p.stockIdeal = :stockIdeal"),
    @NamedQuery(name = "Producto.findByStockMaximo", query = "SELECT p FROM Producto p WHERE p.stockMaximo = :stockMaximo"),
    @NamedQuery(name = "Producto.findByStockMinimo", query = "SELECT p FROM Producto p WHERE p.stockMinimo = :stockMinimo"),
    @NamedQuery(name = "Producto.findByCapacidadEnvase", query = "SELECT p FROM Producto p WHERE p.capacidadEnvase = :capacidadEnvase"),
    @NamedQuery(name = "Producto.findByStockActual", query = "SELECT p FROM Producto p WHERE p.stockActual = :stockActual")})
public class Producto implements Serializable {
    @OneToMany(mappedBy = "idProducto")
    private List<Detalleventa> detalleventaList;
    @Size(max = 50)
    @Column(name = "disponibilidad")
    private String disponibilidad;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_producto")
    private Long idProducto;
    @Size(max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 50)
    @Column(name = "unidad_compra")
    private String unidadCompra;
    @Column(name = "stock_ideal")
    private Integer stockIdeal;
    @Column(name = "stock_maximo")
    private Integer stockMaximo;
    @Column(name = "stock_minimo")
    private Integer stockMinimo;
    @Column(name = "capacidad_envase")
    private BigInteger capacidadEnvase;
    @Column(name = "stock_actual")
    private Integer stockActual;
    @OneToMany(mappedBy = "idProducto")
    private List<Ingrediente> ingredienteList;
    @JoinColumn(name = "id_proveedor", referencedColumnName = "id_proveedor")
    @ManyToOne
    private Proveedor idProveedor;
    @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria")
    @ManyToOne
    private Categoria idCategoria;

    public Producto() {
    }

    public Producto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUnidadCompra() {
        return unidadCompra;
    }

    public void setUnidadCompra(String unidadCompra) {
        this.unidadCompra = unidadCompra;
    }


    public Integer getStockIdeal() {
        return stockIdeal;
    }

    public void setStockIdeal(Integer stockIdeal) {
        this.stockIdeal = stockIdeal;
    }

    public Integer getStockMaximo() {
        return stockMaximo;
    }

    public void setStockMaximo(Integer stockMaximo) {
        this.stockMaximo = stockMaximo;
    }

    public Integer getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(Integer stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public BigInteger getCapacidadEnvase() {
        return capacidadEnvase;
    }

    public void setCapacidadEnvase(BigInteger capacidadEnvase) {
        this.capacidadEnvase = capacidadEnvase;
    }

    public Integer getStockActual() {
        return stockActual;
    }

    public void setStockActual(Integer stockActual) {
        this.stockActual = stockActual;
    }

    @XmlTransient
    public List<Ingrediente> getIngredienteList() {
        return ingredienteList;
    }

    public void setIngredienteList(List<Ingrediente> ingredienteList) {
        this.ingredienteList = ingredienteList;
    }

    public Proveedor getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Proveedor idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Categoria getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Categoria idCategoria) {
        this.idCategoria = idCategoria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProducto != null ? idProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.idProducto == null && other.idProducto != null) || (this.idProducto != null && !this.idProducto.equals(other.idProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.egs.webapp.entities.Producto[ idProducto=" + idProducto + " ]";
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    @XmlTransient
    public List<Detalleventa> getDetalleventaList() {
        return detalleventaList;
    }

    public void setDetalleventaList(List<Detalleventa> detalleventaList) {
        this.detalleventaList = detalleventaList;
    }
    
}
