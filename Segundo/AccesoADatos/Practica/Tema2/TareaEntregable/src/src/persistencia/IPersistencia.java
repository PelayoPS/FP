
package src.persistencia;

import src.excepciones.PersistenciaException;

import java.util.List;

public interface IPersistencia<T> {
    void guardar(T objeto) throws PersistenciaException;
    List<T> listar() throws PersistenciaException;
    T obtenerPorId(int id) throws PersistenciaException;
    boolean actualizar(T objeto) throws PersistenciaException;
    boolean eliminar(int id) throws PersistenciaException;
}