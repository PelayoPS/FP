package src;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    // Variables
    private static final Scanner scanner = new Scanner(System.in);
    private static final Equipo equipo = new Equipo("Equipo de lol");

    // Métodos

    /**
     * Método main
     * @param String[] : argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        int opcion;
        // Bucle que muestra el menú y ejecuta la opción seleccionada
        do {
            mostrarMenu();
            opcion = leerOpcion();
            // Switch que ejecuta la opción seleccionada
            switch (opcion) {
                case 1:
                    // Insertar jugador
                    insertarJugador();
                    break;
                case 2:
                    // Insertar entrenador
                    insertarEntrenador();
                    break;
                case 3:
                    // Borrar persona
                    borrarPersona();
                    break;
                case 4:
                    // Listar equipo
                    listarEquipo();
                    break;
                case 5:
                    // Buscar persona
                    buscarPersona();
                    break;
                case 6:
                    // Salir
                    System.out.println("¡Hasta pronto!");
                    break;
                default:
                    // Opción no válida
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 6);
    }

    /**
     * Método mostrar menú
     */
    private static void mostrarMenu() {
        System.out.println("\n**Menú:**");
        System.out.println("1. Insertar jugador");
        System.out.println("2. Insertar entrenador");
        System.out.println("3. Borrar persona");
        System.out.println("4. Listar equipo");
        System.out.println("5. Buscar persona");
        System.out.println("6. Salir");
    }

    /**
     * Método leer opción
     * @return int : opción seleccionada
     */
    private static int leerOpcion() {
        int opcion = 0;
        boolean valido = false;
        // Bucle que pide una opción hasta que se introduce un número válido
        while (!valido) {
            try {
                System.out.print("Introduzca una opción: ");
                opcion = scanner.nextInt();
                valido = true;
            } catch (InputMismatchException e) {
                System.out.println("No ha introducido un número válido.");
                scanner.nextLine();
            }
        }

        return opcion;
    }

    /**
     * Método listar equipo
     */
    private static void listarEquipo() {
        System.out.println("\n**Listar equipo:**");
        // Bucle que muestra los miembros del equipo
        for (Persona persona : equipo.getMiembros()) {
            System.out.println(persona.toString());
        }
    }

    /**
     * Método insertar jugador
     */
    private static void insertarJugador() {
        // Pedir datos del jugador
        System.out.println("\n**Insertar jugador:**");
        System.out.print("Nombre: ");
        String nombre = scanner.next();
        System.out.print("Apellidos: ");
        String apellidos = scanner.next();
        System.out.print("DNI: ");
        String dni = scanner.next();
        System.out.print("Dirección: ");
        String direccion = scanner.next();
        System.out.print("Equipo: ");
        String team = scanner.next();
        System.out.print("Número: ");
        int numero = scanner.nextInt();
        // Crear jugador y agregarlo al equipo
        Jugador jugador = new Jugador(nombre, apellidos, dni, new Direccion(direccion), team, numero);
        equipo.agregarMiembro(jugador);
    }

    /**
     * Método insertar entrenador
     */
    private static void insertarEntrenador() {
        // Pedir datos del entrenador
        System.out.println("\n**Insertar entrenador:**");
        System.out.print("Nombre: ");
        String nombre = scanner.next();
        System.out.print("Apellidos: ");
        String apellidos = scanner.next();
        System.out.print("DNI: ");
        String dni = scanner.next();
        System.out.print("Dirección: ");
        String direccion = scanner.next();
        System.out.print("Equipo: ");
        String team = scanner.next();
        System.out.print("Licencia: ");
        String licencia = scanner.next();
        // Crear entrenador y agregarlo al equipo
        Entrenador entrenador = new Entrenador(nombre, apellidos, dni, new Direccion(direccion), team, licencia);
        equipo.agregarMiembro(entrenador);
    }

    /**
     * Método borrar persona
     */
    private static void borrarPersona() {
        System.out.println("\n**Borrar persona:**");
        System.out.print("DNI: ");
        String dni = scanner.next();
        // Bucle que busca a la persona con el DNI introducido y la elimina
        for (Persona persona : equipo.getMiembros()) {
            if (persona.getDni().equals(dni)) {
                equipo.eliminarMiembro(persona);
                System.out.println("Persona eliminada.");
                return;
            }
        }
        // Mensaje de error si no se encuentra a la persona
        System.out.println("No se ha encontrado a la persona con el DNI introducido.");
    }

    /**
     * Método buscar persona
     */
    private static void buscarPersona() {
        System.out.println("\n**Buscar persona:**");
        System.out.print("DNI: ");
        String dni = scanner.next();
        // Bucle que busca a la persona con el DNI introducido y la muestra
        for (Persona persona : equipo.getMiembros()) {
            if (persona.getDni().equals(dni)) {
                System.out.println(persona.toString());
                return;
            }
        }
        // Mensaje de error si no se encuentra a la persona
        System.out.println("No se ha encontrado a la persona con el DNI introducido.");
    }

}