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
@Table(name = "tbl_agricultor_cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblAgricultorCliente.findAll", query = "SELECT t FROM TblAgricultorCliente t"),
    @NamedQuery(name = "TblAgricultorCliente.findByCodigoAc", query = "SELECT t FROM TblAgricultorCliente t WHERE t.codigoAc = :codigoAc")})
public class TblAgricultorCliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_ac")
    private Integer codigoAc;
    @JoinColumn(name = "id_agricultor", referencedColumnName = "id_agricultor")
    @ManyToOne(optional = false)
    private TblAgricultor idAgricultor;
    @JoinColumn(name = "cedula", referencedColumnName = "cedula")
    @ManyToOne(optional = false)
    private TblCliente cedula;

    public TblAgricultorCliente() {
    }

    public TblAgricultorCliente(Integer codigoAc) {
        this.codigoAc = codigoAc;
    }

    public Integer getCodigoAc() {
        return codigoAc;
    }

    public void setCodigoAc(Integer codigoAc) {
        this.codigoAc = codigoAc;
    }

    public TblAgricultor getIdAgricultor() {
        return idAgricultor;
    }

    public void setIdAgricultor(TblAgricultor idAgricultor) {
        this.idAgricultor = idAgricultor;
    }

    public TblCliente getCedula() {
        return cedula;
    }

    public void setCedula(TblCliente cedula) {
        this.cedula = cedula;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoAc != null ? codigoAc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblAgricultorCliente)) {
            return false;
        }
        TblAgricultorCliente other = (TblAgricultorCliente) object;
        if ((this.codigoAc == null && other.codigoAc != null) || (this.codigoAc != null && !this.codigoAc.equals(other.codigoAc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.unicauca.apliweb.crud_java.persitence.entities.TblAgricultorCliente[ codigoAc=" + codigoAc + " ]";
    }
    
}
