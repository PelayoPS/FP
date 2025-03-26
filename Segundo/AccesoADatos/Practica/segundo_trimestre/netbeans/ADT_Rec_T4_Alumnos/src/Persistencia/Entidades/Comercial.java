/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia.Entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author pelay
 */
@Entity
@Table(name = "comercial")
@NamedQueries({
    @NamedQuery(name = "Comercial.findAll", query = "SELECT c FROM Comercial c"),
    @NamedQuery(name = "Comercial.findById", query = "SELECT c FROM Comercial c WHERE c.id = :id"),
    @NamedQuery(name = "Comercial.findByNombre", query = "SELECT c FROM Comercial c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Comercial.findByApellido1", query = "SELECT c FROM Comercial c WHERE c.apellido1 = :apellido1"),
    @NamedQuery(name = "Comercial.findByApellido2", query = "SELECT c FROM Comercial c WHERE c.apellido2 = :apellido2"),
    @NamedQuery(name = "Comercial.findByComision", query = "SELECT c FROM Comercial c WHERE c.comision = :comision")})
public class Comercial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "apellido1")
    private String apellido1;
    @Column(name = "apellido2")
    private String apellido2;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "comision")
    private Float comision;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idComercial")
    private Collection<Pedido> pedidoCollection;

    public Comercial() {
    }

    public Comercial(Integer id) {
        this.id = id;
    }

    public Comercial(Integer id, String nombre, String apellido1) {
        this.id = id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public Float getComision() {
        return comision;
    }

    public void setComision(Float comision) {
        this.comision = comision;
    }

    public Collection<Pedido> getPedidoCollection() {
        return pedidoCollection;
    }

    public void setPedidoCollection(Collection<Pedido> pedidoCollection) {
        this.pedidoCollection = pedidoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comercial)) {
            return false;
        }
        Comercial other = (Comercial) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistencia.Entidades.Comercial[ id=" + id + " ]";
    }
    
}
