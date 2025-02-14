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
 * Clase que representa a un proveedor en el sistema.
 * Esta clase es una entidad JPA que se mapea a la tabla 'proveedores' en la
 * base de datos.
 * 
 * @author PelayoPS
 */
@Entity
@Table(name = "proveedores")
@NamedQueries({
        @NamedQuery(name = "Proveedores.findAll", query = "SELECT p FROM Proveedores p"),
        @NamedQuery(name = "Proveedores.findById", query = "SELECT p FROM Proveedores p WHERE p.id = :id"),
        @NamedQuery(name = "Proveedores.findByNombre", query = "SELECT p FROM Proveedores p WHERE p.nombre = :nombre")
})
public class Proveedores implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Identificador único del proveedor */
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    /** Nombre del proveedor */
    @Column(name = "nombre")
    private String nombre;

    /**
     * Constructor por defecto.
     */
    public Proveedores() {
    }

    /**
     * Constructor que inicializa un proveedor con su ID.
     * 
     * @param id El identificador único del proveedor
     */
    public Proveedores(Integer id) {
        this.id = id;
    }

    /**
     * Obtiene el ID del proveedor.
     * 
     * @return El identificador único del proveedor
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el ID del proveedor.
     * 
     * @param id El nuevo identificador único del proveedor
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del proveedor.
     * 
     * @return El nombre del proveedor
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del proveedor.
     * 
     * @param nombre El nuevo nombre del proveedor
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Calcula el código hash del objeto basado en su ID.
     * 
     * @return El código hash calculado
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Compara este proveedor con otro objeto para determinar si son iguales.
     * 
     * @param object El objeto a comparar
     * @return true si son iguales, false en caso contrario
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Proveedores)) {
            return false;
        }
        Proveedores other = (Proveedores) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * Devuelve una representación en cadena del proveedor.
     * 
     * @return Una cadena que representa al proveedor
     */
    @Override
    public String toString() {
        return "Persistencia.Modelo.Proveedores[ id=" + id + " ]";
    }

}
