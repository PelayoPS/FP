package logic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import exceptions.MyException;
import model.Client;
import model.Contract;
import model.LolPlayer;
import model.RankedRank;
import model.Role;
import java.util.Collection;

/**
 * The Manager class represents a manager for a league of e-sports. It manages
 * clients, players, and contracts.
 */
public class Manager {
    Set<Client> clients;
    Map<String, LolPlayer> players;
    Map<Integer, Contract> contracts;
    List<Contract> activeContracts;

    /**
     * Constructs a new Manager object.
     */
    public Manager() {
        clients = new TreeSet<Client>();
        players = new HashMap<String, LolPlayer>();
        contracts = new HashMap<Integer, Contract>();
        activeContracts = new ArrayList<Contract>();
    }

    /**
     * Constructs a new Manager object with the specified clients, players,
     * contracts, and active contracts.
     * 
     * @param clients         the set of clients
     * @param players         the map of players
     * @param contracts       the map of contracts
     * @param activeContracts the list of active contracts
     */
    public Manager(Set<Client> clients, Map<String, LolPlayer> players, Map<Integer, Contract> contracts,
            List<Contract> activeContracts) {
        this.clients = clients;
        this.players = players;
        this.contracts = contracts;
        this.activeContracts = activeContracts;
    }

    /**
     * Creates a new client and adds it to the manager's list of clients.
     * 
     * @param c the client to be created
     * @return true if the client is created successfully, false otherwise
     * @throws MyException if the client already exists
     */
    public boolean createClient(Client c) throws MyException {
        if (clients.stream().anyMatch(client -> client.getCodClient() == c.getCodClient())) {
            throw new MyException("El cliente ya existe");
        }
        clients.add(c);
        return true;
    }

    /**
     * Searches for a client with the given client code.
     * 
     * @param codClient the client code to search for
     * @return the client with the given client code, or null if not found
     * @throws MyException if the client is not found
     */
    public Client searchClient(int codClient) throws MyException {
        if (clients.stream().anyMatch(
                client -> client.getCodClient() == codClient)) {
            return clients.stream()
                    .filter(client -> client.getCodClient() == codClient)
                    .findFirst().get();
        } else {
            throw new MyException("El cliente no existe");
        }
    }

    /**
     * Modifies the name of a client with the given client code.
     * 
     * @param codClient the client code to modify
     * @param name      the new name for the client
     * @return true if the client is modified successfully, false otherwise
     * @throws MyException if the client is not found
     */
    public boolean modifyClient(int codClient, String name) throws MyException {
        if (clients.stream().anyMatch(
                client -> client.getCodClient() == codClient)) {
            clients.stream()
                    .filter(client -> client.getCodClient() == codClient)
                    .findFirst().get().setName(name);
            return true;
        } else {
            throw new MyException("El cliente no existe");
        }
    }

    /**
     * Deletes a client with the given client code.
     * 
     * @param codClient the client code to delete
     * @return true if the client is deleted successfully, false otherwise
     * @throws MyException if the client is not found
     */
    public boolean deleteClient(int codClient) throws MyException {
        if (clients.stream().anyMatch(
                client -> client.getCodClient() == codClient)) {
            clients.removeIf(client -> client.getCodClient() == codClient);
            return true;
        } else {
            throw new MyException("El cliente no existe");
        }

    }

    /**
     * Creates a new player and adds it to the manager's list of players.
     * 
     * @param p the player to be created
     * @return true if the player is created successfully, false otherwise
     * @throws MyException if the player already exists
     */
    public boolean createPlayer(LolPlayer p) throws MyException {
        if (players.containsKey(p.getSummonerName())) {
            throw new MyException("El jugador ya existe");
        }
        players.put(p.getSummonerName(), p);
        return true;
    }

    /**
     * Searches for a player with the given summoner name.
     * 
     * @param summonerName the summoner name to search for
     * @return the player with the given summoner name, or null if not found
     * @throws MyException 
     */
    public LolPlayer searchPlayer(String summonerName) throws MyException {
        if (!players.containsKey(summonerName)) {
            throw new MyException("El no ha sido encontrado");
        }
        return players.get(summonerName);
    }

    /**
     * Modifies the details of a player with the given summoner name.
     * 
     * @param summonerName the summoner name to modify
     * @param name         the new name for the player
     * @param role         the new role for the player
     * @param rank         the new ranked rank for the player
     * @param level        the new level for the player
     * @return true if the player is modified successfully, false otherwise
     * @throws MyException if the player is not found
     */
    public boolean modifyPlayer(String summonerName, String name, Role role, RankedRank rank, int level)
            throws MyException {
        if (players.containsKey(summonerName)) {
            players.get(summonerName).setName(name);
            players.get(summonerName).setRole(role);
            players.get(summonerName).setRank(rank);
            players.get(summonerName).setLevel(level);
            return true;
        } else {
            throw new MyException("El jugador no existe");
        }
    }

    /**
     * Deletes a player with the given summoner name.
     * 
     * @param summonerName the summoner name to delete
     * @return true if the player is deleted successfully, false otherwise
     * @throws MyException if the player is not found
     */
    public boolean deletePlayer(String summonerName) throws MyException {
        if (players.containsKey(summonerName)) {
            players.remove(summonerName);
            return true;
        } else {
            throw new MyException("El jugador no existe");
        }
    }

