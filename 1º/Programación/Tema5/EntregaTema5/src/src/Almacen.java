package src;

import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeSet;

/**
 * La clase Almacen representa un almacén de paquetes con un identificador único y una lista de conductores.
 */
public class Almacen {
    private HashMap<Integer, Paquete> paquetes;
    private TreeSet<Conductor> conductores;

    /**
     * Crea un nuevo objeto Almacen con un HashMap vacío de paquetes y un TreeSet vacío de conductores.
     */
    public Almacen() {
        this.paquetes = new HashMap<Integer,Paquete>();
        this.conductores = new TreeSet<Conductor>(new ConductorComparator());
    }

    /**
     * Agrega un nuevo paquete al HashMap de paquetes con su identificador como clave.
     *
     * @param Paquete : paquete el paquete a agregar
     */
    public void addPaquete(Paquete paquete) {
        this.paquetes.put(paquete.getId(), paquete);
    }

    /**
     * Obtiene un paquete del HashMap de paquetes por su identificador.
     *
     * @param int : id el identificador del paquete a obtener
     * @return Paquete el paquete con el identificador especificado
     */
    public Paquete getPaquete(int id) {
        return this.paquetes.get(id);
    }

    /**
     * Agrega un nuevo conductor al TreeSet de conductores con un comparator personalizado para ordenar por prioridad.
     *
     * @param Conductor : conductor el conductor a agregar
     */
    public void addConductor(Conductor conductor) {
        this.conductores.add(conductor);
    }

    /**
     * Obtiene un conductor del TreeSet de conductores por su prioridad.
     *
     * @param int : prioridad la prioridad del conductor a obtener
     * @return Conductor el conductor con la prioridad especificada
     */
    public Conductor getConductor(int prioridad) {
        for (Conductor conductor : this.conductores) {
            if (conductor.getPrioridad() == prioridad) {
                return conductor;
            }
        }
        return null;
    }

    /**
     * Obtiene el HashMap de paquetes del almacén.
     *
     * @return HashMap<Integer, Paquete> el HashMap de paquetes
     */
    public HashMap<Integer, Paquete> getPaquetes() {
        return this.paquetes;
    }

    /**
     * Obtiene el TreeSet de conductores del almacén.
     *
     * @return TreeSet<Conductor> el TreeSet de conductores
     */
    public TreeSet<Conductor> getConductores() {
        return this.conductores;
    }

    /**
     * Elimina el conductor pasado como parámetro
     * 
     * @param Conductor : conductor a eliminar
     */
    public void removeConductor(Conductor conductor) {
        this.conductores.remove(conductor);
    }

    /**
     * Comparator para ordenar los conductores por prioridad.
     */
    private static class ConductorComparator implements Comparator<Conductor> {
        @Override
        /**
         * Compara dos conductores por su prioridad.
         *
         * @param Conductor : c1 el primer conductor a comparar
         * @param Conductor : c2 el segundo conductor a comparar
         * @return int : la diferencia de prioridad entre los conductores
         */
        public int compare(Conductor c1, Conductor c2) {
            return c1.compareTo(c2);
        }
    }
}