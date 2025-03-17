package interfaz;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import modelo.Campeon;
import modelo.Equipo;
import modelo.Jugador;
import logica.Liga;
import modelo.Partida;

public class Main {
    private static Liga liga;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Ruta al archivo XML
        String rutaXML = "datos.xml";
        File file = new File(rutaXML);

        // Verificar si el archivo existe
        if (!file.exists()) {
            System.out.println("El archivo " + rutaXML + " no existe.");
            System.out.println("Comprueba la ruta y vuelve a intentarlo.");
            return;
        }

        liga = new Liga(rutaXML);
        int opcion;

        do {
            mostrarMenuPrincipal();
            opcion = leerEntero("Ingrese una opción: ");

            switch (opcion) {
                case 1:
                    gestionEquipos();
                    break;
                case 2:
                    gestionCampeones();
                    break;
                case 3:
                    gestionPartidas();
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        } while (opcion != 0);

        scanner.close();
    }

    // Menús y gestiones
    private static void mostrarMenuPrincipal() {
        System.out.println("\n===== GESTIÓN DE LEAGUE OF LEGENDS =====");
        System.out.println("1. Gestión de Equipos");
        System.out.println("2. Gestión de Campeones");
        System.out.println("3. Gestión de Partidas");
        System.out.println("0. Salir");
    }

