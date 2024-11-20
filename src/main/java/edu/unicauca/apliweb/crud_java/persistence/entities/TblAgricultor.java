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
@Table(name = "tbl_agricultor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblAgricultor.findAll", query = "SELECT t FROM TblAgricultor t"),
    @NamedQuery(name = "TblAgricultor.findByIdAgricultor", query = "SELECT t FROM TblAgricultor t WHERE t.idAgricultor = :idAgricultor"),
    @NamedQuery(name = "TblAgricultor.findByNombre", query = "SELECT t FROM TblAgricultor t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TblAgricultor.findByTelefono", query = "SELECT t FROM TblAgricultor t WHERE t.telefono = :telefono")})
public class TblAgricultor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_agricultor")
    private Integer idAgricultor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 15)
    @Column(name = "telefono")
    private String telefono;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAgricultor")
    private Collection<TblAgricultorProducto> tblAgricultorProductoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAgricultor")
    private Collection<TblAgricultorCliente> tblAgricultorClienteCollection;

    public TblAgricultor() {
    }

    public TblAgricultor(Integer idAgricultor) {
        this.idAgricultor = idAgricultor;
    }

    public TblAgricultor(Integer idAgricultor, String nombre) {
        this.idAgricultor = idAgricultor;
        this.nombre = nombre;
    }

    public Integer getIdAgricultor() {
        return idAgricultor;
    }

    public void setIdAgricultor(Integer idAgricultor) {
        this.idAgricultor = idAgricultor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @XmlTransient
    public Collection<TblAgricultorProducto> getTblAgricultorProductoCollection() {
        return tblAgricultorProductoCollection;
    }

    public void setTblAgricultorProductoCollection(Collection<TblAgricultorProducto> tblAgricultorProductoCollection) {
        this.tblAgricultorProductoCollection = tblAgricultorProductoCollection;
    }

    @XmlTransient
    public Collection<TblAgricultorCliente> getTblAgricultorClienteCollection() {
        return tblAgricultorClienteCollection;
    }

    public void setTblAgricultorClienteCollection(Collection<TblAgricultorCliente> tblAgricultorClienteCollection) {
        this.tblAgricultorClienteCollection = tblAgricultorClienteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAgricultor != null ? idAgricultor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblAgricultor)) {
            return false;
        }
        TblAgricultor other = (TblAgricultor) object;
        if ((this.idAgricultor == null && other.idAgricultor != null) || (this.idAgricultor != null && !this.idAgricultor.equals(other.idAgricultor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.unicauca.apliweb.crud_java.persistence.entities.TblAgricultor[ idAgricultor=" + idAgricultor + " ]";
    }
    
}
