package Paquete;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GestorFicheros gestor=new GestorFicheros();
		gestor.crearEstructura();
		gestor.crearFichero("Carp2","Fich6");
		gestor.crearFichero("Carp5","Fich7");
		gestor.borrarFichero("Fich1");
		gestor.borrarFichero("Fich4");
		gestor.copiar("Carp3","Carp6");
	}

}