    private static void gestionEquipos() {
        int opcion;

        do {
            System.out.println("\n===== GESTIÓN DE EQUIPOS =====");
            System.out.println("1. Ver equipos");
            System.out.println("2. Buscar equipo");
            System.out.println("3. Añadir equipo");
            System.out.println("4. Actualizar equipo");
            System.out.println("5. Eliminar equipo");
            System.out.println("6. Gestionar jugadores de un equipo");
            System.out.println("0. Volver al menú principal");

            opcion = leerEntero("Ingrese una opción: ");

            switch (opcion) {
                case 1:
                    mostrarEquipos();
                    break;
                case 2:
                    buscarEquipo();
                    break;
                case 3:
                    añadirEquipo();
                    break;
                case 4:
                    actualizarEquipo();
                    break;
                case 5:
                    eliminarEquipo();
                    break;
                case 6:
                    gestionJugadores();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        } while (opcion != 0);
    }

    private static void gestionJugadores() {
        System.out.println("\n===== GESTIÓN DE JUGADORES =====");
        mostrarEquipos();

        String nombreEquipo = leerString("Ingrese el nombre del equipo: ");
        Equipo equipo = liga.buscarEquipo(nombreEquipo);

        if (equipo == null) {
            System.out.println("Equipo no encontrado.");
            return;
        }

        int opcion;
        do {
            System.out.println("\n===== JUGADORES DE " + equipo.getNombre() + " =====");
            System.out.println("1. Ver jugadores");
            System.out.println("2. Añadir jugador");
            System.out.println("3. Eliminar jugador");
            System.out.println("0. Volver");

            opcion = leerEntero("Ingrese una opción: ");

            switch (opcion) {
                case 1:
                    mostrarJugadores(equipo);
                    break;
                case 2:
                    añadirJugador(equipo);
                    break;
                case 3:
                    eliminarJugador(equipo);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        } while (opcion != 0);
    }

    private static void mostrarJugadores(Equipo equipo) {
        List<Jugador> jugadores = equipo.getJugadores();

        if (jugadores.isEmpty()) {
            System.out.println("No hay jugadores en este equipo.");
            return;
        }

        System.out.println("\n=== Jugadores del equipo " + equipo.getNombre() + " ===");
        for (Jugador jugador : jugadores) {
            System.out.println("Nombre: " + jugador.getNombre() + " | Rol: " + jugador.getRol());
        }
    }

    private static void añadirJugador(Equipo equipo) {
        String nombre = leerString("Ingrese el nombre del jugador: ");
        String rol = leerString("Ingrese el rol del jugador: ");

        equipo.agregarJugador(new Jugador(nombre, rol));
        liga.actualizarEquipo(equipo);

        System.out.println("Jugador añadido correctamente.");
    }

    private static void eliminarJugador(Equipo equipo) {
        mostrarJugadores(equipo);

        if (equipo.getJugadores().isEmpty()) {
            return;
        }

        String nombre = leerString("Ingrese el nombre del jugador a eliminar: ");

        if (equipo.eliminarJugador(nombre)) {
            liga.actualizarEquipo(equipo);
            System.out.println("Jugador eliminado correctamente.");
        } else {
            System.out.println("No se encontró el jugador.");
        }
    }

    private static void gestionCampeones() {
        int opcion;

        do {
            System.out.println("\n===== GESTIÓN DE CAMPEONES =====");
            System.out.println("1. Ver campeones");
            System.out.println("2. Buscar campeón");
            System.out.println("3. Añadir campeón");
            System.out.println("4. Actualizar campeón");
            System.out.println("5. Eliminar campeón");
            System.out.println("0. Volver al menú principal");

            opcion = leerEntero("Ingrese una opción: ");

            switch (opcion) {
                case 1:
                    mostrarCampeones();
                    break;
                case 2:
                    buscarCampeon();
                    break;
                case 3:
                    añadirCampeon();
                    break;
                case 4:
                    actualizarCampeon();
                    break;
                case 5:
                    eliminarCampeon();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        } while (opcion != 0);
    }

    private static void gestionPartidas() {
        int opcion;

        do {
            System.out.println("\n===== GESTIÓN DE PARTIDAS =====");
            System.out.println("1. Ver partidas");
            System.out.println("2. Añadir partida");
            System.out.println("3. Actualizar partida");
            System.out.println("4. Eliminar partida");
            System.out.println("0. Volver al menú principal");

            opcion = leerEntero("Ingrese una opción: ");

            switch (opcion) {
                case 1:
                    mostrarPartidas();
                    break;
                case 2:
                    añadirPartida();
                    break;
                case 3:
                    actualizarPartida();
                    break;
                case 4:
                    eliminarPartida();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        } while (opcion != 0);
    }

    // Métodos de Equipos
    private static void mostrarEquipos() {
        List<Equipo> equipos = liga.getEquipos();

        if (equipos.isEmpty()) {
            System.out.println("No hay equipos registrados.");
            return;
        }

        System.out.println("\n=== EQUIPOS REGISTRADOS ===");
        for (Equipo equipo : equipos) {
            System.out.println("Nombre: " + equipo.getNombre());
            System.out.println("Número de jugadores: " + equipo.getJugadores().size());
            System.out.println("-------------------------");
        }
    }

    private static void buscarEquipo() {
        String nombre = leerString("Ingrese el nombre del equipo a buscar: ");

        Equipo equipo = liga.buscarEquipo(nombre);

        if (equipo == null) {
            System.out.println("Equipo no encontrado.");
            return;
        }

        System.out.println("\n=== INFORMACIÓN DEL EQUIPO ===");
        System.out.println("Nombre: " + equipo.getNombre());
        System.out.println("Jugadores:");

        for (Jugador jugador : equipo.getJugadores()) {
            System.out.println("  - " + jugador.getNombre() + " (" + jugador.getRol() + ")");
        }
    }

    private static void añadirEquipo() {
        String nombre = leerString("Ingrese el nombre del nuevo equipo: ");

        if (liga.buscarEquipo(nombre) != null) {
            System.out.println("Ya existe un equipo con ese nombre.");
            return;
        }

        Equipo nuevoEquipo = new Equipo(nombre);
        liga.agregarEquipo(nuevoEquipo);

        System.out.println("Equipo añadido correctamente.");
    }

    private static void actualizarEquipo() {
        mostrarEquipos();

        String nombreActual = leerString("Ingrese el nombre del equipo a actualizar: ");
        Equipo equipoActual = liga.buscarEquipo(nombreActual);

        if (equipoActual == null) {
            System.out.println("Equipo no encontrado.");
            return;
        }

        String nuevoNombre = leerString("Ingrese el nuevo nombre del equipo (deje vacío para mantener el actual): ");

        if (!nuevoNombre.isEmpty()) {
            equipoActual.setNombre(nuevoNombre);
        }

        liga.actualizarEquipo(equipoActual);

        System.out.println("Equipo actualizado correctamente.");
    }

    private static void eliminarEquipo() {
        mostrarEquipos();

        String nombre = leerString("Ingrese el nombre del equipo a eliminar: ");

        if (liga.eliminarEquipo(nombre)) {
            System.out.println("Equipo eliminado correctamente.");
        } else {
            System.out.println("No se encontró el equipo.");
        }
    }

    // Métodos de Campeones
    private static void mostrarCampeones() {
        List<Campeon> campeones = liga.getCampeones();

        if (campeones.isEmpty()) {
            System.out.println("No hay campeones registrados.");
            return;
        }

        System.out.println("\n=== CAMPEONES REGISTRADOS ===");
        for (Campeon campeon : campeones) {
            System.out.println("Nombre: " + campeon.getNombre());
            System.out.println("Rol: " + campeon.getRol());
            System.out.println("-------------------------");
        }
    }

    private static void buscarCampeon() {
        String nombre = leerString("Ingrese el nombre del campeón a buscar: ");

        Campeon campeon = liga.buscarCampeon(nombre);

        if (campeon == null) {
            System.out.println("Campeón no encontrado.");
            return;
        }

        System.out.println("\n=== INFORMACIÓN DEL CAMPEÓN ===");
        System.out.println("Nombre: " + campeon.getNombre());
        System.out.println("Rol: " + campeon.getRol());
    }

    private static void añadirCampeon() {
        String nombre = leerString("Ingrese el nombre del nuevo campeón: ");

        if (liga.buscarCampeon(nombre) != null) {
            System.out.println("Ya existe un campeón con ese nombre.");
            return;
        }

        String rol = leerString("Ingrese el rol del campeón: ");

        Campeon nuevoCampeon = new Campeon(nombre, rol);
        liga.agregarCampeon(nuevoCampeon);

        System.out.println("Campeón añadido correctamente.");
    }

    private static void actualizarCampeon() {
        mostrarCampeones();

        String nombreActual = leerString("Ingrese el nombre del campeón a actualizar: ");
        Campeon campeonActual = liga.buscarCampeon(nombreActual);

        if (campeonActual == null) {
            System.out.println("Campeón no encontrado.");
            return;
        }

        String nuevoNombre = leerString("Ingrese el nuevo nombre del campeón (deje vacío para mantener el actual): ");
        String nuevoRol = leerString("Ingrese el nuevo rol del campeón (deje vacío para mantener el actual): ");

        if (!nuevoNombre.isEmpty()) {
            campeonActual.setNombre(nuevoNombre);
        }

        if (!nuevoRol.isEmpty()) {
            campeonActual.setRol(nuevoRol);
        }

        liga.actualizarCampeon(campeonActual);

        System.out.println("Campeón actualizado correctamente.");
    }

    private static void eliminarCampeon() {
        mostrarCampeones();

        String nombre = leerString("Ingrese el nombre del campeón a eliminar: ");

        if (liga.eliminarCampeon(nombre)) {
            System.out.println("Campeón eliminado correctamente.");
        } else {
            System.out.println("No se encontró el campeón.");
        }
    }

    // Métodos de Partidas
    private static void mostrarPartidas() {
        List<Partida> partidas = liga.getPartidas();

        if (partidas.isEmpty()) {
            System.out.println("No hay partidas registradas.");
            return;
        }

        System.out.println("\n=== PARTIDAS REGISTRADAS ===");
        for (int i = 0; i < partidas.size(); i++) {
            Partida partida = partidas.get(i);
            System.out.println("Índice: " + i);
            System.out.println("Equipo: " + partida.getEquipo());
            System.out.println("Oponente: " + partida.getOponente());
            System.out.println("Resultado: " + partida.getResultado());
            System.out.println("-------------------------");
        }
    }

    private static void añadirPartida() {
        mostrarEquipos();

        String equipo = leerString("Ingrese el nombre del equipo: ");
        String oponente = leerString("Ingrese el nombre del oponente: ");
        String resultado = leerString("Ingrese el resultado (Ganado/Perdido): ");

        Partida nuevaPartida = new Partida(equipo, oponente, resultado);
        liga.agregarPartida(nuevaPartida);

        System.out.println("Partida añadida correctamente.");
    }

    private static void actualizarPartida() {
        mostrarPartidas();

        List<Partida> partidas = liga.getPartidas();
        if (partidas.isEmpty()) {
            return;
        }

        int indice = leerEntero("Ingrese el índice de la partida a actualizar: ");

        if (indice < 0 || indice >= partidas.size()) {
            System.out.println("Índice no válido.");
            return;
        }

        Partida partidaActual = partidas.get(indice);

        System.out.println("Deje vacío para mantener el valor actual.");

        String nuevoEquipo = leerString("Nuevo equipo (" + partidaActual.getEquipo() + "): ");
        String nuevoOponente = leerString("Nuevo oponente (" + partidaActual.getOponente() + "): ");
        String nuevoResultado = leerString("Nuevo resultado (" + partidaActual.getResultado() + "): ");

        if (!nuevoEquipo.isEmpty()) {
            partidaActual.setEquipo(nuevoEquipo);
        }

        if (!nuevoOponente.isEmpty()) {
            partidaActual.setOponente(nuevoOponente);
        }

        if (!nuevoResultado.isEmpty()) {
            partidaActual.setResultado(nuevoResultado);
        }

        liga.actualizarPartida(partidaActual, indice);

        System.out.println("Partida actualizada correctamente.");
    }

    private static void eliminarPartida() {
        mostrarPartidas();

        List<Partida> partidas = liga.getPartidas();
        if (partidas.isEmpty()) {
            return;
        }

        int indice = leerEntero("Ingrese el índice de la partida a eliminar: ");

        if (indice < 0 || indice >= partidas.size()) {
            System.out.println("Índice no válido.");
            return;
        }

        if (liga.eliminarPartida(indice)) {
            System.out.println("Partida eliminada correctamente.");
        } else {
            System.out.println("No se pudo eliminar la partida.");
        }
    }

    // Métodos de utilidad
    private static int leerEntero(String mensaje) {
        int entero = 0;
        boolean valido = false;

        do {
            try {
                System.out.print(mensaje);
                entero = Integer.parseInt(scanner.nextLine());
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número entero válido.");
            }
        } while (!valido);

        return entero;
    }

    private static String leerString(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }
}