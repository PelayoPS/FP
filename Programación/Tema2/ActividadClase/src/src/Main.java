package src;

public class Main {

	/**
	 * Función principal del programa que según la opción seleccionada del menú
	 * llama a la función correspondiente
	 * @param args
	 */
	public static void main(String[] args) {
		int option = Library.menu();
		switch (option) {
			case 1:
				Library.triangleArea();
				break;
			case 2:
				Library.trapezoidArea();
				break;
			case 3:
				Library.rectangleArea();
				break;
			case 4:
				Library.squareArea();
				break;
			default:
				System.out.println("Opción no válida");
			}

	}

}
