package main;

import src.Electrodomestico;
import src.Lavavajillas;
import src.Monitor;

/**
 * Clase que prueba la funcionalidad de las clases Electrodomestico,
 * Lavavajillas y Monitor
 * 
 * @version 1.0, 01/03/2024
 * @author Pelayo Palacio Suárez
 */
public class Tienda {

    public static void main(String[] args) {
        Electrodomestico[] electrodomesticos = new Electrodomestico[6];
        // tanto carga como pulgadas no se pueden testear por la definición de las
        // clases

        // Creammos dos lavavajillas
        // expected price
        // 200 + (precio 'F' = 40) + (tamaño[0-19] 11 = 10) = 250
        electrodomesticos[2] = new Lavavajillas(200, 11);
        // expected price
        // 300 + (precio 'F' = 40) + (tamaño[0-19] 11 = 10) = 350
        electrodomesticos[3] = new Lavavajillas(300, 9);

        // Creamos dos monitores
        // expected price
        // 100 + (precio 'F' = 40) + (tamaño[0-19] 11 = 10) = 150
        electrodomesticos[4] = new Monitor(100, 11);
        // expected price
        // 100 + (precio 'F' = 40) + (tamaño > 80 = 100) = 240
        electrodomesticos[5] = new Monitor(100, 81);

        // recorre el array de electrodomesticos
        // y muestra el precio final de cada uno
        for (Electrodomestico electrodomestico : electrodomesticos) {
            if (electrodomestico != null) {
                electrodomestico.precioFinal();
                System.out.println("Precio final: " + electrodomestico.getPrecioBase());
            } else {
                System.out.println("No hay electrodomestico");
            }

        }

    }

}
