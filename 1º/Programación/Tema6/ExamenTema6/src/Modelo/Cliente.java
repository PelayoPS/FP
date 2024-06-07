package Modelo;

public class Cliente  implements Comparable<Cliente>{
	private String nif;
	private String nombre;
	
	public Cliente(String nif, String nombre) {
		this.nif = nif;
		this.nombre = nombre;
	}

	public String getNif() {
		return nif;
	}

	@Override
	public int compareTo(Cliente arg0) {
		return this.nif.compareTo(arg0.nif);
	}

	
	
	
	
	
	
}
