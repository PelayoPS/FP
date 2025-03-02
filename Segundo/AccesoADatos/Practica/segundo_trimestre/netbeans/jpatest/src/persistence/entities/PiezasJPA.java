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
@Table(name = "piezas")
@NamedQueries({
    @NamedQuery(name = "PiezasJPA.findAll", query = "SELECT p FROM PiezasJPA p"),
    @NamedQuery(name = "PiezasJPA.findByCodigo", query = "SELECT p FROM PiezasJPA p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "PiezasJPA.findByNombre", query = "SELECT p FROM PiezasJPA p WHERE p.nombre = :nombre")})
public class PiezasJPA implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "nombre")
    private String nombre;

    public PiezasJPA() {
    }

    public PiezasJPA(Integer codigo) {
        this.codigo = codigo;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PiezasJPA)) {
            return false;
        }
        PiezasJPA other = (PiezasJPA) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.entities.PiezasJPA[ codigo=" + codigo + " ]";
    }
    
}
