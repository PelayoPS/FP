package src.persistencia;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que maneja la persistencia de objetos en archivos.
 */
public class Persistencia {

    /**
     * Guarda un objeto en el archivo especificado.
     *
     * @param objeto el objeto a guardar
     * @param filePath la ruta del archivo donde se guardará el objeto
     * @param <T> el tipo del objeto
     */
    public <T> void guardarObjeto(T objeto, String filePath) {
        List<T> objetos = listarObjetos(filePath);
        objetos.add(objeto);
        guardarObjetosEnArchivo(objetos, filePath);
    }

    /**
     * Lista todos los objetos almacenados en el archivo especificado.
     *
     * @param filePath la ruta del archivo de donde se leerán los objetos
     * @param <T> el tipo de los objetos
     * @return una lista de objetos leídos del archivo
     */
    public <T> List<T> listarObjetos(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (List<T>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Guarda una lista de objetos en el archivo especificado.
     *
     * @param objetos la lista de objetos a guardar
     * @param filePath la ruta del archivo donde se guardarán los objetos
     * @param <T> el tipo de los objetos
     */
    public <T> void guardarObjetosEnArchivo(List<T> objetos, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(objetos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}