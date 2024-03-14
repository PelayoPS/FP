package src;

public class Tienda {
    private Producto[] productos;
    private Venta[] ventas;
    private int numProductos;
    private int numVentas;

    /**
     * Constructor de la clase Tienda
     * Inicializa los arrays de productos y ventas con un tamaño inicial
     */
    public Tienda() {
        this.productos = new Producto[10]; // Tamaño inicial del array
        this.ventas = new Venta[10]; // Tamaño inicial del array
        this.numProductos = 0;
        this.numVentas = 0;
    }

    /**
     * Añade un producto al array de productos
     * Si el array está lleno, se duplica su tamaño
     * 
     * @param Producto : producto a añadir
     */
    public void añadirProducto(Producto producto) {
        if (numProductos < productos.length) {
            this.productos[numProductos] = producto;
            numProductos++;
        } else {
            Producto[] temp = new Producto[productos.length * 2];
            for (int i = 0; i < productos.length; i++) {
                temp[i] = productos[i];
            }
            this.productos = temp;
        }
    }

    /**
     * Añade una venta al array de ventas
     * @param Venta : venta a añadir
     */
    public void añadirVenta(Venta venta) {
        if (numVentas < ventas.length) {
            this.ventas[numVentas] = venta;
            numVentas++;
        } else {
            Venta[] temp = new Venta[ventas.length * 2];
            for (int i = 0; i < ventas.length; i++) {
                temp[i] = ventas[i];
            }
            this.ventas = temp;
        }
    }

    /**
     * Busca un producto por su código
     * Si no se encuentra el producto, se muestra un mensaje de error
     * y se devuelve null
     * @param int : cod del producto a buscar
     * @return Producto : producto encontrado o null si no se encuentra
     */
    public Producto buscarProducto(int cod) {
        for (Producto producto : this.productos) {
            if (producto.getCod() == cod) {
                return producto;
            }
        }
        // Si no se encuentra el producto, se devuelve null y se muestra un mensaje de error
        System.out.println("No se ha encontrado el producto con código " + cod);
        return null;
    }

    public Venta buscarVenta(int idVentas) {
        for (Venta venta : this.ventas) {
            if (venta.getIdVentas() == idVentas) {
                return venta;
            }
        }
        // Si no se encuentra la venta, se devuelve null y se muestra un mensaje de error
        System.out.println("No se ha encontrado la venta con id " + idVentas);
        return null;
    }

    /**
     * Muestra los productos de la tienda
     * @return String : con la información de los productos
     */
    public String mostrarProductos() {
        StringBuilder sb = new StringBuilder();
        for (Producto producto : this.productos) {
            if (producto != null) {
                sb.append(producto.toString()).append("\n");
            }
            
        }
        return sb.toString();
    }

    /**
     * Muestra las ventas realizadas
     * @return String : con la información de las ventas
     */
    public String mostrarVentas() {
        StringBuilder sb = new StringBuilder();
        for (Venta venta : this.ventas) {
            if (venta != null) {
                sb.append(venta.toString()).append("\n");
            }
        }
        return sb.toString();
    }

    // Getters y setters para los atributos

    public Producto[] getProductos() {
        return productos;
    }

    public Venta[] getVentas() {
        return ventas;
    }
}