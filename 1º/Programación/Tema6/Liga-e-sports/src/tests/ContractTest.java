package tests;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

import model.Contract;

public class ContractTest {

    @Test
    public void testContract() {
        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime() + 3600000); // one hour later
        Contract contract = new Contract(1, "Player1", startDate, endDate);
        assertEquals(1, contract.getCodContract());
        assertEquals(1, contract.getCodClient());
        assertEquals("Player1", contract.getCodPlayer());
        assertEquals(startDate, contract.getStartDate());
        assertEquals(endDate, contract.getEndDate());
    }

    @Test
    public void testGetCodContract() {
        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime() + 3600000); // one hour later
        Contract contract = new Contract(1, "Player1", startDate, endDate);
        assertEquals(1, contract.getCodContract());
    }

    @Test
    public void testGetCodClient() {
        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime() + 3600000); // one hour later
        Contract contract = new Contract(1, "Player1", startDate, endDate);
        assertEquals(1, contract.getCodClient());
    }

    @Test
    public void testGetCodPlayer() {
        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime() + 3600000); // one hour later
        Contract contract = new Contract(1, "Player1", startDate, endDate);
        assertEquals("Player1", contract.getCodPlayer());
    }

    @Test
    public void testGetStartDate() {
        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime() + 3600000); // one hour later
        Contract contract = new Contract(1, "Player1", startDate, endDate);
        assertEquals(startDate, contract.getStartDate());
    }

    @Test
    public void testGetEndDate() {
        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime() + 3600000); // one hour later
        Contract contract = new Contract(1, "Player1", startDate, endDate);
        assertEquals(endDate, contract.getEndDate());
    }

    @Test
    public void testSetCodClient() {
        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime() + 3600000); // one hour later
        Contract contract = new Contract(1, "Player1", startDate, endDate);
        contract.setCodClient(2);
        assertEquals(2, contract.getCodClient());
    }

    @Test
    public void testSetCodPlayer() {
        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime() + 3600000); // one hour later
        Contract contract = new Contract(1, "Player1", startDate, endDate);
        contract.setCodPlayer("Player2");
        assertEquals("Player2", contract.getCodPlayer());
    }

    @Test
    public void testSetStartDate() {
        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime() + 3600000); // one hour later
        Contract contract = new Contract(1, "Player1", startDate, endDate);
        Date newStartDate = new Date(startDate.getTime() - 3600000); // one hour earlier
        contract.setStartDate(newStartDate);
        assertEquals(newStartDate, contract.getStartDate());
    }

    @Test
    public void testSetEndDate() {
        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime() + 3600000); // one hour later
        Contract contract = new Contract(1, "Player1", startDate, endDate);
        Date newEndDate = new Date(endDate.getTime() + 3600000); // one hour later
        contract.setEndDate(newEndDate);
        assertEquals(newEndDate, contract.getEndDate());
    }

    @Test
    public void testToString() {
        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime() + 3600000); // one hour later
        Contract contract = new Contract(1, "Player1", startDate, endDate);
        String expected = "Contract [codContract=" + contract.getCodContract() + ", codClient="
                + contract.getCodClient()
                + ", codPlayer=" + contract.getCodPlayer() + ", startDate=" + contract.getStartDate() + ", endDate="
                + contract.getEndDate() + "]";
        assertEquals(expected, contract.toString());
    }
}