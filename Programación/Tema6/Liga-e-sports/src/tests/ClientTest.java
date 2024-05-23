package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import exceptions.MyException;
import model.Client;

public class ClientTest {

    @Test
    public void testClient() throws MyException {
        Client client = new Client(1, "John Doe");
        assertEquals(1, client.getCodClient());
        assertEquals("John Doe", client.getName());
    }

    @Test
    public void testClientNegativeCode() {
        assertThrows(MyException.class, () -> new Client(-1, "John Doe"));
    }

    @Test
    public void testClientEmptyName() throws MyException {
        Client client = new Client(1);
        assertEquals(1, client.getCodClient());
        assertEquals("", client.getName());
    }

    @Test
    public void testGetCodClient() throws MyException {
        Client client = new Client(1, "John Doe");
        assertEquals(1, client.getCodClient());
    }

    @Test
    public void testGetName() throws MyException {
        Client client = new Client(1, "John Doe");
        assertEquals("John Doe", client.getName());
    }

    @Test
    public void testSetCodClient() throws MyException {
        Client client = new Client(1, "John Doe");
        client.setCodClient(2);
        assertEquals(2, client.getCodClient());
    }

    @Test
    public void testSetName() throws MyException {
        Client client = new Client(1, "John Doe");
        client.setName("Jane Doe");
        assertEquals("Jane Doe", client.getName());
    }

    @Test
    public void testToString() throws MyException {
        Client client = new Client(1, "John Doe");
        assertEquals("Client [codClient=1, name=John Doe]", client.toString());
    }

    @Test
    public void testCompareTo() throws MyException {
        Client client1 = new Client(1, "John Doe");
        Client client2 = new Client(2, "Jane Doe");
        Client client3 = new Client(1, "John Doe");
        assertTrue(client1.compareTo(client2) < 0);
        assertTrue(client2.compareTo(client1) > 0);
        assertEquals(0, client1.compareTo(client3));
    }
}