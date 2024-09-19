package model;

import exceptions.MyException;

/**
 * The Client class represents a client in the system.
 * It implements the Comparable interface to allow for comparison between
 * clients based on their code.
 */
public class Client implements Comparable<Client> {
	int codClient;
	String name;

	/**
	 * Constructs a Client object with the specified code and name.
	 * 
	 * @param codClient the code of the client
	 * @param name      the name of the client
	 * @throws MyException if the code of the client is negative
	 */
	public Client(int codClient, String name) throws MyException {
		if (codClient < 0)
			throw new MyException("El código de cliente no puede ser negativo");
		this.codClient = codClient;
		this.name = name;
	}

	/**
	 * Constructs a Client object with the specified code and an empty name.
	 * 
	 * @param codClient the code of the client
	 * @throws MyException if the code of the client is negative
	 */
	public Client(int codClient) throws MyException {
		this(codClient, "");
	}

	/**
	 * Returns the code of the client.
	 * 
	 * @return the code of the client
	 */
	public int getCodClient() {
		return codClient;
	}

	/**
	 * Returns the name of the client.
	 * 
	 * @return the name of the client
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the code of the client.
	 * 
	 * @param codClient the new code of the client
	 */
	public void setCodClient(int codClient) {
		this.codClient = codClient;
	}

	/**
	 * Sets the name of the client.
	 * 
	 * @param name the new name of the client
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns a string representation of the client.
	 * 
	 * @return a string representation of the client
	 */
	@Override
	public String toString() {
		return "Client [codClient=" + codClient + ", name=" + name + "]";
	}

	/**
	 * Compares this client with the specified client for order.
	 * 
	 * @param c the client to be compared
	 * @return a negative integer, zero, or a positive integer as this client is
	 *         less than, equal to, or greater than the specified client
	 */
	@Override
	public int compareTo(Client c) {
		if (this.codClient == c.codClient)
			return 0;
		else if (this.codClient < c.codClient)
			return -1;
		else
			return 1;
	}
}
