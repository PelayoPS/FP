package interfaz;

import java.util.Scanner;

import logic.Gestor;
import model.Coach;
import model.Equipo;
import model.Jugador;
import model.Penalizacion;

/**
 * La clase Main es la clase principal del programa. Contiene el método main que
 * inicia la ejecución del programa.
 */
public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("Menu:");
			System.out.println("1. Agregar");
			System.out.println("2. Obtener");
			System.out.println("3. Eliminar");
			System.out.println("4. Salir");

			System.out.print("Ingrese una opción: ");
			int option = scanner.nextInt();

			switch (option) {
				case 1:
					agregar(scanner);
					break;
				case 2:
					obtener(scanner);
					break;
				case 3:
					eliminar(scanner);
					break;
				case 4:
					System.out.println("Saliendo...");
					scanner.close();
					return;
				default:
					System.out.println("Opción inválida");
			}
		}
	}

	private static void agregar(Scanner scanner) {
		System.out.println("1. Agregar Equipo");
		System.out.println("2. Agregar Penalizacion");
		System.out.println("3. Agregar Jugador");
		System.out.println("4. Agregar Coach");

		System.out.print("Ingrese una opción: ");
		int option = scanner.nextInt();

		switch (option) {
			case 1:
				agregarEquipo(scanner);
				break;
			case 2:
				agregarPenalizacion(scanner);
				break;
			case 3:
				agregarJugador(scanner);
				break;
			case 4:
				agregarCoach(scanner);
				break;
			default:
				System.out.println("Opción inválida");
		}
	}

	private static void obtener(Scanner scanner) {
		System.out.println("1. Obtener Equipo");
		System.out.println("2. Obtener Penalizacion");
		System.out.println("3. Obtener Jugador");
		System.out.println("4. Obtener Coach");

		System.out.print("Ingrese una opción: ");
		int option = scanner.nextInt();

		switch (option) {
			case 1:
				obtenerEquipo(scanner);
				break;
			case 2:
				obtenerPenalizacion(scanner);
				break;
			case 3:
				obtenerJugador(scanner);
				break;
			case 4:
				obtenerCoach(scanner);
				break;
			default:
				System.out.println("Opción inválida");
		}
	}

	private static void eliminar(Scanner scanner) {
		System.out.println("1. Eliminar Equipo");
		System.out.println("2. Eliminar Penalizacion");
		System.out.println("3. Eliminar Jugador");
		System.out.println("4. Eliminar Coach");

		System.out.print("Ingrese una opción: ");
		int option = scanner.nextInt();

		switch (option) {
			case 1:
				eliminarEquipo(scanner);
				break;
			case 2:
				eliminarPenalizacion(scanner);
				break;
			case 3:
				eliminarJugador(scanner);
				break;
			case 4:
				eliminarCoach(scanner);
				break;
			default:
				System.out.println("Opción inválida");
		}
	}

	private static void agregarEquipo(Scanner scanner) {
		System.out.print("Ingrese el ID del equipo: ");
		int equipoId = scanner.nextInt();
		System.out.print("Ingrese el nombre del equipo: ");
		String equipoName = scanner.next();
		Equipo equipo = new Equipo(equipoId, equipoName);
		Gestor.addEquipo(equipo);
	}

	private static void agregarPenalizacion(Scanner scanner) {
		System.out.print("Ingrese el ID de la penalizacion: ");
		int penalizacionId = scanner.nextInt();
		System.out.print("Ingrese la descripcion de la penalizacion: ");
		String penalizacionDesc = scanner.next();
		Penalizacion penalizacion = new Penalizacion(penalizacionId, penalizacionDesc);
		Gestor.addPenalizacion(penalizacion);
	}

	private static void agregarJugador(Scanner scanner) {
		System.out.print("Ingrese el ID del jugador: ");
		int jugadorId = scanner.nextInt();
		System.out.print("Ingrese el nombre del jugador: ");
		String jugadorName = scanner.next();
		System.out.print("Ingrese el rol del jugador: ");
		String jugadorRole = scanner.next();
		System.out.println("Ingrese si el jugador es agente libre (true/false): ");
		boolean jugadorFreeAgent = scanner.nextBoolean();
		Jugador jugador = new Jugador(jugadorName, jugadorId, jugadorRole, jugadorFreeAgent);
		Gestor.agregarJugador(jugador);
	}

	private static void agregarCoach(Scanner scanner) {
		System.out.print("Ingrese el ID del coach: ");
		int coachId = scanner.nextInt();
		System.out.print("Ingrese el nombre del coach: ");
		String coachName = scanner.next();
		System.out.print("Ingrese el rol del coach: ");
		String role = scanner.next();
		System.out.println("Ingrese la experiencia del coach: ");
		int experience = scanner.nextInt();
		System.out.println("Ingrese si el coach es agente libre (true/false): ");
		boolean free = scanner.nextBoolean();
		Coach coach = new Coach(coachName, coachId, role, experience, free);
		Gestor.agregarEntrenador(coach);
	}

	private static void obtenerEquipo(Scanner scanner) {
		System.out.print("Ingrese el ID del equipo a obtener: ");
		int equipoIdToGet = scanner.nextInt();
		Equipo retrievedEquipo = Gestor.getEquipo(equipoIdToGet);
		if (retrievedEquipo != null) {
			System.out.println("Equipo encontrado: " + retrievedEquipo.getNombre());
		} else {
			System.out.println("Equipo no encontrado");
		}
	}

	private static void obtenerPenalizacion(Scanner scanner) {
		System.out.print("Ingrese el ID de la penalizacion a obtener: ");
		int penalizacionIdToGet = scanner.nextInt();
		Penalizacion retrievedPenalizacion = Gestor.getPenalizacion(penalizacionIdToGet);
		if (retrievedPenalizacion != null) {
			System.out.println("Penalizacion encontrada: " + retrievedPenalizacion.getDescription());
		} else {
			System.out.println("Penalizacion no encontrada");
		}
	}

	private static void obtenerJugador(Scanner scanner) {
		System.out.print("Ingrese el ID del jugador a obtener: ");
		int jugadorIdToGet = scanner.nextInt();
		Jugador retrievedJugador = Gestor.getJugador(jugadorIdToGet);
		if (retrievedJugador != null) {
			System.out.println("Jugador encontrado: " + retrievedJugador.getName());
		} else {
			System.out.println("Jugador no encontrado");
		}
	}

	private static void obtenerCoach(Scanner scanner) {
		System.out.print("Ingrese el ID del coach a obtener: ");
		int coachIdToGet = scanner.nextInt();
		Coach retrievedCoach = Gestor.getCoach(coachIdToGet);
		if (retrievedCoach != null) {
			System.out.println("Coach encontrado: " + retrievedCoach.getName());
		} else {
			System.out.println("Coach no encontrado");
		}
	}

	private static void eliminarEquipo(Scanner scanner) {
		System.out.print("Ingrese el ID del equipo a eliminar: ");
		int equipoIdToDelete = scanner.nextInt();
		Gestor.removeEquipo(equipoIdToDelete);
	}

	private static void eliminarPenalizacion(Scanner scanner) {
		System.out.print("Ingrese el ID de la penalizacion a eliminar: ");
		int penalizacionIdToDelete = scanner.nextInt();
		Gestor.removePenalizacion(penalizacionIdToDelete);
	}

	private static void eliminarJugador(Scanner scanner) {
		System.out.print("Ingrese el ID del jugador a eliminar: ");
		int jugadorIdToDelete = scanner.nextInt();
		Gestor.eliminarJugador(Gestor.getJugador(jugadorIdToDelete));
	}

	private static void eliminarCoach(Scanner scanner) {
		System.out.print("Ingrese el ID del coach a eliminar: ");
		int coachIdToDelete = scanner.nextInt();
		Gestor.eliminarEntrenador(Gestor.getCoach(coachIdToDelete));
	}
}