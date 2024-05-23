package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import exceptions.MyException;
import logic.Manager;
import model.Client;
import model.Contract;
import model.LolPlayer;
import model.RankedRank;
import model.Role;

public class ManagerTest {

    private Manager manager;

    @Before
    public void setUp() {
        manager = new Manager();
    }

    @Test
    public void testCreateClient() throws MyException {
        Client client = new Client(1, "Client1");
        assertTrue(manager.createClient(client));
        assertEquals(1, manager.numClients());
    }

    @Test
    public void testCreateClientWithException() throws MyException {
        Client client1 = new Client(1, "Client1");
        manager.createClient(client1);
        Client client2 = new Client(1, "Client1");
        assertThrows(MyException.class, () -> manager.createClient(client2));
        assertEquals(1, manager.numClients());
    }

    @Test
    public void testSearchClient() throws MyException {
        Client client = new Client(1, "Client1");
        manager.createClient(client);
        assertEquals(client, manager.searchClient(1));
    }

    @Test
    public void testSearchClientWithException() throws MyException {
        assertThrows(MyException.class, () -> manager.searchClient(1));
    }

    @Test
    public void testModifyClient() throws MyException {
        Client client = new Client(1, "Client1");
        manager.createClient(client);
        manager.modifyClient(1, "Client2");
        assertEquals("Client2", manager.searchClient(1).getName());
    }

    @Test
    public void testModifyClientWithException() throws MyException {
        assertThrows(MyException.class, () -> manager.modifyClient(1, "Client2"));
    }

    @Test
    public void testDeleteClient() throws MyException {
        Client client = new Client(1, "Client1");
        manager.createClient(client);
        manager.deleteClient(1);
        assertThrows(MyException.class, () -> manager.searchClient(1));
    }

    @Test
    public void testDeleteClientWithException() throws MyException {
        assertThrows(MyException.class, () -> manager.deleteClient(1));
    }

    @Test
    public void testCreatePlayer() throws MyException {
        LolPlayer player = new LolPlayer("Player1", "Name1", Role.TOP, RankedRank.GOLD, 100);
        manager.createPlayer(player);
        assertEquals(player, manager.searchPlayer("Player1"));
    }

    @Test
    public void testCreatePlayerWithException() throws MyException {
        LolPlayer player1 = new LolPlayer("Player1", "Name1", Role.TOP, RankedRank.GOLD, 100);
        manager.createPlayer(player1);
        LolPlayer player2 = new LolPlayer("Player1", "Name2", Role.TOP, RankedRank.GOLD, 100);
        assertThrows(MyException.class, () -> manager.createPlayer(player2));
        assertEquals(1, manager.numPlayers());
    }

    @Test
    public void testSearchPlayer() throws MyException {
        LolPlayer player = new LolPlayer("Player1", "Name1", Role.TOP, RankedRank.GOLD, 100);
        manager.createPlayer(player);
        assertEquals(player, manager.searchPlayer("Player1"));
    }

    @Test
    public void testModifyPlayer() throws MyException {
        LolPlayer player = new LolPlayer("Player1", "Name1", Role.TOP, RankedRank.GOLD, 100);
        manager.createPlayer(player);
        manager.modifyPlayer("Player1", "Name2", Role.JUNGLE, RankedRank.PLATINUM, 150);
        assertEquals("Name2", manager.searchPlayer("Player1").getName());
        assertEquals(Role.JUNGLE, manager.searchPlayer("Player1").getRole());
        assertEquals(RankedRank.PLATINUM, manager.searchPlayer("Player1").getRank());
        assertEquals(150, manager.searchPlayer("Player1").getLevel());
    }

    @Test
    public void testModifyPlayerWithException() throws MyException {
        assertThrows(MyException.class, () -> manager.modifyPlayer("Player1", "Name2", Role.JUNGLE, RankedRank.PLATINUM, 150));
    }

