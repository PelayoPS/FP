package model;

import java.util.Date;

/**
 * The Contract class represents a contract between a client and a player in the e-sports league.
 * It contains information such as the contract code, client code, player code, start date, and end date.
 */
public class Contract {
	int COUNTER=1;
	int codContract;
	int codClient;
	String codPlayer;
	Date startDate;
	Date endDate;

	/**
	 * Constructs a Contract object with the specified client code, player code, start date, and end date.
	 * The contract code is automatically generated and incremented.
	 * 
	 * @param codClient the client code
	 * @param codPlayer the player code
	 * @param startDate the start date of the contract
	 * @param endDate the end date of the contract
	 */
	public Contract(int codClient, String codPlayer, Date startDate, Date endDate) {
		codContract = COUNTER++;
		this.codClient = codClient;
		this.codPlayer = codPlayer;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	/**
	 * Returns the contract code.
	 * 
	 * @return the contract code
	 */
	public int getCodContract() {
		return codContract;
	}

	/**
	 * Returns the client code.
	 * 
	 * @return the client code
	 */
	public int getCodClient() {
		return codClient;
	}

	/**
	 * Returns the player code.
	 * 
	 * @return the player code
	 */
	public String getCodPlayer() {
		return codPlayer;
	}

	/**
	 * Returns the start date of the contract.
	 * 
	 * @return the start date of the contract
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * Returns the end date of the contract.
	 * 
	 * @return the end date of the contract
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * Sets the client code.
	 * 
	 * @param codClient the client code to set
	 */
	public void setCodClient(int codClient) {
		this.codClient = codClient;
	}

	/**
	 * Sets the player code.
	 * 
	 * @param codPlayer the player code to set
	 */
	public void setCodPlayer(String codPlayer) {
		this.codPlayer = codPlayer;
	}

	/**
	 * Sets the start date of the contract.
	 * 
	 * @param startDate the start date of the contract to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Sets the end date of the contract.
	 * 
	 * @param endDate the end date of the contract to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Returns a string representation of the Contract object.
	 * 
	 * @return a string representation of the Contract object
	 */
	@Override
	public String toString() {
		return "Contract [codContract=" + codContract + ", codClient=" + codClient + ", codPlayer=" + codPlayer + ", startDate=" + startDate
				+ ", endDate=" + endDate + "]";
	}
}