    /**
     * Creates a new contract between a client and a player.
     * 
     * @param codClient    the client code
     * @param summonerName the summoner name of the player
     * @param startDate    the start date of the contract
     * @param endDate      the end date of the contract
     * @return true if the contract is created successfully, false otherwise
     * @throws MyException if the client or player is not found
     */
    public boolean createContract(int codClient, String summonerName, Date startDate, Date endDate) throws MyException {
        if (!clients.stream().anyMatch(client -> client.getCodClient() == codClient)) {
            throw new MyException("El cliente no existe");
        }
        if (!players.containsKey(summonerName)) {
            throw new MyException("El jugador no existe");
        }
        Contract contract = new Contract(codClient, summonerName, startDate, endDate);
        contracts.put(contract.getCodContract(), contract);
        return true;
    }

    /**
     * Searches for a contract with the given contract code.
     * 
     * @param codContract the contract code to search for
     * @return the contract with the given contract code, or null if not found
     * @throws MyException 
     */
    public Contract searchContract(int codContract) throws MyException {
        if (!contracts.containsKey(codContract)) {
            throw new MyException("El contrato no se ha encontrado");
        }
        return contracts.get(codContract);
    }

    /**
     * Modifies the details of a contract with the given contract code.
     * 
     * @param codContract  the contract code to modify
     * @param codClient    the new client code for the contract
     * @param summonerName the new summoner name for the contract
     * @param startDate    the new start date for the contract
     * @param endDate      the new end date for the contract
     * @return true if the contract is modified successfully, false otherwise
     * @throws MyException if the contract, client, or player is not found
     */
    public boolean modifyContract(int codContract, int codClient, String summonerName, Date startDate, Date endDate)
            throws MyException {
        if (!contracts.containsKey(codContract)) {
            throw new MyException("El contrato no existe");
        }
        if (!clients.stream().anyMatch(client -> client.getCodClient() == codClient)) {
            throw new MyException("El cliente no existe");
        }
        if (!players.containsKey(summonerName)) {
            throw new MyException("El jugador no existe");
        }
        contracts.get(codContract).setCodClient(codClient);
        contracts.get(codContract).setCodPlayer(summonerName);
        contracts.get(codContract).setStartDate(startDate);
        contracts.get(codContract).setEndDate(endDate);
        return true;
    }

    /**
     * Deletes a contract with the given contract code.
     * 
     * @param codContract the contract code to delete
     * @return true if the contract is deleted successfully, false otherwise
     * @throws MyException if the contract is not found
     */
    public boolean deleteContract(int codContract) throws MyException {
        if (!contracts.containsKey(codContract)) {
            throw new MyException("El contrato no existe");
        }
        contracts.remove(codContract);
        return true;
    }

    /**
     * Parses a date string into a Date object.
     * 
     * @param dateString the date string to parse
     * @return the parsed Date object
     * @throws ParseException if the date string is not in the correct format
     */
    public static Date parseDate(String dateString) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date date = formato.parse(dateString);
        return date;
    }

    /**
     * Returns a list of active contracts.
     * 
     * @return a list of active contracts
     */
    public List<Contract> activeContracts() {
        Collection<Contract> contractList = contracts.values();
        for (Contract contract : contractList) {
            if (contract.getEndDate().after(contract.getStartDate())) {
                activeContracts.add(contract);
            }
        }
        return activeContracts;
    }

    /**
     * Returns a list of contracts associated with a client.
     *
     * @param codClient the client code
     * @return a list of contracts associated with the client
     * @throws MyException if the client is not found
     */
    public List<Contract> contractsByClient(int codClient) throws MyException {
        clients.stream()
                .filter(c -> c.getCodClient() == codClient)
                .findFirst()
                .orElseThrow(() -> new MyException("El cliente no existe"));

        return contracts.values().stream()
                .filter(contract -> contract.getCodClient() == codClient)
                .collect(Collectors.toList());
    }

    /**
     * Returns a list of contracts associated with a player.
     * 
     * @param summonerName the summoner name of the player
     * @return a list of contracts associated with the player
     * @throws MyException if the player is not found
     */
    public List<Contract> contractsByPlayer(String summonerName) throws MyException {
        if (!players.containsKey(summonerName)) {
            throw new MyException("El jugador no existe");
        }
        return contracts.values().stream()
                .filter(contract -> contract.getCodPlayer().equals(summonerName))
                .collect(Collectors.toList());
    }

    /**
     * Returns the number of clients.
     * 
     * @return the number of clients
     */
    public int numClients() {
        return clients.size();
    }

    /**
     * Returns the number of players.
     * 
     * @return the number of players
     */
    public int numPlayers() {
        return players.size();
    }

    /**
     * Returns the number of contracts.
     * 
     * @return the number of contracts
     */
    public int numContracts() {
        return contracts.size();
    }

    /**
     * Returns the set of clients.
     * 
     * @return the set of clients
     */
    public Set<Client> getClients() {
        return clients;
    }

    /**
     * Returns the map of players.
     * 
     * @return the map of players
     */
    public Map<String, LolPlayer> getPlayers() {
        return players;
    }

    /**
     * Returns the map of contracts.
     * 
     * @return the map of contracts
     */
    public Map<Integer, Contract> getContracts() {
        return contracts;
    }

    /**
     * Returns the list of active contracts.
     * 
     * @return the list of active contracts
     */
    public List<Contract> getActiveContracts() {
        return activeContracts;
    }

}
