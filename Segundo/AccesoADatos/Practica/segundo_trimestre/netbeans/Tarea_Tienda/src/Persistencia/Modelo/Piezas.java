/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia.Modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Clase que representa una pieza en el sistema.
 * Esta entidad está mapeada a la tabla 'piezas' en la base de datos.
 * Contiene información básica sobre las piezas como su código único y nombre.
 */
@Entity
@Table(name = "piezas")
@NamedQueries({
        @NamedQuery(name = "Piezas.findAll", query = "SELECT p FROM Piezas p"),
        @NamedQuery(name = "Piezas.findByCodigo", query = "SELECT p FROM Piezas p WHERE p.codigo = :codigo"),
        @NamedQuery(name = "Piezas.findByNombre", query = "SELECT p FROM Piezas p WHERE p.nombre = :nombre") })
public class Piezas implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Código único que identifica la pieza */
    @Id
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;

    /** Nombre descriptivo de la pieza */
    @Column(name = "nombre")
    private String nombre;

    /**
     * Constructor por defecto.
     */
    public Piezas() {
    }

    /**
     * Constructor que inicializa una pieza con su código.
     * 
     * @param codigo Código único de la pieza
     */
    public Piezas(Integer codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtiene el código de la pieza.
     * 
     * @return El código único de la pieza
     */
    public Integer getCodigo() {
        return codigo;
    }

    /**
     * Establece el código de la pieza.
     * 
     * @param codigo El nuevo código de la pieza
     */
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtiene el nombre de la pieza.
     * 
     * @return El nombre de la pieza
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la pieza.
     * 
     * @param nombre El nuevo nombre de la pieza
     */
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
        if (!(object instanceof Piezas)) {
            return false;
        }
        Piezas other = (Piezas) object;
        if ((this.codigo == null && other.codigo != null)
                || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistencia.Modelo.Piezas[ codigo=" + codigo + " ]";
    }

}
