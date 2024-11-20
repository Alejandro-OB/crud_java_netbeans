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
@Table(name = "tbl_cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblCliente.findAll", query = "SELECT t FROM TblCliente t"),
    @NamedQuery(name = "TblCliente.findByCedula", query = "SELECT t FROM TblCliente t WHERE t.cedula = :cedula"),
    @NamedQuery(name = "TblCliente.findByNombre", query = "SELECT t FROM TblCliente t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TblCliente.findByTelefono", query = "SELECT t FROM TblCliente t WHERE t.telefono = :telefono"),
    @NamedQuery(name = "TblCliente.findByDireccion", query = "SELECT t FROM TblCliente t WHERE t.direccion = :direccion")})
public class TblCliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cedula")
    private Integer cedula;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 15)
    @Column(name = "telefono")
    private String telefono;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "direccion")
    private String direccion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cedula")
    private Collection<TblAgricultorCliente> tblAgricultorClienteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cedula")
    private Collection<TblProductoCliente> tblProductoClienteCollection;

    public TblCliente() {
    }

    public TblCliente(Integer cedula) {
        this.cedula = cedula;
    }

    public TblCliente(Integer cedula, String nombre, String direccion) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public Integer getCedula() {
        return cedula;
    }

    public void setCedula(Integer cedula) {
        this.cedula = cedula;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @XmlTransient
    public Collection<TblAgricultorCliente> getTblAgricultorClienteCollection() {
        return tblAgricultorClienteCollection;
    }

    public void setTblAgricultorClienteCollection(Collection<TblAgricultorCliente> tblAgricultorClienteCollection) {
        this.tblAgricultorClienteCollection = tblAgricultorClienteCollection;
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
        hash += (cedula != null ? cedula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblCliente)) {
            return false;
        }
        TblCliente other = (TblCliente) object;
        if ((this.cedula == null && other.cedula != null) || (this.cedula != null && !this.cedula.equals(other.cedula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.unicauca.apliweb.crud_java.persitence.entities.TblCliente[ cedula=" + cedula + " ]";
    }
    
}
