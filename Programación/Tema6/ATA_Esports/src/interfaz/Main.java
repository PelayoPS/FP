package interfaz;

import java.util.Scanner;

import logic.Gestor;
import model.Coach;
import model.Equipo;
import model.Jugador;
import model.Penalizacion;

// clase principal que mediante el menú ejecuta
public class Main {

	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Agregar Equipo");
            System.out.println("2. Agregar Penalizacion");
            System.out.println("3. Agregar Jugador");
            System.out.println("4. Agregar Coach");
            System.out.println("5. Obtener Equipo");
            System.out.println("6. Obtener Penalizacion");
            System.out.println("7. Obtener Jugador");
            System.out.println("8. Obtener Coach");
            System.out.println("9. Eliminar Equipo");
            System.out.println("10. Eliminar Penalizacion");
            System.out.println("11. Eliminar Jugador");
            System.out.println("12. Eliminar Coach");
            System.out.println("13. Salir");

            System.out.print("Ingrese una opción: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Ingrese el ID del equipo: ");
                    int equipoId = scanner.nextInt();
                    System.out.print("Ingrese el nombre del equipo: ");
                    String equipoName = scanner.next();
					Equipo equipo = new Equipo(equipoId, equipoName);
                    Gestor.addEquipo(equipo);
                    break;
                case 2:
                    System.out.print("Ingrese el ID de la penalizacion: ");
                    int penalizacionId = scanner.nextInt();
                    System.out.print("Ingrese la descripcion de la penalizacion: ");
                    String penalizacionDesc = scanner.next();
                    Penalizacion penalizacion = new Penalizacion(penalizacionId, penalizacionDesc);
                    Gestor.addPenalizacion(penalizacion);
                    break;
                case 3:
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
                    break;
                case 4:
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
                    break;
                case 5:
                    System.out.print("Ingrese el ID del equipo a obtener: ");
                    int equipoIdToGet = scanner.nextInt();
                    Equipo retrievedEquipo = Gestor.getEquipo(equipoIdToGet);
                    if (retrievedEquipo!= null) {
                        System.out.println("Equipo encontrado: " + retrievedEquipo.getNombre());
                    } else {
                        System.out.println("Equipo no encontrado");
                    }
                    break;
                case 6:
                    System.out.print("Ingrese el ID de la penalizacion a obtener: ");
                    int penalizacionIdToGet = scanner.nextInt();
                    Penalizacion retrievedPenalizacion = Gestor.getPenalizacion(penalizacionIdToGet);
                    if (retrievedPenalizacion!= null) {
                        System.out.println("Penalizacion encontrada: " + retrievedPenalizacion.getDescription());
                    } else {
                        System.out.println("Penalizacion no encontrada");
                    }
                    break;
                case 7:
                    System.out.print("Ingrese el ID del jugador a obtener: ");
                    int jugadorIdToGet = scanner.nextInt();
                    Jugador retrievedJugador = Gestor.getJugador(jugadorIdToGet);
                    if (retrievedJugador!= null) {
                        System.out.println("Jugador encontrado: " + retrievedJugador.getName());
                    } else {
                        System.out.println("Jugador no encontrado");
                    }
                    break;
                case 8:
                    System.out.print("Ingrese el ID del coach a obtener: ");
                    int coachIdToGet = scanner.nextInt();
                    Coach retrievedCoach = Gestor.getCoach(coachIdToGet);
                    if (retrievedCoach!= null) {
                        System.out.println("Coach encontrado: " + retrievedCoach.getName());
                    } else {
                        System.out.println("Coach no encontrado");
                    }
                    break;
                case 9:
                    System.out.print("Ingrese el ID del equipo a eliminar: ");
                    int equipoIdToDelete = scanner.nextInt();
                    Gestor.removeEquipo(equipoIdToDelete);
                    break;
                case 10:
                    System.out.print("Ingrese el ID de la penalizacion a eliminar: ");
                    int penalizacionIdToDelete = scanner.nextInt();
                    Gestor.removePenalizacion(penalizacionIdToDelete);
                    break;
                case 11:
                    System.out.print("Ingrese el ID del jugador a eliminar: ");
					int jugadorIdToDelete = scanner.nextInt();
					Gestor.eliminarJugador(Gestor.getJugador(jugadorIdToDelete));
                    break;
                case 12:
                    System.out.print("Ingrese el ID del coach a eliminar: ");
                    int coachIdToDelete = scanner.nextInt();
                    Gestor.eliminarEntrenador(Gestor.getCoach(coachIdToDelete));
                    break;
                case 13:
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }
}
