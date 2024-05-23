package interfaces;

import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

import exceptions.MyException;
import logic.Manager;
import model.Client;
import model.Contract;
import model.LolPlayer;
import model.RankedRank;
import model.Role;

/**
 * The main class of the program that serves as the entry point.
 * It contains the main method which runs the program's menu-driven interface.
 */
public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menú:");
            System.out.println("1. Crear cliente");
            System.out.println("2. Buscar cliente");
            System.out.println("3. Modificar cliente");
            System.out.println("4. Eliminar cliente");
            System.out.println("5. Crear jugador");
            System.out.println("6. Buscar jugador");
            System.out.println("7. Modificar jugador");
            System.out.println("8. Eliminar jugador");
            System.out.println("9. Crear contrato");
            System.out.println("10. Buscar contrato");
            System.out.println("11. Modificar contrato");
            System.out.println("12. Eliminar contrato");
            System.out.println("13. Ver contratos activos");
            System.out.println("14. Ver contratos por cliente");
            System.out.println("15. Ver contratos por jugador");
            System.out.println("16. Ver número de clientes");
            System.out.println("17. Ver número de jugadores");
            System.out.println("18. Ver número de contratos");
            System.out.println("19. Salir");

            System.out.print("Ingrese una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    crearCliente(manager, scanner);
                    break;
                case 2:
                    buscarCliente(manager, scanner);
                    break;
                case 3:
                    modificarCliente(manager, scanner);
                    break;
                case 4:
                    eliminarCliente(manager, scanner);
                    break;
                case 5:
                    crearJugador(manager, scanner);
                    break;
                case 6:
                    buscarJugador(manager, scanner);
                    break;
                case 7:
                    modificarJugador(manager, scanner);
                    break;
                case 8:
                    eliminarJugador(manager, scanner);
                    break;
                case 9:
                    crearContrato(manager, scanner);
                    break;
                case 10:
                    buscarContrato(manager, scanner);
                    break;
                case 11:
                    modificarContrato(manager, scanner);
                    break;
                case 12:
                    eliminarContrato(manager, scanner);
                    break;
                case 13:
                    verContratosActivos(manager);
                    break;
                case 14:
                    verContratosPorCliente(manager, scanner);
                    break;
                case 15:
                    verContratosPorJugador(manager, scanner);
                    break;
                case 16:
                    verNumeroClientes(manager);
                    break;
                case 17:
                    verNumeroJugadores(manager);
                    break;
                case 18:
                    verNumeroContratos(manager);
                    break;
                case 19:
                    System.out.println("Adiós!");
                    return;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    /**
     * Creates a new client with the given code and name.
     *
     * @param manager the manager object used to create the client
     * @param scanner the scanner object used to read user input
     *                The method reads the code and name of the client from the user
     *                input,
     *                creates a new Client object with the given parameters, and
     *                adds it to the
     *                manager's list of clients. If the client already exists, a
     *                MyException
     *                is thrown with the message "Cliente ya existe".
     */
    private static void crearCliente(Manager manager, Scanner scanner) {
        System.out.print("Ingrese el código del cliente: ");
        int codClient = scanner.nextInt();
        System.out.print("Ingrese el nombre del cliente: ");
        String name = scanner.next();

        try {
            manager.createClient(new Client(codClient, name));
            System.out.println("Cliente creado con éxito");
        } catch (MyException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Searches for a client with the given code.
     *
     * @param manager the manager object used to search for the client
     * @param scanner the scanner object used to read user input
     *                The method reads the code of the client from the user input,
     *                searches for
     *                the client in the manager's list of clients, and prints its
     *                name. If the
     *                client is not found, a MyException is thrown with the message
     *                "Cliente no
     *                encontrado".
     */
    private static void buscarCliente(Manager manager, Scanner scanner) {
        System.out.print("Ingrese el código del cliente: ");
        int codClient = scanner.nextInt();

        try {
            Client client = manager.searchClient(codClient);
            System.out.println("Cliente encontrado: " + client.getName());
        } catch (MyException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Modifies the name of a client with the given code.
     *
     * @param manager the manager object used to modify the client
     * @param scanner the scanner object used to read user input
     *                The method reads the code and new name of the client from the
     *                user input,
     *                searches for the client in the manager's list of clients,
     *                modifies its name,
     *                and prints a success message. If the client is not found, a
     *                MyException
     *                is thrown with the message "Cliente no encontrado".
     */
    private static void modificarCliente(Manager manager, Scanner scanner) {
        System.out.print("Ingrese el código del cliente: ");
        int codClient = scanner.nextInt();
        System.out.print("Ingrese el nuevo nombre del cliente: ");
        String name = scanner.next();

        try {
            manager.modifyClient(codClient, name);
            System.out.println("Cliente modificado con éxito");
        } catch (MyException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Deletes a client with the given code.
     *
     * @param manager the manager object used to delete the client
     * @param scanner the scanner object used to read user input
     *                The method reads the code of the client from the user input,
     *                searches for
     *                the client in the manager's list of clients, and deletes it.
     *                If the client
     *                is not found, a MyException is thrown with the message
     *                "Cliente no
     *                encontrado".
     */
    private static void eliminarCliente(Manager manager, Scanner scanner) {
        System.out.print("Ingrese el código del cliente: ");
        int codClient = scanner.nextInt();

        try {
            manager.deleteClient(codClient);
            System.out.println("Cliente eliminado con éxito");
        } catch (MyException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Creates a new player with the given summoner name, name, role, rank, and
     * level.
     *
     * @param manager the manager object used to create the player
     * @param scanner the scanner object used to read user input
     *                The method reads the summoner name, name, role, rank, and
     *                level of the
     *                player from the user input, creates a new Player object with
     *                the given
     *                parameters, and adds it to the manager's list of players. If
     *                the player
     *                already exists, a MyException is thrown with the message
     *                "Jugador ya
     *                existe".
     */
    private static void crearJugador(Manager manager, Scanner scanner) {
        System.out.print("Ingrese el nombre de invocador del jugador: ");
        String summonerName = scanner.next();
        System.out.print("Ingrese el nombre del jugador: ");
        String name = scanner.next();
        System.out.print("Ingrese el rol del jugador (TOP, JUNGLE, MID, ADC, SUPPORT): ");
        String roleString = scanner.next();
        Role role = Role.valueOf(roleString);
        System.out
                .print("Ingrese el rango del jugador (BRONZE, SILVER, GOLD, PLATINUM, DIAMOND, MASTER, CHALLENGER): ");
        String rankString = scanner.next();
        RankedRank rank = RankedRank.valueOf(rankString);
        System.out.print("Ingrese el nivel del jugador: ");
        int level = scanner.nextInt();

        try {
            manager.createPlayer(new LolPlayer(summonerName, name, role, rank, level));
            System.out.println("Jugador creado con éxito");
        } catch (MyException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Searches for a player with the given code.
     *
     * @param manager the manager object used to search for the player
     * @param scanner the scanner object used to read user input
     *                The method reads the code of the player from the user input,
     *                searches for
     *                the player in the manager's list of players, and prints its
     *                name. If the
     *                player is not found, a MyException is thrown with the message
     *                "Jugador no
     *                encontrado".
     */
    private static void buscarJugador(Manager manager, Scanner scanner) {
        System.out.print("Ingrese el código del jugador: ");
        String codPlayer = scanner.next();

        LolPlayer player;
        try {
            player = manager.searchPlayer(codPlayer);
            System.out.println("Jugador encontrado: " + player.getName());

        } catch (MyException e) {
            e.printStackTrace();
        }
        
    }

    /**
     * Modifies the name, role, rank, and level of a player with the given code.
     *
     * @param manager the manager object used to modify the player
     * @param scanner the scanner object used to read user input
     *                The method reads the code, new name, new role, new rank, and
     *                new level of
     *                the player from the user input, searches for the player in the
     *                manager's
     *                list of players, modifies its attributes, and prints a success
     *                message.
     *                If the player is not found, a MyException is thrown with the
     *                message
     *                "Jugador no encontrado".
     */
    private static void modificarJugador(Manager manager, Scanner scanner) {
        System.out.print("Ingrese el código del jugador: ");
        String codPlayer = scanner.next();
        System.out.print("Ingrese el nuevo nombre del jugador: ");
        String name = scanner.next();
        System.out.print("Ingrese el nuevo rol del jugador (TOP, JUNGLE, MID, ADC, SUPPORT): ");
        String roleString = scanner.next();
        Role role = Role.valueOf(roleString);
        System.out.print(
                "Ingrese el nuevo rango del jugador (BRONZE, SILVER, GOLD, PLATINUM, DIAMOND, MASTER, CHALLENGER): ");
        String rankString = scanner.next();
        RankedRank rank = RankedRank.valueOf(rankString);
        System.out.print("Ingrese el nuevo nivel del jugador: ");
        int level = scanner.nextInt();

        try {
            manager.modifyPlayer(codPlayer, name, role, rank, level);
            System.out.println("Jugador modificado con éxito");
        } catch (MyException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    /**
     * Deletes a player with the given code.
     *
     * @param manager the manager object used to delete the player
     * @param scanner the scanner object used to read user input
     *                The method reads the code of the player from the user input,
     *                searches for
     *                the player in the manager's list of players, and deletes it.
     *                If the player
     *                is not found, a MyException is thrown with the message
     *                "Jugador no
     *                encontrado".
     */
    private static void eliminarJugador(Manager manager, Scanner scanner) {
        System.out.print("Ingrese el código del jugador: ");
        String codPlayer = scanner.next();

        try {
            manager.deletePlayer(codPlayer);
            System.out.println("Jugador eliminado con éxito");
        } catch (MyException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Creates a new contract with the given client code, player code, start date,
     * and end date.
     *
     * @param manager the manager object used to createthe contract
     * @param scanner the scanner object used to read user input
     *                The method reads the client code, player code, start date, and
     *                end date
     *                of the contract from the user input, creates a new Contract
     *                object with
     *                the given parameters, and adds it to the manager's list of
     *                contracts. If
     *                the contract already exists, a MyException is thrown with the
     *                message
     *                "Contrato ya existe".
     */
    private static void crearContrato(Manager manager, Scanner scanner) {
        System.out.print("Ingrese el nuevo código del cliente: ");
        int codClient = scanner.nextInt();
        System.out.print("Ingrese el nuevo código del jugador: ");
        String codPlayer = scanner.next();
        System.out.println("Ingrese la fecha con formato yyyy-MM-dd");
        System.out.print("Ingrese la nueva fecha de inicio del contrato: ");
        Date startDate = null;
        try {
            startDate = Manager.parseDate(scanner.next());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.print("Ingrese la nueva fecha de fin del contrato: ");
        Date endDate = null;
        try {
            endDate = Manager.parseDate(scanner.next());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            manager.createContract(codClient, codPlayer, startDate, endDate);
            System.out.println("Contrato creado con éxito");
        } catch (MyException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Searches for a contract with the given code.
     *
     * @param manager the manager object used to search for the contract
     * @param scanner the scanner object used to read user input
     *                The method reads the code of the contract from the user input,
     *                searches
     *                for the contract in the manager's list of contracts, and
     *                prints its
     *                information. If the contract is not found, a MyException is
     *                thrown with
     *                the message "Contrato no encontrado".
     */
    private static void buscarContrato(Manager manager, Scanner scanner) {
        System.out.print("Ingrese el código del contrato: ");
        int codContract = scanner.nextInt();
        try{
            
            System.out.println("Contrato encontrado: " + manager.searchContract(codContract).toString());
        } catch (MyException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
    }

    /**
     * Modifies the client code, player code, start date, and end date of a contract
     * with the given code.
     *
     * @param manager the manager object used to modify the contract
     * @param scanner the scanner object used to read user input
     *                The method reads the code, new client code, new player code,
     *                new start
     *                date, and new end date of the contract from the user input,
     *                searches for
     *                the contract in the manager's list of contracts, modifies its
     *                attributes,
     *                and prints a success message. If the contract is not found, a
     *                MyException
     *                is thrown with the message "Contrato no encontrado".
     */
    private static void modificarContrato(Manager manager, Scanner scanner) {
        System.out.print("Ingrese el código del contrato: ");
        int codContract = scanner.nextInt();
        System.out.print("Ingrese el nuevo código del cliente: ");
        int codClient = scanner.nextInt();
        System.out.print("Ingrese el nuevo código del jugador: ");
        String codPlayer = scanner.next();
        System.out.println("Ingrese la fecha con formato yyyy-MM-dd");
        System.out.print("Ingrese la nueva fecha de inicio del contrato: ");
        Date startDate = null;
        try {
            startDate = Manager.parseDate(scanner.next());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.print("Ingrese la nueva fecha de fin del contrato: ");
        Date endDate = null;
        try {
            endDate = Manager.parseDate(scanner.next());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            manager.modifyContract(codContract, codClient, codPlayer, startDate, endDate);
            System.out.println("Contrato modificado con éxito");
        } catch (MyException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Deletes a contract with the given code.
     *
     * @param manager the manager object used to delete the contract
     * @param scanner the scanner object used to read user input
     *                The method reads the code of the contract from the user input,
     *                searches
     *                for the contract in the manager's list of contracts, and
     *                deletes it. If the
     *                contract is not found, a MyException is thrown with the
     *                message
     *                "Contrato no encontrado".
     */
    private static void eliminarContrato(Manager manager, Scanner scanner) {
        System.out.print("Ingrese el código del contrato: ");
        int codContract = scanner.nextInt();

        try {
            manager.deleteContract(codContract);
            System.out.println("Contrato eliminado con éxito");
        } catch (MyException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Prints the list of active contracts.
     *
     * @param manager the manager object used to get the list of active contracts
     *                The method calls the getActiveContracts method of the manager
     *                object to
     *                get the list of active contracts, and prints their
     *                information.
     */
    private static void verContratosActivos(Manager manager) {
        System.out.println("Contratos activos:");
        for (Contract contract : manager.getActiveContracts()) {
            System.out.println(contract.toString());
        }
    }

    /**
     * Prints the list of contracts of a client with the given code.
     *
     * @param manager the manager object used to get the list of contracts of a
     *                client
     * @param scanner the scanner object used to read user input
     *                The method reads the code of the client from the user input,
     *                searches for
     *                the client in the manager's list of clients, and prints the
     *                information of
     *                its contracts. If the client is not found, a MyException is
     *                thrown with
     *                the message "Cliente no encontrado".
     */
    private static void verContratosPorCliente(Manager manager, Scanner scanner) {
        System.out.print("Ingrese el código del cliente: ");
        int codClient = scanner.nextInt();

        System.out.println("Contratos del cliente:");
        try {
            for (Contract contract : manager.contractsByClient(codClient)) {
                System.out.println(contract.toString());
            }
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prints the list of contracts of a player with the given code.
     *
     * @param manager the manager object used to get the list of contracts of a
     *                player
     * @param scanner the scanner object used to read user input
     *                The method reads the code of the player from the user input,
     *                searches for
     *                the player in the manager's list of players, and prints the
     *                information of
     *                its contracts. If the player is not found, a MyException is
     *                thrown with
     *                the message "Jugador no encontrado".
     */
    private static void verContratosPorJugador(Manager manager, Scanner scanner) {
        System.out.print("Ingrese el código del jugador: ");
        String codPlayer = scanner.next();

        System.out.println("Contratos del jugador:");
        try {
            for (Contract contract : manager.contractsByPlayer(codPlayer)) {
                System.out.println(contract.toString());
            }
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prints the number of clients.
     *
     * @param manager the manager object used to get the number of clients
     *                The method calls the getNumberOfClients method of the manager
     *                object to
     *                get the number of clients, and prints it.
     */
    private static void verNumeroClientes(Manager manager) {
        System.out.println("Número de clientes: " + manager.getClients().size());
    }

    /**
     * Prints the number of players.
     *
     * @param manager the manager object used to get the number of players
     *                The method calls the getNumberOfPlayers method of the manager
     *                object to
     *                get the number of players, and prints it.
     */
    private static void verNumeroJugadores(Manager manager) {
        System.out.println("Número de jugadores: " + manager.getPlayers().size());
    }

    /**
     * Prints the number of contracts.
     *
     * @param manager the manager object used to get the number of contracts
     *                The method calls the getNumberOfContracts method of the
     *                manager object to
     *                get the number of contracts, and prints it.
     */
    private static void verNumeroContratos(Manager manager) {
        System.out.println("Número de contratos: " + manager.getContracts().size());
    }

}