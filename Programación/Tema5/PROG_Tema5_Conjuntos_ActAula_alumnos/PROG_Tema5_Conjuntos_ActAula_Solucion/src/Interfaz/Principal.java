package Interfaz;

import Ficheros.*;

public class Principal {
	public static int NUM_PAQUETES=30;
	public static int cps[]={33203,33204,33025,33207};
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Empresa empresa=new Empresa();
		cargarDatos(empresa);
		System.out.println("***************************************");
		empresa.mostrarPaquetes();
		empresa.mostrarVehiculos();

		System.out.println("***************************************");
		empresa.repartirPaquetes(cps);
		empresa.mostrarReparto();
		
	}

	private static void cargarDatos(Empresa empresa) {
		// TODO Auto-generated method stub
		
		for(int i=1;i<=NUM_PAQUETES;i++) {
			int posCp=i%cps.length;
			empresa.añadirPaquete(new Paquete("paq"+i, "dir"+i,cps[posCp]));
		}
		
		empresa.añadirVehiculo(new Vehiculo("0001-AAA", "1111", "pepe1"));
		empresa.añadirVehiculo(new Vehiculo("0002-BBB", "2222", "pepe2"));
		empresa.añadirVehiculo(new Vehiculo("0003-CCC", "3333", "pepe3"));
		empresa.añadirVehiculo(new Vehiculo("0004-DDD", "4444", "pepe4"));
		empresa.añadirVehiculo(new Vehiculo("0005-EEE", "5555", "pepe5"));
		empresa.añadirVehiculo(new Vehiculo("0006-FFF", "6666", "pepe6"));
		empresa.añadirVehiculo(new Vehiculo("0007-GGG", "7777", "pepe7"));
		
		
		
	}

}
