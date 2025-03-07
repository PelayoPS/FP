package src;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeSet;

public class Taller {

    private HashMap<Matricula, Vehiculo> vehiculos;
    private TreeSet<Cliente> clientes;
    private LinkedList<Reparacion> reparaciones;

    public Taller() {
        this.vehiculos = new HashMap<>();
        this.clientes = new TreeSet<>();
        this.reparaciones = new LinkedList<>();
    }

    /**
     * Añade un vehiculo al taller
     * No se permiten matrículas repetidas
     * No se permiten matrículas erróneas
     * 
     * @param Vehiculo : v
     */
    public void añadirVehiculo(Vehiculo v) {
        // Si la matrícula no está en el taller
        if (!vehiculos.containsKey(v.getMatricula())) {
            // Si la matrícula es correcta
            if (!v.getMatricula().getMatricula().equals("")) {
                vehiculos.put(v.getMatricula(), v);
            }
        }
    }

    /**
     * Muestra los vehiculos al tener cada toString implementado en las
     * subclases ya se indica el tipo de vehiculo
     */
    public void mostrarVehiculos() {
        for (Vehiculo v : vehiculos.values()) {
            System.out.println(v.toString());
        }
    }

    /**
     * Añade un cliente al taller
     * No se permiten DNI repetidos
     * No se permiten DNI erróneos
     * 
     * @param Cliente : c
     */
    public void añadirCliente(Cliente c) {
        // Si el DNI no está en el taller
        if (!clientes.contains(c)) {
            // Si el DNI es correcto
            if (!c.getDni().equals("")) {
                clientes.add(c);
            }
        }
    }

    /**
     * Muestra los clientes
     */
    public void mostrarClientes() {
        for (Cliente c : clientes) {
            System.out.println(c.toString());
        }
    }

    /**
     * Crea una reparación
     * El dni debe estar en el taller
     * El dni debe cumplir con las condiciones de la clase cliente
     * La matricula debe estar en el taller
     * 
     * @param String    : dni
     * @param Matricula : matricula
     * @param String    : fecha
     *
     */
    public void crearReparacion(String dni, Matricula matricula, String fecha) {
        // Si el dni es correcto
        if (Cliente.verificarDni(dni).equals("")) {
            return;
        }
        // Si el DNI está en el taller
        for (Cliente c : clientes) {
            if (c.getDni().equals(dni)) {
                // Si la matrícula está en el taller
                if (vehiculos.containsKey(matricula)) {
                    reparaciones.add(new Reparacion(dni, matricula, fecha));
                }
            }
        }
    }

    /**
     * Muestra las reparaciones
     */
    public void mostrarReparaciones() {
        for (Reparacion r : reparaciones) {
            System.out.println(r.toString());
        }
    }

    /**
     * Muestra las reparaciones por cliente
     * Si el dni no pertenece a ningún cliente se muestra 
     * un mensaje de error
     * 
     * @param String : dni
     */
    public void mostrarReparacionesCliente(String dni) {
        // busca el cliente por dni
        for (Cliente c : clientes) {
            if (c.getDni().equals(dni)) {
                // Si lo encuentra muestra las reparaciones
                int contador = 0;
                System.out.println("Reparaciones para el cliente con DNI " + dni);
                String result = "";
                for (Reparacion r : reparaciones) {
                    if (r.getDni().equals(dni)) {
                        result += r.toString() + "\n";
                        // contador para saber si hay reparaciones
                        contador++;
                    }
                }
                // Si no hay reparaciones
                if (contador == 0) {
                    System.err.println("No hay reparaciones para el cliente con DNI " + dni);
                } else {
                    System.out.println(result);
                }
                return;
            }
        }
    }

}
