package interfaz;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import modelo.Campeon;
import modelo.Equipo;
import modelo.Jugador;
import logica.Liga;
import modelo.Partida;
import logs.Logger;

/**
 * Clase principal que implementa la interfaz de usuario para la gestión de una
 * liga de League of Legends.
 * Permite gestionar equipos, campeones, partidas y ver logs del sistema.
 * 
 * Esta clase maneja todas las interacciones con el usuario a través de un menú
 * por consola
 * y coordina las operaciones con la capa de lógica de negocio.
 * 
 * @author PelayoPS
 * @version 1.0
 */
public class Main {
    private static Liga liga;
    private static Scanner scanner = new Scanner(System.in);
    private static Logger logger;

    /**
     * Punto de entrada principal de la aplicación.
     * Inicializa el sistema, carga los datos desde XML y presenta el menú
     * principal.
     * 
     * @param args Argumentos de línea de comando (no utilizados)
     */
    public static void main(String[] args) {
        // Inicializar el logger
        logger = Logger.getInstance();

        // Ruta al archivo XML
        String rutaXML = "ficheros/database.xml";
        File file = new File(rutaXML);

        // Verificar si el archivo existe
        if (!file.exists()) {
            logger.error("El archivo " + rutaXML + " no existe.");
            System.out.println("El archivo " + rutaXML + " no existe.");
            System.out.println("Comprueba la ruta y vuelve a intentarlo.");
            return;
        }

        liga = new Liga(rutaXML);

        // Configurar un Runtime Shutdown Hook para guardar datos en caso de cierre
        // inesperado
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("Guardando datos antes de cerrar...");
            liga.guardarXML();
        }));

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
                case 4:
                    mostrarLogs();
                    break;
                case 0:
                    logger.info("Guardando datos y saliendo del programa...");
                    System.out.println("Guardando datos y saliendo del programa...");
                    // Asegurar que los datos se guarden en el archivo al salir
                    liga.guardarXML();
                    break;
                default:
                    logger.warning("Opción no válida seleccionada: " + opcion);
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        } while (opcion != 0);

        scanner.close();
    }

    /**
     * Muestra el menú principal de la aplicación con todas las opciones
     * disponibles.
     */
    private static void mostrarMenuPrincipal() {
        System.out.println("\n===== GESTIÓN DE LEAGUE OF LEGENDS =====");
        System.out.println("1. Gestión de Equipos");
        System.out.println("2. Gestión de Campeones");
        System.out.println("3. Gestión de Partidas");
        System.out.println("4. Ver Logs del Sistema");
        System.out.println("0. Salir");
    }

    /**
     * Gestiona las operaciones relacionadas con equipos, incluyendo visualización,
     * búsqueda, creación, actualización y eliminación de equipos.
     */
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

    /**
     * Gestiona las operaciones relacionadas con los jugadores de un equipo
     * específico.
     */
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

    /**
     * Muestra la lista de jugadores de un equipo específico.
     * 
     * @param equipo El equipo del cual se mostrarán los jugadores
     */
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

    /**
     * Añade un nuevo jugador a un equipo específico.
     * 
     * @param equipo El equipo al cual se añadirá el jugador
     */
    private static void añadirJugador(Equipo equipo) {
        String nombre = leerString("Ingrese el nombre del jugador: ");
        String rol = leerString("Ingrese el rol del jugador: ");

        equipo.agregarJugador(new Jugador(nombre, rol));
        liga.actualizarEquipo(equipo);

        System.out.println("Jugador añadido correctamente.");
    }

    /**
     * Elimina un jugador de un equipo específico.
     * 
     * @param equipo El equipo del cual se eliminará el jugador
     */
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

    /**
     * Gestiona las operaciones relacionadas con campeones, incluyendo
     * visualización,
     * búsqueda, creación, actualización y eliminación de campeones.
     */
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

    /**
     * Gestiona las operaciones relacionadas con partidas, incluyendo visualización,
     * creación, actualización y eliminación de partidas.
     */
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

    /**
     * Muestra los logs del sistema, permitiendo al usuario filtrar por tipo de log
     * (error, advertencia, información) o limpiar los logs.
     */
    private static void mostrarLogs() {
        System.out.println("\n===== LOGS DEL SISTEMA =====");
        System.out.println("1. Ver todos los logs");
        System.out.println("2. Ver logs de error");
        System.out.println("3. Ver logs de advertencia");
        System.out.println("4. Ver logs de información");
        System.out.println("5. Limpiar logs");
        System.out.println("0. Volver al menú principal");

        int opcion = leerEntero("Ingrese una opción: ");

        switch (opcion) {
            case 1:
                System.out.println("\n=== TODOS LOS LOGS ===");
                System.out.println(logger.getColoredLogs());
                break;
            case 2:
                mostrarLogsFiltrados("ERROR");
                break;
            case 3:
                mostrarLogsFiltrados("WARNING");
                break;
            case 4:
                mostrarLogsFiltrados("INFO");
                break;
            case 5:
                System.out.println("¿Está seguro de que desea limpiar todos los logs? (S/N)");
                String confirmacion = scanner.nextLine().trim().toUpperCase();
                if (confirmacion.equals("S")) {
                    logger.clearLogs();
                    logger.info("Logs limpiados correctamente");
                    System.out.println("Logs limpiados correctamente.");
                }
                break;
            case 0:
                break;
            default:
                System.out.println("Opción no válida. Inténtelo de nuevo.");
        }
    }

    /**
     * Muestra los logs filtrados por tipo (error, advertencia, información).
     * 
     * @param tipo El tipo de log a mostrar
     */
    private static void mostrarLogsFiltrados(String tipo) {
        System.out.println("\n=== LOGS DE " + tipo + " ===");
        try {
            List<String> logs = logger.readAllLogs();
            boolean encontrado = false;

            for (String log : logs) {
                if (log.contains(tipo)) {
                    encontrado = true;
                    // Determinar color apropiado
                    String colorCode = "";
                    if (tipo.equals("ERROR")) {
                        colorCode = "\u001B[31m"; // Rojo
                    } else if (tipo.equals("WARNING")) {
                        colorCode = "\u001B[33m"; // Amarillo
                    } else if (tipo.equals("INFO")) {
                        colorCode = "\u001B[32m"; // Verde
                    }

                    System.out.println(colorCode + log + "\u001B[0m");
                }
            }

            if (!encontrado) {
                System.out.println("No hay logs de tipo " + tipo);
            }
        } catch (Exception e) {
            System.out.println("Error al leer los logs: " + e.getMessage());
        }
    }

    // Métodos de Equipos
    /**
     * Muestra la lista de equipos registrados en la liga.
     * Si no hay equipos, informa al usuario.
     */
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

    /**
     * Busca un equipo por su nombre y muestra su información.
     * Si el equipo no se encuentra, informa al usuario.
     */
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

    /**
     * Añade un nuevo equipo a la liga.
     * Si el equipo ya existe, informa al usuario.
     */
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

    /**
     * Actualiza la información de un equipo existente.
     * Si el equipo no se encuentra, informa al usuario.
     */
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

    /**
     * Elimina un equipo de la liga.
     * Si el equipo no se encuentra, informa al usuario.
     */
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
    /**
     * Muestra la lista de campeones registrados en la liga.
     * Si no hay campeones, informa al usuario.
     */
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

    /**
     * Busca un campeón por su nombre y muestra su información.
     * Si el campeón no se encuentra, informa al usuario.
     */
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

    /**
     * Añade un nuevo campeón a la liga.
     * Si el campeón ya existe, informa al usuario.
     */
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

    /**
     * Actualiza la información de un campeón existente.
     * Si el campeón no se encuentra, informa al usuario.
     */
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

    /**
     * Elimina un campeón de la liga.
     * Si el campeón no se encuentra, informa al usuario.
     */
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
    /**
     * Muestra la lista de partidas registradas en la liga.
     * Si no hay partidas, informa al usuario.
     */
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

    /**
     * Añade una nueva partida a la liga.
     * Si la partida ya existe, informa al usuario.
     */
    private static void añadirPartida() {
        mostrarEquipos();

        String equipo = leerString("Ingrese el nombre del equipo: ");
        String oponente = leerString("Ingrese el nombre del oponente: ");
        String resultado = leerString("Ingrese el resultado (Ganado/Perdido): ");

        Partida nuevaPartida = new Partida(equipo, oponente, resultado);
        liga.agregarPartida(nuevaPartida);

        System.out.println("Partida añadida correctamente.");
    }

    /**
     * Actualiza la información de una partida existente.
     * Si la partida no se encuentra, informa al usuario.
     */
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

    /**
     * Elimina una partida de la liga.
     * Si la partida no se encuentra, informa al usuario.
     */
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

    /**
     * Lee un valor entero desde la entrada estándar, con validación y manejo de
     * errores.
     * 
     * @param mensaje El mensaje a mostrar al usuario antes de la lectura
     * @return El número entero ingresado por el usuario
     */
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

    /**
     * Lee una cadena de texto desde la entrada estándar.
     * 
     * @param mensaje El mensaje a mostrar al usuario antes de la lectura
     * @return La cadena ingresada por el usuario
     */
    private static String leerString(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }
}