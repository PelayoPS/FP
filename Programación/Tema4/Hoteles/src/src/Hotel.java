package src;

import java.util.Scanner;

public class Hotel {
    // =======================ATRIBUTOS========================================
    private Habitacion[] habitaciones = { null, null, null, null, null,
            null, null, null, null, null };

    // =======================METODOS==========================================

    /**
     * Método que busca la posición libre en el array de habitaciones
     * e inserta una habitación en esa posición, si no hay posiciones libres
     * no inserta la habitación y avisará de que no hay habitaciones libres
     */
    public void nuevaHabitacion(Habitacion habitacion) {
        // Recorre el array de habitaciones 
        for (int i = 0; i < habitaciones.length; i++) {
            if (habitaciones[i] == null) {// Si encuentra una posición libre
                habitaciones[i] = habitacion;// Inserta la habitación
                return;
            }
        }
        // Si no encuentra ninguna posición libre
        // Avisará de que no hay habitaciones libres
        System.out.println("No hay habitaciones libres");
    }

    /**
     * Método que busca una habitación por su identificador y la elimina
     * si no encuentra la habitación avisará de que no existe.
     * No elimina la habitación si:
     * - No existe ninguna con ese identificador
     * - La habitación está ocupada
     */
    public void borrarHabitacion(int identificador) {
        // Recorre el array de habitaciones
        for (int i = 0; i < habitaciones.length; i++) {
            // Si encuentra la habitación y existe
            if (habitaciones[i] != null && habitaciones[i].getIdentificador() == identificador) {
                // Si la habitación está ocupada
                if (habitaciones[i].getOcupada()) {
                    System.out.println("No se puede eliminar la habitación, está ocupada");
                    return;
                }
                // Si la habitación no está ocupada
                // Elimina la habitación
                habitaciones[i] = null;
                return;
            }
        }
        // Si no encuentra ninguna habitación con ese identificador
        System.out.println("No existe ninguna habitación con ese identificador");
    }

    /**
     * Muestra por pantalla los datos de las habitaciones del hotel
     */
    public void mostrarHabitaciones() {
        // Inicializa el mensaje
        String mensaje = "Habitaciones [";
        // Recorre el array de habitaciones
        for (int i = 0; i < habitaciones.length; i++) {
            // Si la habitación no es nula
            if (habitaciones[i] != null) {
                // Añade la habitación al mensaje
                mensaje += habitaciones[i].toString() + "| ";
            }
        }
        // Elimina el último carácter que es un espacio
        mensaje = mensaje.substring(0, mensaje.length() - 2);
        mensaje += "]";
        // Muestra el mensaje
        System.out.println(mensaje);
    }

    /**
     * Método que muestra un listado de las habitaciones disponibles
     */
    public void habitacionesDisponibles() {
        // Inicializa el mensaje
        String mensaje = "Habitaciones disponibles [";
        // Recorre el array de habitaciones
        for (Habitacion habitacion : habitaciones) {
            // Si la habitación no es nula y no está ocupada
            if (habitacion != null && !habitacion.getOcupada()) {
                // Añade la habitación al mensaje
                mensaje += habitacion.toString() + "| ";
            }
        }
        // Elimina el último carácter que es un espacio
        mensaje = mensaje.substring(0, mensaje.length() - 2);
        mensaje += "]";
        // Muestra el mensaje
        System.out.println(mensaje);
    }

    /**
     * Método que muestra el precio de la habitación según su identificador
     */
    public void precioHabitacion() {
        // Crea el objeto Scanner para leer por teclado
        Scanner keyboard = new Scanner(System.in);
        // Pide el identificador de la habitación
        System.out.println("Introduce el identificador de la habitación");
        int identificador = keyboard.nextInt();
        keyboard.close();
        // Recorre el array de habitaciones
        for (Habitacion habitacion : habitaciones) {
            // Si la habitación no es nula y encuentra la habitación
            if (habitacion != null && habitacion.getIdentificador() == identificador) {
                // Muestra el precio de la habitación
                System.out.println("El precio de la habitación es: " + habitacion.getPrecio());
                return;
            }
        }
        // Si no encuentra ninguna habitación con ese identificador
        System.out.println("No existe ninguna habitación con ese identificador");
    }

    /**
     * Método que permite hacer una reserva, para ello se pide el identificador
     * de la habitación y se marca como ocupada
     * Si la habitación no existe o ya está ocupada se avisará al usuario
     */
    public void reservarHabitacion() {
        // Crea el objeto Scanner para leer por teclado
        Scanner keyboard = new Scanner(System.in);
        // Pide el identificador de la habitación
        System.out.println("Introduce el identificador de la habitación");
        int identificador = keyboard.nextInt();
        keyboard.close();
        // Recorre el array de habitaciones
        for (Habitacion habitacion : habitaciones) {
            // Si la habitación no es nula y encuentra la habitación
            if (habitacion != null && habitacion.getIdentificador() == identificador) {
                // Si la habitación ya está ocupada
                if (habitacion.getOcupada()) {
                    // Avisa al usuario
                    System.out.println("La habitación ya está ocupada");
                    return;
                }
                // Si la habitación no está ocupada
                habitacion.setOcupada(true);
                return;
            }
        }
    }

    /**
     * Método que lista las habitaciones ocupadas
     */
    public void habitacionesOcupadas() {
        // Inicializa el mensaje
        String mensaje = "Habitaciones ocupadas [";
        // Recorre el array de habitaciones
        for (Habitacion habitacion : habitaciones) {
            // Si la habitación no es nula y está ocupada
            if (habitacion != null && habitacion.getOcupada()) {
                mensaje += habitacion.toString() + "| ";
            }
        }
        // Elimina el último carácter que es un espacio
        mensaje = mensaje.substring(0, mensaje.length() - 2);
        mensaje += "]";
        // Muestra el mensaje
        System.out.println(mensaje);
    }

}
