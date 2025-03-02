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
@Table(name = "proveedores")
@NamedQueries({
    @NamedQuery(name = "ProveedoresJPA.findAll", query = "SELECT p FROM ProveedoresJPA p"),
    @NamedQuery(name = "ProveedoresJPA.findById", query = "SELECT p FROM ProveedoresJPA p WHERE p.id = :id"),
    @NamedQuery(name = "ProveedoresJPA.findByNombre", query = "SELECT p FROM ProveedoresJPA p WHERE p.nombre = :nombre")})
public class ProveedoresJPA implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nombre")
    private String nombre;

    public ProveedoresJPA() {
    }

    public ProveedoresJPA(Integer id) {
        this.id = id;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProveedoresJPA)) {
            return false;
        }
        ProveedoresJPA other = (ProveedoresJPA) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.entities.ProveedoresJPA[ id=" + id + " ]";
    }
    
}
