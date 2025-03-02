/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.modelo;

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
 * @author pelay
 */
@Entity
@Table(name = "alumno")
@NamedQueries({
    @NamedQuery(name = "Alumno.findAll", query = "SELECT a FROM Alumno a"),
    @NamedQuery(name = "Alumno.findByNumExpediente", query = "SELECT a FROM Alumno a WHERE a.numExpediente = :numExpediente"),
    @NamedQuery(name = "Alumno.findByApellidos", query = "SELECT a FROM Alumno a WHERE a.apellidos = :apellidos"),
    @NamedQuery(name = "Alumno.findByNombre", query = "SELECT a FROM Alumno a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "Alumno.findByCurso", query = "SELECT a FROM Alumno a WHERE a.curso = :curso"),
    @NamedQuery(name = "Alumno.findByPendiente", query = "SELECT a FROM Alumno a WHERE a.pendiente = :pendiente")})
public class Alumno implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "numExpediente")
    private Integer numExpediente;
    @Column(name = "apellidos")
    private String apellidos;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "curso")
    private String curso;
    @Column(name = "pendiente")
    private Integer pendiente;

    public Alumno() {
    }

    public Alumno(Integer numExpediente) {
        this.numExpediente = numExpediente;
    }

    public Integer getNumExpediente() {
        return numExpediente;
    }

    public void setNumExpediente(Integer numExpediente) {
        this.numExpediente = numExpediente;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public Integer getPendiente() {
        return pendiente;
    }

    public void setPendiente(Integer pendiente) {
        this.pendiente = pendiente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numExpediente != null ? numExpediente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alumno)) {
            return false;
        }
        Alumno other = (Alumno) object;
        if ((this.numExpediente == null && other.numExpediente != null) || (this.numExpediente != null && !this.numExpediente.equals(other.numExpediente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.modelo.Alumno[ numExpediente=" + numExpediente + " ]";
    }
    
}
