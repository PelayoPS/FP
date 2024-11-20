package Modelo;

import Excepciones.PersistenciaExcepcion;

public interface Serialize {

	// Se crea para obligar a todas las clases del modelo a tener el método

	public abstract String serialize(Object obj);
	public abstract Object serialize(String linea) throws PersistenciaExcepcion;
}
