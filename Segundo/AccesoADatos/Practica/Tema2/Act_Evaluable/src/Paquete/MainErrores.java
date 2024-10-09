package Paquete;

public class MainErrores {

	public static void main(String[] args) {
		GestorFicheros gestor=new GestorFicheros();
		gestor.crearEstructura();
		// error el fichero ya existe
		gestor.crearFichero("Carp2","Fich1");
		//error la carpeta no existe
		gestor.crearFichero("Carp7","Fich7");
		//Error el fichero no existe
		gestor.borrarFichero("Fich9");
		//Error el fichero no es fichero e suna carpeta
		gestor.borrarFichero("Carp1");
		//Error la carptea origen no existe
		gestor.copiar("Carp8","Carp6");
		//Error la carpeta destino existe
		gestor.copiar("Carp3","Carp4");

	}

}
