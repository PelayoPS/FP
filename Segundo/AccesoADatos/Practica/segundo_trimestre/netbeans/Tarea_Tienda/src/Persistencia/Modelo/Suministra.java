/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia.Modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Clase que representa la relación de suministro entre Piezas y Proveedores.
 * Esta entidad almacena información sobre qué proveedor suministra qué pieza y
 * a qué precio.
 * 
 * @author PelayoPS
 */
@Entity
@Table(name = "suministra")
@NamedQueries({
        @NamedQuery(name = "Suministra.findAll", query = "SELECT s FROM Suministra s"),
        @NamedQuery(name = "Suministra.findByCodigoSuministro", query = "SELECT s FROM Suministra s WHERE s.codigoSuministro = :codigoSuministro"),
        @NamedQuery(name = "Suministra.findByCodigoPieza", query = "SELECT s FROM Suministra s WHERE s.codigoPieza = :codigoPieza"),
        @NamedQuery(name = "Suministra.findByIdProveedor", query = "SELECT s FROM Suministra s WHERE s.idProveedor = :idProveedor"),
        @NamedQuery(name = "Suministra.findByPrecio", query = "SELECT s FROM Suministra s WHERE s.precio = :precio") })
public class Suministra implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Código único que identifica cada suministro.
     * Se genera automáticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_suministro")
    private Integer codigoSuministro;

    /**
     * Código de la pieza que se suministra.
     */
    @Column(name = "codigo_pieza")
    private Integer codigoPieza;

    /**
     * ID del proveedor que realiza el suministro.
     */
    @Column(name = "id_proveedor")
    private Integer idProveedor;

    /**
     * Precio del suministro.
     */
    @Column(name = "precio")
    private Integer precio;

    /**
     * Constructor por defecto.
     */
    public Suministra() {
    }

    /**
     * Constructor con código de suministro.
     * 
     * @param codigoSuministro Código único del suministro
     */
    public Suministra(Integer codigoSuministro) {
        this.codigoSuministro = codigoSuministro;
    }

    /**
     * Obtiene el código del suministro.
     * 
     * @return Código del suministro
     */
    public Integer getCodigoSuministro() {
        return codigoSuministro;
    }

    /**
     * Establece el código del suministro.
     * 
     * @param codigoSuministro Nuevo código de suministro
     */
    public void setCodigoSuministro(Integer codigoSuministro) {
        this.codigoSuministro = codigoSuministro;
    }

    /**
     * Obtiene el código de la pieza suministrada.
     * 
     * @return Código de la pieza
     */
    public Integer getCodigoPieza() {
        return codigoPieza;
    }

    /**
     * Establece el código de la pieza suministrada.
     * 
     * @param codigoPieza Nuevo código de pieza
     */
    public void setCodigoPieza(Integer codigoPieza) {
        this.codigoPieza = codigoPieza;
    }

    /**
     * Obtiene el ID del proveedor.
     * 
     * @return ID del proveedor
     */
    public Integer getIdProveedor() {
        return idProveedor;
    }

    /**
     * Establece el ID del proveedor.
     * 
     * @param idProveedor Nuevo ID de proveedor
     */
    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    /**
     * Obtiene el precio del suministro.
     * 
     * @return Precio del suministro
     */
    public Integer getPrecio() {
        return precio;
    }

    /**
     * Establece el precio del suministro.
     * 
     * @param precio Nuevo precio
     */
    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoSuministro != null ? codigoSuministro.hashCode() : 0);
        return hash;
    }

    /**
     * Compara este suministro con otro objeto para determinar si son iguales.
     * 
     * @param object Objeto a comparar
     * @return true si son iguales, false en caso contrario
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Suministra)) {
            return false;
        }
        Suministra other = (Suministra) object;
        if ((this.codigoSuministro == null && other.codigoSuministro != null)
                || (this.codigoSuministro != null && !this.codigoSuministro.equals(other.codigoSuministro))) {
            return false;
        }
        return true;
    }

    /**
     * Devuelve una representación en cadena del suministro.
     * 
     * @return Cadena que representa el suministro
     */
    @Override
    public String toString() {
        return "Persistencia.Modelo.Suministra[ codigoSuministro=" + codigoSuministro + " ]";
    }

}
