package logic.exceptions;

public class ExceptionJugadorNoEncontrado extends Exception {
    public ExceptionJugadorNoEncontrado() {
        super("El jugador no se encuentra en el equipo");
    }

}
