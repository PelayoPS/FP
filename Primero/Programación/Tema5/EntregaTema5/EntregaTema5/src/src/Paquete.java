package src;

import java.util.LinkedList;

/**
 * La clase Paquete representa un paquete con un identificador único y una ruta
 * de direcciones.
 */
public class Paquete {
    private int id;
    private LinkedList<Direccion> ruta;

    /**
     * Crea un nuevo objeto Paquete con el identificador y la ruta especificados.
     * 
     * @param int : id   el identificador del paquete
     * @param LinkedList<Direccion> : ruta la ruta de direcciones del paquete
     */
    public Paquete(int id, LinkedList<Direccion> ruta) {
        this.id = id;
        this.ruta = ruta;
    }

    /**
     * Obtiene el identificador del paquete.
     * 
     * @return int : id el identificador del paquete
     */
    public int getId() {
        return id;
    }

    /**
     * Obtiene la ruta de direcciones del paquete.
     * 
     * @return LinkedList<Direccion> : ruta la ruta de direcciones del paquete
     */
    public LinkedList<Direccion> getRuta() {
        return ruta;
    }

    /**
     * Muestra el paquete como una cadena
     * 
     * @return String : cadena
     */

    @Override
    public String toString() {
        return "Paquete [id=" + id + ", ruta=" + ruta + "]";
    }
}