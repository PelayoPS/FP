package src;

import Ficheros.Empresa;
import Ficheros.Paquete;
import Ficheros.Vehiculo;

public class PrincipalConErrores {

	public static int cps[]={33203,33204,33025,33207};
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Empresa empresa=new Empresa();
		cargarDatos(empresa);
		empresa.mostrarPaquetes();
		empresa.mostrarVehiculos();

		System.out.println("***************************************");
		empresa.repartirPaquetes(cps);
		empresa.mostrarReparto();
		
	}

	private static void cargarDatos(Empresa empresa) {
		// TODO Auto-generated method stub

		empresa.añadirPaquete(new Paquete("paq1","dir1",cps[0]));
		empresa.añadirPaquete(new Paquete("paq2","dir1",cps[1]));
		empresa.añadirPaquete(new Paquete("paq3","dir1",cps[2]));
		empresa.añadirPaquete(new Paquete("paq4","dir1",cps[3]));
		
		// ERROR no pudee haber dos paquetes con el mismo codigo
		empresa.añadirPaquete(new Paquete("paq1","dir1",cps[1]));
		
		
		empresa.añadirVehiculo(new Vehiculo("0001-AAA", "1111", "pepe1"));
		empresa.añadirVehiculo(new Vehiculo("0002-BBB", "2222", "pepe2"));
		empresa.añadirVehiculo(new Vehiculo("0003-CCC", "3333", "pepe3"));
		
		// ERROR no puede haber dos vehiculos con la misma matricula
		empresa.añadirVehiculo(new Vehiculo("0001-AAA", "4444", "pepe5"));
		
	}	
		
		
}
