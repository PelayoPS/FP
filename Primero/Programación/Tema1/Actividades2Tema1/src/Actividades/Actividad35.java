package Actividades;

public class Actividad35 {
    public static void main(String[] args) {
        /*
         * Reutiliza el ejercicio anterior: Genera una apuesta al azar y unos
         * resultados también al azar; después indica cuantos aciertos se han
         * producido.
         */
        String[] apuesta = generaApuesta();
        String[] resultados = generaResultados();
        int aciertos = 0;
        for (int i = 0; i < apuesta.length; i++) {
            if (apuesta[i].equals(resultados[i])) {
                aciertos++;
            }
        }
        System.out.println("Aciertos: " + aciertos);


    }

    /**
     * Genera unos resultados al azar
     * @return
     */
    private static String[] generaResultados(){
        String[] results = { "1", "X", "2" };
        String[] games = new String[15];
        //genera 15 resultados
        for (int i = 0; i < 15; i++) {
            //genera un resultado
            int result = (int) (Math.random() * 3);
            games[i] = results[result];
        }
        return games;
    }

    /**
     * Genera una apuesta al azar
     * @return
     */
    public static String[] generaApuesta(){
        String[] results = { "1", "X", "2" };
        String[] games = new String[15];
        //genera 15 apuestas
        for (int i = 0; i < 15; i++) {
            //genera una apuesta
            int result = (int) (Math.random() * 3);
            games[i] = results[result];
        }
        return games;
    }
    
}