    @Test
    public void testDeletePlayer() throws MyException {
        LolPlayer player = new LolPlayer("Player1", "Name1", Role.TOP, RankedRank.GOLD, 100);
        manager.createPlayer(player);
        manager.deletePlayer("Player1");
        assertThrows(MyException.class, () -> manager.searchPlayer("Player1"));
    }

    @Test
    public void testDeletePlayerWithException() throws MyException {
        assertThrows(MyException.class, () -> manager.deletePlayer("Player1"));
    }

    @Test
    public void testCreateContract() throws MyException, ParseException {
        Client client = new Client(1, "Client1");
        manager.createClient(client);
        LolPlayer player = new LolPlayer("Player1", "Name1", Role.TOP, RankedRank.GOLD, 100);
        manager.createPlayer(player);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate = formatter.parse("01/01/2022");
        Date endDate = formatter.parse("01/01/2023");
        manager.createContract(1, "Player1", startDate, endDate);
        Contract contract = manager.searchContract(1);
        assertEquals(1, contract.getCodContract());
        assertEquals(1, contract.getCodClient());
        assertEquals("Player1", contract.getCodPlayer());
        assertEquals(startDate, contract.getStartDate());
        assertEquals(endDate, contract.getEndDate());
    }

    @Test
    public void testSearchContract() throws MyException, ParseException {
        Client client = new Client(1, "Client1");
        manager.createClient(client);
        LolPlayer player = new LolPlayer("Player1", "Name1", Role.TOP, RankedRank.GOLD, 100);
        manager.createPlayer(player);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate = formatter.parse("01/01/2022");
        Date endDate = formatter.parse("01/01/2023");
        manager.createContract(1, "Player1", startDate, endDate);
        Contract contract = manager.searchContract(1);
        assertEquals(1, contract.getCodContract());
        assertEquals(1, contract.getCodClient());
        assertEquals("Player1", contract.getCodPlayer());
        assertEquals(startDate, contract.getStartDate());
        assertEquals(endDate, contract.getEndDate());
    }

    @Test
    public void testSearchContractWithException() throws MyException {
        assertThrows(MyException.class, () -> manager.searchContract(1000));
    }

    @Test
    public void testModifyContract() throws MyException, ParseException {
        Client client = new Client(1, "Client1");
        manager.createClient(client);
        LolPlayer player = new LolPlayer("Player1", "Name1", Role.TOP, RankedRank.GOLD, 100);
        manager.createPlayer(player);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate = formatter.parse("01/01/2022");
        Date endDate = formatter.parse("01/01/2023");
        manager.createContract(1, "Player1", startDate, endDate);
        manager.modifyContract(1, 1, "Player1", formatter.parse("01/01/2023"), formatter.parse("01/01/2024"));
        Contract contract = manager.searchContract(1);
        assertEquals(1, contract.getCodContract());
        assertEquals(1, contract.getCodClient());
        assertEquals("Player1", contract.getCodPlayer());
        assertEquals(formatter.parse("01/01/2023"), contract.getStartDate());
        assertEquals(formatter.parse("01/01/2024"), contract.getEndDate());
    }

    @Test
    public void testModifyContractWithException() throws MyException, ParseException {
        Client client = new Client(1, "Client1");
        manager.createClient(client);
        LolPlayer player = new LolPlayer("Player1", "Name1", Role.TOP, RankedRank.GOLD, 100);
        manager.createPlayer(player);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate = formatter.parse("01/01/2022");
        Date endDate = formatter.parse("01/01/2023");
        manager.createContract(1, "Player1", startDate, endDate);
        assertThrows(MyException.class, () -> manager.modifyContract(1, 2, "Player2", formatter.parse("01/01/2023"), formatter.parse("01/01/2024")));
    }

