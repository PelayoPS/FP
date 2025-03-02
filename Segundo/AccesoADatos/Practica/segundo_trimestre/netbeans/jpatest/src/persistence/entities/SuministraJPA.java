/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistence.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author pelayo
 */
@Entity
@Table(name = "suministra")
@NamedQueries({
    @NamedQuery(name = "SuministraJPA.findAll", query = "SELECT s FROM SuministraJPA s"),
    @NamedQuery(name = "SuministraJPA.findByCodigoPieza", query = "SELECT s FROM SuministraJPA s WHERE s.codigoPieza = :codigoPieza"),
    @NamedQuery(name = "SuministraJPA.findByIdProveedor", query = "SELECT s FROM SuministraJPA s WHERE s.idProveedor = :idProveedor"),
    @NamedQuery(name = "SuministraJPA.findByPrecio", query = "SELECT s FROM SuministraJPA s WHERE s.precio = :precio")})
public class SuministraJPA implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codigo_pieza")
    private Integer codigoPieza;
    @Column(name = "id_proveedor")
    private Integer idProveedor;
    @Column(name = "precio")
    private Integer precio;

    public SuministraJPA() {
    }

    public SuministraJPA(Integer codigoPieza) {
        this.codigoPieza = codigoPieza;
    }

    public Integer getCodigoPieza() {
        return codigoPieza;
    }

    public void setCodigoPieza(Integer codigoPieza) {
        this.codigoPieza = codigoPieza;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoPieza != null ? codigoPieza.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SuministraJPA)) {
            return false;
        }
        SuministraJPA other = (SuministraJPA) object;
        if ((this.codigoPieza == null && other.codigoPieza != null) || (this.codigoPieza != null && !this.codigoPieza.equals(other.codigoPieza))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.entities.SuministraJPA[ codigoPieza=" + codigoPieza + " ]";
    }
    
}
