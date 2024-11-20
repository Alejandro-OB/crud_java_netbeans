/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.unicauca.apliweb.crud_java.persistence.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ortega
 */
@Entity
@Table(name = "tbl_producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblProducto.findAll", query = "SELECT t FROM TblProducto t"),
    @NamedQuery(name = "TblProducto.findByCodigo", query = "SELECT t FROM TblProducto t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TblProducto.findByNombre", query = "SELECT t FROM TblProducto t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TblProducto.findByTemporada", query = "SELECT t FROM TblProducto t WHERE t.temporada = :temporada")})
public class TblProducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "temporada")
    private String temporada;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigo")
    private Collection<TblAgricultorProducto> tblAgricultorProductoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigo")
    private Collection<TblProductoCliente> tblProductoClienteCollection;

    public TblProducto() {
    }

    public TblProducto(Integer codigo) {
        this.codigo = codigo;
    }

    public TblProducto(Integer codigo, String nombre, String temporada) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.temporada = temporada;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTemporada() {
        return temporada;
    }

    public void setTemporada(String temporada) {
        this.temporada = temporada;
    }

    @XmlTransient
    public Collection<TblAgricultorProducto> getTblAgricultorProductoCollection() {
        return tblAgricultorProductoCollection;
    }

    public void setTblAgricultorProductoCollection(Collection<TblAgricultorProducto> tblAgricultorProductoCollection) {
        this.tblAgricultorProductoCollection = tblAgricultorProductoCollection;
    }

    @XmlTransient
    public Collection<TblProductoCliente> getTblProductoClienteCollection() {
        return tblProductoClienteCollection;
    }

    public void setTblProductoClienteCollection(Collection<TblProductoCliente> tblProductoClienteCollection) {
        this.tblProductoClienteCollection = tblProductoClienteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblProducto)) {
            return false;
        }
        TblProducto other = (TblProducto) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.unicauca.apliweb.crud_java.persitence.entities.TblProducto[ codigo=" + codigo + " ]";
    }
    
}
