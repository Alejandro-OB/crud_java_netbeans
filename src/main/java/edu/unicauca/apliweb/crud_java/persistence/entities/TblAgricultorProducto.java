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
@Table(name = "tbl_agricultor_producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblAgricultorProducto.findAll", query = "SELECT t FROM TblAgricultorProducto t"),
    @NamedQuery(name = "TblAgricultorProducto.findByCodigoAp", query = "SELECT t FROM TblAgricultorProducto t WHERE t.codigoAp = :codigoAp")})
public class TblAgricultorProducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_ap")
    private Integer codigoAp;
    @JoinColumn(name = "id_agricultor", referencedColumnName = "id_agricultor")
    @ManyToOne(optional = false)
    private TblAgricultor idAgricultor;
    @JoinColumn(name = "codigo", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private TblProducto codigo;

    public TblAgricultorProducto() {
    }

    public TblAgricultorProducto(Integer codigoAp) {
        this.codigoAp = codigoAp;
    }

    public Integer getCodigoAp() {
        return codigoAp;
    }

    public void setCodigoAp(Integer codigoAp) {
        this.codigoAp = codigoAp;
    }

    public TblAgricultor getIdAgricultor() {
        return idAgricultor;
    }

    public void setIdAgricultor(TblAgricultor idAgricultor) {
        this.idAgricultor = idAgricultor;
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
        hash += (codigoAp != null ? codigoAp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblAgricultorProducto)) {
            return false;
        }
        TblAgricultorProducto other = (TblAgricultorProducto) object;
        if ((this.codigoAp == null && other.codigoAp != null) || (this.codigoAp != null && !this.codigoAp.equals(other.codigoAp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.unicauca.apliweb.crud_java.persitence.entities.TblAgricultorProducto[ codigoAp=" + codigoAp + " ]";
    }
    
}
