package src;

public class Funciones {

    /**
     * Devuelve true si el número es primo
     * @param numero
     * @return
     */
    public static boolean esPrimo(int numero) {
        boolean primo = true;
        int contador = 2;
        while ((primo) && (contador != numero)) {
            if (numero % contador == 0) {
                primo = false;
            }
            contador++;
        }
        return primo;
    }

    /**
     * Devuelve el siguiente número primo
     * @param numero
     * @return
     */
    public static int siguientePrimo(int numero) {
        int contador = numero + 1;
        while (!esPrimo(contador)) {
            contador++;
        }
        return contador;
    }

    /**
     * Devuelve el número de dígitos que tiene un número
     * @param numero
     * @return
     */
    public static int digitos(int numero) {
        int contador = 0;
        while (numero != 0) {
            numero = numero / 10;
            contador++;
        }
        return contador;
    }

    /**
     * Devuelve el número volteado
     * @param numero
     * @return
     */
    public static int voltea(int numero) {
        int volteado = 0;
        while (numero != 0) {
            volteado = (volteado * 10) + (numero % 10);
            numero = numero / 10;
        }
        return volteado;
    }

    /**
     * Devuelve el dígito que está en la posición n de un número entero. Se
     * empieza contando por el 0 y de izquierda a derecha
     * devuelve -1 si la posición no es válida
     * @param numero
     * @param posicion
     * @return
     */
    public static int digitoN(int numero, int posicion) {
        if (posicion >= digitos(numero)) {
            return -1;
        }
        int digito = 0;
        int contador = 0;
        while (contador <= posicion) {
            digito = numero % 10;
            numero = numero / 10;
            contador++;
        }
        return digito;
    }

    /**
     * Devuelve el dígito que está en la posición n de un número entero. Se
     * empieza contando por el 0 y de derecha a izquierda. Si la posición
     * no es válida, devuelve -1
     * @param numero
     * @param digito
     * @return
     */
    public static int posicionDeDigito(int numero, int digito) {
        int posicion = 0;
        int digitoActual = 0;
        while (numero != 0) {
            digitoActual = numero % 10;
            if (digitoActual == digito) {
                return posicion;
            }
            numero = numero / 10;
            posicion++;
        }
        return -1;
    }

    /**
     * Le quita a un número 1 dígito por detrás (por la derecha)
     * @param numero
     * @param digitos
     * @return
     */
    public static int quitaPorDetras(int numero) {
        return numero / 10;
    }

    /**
     * Le quita a un número 1 dígito por delante (por la izquierda)
     * @param numero
     * @param digitos
     * @return
     */
    public static int quitaPorDelante(int numero) {
        return voltea(quitaPorDetras(voltea(numero)));
    }

    /**
     * Añade un dígito a un número por detrás
     * @param numero
     * @param digito
     * @return
     */
    public static int pegaPorDetras(int numero, int digito) {
        return (numero * 10) + digito;
    }

    /**
     * Añade un dígito a un número por delante
     * @param numero
     * @param digito
     * @return
     */
    public static int pegaPorDelante(int numero, int digito) {
        return voltea(pegaPorDetras(voltea(numero), digito));
    }


}
