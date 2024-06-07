package src;

public class Actividad10 {
    public static void main(String[] args) {
        /*
         * Como podemos ver, en este caso se trata de mostrar las letras del
         * abecedario (sin la Ñ) en mayúsculas y en orden inverso. A
         * continuación en cada fila mostrar una letra menos hasta llegar a
         * mostrar solamente la A.
         */
        char letter = 'Z';
        // bucle para conseguir
        /*
         * ZYXWVUTSRQPONMLKJIHGFEDCBA
         * YXWVUTSRQPONMLKJIHGFEDCBA
         * XWVUTSRQPONMLKJIHGFEDCBA
         * WVUTSRQPONMLKJIHGFEDCBA
         * ...
         * CBA
         * BA
         * A
         */
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26 - i; j++) {
                System.out.print(letter);
                letter--;
            }
            System.out.println();
            letter = 'Z';
            letter -= i + 1;
        }
    }

}
