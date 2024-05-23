
package tests;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import exceptions.MyException;
import logic.Manager;
import model.Client;
import model.Contract;
import model.LolPlayer;
import model.RankedRank;

public class ModelCreation {
    public static Manager dataCreation() throws ParseException, MyException {
        Client client1 = new Client(1, "Cliente 1");
        Client client2 = new Client(2, "Cliente 2");

        Set<Client> clients = Set.of(client1, client2);

        LolPlayer player1 = new LolPlayer("player1", "Player 1", model.Role.TOP, RankedRank.GOLD, 100);
        LolPlayer player2 = new LolPlayer("player2", "Player 2", model.Role.JUNGLE, RankedRank.SILVER, 200);
        LolPlayer player3 = new LolPlayer("player3", "Player 3", model.Role.MID, RankedRank.PLATINUM, 300);

        Map<String, LolPlayer> players = new HashMap<String, LolPlayer>();
        players.put(player1.getSummonerName(), player1);
        players.put(player2.getSummonerName(), player2);
        players.put(player3.getSummonerName(), player3);



        Contract contract1 = new Contract(client1.getCodClient(), player1.getSummonerName(), Manager.parseDate(
                "2021-01-01"), Manager.parseDate("2021-12-31"));
        
        Contract contract2 = new Contract(client1.getCodClient(), player2.getSummonerName(), Manager.parseDate(
                "2021-01-01"), Manager.parseDate("2022-12-31"));
        
        Contract contract3 = new Contract(client2.getCodClient(), player3.getSummonerName(), Manager.parseDate(
                "2021-01-01"), Manager.parseDate("2023-12-31"));

                Map<Integer, Contract> contracts; contracts = new HashMap<Integer, Contract>();
        contracts.put(contract1.getCodContract(), contract1);
        contracts.put(contract2.getCodContract(), contract2);
        contracts.put(contract3.getCodContract(), contract3);

        List<Contract> activeContracts = new ArrayList<>();
        activeContracts.add(contract1);

        Manager manager = new Manager(clients, players, contracts, activeContracts);

        System.out.println("Creación de clientes, jugadores, contratos e contratos activos iniciales exitosa.");

        return manager;
    }
}