    @Test
    public void testDeleteContract() throws MyException, ParseException {
        Client client = new Client(1, "Client1");
        manager.createClient(client);
        LolPlayer player = new LolPlayer("Player1", "Name1", Role.TOP, RankedRank.GOLD, 100);
        manager.createPlayer(player);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate = formatter.parse("01/01/2022");
        Date endDate = formatter.parse("01/01/2023");
        manager.createContract(1, "Player1", startDate, endDate);
        manager.deleteContract(1);
        assertThrows(MyException.class, () -> manager.searchContract(1));
    }

    @Test
    public void testDeleteContractWithException() throws MyException {
        assertThrows(MyException.class, () -> manager.deleteContract(1));
    }

    @Test
    public void testParseDate() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = formatter.parse("01/01/2022");
        assertEquals(date, Manager.parseDate("01/01/2022"));
    }

    @Test
    public void testActiveContracts() throws MyException, ParseException {
        Client client = new Client(1, "Client1");
        manager.createClient(client);
        LolPlayer player = new LolPlayer("Player1", "Name1", Role.TOP, RankedRank.GOLD, 100);
        manager.createPlayer(player);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate = formatter.parse("01/01/2022");
        Date endDate = formatter.parse("01/02/2023");
        manager.createContract(1, "Player1", startDate, endDate);
        List<Contract> activeContracts = manager.activeContracts();
        assertEquals(1, activeContracts.size());
        //assertEquals(1, activeContracts.get(0).getCodContract());
    }

    @Test
    public void testContractsByClient() throws MyException, ParseException {
        Client client = new Client(1, "Client1");
        manager.createClient(client);
        LolPlayer player = new LolPlayer("Player1", "Name1", Role.TOP, RankedRank.GOLD, 100);
        manager.createPlayer(player);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate = formatter.parse("01/01/2022");
        Date endDate = formatter.parse("01/01/2023");
        manager.createContract(1, "Player1", startDate, endDate);
        List<Contract> contracts = manager.contractsByClient(1);
        assertEquals(1, contracts.size());
        assertEquals(1, contracts.get(0).getCodContract());
    }

    @Test
    public void testContractsByClientWithException() throws MyException {
        assertThrows(MyException.class, () -> manager.contractsByClient(1));
    }

    @Test
    public void testContractsByPlayer() throws MyException, ParseException {
        Client client = new Client(1, "Client1");
        manager.createClient(client);
        LolPlayer player = new LolPlayer("Player1", "Name1", Role.TOP, RankedRank.GOLD, 100);
        manager.createPlayer(player);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate = formatter.parse("01/01/2022");
        Date endDate = formatter.parse("01/01/2023");
        manager.createContract(1, "Player1", startDate, endDate);
        List<Contract> contracts = manager.contractsByPlayer("Player1");
        assertEquals(1, contracts.size());
        assertEquals(1, contracts.get(0).getCodContract());
    }

    @Test
    public void testContractsByPlayerWithException() throws MyException {
        assertThrows(MyException.class, () -> manager.contractsByPlayer("Player1"));
    }

    @Test
    public void testNumClients() throws MyException {
        assertEquals(0, manager.numClients());
        Client client = new Client(1, "Client1");
        manager.createClient(client);
        assertEquals(1, manager.numClients());
    }

    @Test
    public void testNumPlayers() throws MyException {
        assertEquals(0, manager.numPlayers());
        LolPlayer player = new LolPlayer("Player1", "Name1", Role.TOP, RankedRank.GOLD, 100);
        manager.createPlayer(player);
        assertEquals(1, manager.numPlayers());
    }

    @Test
    public void testNumContracts() throws MyException, ParseException {
        assertEquals(0, manager.numContracts());
        Client client = new Client(1, "Client1");
        manager.createClient(client);
        LolPlayer player = new LolPlayer("Player1", "Name1", Role.TOP, RankedRank.GOLD, 100);
        manager.createPlayer(player);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate = formatter.parse("01/01/2022");
        Date endDate = formatter.parse("01/01/2023");
        manager.createContract(1, "Player1", startDate, endDate);
        assertEquals(1, manager.numContracts());
    }

}