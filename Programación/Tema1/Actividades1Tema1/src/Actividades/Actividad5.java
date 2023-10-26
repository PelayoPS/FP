package Actividades;

public class Actividad5 {

	public static void main(String[] args) {
		//intercambiar las variable a y b sean cuales sean
		Object a = "a";
		Object b = "b";
		Object temp = null;
		System.out.printf("Valores antes: a = %s, b = %s \n",a,b);
		temp = a;
		a = b;
		b = temp;
		System.out.printf("Valores después: a = %s, b = %s",a,b);
		
	}
}
