package src;

/**
 * La clase Direccion representa una dirección con detalles como la calle, el
 * número, la ciudad y el país.
 */
public class Direccion {
    private String calle;
    private String numero;
    private String ciudad;
    private String pais;

    /**
     * Construye un objeto Direccion con los detalles de la dirección especificados.
     * 
     * @param String : calle  el nombre de la calle
     * @param String : numero el número de la calle
     * @param String : ciudad el nombre de la ciudad
     * @param String : pais   el nombre del país
     */
    public Direccion(String calle, String numero, String ciudad, String pais) {
        this.calle = calle;
        this.numero = numero;
        this.ciudad = ciudad;
        this.pais = pais;
    }

    /**
     * Devuelve el nombre de la calle de la dirección.
     * 
     * @return String : calle el nombre de la calle
     */
    public String getCalle() {
        return calle;
    }

    /**
     * Establece el nombre de la calle de la dirección.
     * 
     * @param String : calle el nombre de la calle a establecer
     */
    public void setCalle(String calle) {
        this.calle = calle;
    }

    /**
     * Devuelve el número de la calle de la dirección.
     * 
     * @return String : numero el número de la calle
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Establece el número de la calle de la dirección.
     * 
     * @param String : numero el número de la calle a establecer
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Devuelve el nombre de la ciudad de la dirección.
     * 
     * @return String : ciudad el nombre de la ciudad
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Establece el nombre de la ciudad de la dirección.
     * 
     * @param String : ciudad el nombre de la ciudad a establecer
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * Devuelve el nombre del país de la dirección.
     * 
     * @return String : pais el nombre del país
     */
    public String getPais() {
        return pais;
    }

    /**
     * Establece el nombre del país de la dirección.
     * 
     * @param String : pais el nombre del país a establecer
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     * Devuelve la representación en forma de cadena de la dirección.
     * 
     * @return String la representación en forma de cadena de la dirección
     */
    @Override
    public String toString() {
        return "Direccion{" +
                "calle='" + calle + '\'' +
                ", numero='" + numero + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", pais='" + pais + '\'' +
                '}';
    }
}