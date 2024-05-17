package interfaz;

import java.util.Scanner;

import logic.Gestor;
import logic.exceptions.ExcepcionMaxJugadores;
import logic.exceptions.ExceptionJugadorNoEncontrado;
import model.Coach;
import model.Equipo;
import model.Jugador;
import model.Penalizacion;

/**
 * La clase Main es la clase principal del programa. Contiene el método main que
 * inicia la ejecución del programa.
 * 
 * TODO:Equals and hashcode where needed
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

	/**
	 * Agrupa los métodos del menú que sirven para agregar
	 * 
	 * @param scanner input
	 */
	private static void agregar(Scanner scanner) {
		System.out.println("1. Agregar Equipo");
		System.out.println("2. Agregar Penalizacion");
		System.out.println("3. Agregar Jugador");
		System.out.println("4. Agregar Coach");
		System.out.println("5. Agregar Jugador a Equipo");
		System.out.println("6. Agregar Coach a Equipo");

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
			case 5:
				agregarJugadorAEquipo(scanner);
				break;
			case 6:
				agregarCoachAEquipo(scanner);
				break;
			default:
				System.out.println("Opción inválida");
		}
	}

	/**
	 * Agrupa los métodos de obtener
	 * 
	 * @param scanner input
	 */
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

	/**
	 * Agrupa los métodos de eliminar
	 * 
	 * @param scanner input
	 */
	private static void eliminar(Scanner scanner) {
		System.out.println("1. Eliminar Equipo");
		System.out.println("2. Eliminar Penalizacion");
		System.out.println("3. Eliminar Jugador");
		System.out.println("4. Eliminar Coach");
		System.out.println("5. Eliminar Jugador de Equipo");
		System.out.println("6. Eliminar Coach de Equipo");

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
			case 5:
				eliminarJugadorEquipo(scanner);
				break;
			case 6:
				eliminarCoachEquipo(scanner);
				break;
			default:
				System.out.println("Opción inválida");
		}
	}

	/**
	 * Utiliza el gestor para agregar un equipo
	 * 
	 * @param scanner input
	 */
	private static void agregarEquipo(Scanner scanner) {
		System.out.print("Ingrese el ID del equipo: ");
		int equipoId = scanner.nextInt();
		System.out.print("Ingrese el nombre del equipo: ");
		String equipoName = scanner.next();
		Equipo equipo = new Equipo(equipoId, equipoName);
		Gestor.addEquipo(equipo);
	}

	/**
	 * Utiliza el gestor para agregar una penalizacion
	 * 
	 * @param scanner input
	 */
	private static void agregarPenalizacion(Scanner scanner) {
		System.out.print("Ingrese el ID de la penalizacion: ");
		int penalizacionId = scanner.nextInt();
		System.out.print("Ingrese la descripcion de la penalizacion: ");
		String penalizacionDesc = scanner.next();
		Penalizacion penalizacion = new Penalizacion(penalizacionId, penalizacionDesc);
		Gestor.addPenalizacion(penalizacion);
	}

	/**
	 * Utiliza el gestor para agregar un jugador
	 * 
	 * @param scanner input
	 */
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

	/**
	 * Agregar jugador a equipo
	 * 
	 * @param scanner input
	 */
	private static void agregarJugadorAEquipo(Scanner scanner) {
		System.out.print("Ingrese el ID del jugador: ");
		int jugadorId = scanner.nextInt();
		System.out.print("Ingrese el ID del equipo: ");
		int equipoId = scanner.nextInt();
		Jugador jugador = Gestor.getJugador(jugadorId);
		Equipo equipo = Gestor.getEquipo(equipoId);
		if (jugador != null && equipo != null) {
			try {
				equipo.addJugador(jugador);
				jugador.addEquipo(equipo);
			} catch (ExcepcionMaxJugadores e) {
				System.out.println("No se puede agregar el jugador al equipo: " + e.getMessage());
			}
		} else {
			System.out.println("Jugador o equipo no encontrado");
		}
	}

	/**
	 * Agrega un coach al equipo
	 * 
	 * @param scanner input
	 */
	private static void agregarCoachAEquipo(Scanner scanner) {
		System.out.print("Ingrese el ID del coach: ");
		int coachId = scanner.nextInt();
		System.out.print("Ingrese el ID del equipo: ");
		int equipoId = scanner.nextInt();
		Coach coach = Gestor.getCoach(coachId);
		Equipo equipo = Gestor.getEquipo(equipoId);
		if (coach != null && equipo != null) {
			equipo.setCoach(coach);
		} else {
			System.out.println("Coach o equipo no encontrado");
		}
	}

	/**
	 * Utiliza el gestor para agregar un coach
	 * 
	 * @param scanner input
	 */
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

	/**
	 * Obtiene el equipo por id
	 * 
	 * @param scanner input
	 */
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

	/**
	 * Obtiene la penalización por id
	 * 
	 * @param scanner input
	 */
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

	/**
	 * Obtiene el jugador por id
	 * 
	 * @param scanner input
	 */
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

	/**
	 * Obtiene el coach por id
	 * 
	 * @param scanner input
	 */
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

	/**
	 * Elimina el equipo por id
	 * 
	 * @param scanner input
	 */
	private static void eliminarEquipo(Scanner scanner) {
		System.out.print("Ingrese el ID del equipo a eliminar: ");
		int equipoIdToDelete = scanner.nextInt();
		Gestor.removeEquipo(equipoIdToDelete);
	}

	/**
	 * Elimina la penalización por id
	 * 
	 * @param scanner input
	 */
	private static void eliminarPenalizacion(Scanner scanner) {
		System.out.print("Ingrese el ID de la penalizacion a eliminar: ");
		int penalizacionIdToDelete = scanner.nextInt();
		Gestor.removePenalizacion(penalizacionIdToDelete);
	}

	/**
	 * Elimina el jugador por id
	 * 
	 * @param scanner input
	 */
	private static void eliminarJugador(Scanner scanner) {
		System.out.print("Ingrese el ID del jugador a eliminar: ");
		int jugadorIdToDelete = scanner.nextInt();
		Gestor.eliminarJugador(Gestor.getJugador(jugadorIdToDelete));
	}

	/**
	 * Elimina el coach por id
	 * 
	 * @param scanner input
	 */
	private static void eliminarCoach(Scanner scanner) {
		System.out.print("Ingrese el ID del coach a eliminar: ");
		int coachIdToDelete = scanner.nextInt();
		Gestor.eliminarEntrenador(Gestor.getCoach(coachIdToDelete));
	}

	/**
	 * Elimina el jugador del equipo
	 * 
	 * @param scanner input
	 */
	private static void eliminarJugadorEquipo(Scanner scanner) {
		System.out.print("Ingrese el ID del jugador a eliminar: ");
		int jugadorIdToDelete = scanner.nextInt();
		System.out.print("Ingrese el ID del equipo: ");
		int equipoId = scanner.nextInt();
		Jugador jugador = Gestor.getJugador(jugadorIdToDelete);
		Equipo equipo = Gestor.getEquipo(equipoId);
		if (jugador != null && equipo != null) {
			try {
				equipo.removeJugador(jugador);
				jugador.removeEquipo(equipo);
			} catch (ExceptionJugadorNoEncontrado e) {
				System.out.println("No se puede eliminar el jugador del equipo: " + e.getMessage());

			}
		} else {
			System.out.println("Jugador o equipo no encontrado");
		}
	}

	/**
	 * Elimina el coach del equipo
	 * 
	 * @param scanner input
	 */
	private static void eliminarCoachEquipo(Scanner scanner) {
		System.out.print("Ingrese el ID del coach a eliminar: ");
		int coachIdToDelete = scanner.nextInt();
		System.out.print("Ingrese el ID del equipo: ");
		int equipoId = scanner.nextInt();
		Coach coach = Gestor.getCoach(coachIdToDelete);
		Equipo equipo = Gestor.getEquipo(equipoId);
		if (coach != null && equipo != null) {
			equipo.setCoach(null);
		} else {
			System.out.println("Coach o equipo no encontrado");
		}
	}
}