package Interfaz;

import java.util.Scanner;

import Excepciones.LogicaExcepcion;
import Logica.Biblioteca;

public class Principal {

  Biblioteca biblioteca;
  Loggerfichero log = Loggerfichero.getInstance();

  public static void main(String[] args) {
    new Principal().run();
  }

  public void run() {
    String nombreFichero = pedirString("Introduce el nombre del fichero de datos: ");
    this.biblioteca = new Biblioteca(nombreFichero);

    int opcion;
    do {
      mostrarMenu();
      opcion = pedirOpcion();

      procesarOpcion(opcion);

    } while (opcion != 0);
    System.out.println("Programa finalizado.");

  }

  private void mostrarMenu() {
    String[] menu = {
        "\n-------------- Sistemas de reservas --------------",
        " 1.- Eliminar socios con mas de 3 préstamos",
        " 2.- Listar socios",
        " 3.- Listar libros",
        " 4.- Realizar préstamo",
        " 5.- Eliminar préstamo",
        " 6.- Listar préstamos de un socio",
        "",
        " 0.- Salir",
        "----------------------------------------" };
    for (int i = 0; i < menu.length; i++) {
      System.out.println(menu[i]);
    }
  }

  private void procesarOpcion(int opcion) {
    switch (opcion) {
      case 1:
        eliminarSociosMasPrestamos();
        break;
      case 2:
        listarSocios();
        break;
      case 3:
        listarLibros();
        break;
      case 4:
        realizarPrestamo();
        break;
      case 5:
        eliminarPrestamo();
        break;
      case 6:
        listarPrestamosSocio();
        break;
      default:
        biblioteca.guardarFicheros();
        log.writeSmg("Programa finalizado");
        break;
    }

  }

  private int pedirOpcion() {
    log.writeSmg("Se ha pedido una opción por teclado");
    return pedirEntero("Opcion: ");
  }

  /**
   * pide un entero por teclado.
   * 
   * @param mensaje mensjae que muestra por consola para pedir la entrada
   * @return entero introducido
   */
  private int pedirEntero(String mensaje) {
    System.out.println(mensaje);
    Scanner teclado = new Scanner(System.in);
    int op = teclado.nextInt();
    return op;

  }

  /**
   * pide un string por teclado
   * 
   * @param mensaje mensjae que muestra por consola para pedir la entrada
   * @return string introducida
   */
  private String pedirString(String mensaje) {
    System.out.println(mensaje);
    Scanner teclado = new Scanner(System.in);
    String op = teclado.next();
    return op;
  }

  /**
   * elimna los socios que tengan mas de 3 (>3) prestamos de libros
   */
  private void eliminarSociosMasPrestamos() {
    biblioteca.eliminarSociosConMuchosPrestamos();
    log.writeSmg("Socios eliminados");
  }

  /**
   * lista todos los socios
   * Ordenados por apellidos en orden ascendente
   * Una fila por cada socio en la que aparecer�n todos sus datos excepto los
   * pr�stamos
   *
   */
  private void listarSocios() {
    String out = biblioteca.listarSocios();
    System.out.println(out);
    log.writeSmg("Socios listados");
  }

  /**
   * lista todos los libros
   * Ordenados por T�tulo en orden ascendente
   * Una fila por cada libro en la que aparecer�n todos sus datos
   */
  private void listarLibros() {
    String out = biblioteca.listarLibros();
    System.out.println(out);
    log.writeSmg("Libros listados");
  }

  /**
   * se piden los datos del socio que saca el libro y el libro que se le presta y
   * se a�aden
   */
  private void realizarPrestamo() {
    // pide los datos del socio
    int nSocio = pedirEntero("Introduce el número de socio: ");
    // pide los datos del libro
    int codigo = pedirEntero("Introduce el código del libro: ");

    // añade el prestamo a la biblioteca
    try {
      biblioteca.Prestar(nSocio, codigo);
    } catch (LogicaExcepcion e) {
      log.writeSmg(e.getMessage());
    }
    log.writeSmg("Prestamo realizado");

  }

  /**
   * se piden por teclado los datos del socio y libro y se elimina el prestamo
   */
  private void eliminarPrestamo() {
    // pide los datos del socio
    int nSocio = pedirEntero("Introduce el número de socio: ");
    // pide los datos del libro
    int codigo = pedirEntero("Introduce el código del libro: ");

    // elimina el prestamo de la biblioteca
    try {

      biblioteca.EliminarPrestamo(nSocio, codigo);
    } catch (LogicaExcepcion e) {
      log.writeSmg(e.getMessage());
    }

    log.writeSmg("Prestamo eliminado");

  }

  /**
   * se listan todos los prestamos de un socio cuyo identificador se pide por
   * teclado
   */
  private void listarPrestamosSocio() {
    // pide los datos del socio
    int nSocio = pedirEntero("Introduce el número de socio: ");
    // lista los prestamos del socio
    String out = biblioteca.ListarPrestamos(nSocio);
    System.out.println(out);

    log.writeSmg("Prestamos listados");
  }
}
