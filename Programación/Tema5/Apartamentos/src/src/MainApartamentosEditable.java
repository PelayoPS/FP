package src;

public class MainApartamentosEditable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GestorApartamentos gestor=new GestorApartamentos();
		
		gestor.añadirApartamento(1,'A',6,1500.0);
		gestor.añadirApartamento(1,'B',2,50.0);
		gestor.añadirApartamento(1,'C',4,100.0);
		gestor.añadirApartamento(1,'D',8,200.0);
		
		gestor.añadirApartamento(2,'A',10,500.0);
		gestor.añadirApartamento(2,'D',12,600.0);
		
		gestor.añadirApartamento(3,'A',2,500.0);
		gestor.añadirApartamento(3,'B',2,50.0);
		gestor.añadirApartamento(3,'C',2,50.0);
		gestor.añadirApartamento(3,'D',2,50.0);
		gestor.añadirApartamento(3,'E',2,50.0);
		
		System.out.println("********************************************");
		System.out.println("Listado de apartamentos");
		gestor.mostrarApartamentos();
		System.out.println("Control de errores");
		
		//control de errores
		//clave duplicada
		gestor.añadirApartamento(1,'A',6,1500.0);
		//clave incorrecta en el piso
		gestor.añadirApartamento(10,'A',6,1500.0);
		//clave incorrecta en la letra
		gestor.añadirApartamento(1,'X',6,1500.0);
		
		System.out.println("********************************************");
		System.out.println("Listado de apartamentos con capacidad mayor 7");
		gestor.buscarApartamentosPorCapacidad(7);
		
		System.out.println("********************************************");
		System.out.println("Despues de eliminar Piso1-LetraC");
		gestor.eliminarApartamento("Piso1-LetraC");
		gestor.mostrarApartamentos();
		
		gestor.hacerReserva("Piso3-LetraC");
		gestor.hacerReserva("Piso3-LetraC");
		gestor.hacerReserva("Piso3-LetraC");
		
		gestor.hacerReserva("Piso1-LetraA");
		gestor.hacerReserva("Piso1-LetraA");
		
		gestor.hacerReserva("Piso2-LetraA");
		gestor.hacerReserva("Piso2-LetraA");
		gestor.hacerReserva("Piso2-LetraA");
		
		System.out.println("********************************************");
		System.out.println("Despues de hacer reservas");
		gestor.mostrarApartamentos();
		
		//control de errores
		//Error en el piso
		gestor.hacerReserva("Piso8-LetraA");
		//Error en la letra
		gestor.hacerReserva("Piso1-LetraX");
		//Error apartamento no encontrado
		gestor.hacerReserva("Piso2-LetraF");
		
		
		System.out.println("********************************************");
		System.out.println("Mostrar apartamentos de la tercera planta");
		gestor.mostrarApartamentosPorPiso(3);
		
		System.out.println("********************************************");
		System.out.println("Apartamento con mayor capacidad");
		gestor.apartamentoConMayorCapacidad();
	}

}
