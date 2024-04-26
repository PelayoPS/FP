package src;

import java.util.HashMap;

public class GestorApartamentos {

    private HashMap<Referencia, Apartamento> apartamentos;

    public GestorApartamentos() {
        apartamentos = new HashMap<Referencia, Apartamento>();
    }

    /**
     * Añade un apartamento al listado
     * No se admiten apartamentos con la misma referencia
     * Se debe verificar que la referencia es correcta
     * 
     * @param int    : piso del apartamento
     * @param char   : letra del apartamento
     * @param int    : capacidad máxima del apartamento
     * @param double : precio del apartamento
     */
    public void añadirApartamento(int piso, char letra, int capacidadMax, double precio) {
        Referencia ref = new Referencia(piso, letra);
        boolean valid = true;
        // Si la referencia ya existe, no se añade el apartamento
        if (apartamentos.containsKey(ref)) {
            System.err.println("ERROR clave duplicada");
            valid = false;
        }
        // Si la referencia no es correcta, no se añade el apartamento
        if (!ref.isCorrecta()) {
            System.err.println("Error en la referencia");
            valid = false;
        }
        
        if (valid) {
            // Se añade el apartamento
            Apartamento apart = new Apartamento(piso, letra, capacidadMax, precio);
            apartamentos.put(ref, apart);
            System.out.println("Apartamento añadido: " + apart.toString());
        }
    }

    /**
     * Muestra los apartamentos del listado
     */
    public void mostrarApartamentos() {
        for (Referencia ref : apartamentos.keySet()) {
            System.out.println(apartamentos.get(ref).toString());
        }
    }

    /**
     * busca los apartamentos cuya capacidad máxima sea mayor o igual a la indicada
     * muestra por pantalla los apartamentos encontrados
     * 
     * @param int : capacidad mínima de los apartamentos a buscar
     */
    public void buscarApartamentosPorCapacidad(int capacidad) {
        for (Referencia ref : apartamentos.keySet()) {
            if (apartamentos.get(ref).getCapacidadMax() >= capacidad) {
                System.out.println(apartamentos.get(ref).toString());
            }
        }
    }

    /**
     * Elimina el apartamento identificado por la referencia indicada en formato
     * "PisoX-LetraX"
     * No hay que verificar si la referencia es correcta
     * 
     * @param String : referencia del apartamento a eliminar
     */
    public void eliminarApartamento(String ref) {
        Referencia referencia = new Referencia(
                Integer.parseInt(ref.substring(4, 5)), ref.charAt(11));
        apartamentos.remove(referencia);
    }

    /**
     * Recibe como parámetro la referencia del apartamento a reservar en formato
     * "PisoX-LetraX" y el número de personas que lo van a ocupar
     * si el apartamento tiene capacidad suficiente, se incrementa el contador de
     * reservas.
     * Hay que verificar si la referencia es correcta
     * 
     * @param String : referencia del apartamento a reservar
     */
    public void hacerReserva(String ref) {
        // Se comprueba si la referencia es correcta
        Referencia referencia = new Referencia(
                Integer.parseInt(ref.substring(4, 5)), ref.charAt(11));
        if (!referencia.isCorrecta()) {
            System.err.println("Error en la referencia");
            return;
        }
        // Se incrementa el contador de reservas si encuentra el apartamento
        if (apartamentos.containsKey(referencia)) {
            Apartamento apart = apartamentos.get(referencia);
            // Si el apartamento tiene capacidad suficiente, se incrementa el contador de
            // reservas
            if (apart.getContadorDeReservas() < apart.getCapacidadMax()) {
                apart.incrementarContadorDeReservas();
            }
        } else {
            System.err.println("Error apartamento no encontrado");
        }
    }

    /**
     * Muestra los apartamentos por piso
     * 
     * @param int : piso de los apartamentos a mostrar
     */
    public void mostrarApartamentosPorPiso(int piso) {
        for (Referencia ref : apartamentos.keySet()) {
            if (ref.getPiso() == piso) {
                System.out.println(apartamentos.get(ref).toString());
            }
        }
    }

    /**
     * Muestra por pantalla el apartamento con mayor capacidad
     */
    public void apartamentoConMayorCapacidad() {
        int max = 0;
        Referencia refMax = null;
        for (Referencia ref : apartamentos.keySet()) {
            if (apartamentos.get(ref).getCapacidadMax() > max) {
                max = apartamentos.get(ref).getCapacidadMax();
                refMax = ref;
            }
        }
        System.out.println(apartamentos.get(refMax).toString());
    }
}
