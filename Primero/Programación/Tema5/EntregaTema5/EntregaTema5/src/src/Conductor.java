package src;

import java.util.ArrayList;
import java.util.List;

/**
 * La clase Conductor representa un conductor de la empresa de mensajería, con
 * atributos como
 * ID, nombre, lista de paquetes asignados y prioridad.
 */
public class Conductor implements Comparable<Conductor> {
    private int id;
    private String nombre;
    private List<Paquete> paquetesAsignados;
    private int prioridad;

    /**
     * Construye un objeto Conductor con los detalles especificados.
     * 
     * @param int    : id el ID del conductor
     * @param String : nombre el nombre del conductor
     */
    public Conductor(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.paquetesAsignados = new ArrayList<Paquete>();
        this.prioridad = 0;
    }

    /**
     * Devuelve el ID del conductor.
     * 
     * @return int : id el ID del conductor
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID del conductor.
     * 
     * @param int : id el ID del conductor a establecer
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Devuelve el nombre del conductor.
     * 
     * @return String : nombre el nombre del conductor
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del conductor.
     * 
     * @param String : nombre el nombre del conductor a establecer
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve la lista de paquetes asignados al conductor.
     * 
     * @return List<Paquete> : paquetesAsignados la lista de paquetes asignados
     */
    public List<Paquete> getPaquetesAsignados() {
        return paquetesAsignados;
    }

    /**
     * Establece la lista de paquetes asignados al conductor.
     * 
     * @param List<Paquete> : paquetesAsignados la lista de paquetes asignados a
     *                      establecer
     */
    public void setPaquetesAsignados(List<Paquete> paquetesAsignados) {
        this.paquetesAsignados = paquetesAsignados;
    }

    /**
     * Devuelve la prioridad del conductor.
     * 
     * @return int : prioridad la prioridad del conductor
     */
    public int getPrioridad() {
        return prioridad;
    }

    /**
     * Establece la prioridad del conductor. La prioridad se actualiza cada vez que
     * se
     * asigna un nuevo paquete al conductor.
     * 
     * @param int : prioridad la prioridad a establecer
     */
    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    /**
     * Añade un paquete a la lista de paquetes asignados al conductor y actualiza la
     * prioridad.
     * 
     * @param Paquete : paquete el paquete a añadir
     */
    public void addPaquete(Paquete paquete) {
        this.paquetesAsignados.add(paquete);
        this.prioridad++;
    }

    /**
     * Devuelve la representación en forma de cadena del conductor.
     * 
     * @return String : la representación en forma de cadena del conductor
     */
    @Override
    public String toString() {
        return "Conductor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", prioridad=" + prioridad + '}';
    }

    /**
     * Compara dos conductores por prioridad.
     * 
     * @param Conductor : o el conductor con el que comparar
     * @return int : la diferencia de prioridad entre los conductores
     */
    @Override
    public int compareTo(Conductor o) {
        return Integer.compare(id, o.getId());
    }
}