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
@Table(name = "libros")
@NamedQueries({
    @NamedQuery(name = "Libros.findAll", query = "SELECT l FROM Libros l"),
    @NamedQuery(name = "Libros.findByNRegistro", query = "SELECT l FROM Libros l WHERE l.nRegistro = :nRegistro"),
    @NamedQuery(name = "Libros.findByTitulo", query = "SELECT l FROM Libros l WHERE l.titulo = :titulo"),
    @NamedQuery(name = "Libros.findByAutor", query = "SELECT l FROM Libros l WHERE l.autor = :autor"),
    @NamedQuery(name = "Libros.findBySignatura", query = "SELECT l FROM Libros l WHERE l.signatura = :signatura")})
public class Libros implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nRegistro")
    private Integer nRegistro;
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "autor")
    private String autor;
    @Column(name = "signatura")
    private String signatura;

    public Libros() {
    }

    public Libros(Integer nRegistro) {
        this.nRegistro = nRegistro;
    }

    public Integer getNRegistro() {
        return nRegistro;
    }

    public void setNRegistro(Integer nRegistro) {
        this.nRegistro = nRegistro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getSignatura() {
        return signatura;
    }

    public void setSignatura(String signatura) {
        this.signatura = signatura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nRegistro != null ? nRegistro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Libros)) {
            return false;
        }
        Libros other = (Libros) object;
        if ((this.nRegistro == null && other.nRegistro != null) || (this.nRegistro != null && !this.nRegistro.equals(other.nRegistro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.modelo.Libros[ nRegistro=" + nRegistro + " ]";
    }
    
}
