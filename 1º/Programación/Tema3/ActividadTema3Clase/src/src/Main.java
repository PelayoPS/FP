package src;

public class Main {

	public static void main(String[] args) {
		int[][] notas = {
			{3,2,7,8,6,4,5,9,5,1},
			{4,8,7,6,3,4,8,9,2,4},
			{7,2,6,8,9,10,0,5,4,10},
			{8,6,4,7,1,10,5,3,4,8}
		};

		Library.notaMasAlta(notas);
		Library.calcularMediaCurso('A', notas);
		Library.numeroAprobados('A', notas);
		Library.numeroSuspensos('A', notas);
		Library.mostrarEstadistica(notas);

	}

}
