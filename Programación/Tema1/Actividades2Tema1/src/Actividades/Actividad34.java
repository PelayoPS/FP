package Actividades;

public class Actividad34 {
    public static void main(String[] args) {
        /*
         * Genera combinaciones al azar para una quiniela de futbol.
         * Recordemos que son 15 resultados : 1, X y 2
         */
        String[] results = { "1", "X", "2" };
        //genera 15 resultados
        for (int i = 0; i < 15; i++) {
            //genera un resultado
            int result = (int) (Math.random() * 3);
            System.out.println("Partido " + (i + 1) + ": " + results[result]);
        }
    }

}
