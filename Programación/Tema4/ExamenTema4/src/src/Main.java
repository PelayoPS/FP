package src;

public class Main {
	private static Tienda tienda = new Tienda();

	public static void main(String[] args) {
		// Calcula mal el precio con descuento no me dio tiempo a arreglarlo el resto funciona creo
		cargarProductos();
		nuevaVenta();
		System.out.println("Productos al inicio");
		System.out.println("******************");
		listarProductosConExistencias();
		System.out.println("******************");
		System.out.println("Ventas al inicio");
		System.out.println("******************");
		resumenDeVentas();
		System.out.println("******************");
		System.out.println("Ventas al abonar");
		System.out.println("******************");
		Venta v1 = tienda.buscarVenta(1);
		v1.abonar(8000);
		resumenDeVentas();
		System.out.println("******************");
		System.out.println("Productos al finalizar la venta");
		listarProductosConExistencias();
		
	}

	/**
	 * Carga productos en la tienda
	 * Diferenciando entre productos normales y productos en oferta
	 */
	public static void cargarProductos() {
		Producto p1 = new Producto(1, "prod1", 100, 100.0);
		Producto p2 = new Producto(2, "prod2", 200, 200.0);
		ProductoEnOferta p3 = new ProductoEnOferta(3, "prod3", 300, 300.0, 2, 10);
		ProductoEnOferta p4 = new ProductoEnOferta(4, "prod4", 400, 400.0, 4, 5);
		tienda.añadirProducto(p1);
		tienda.añadirProducto(p2);
		tienda.añadirProducto(p3);
		tienda.añadirProducto(p4);

	}

	/**
	 * Crea una nueva venta y la añade a la tienda
	 * Añade detalles a la venta
	 */
	public static void nuevaVenta() {
		//venta 1
		Producto p2 = tienda.buscarProducto(2);
		Producto p4 = tienda.buscarProducto(4);
		Producto p3 = tienda.buscarProducto(3);
		DetalleVenta dv1 = new DetalleVenta(p2, 10);
		DetalleVenta dv2 = new DetalleVenta(p4, 1);
		DetalleVenta dv3 = new DetalleVenta(p3, 10);
		Venta v1 = new Venta("Marcial");
		v1.añadirDetalle(dv1);
		v1.añadirDetalle(dv2);
		v1.añadirDetalle(dv3);
		tienda.añadirVenta(v1);

	}

	public static void listarProductosConExistencias() {
		Producto[] list = tienda.getProductos();
		for (Producto producto : list) {
			if (producto != null) {
				if (producto.getExistencias() > 0) {
					System.out.println(producto);
				}
			}
			
		}
	}


	public static void resumenDeVentas() {
		Venta[] list = tienda.getVentas();
		for (Venta venta : list) {
			if (venta != null) {
				System.out.println(venta);
			}
		}
	}
}
