package logic.exceptions;

public class ExcepcionMaxJugadores extends Exception {
    public ExcepcionMaxJugadores() {
        super("No se pueden añadir más jugadores al equipo.");
    }
}
