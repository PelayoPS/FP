package src.excepciones;

public class PersistenciaException extends Exception {
    public PersistenciaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
    
    public PersistenciaException(String mensaje) {
        super(mensaje);
    }
}
