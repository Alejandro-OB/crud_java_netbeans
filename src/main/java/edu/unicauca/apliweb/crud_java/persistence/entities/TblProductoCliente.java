/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.unicauca.apliweb.crud_java.persistence.entities;

import java.io.Serializable;
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
 * @author ortega
 */
@Entity
@Table(name = "tbl_producto_cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblProductoCliente.findAll", query = "SELECT t FROM TblProductoCliente t"),
    @NamedQuery(name = "TblProductoCliente.findByCodigoPc", query = "SELECT t FROM TblProductoCliente t WHERE t.codigoPc = :codigoPc")})
public class TblProductoCliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_pc")
    private Integer codigoPc;
    @JoinColumn(name = "cedula", referencedColumnName = "cedula")
    @ManyToOne(optional = false)
    private TblCliente cedula;
    @JoinColumn(name = "codigo", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private TblProducto codigo;

    public TblProductoCliente() {
    }

    public TblProductoCliente(Integer codigoPc) {
        this.codigoPc = codigoPc;
    }

    public Integer getCodigoPc() {
        return codigoPc;
    }

    public void setCodigoPc(Integer codigoPc) {
        this.codigoPc = codigoPc;
    }

    public TblCliente getCedula() {
        return cedula;
    }

    public void setCedula(TblCliente cedula) {
        this.cedula = cedula;
    }

    public TblProducto getCodigo() {
        return codigo;
    }

    public void setCodigo(TblProducto codigo) {
        this.codigo = codigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoPc != null ? codigoPc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblProductoCliente)) {
            return false;
        }
        TblProductoCliente other = (TblProductoCliente) object;
        if ((this.codigoPc == null && other.codigoPc != null) || (this.codigoPc != null && !this.codigoPc.equals(other.codigoPc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.unicauca.apliweb.crud_java.persitence.entities.TblProductoCliente[ codigoPc=" + codigoPc + " ]";
    }
    
}
