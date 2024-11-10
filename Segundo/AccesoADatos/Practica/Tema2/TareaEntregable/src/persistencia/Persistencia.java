package persistencia;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Persistencia {

    public <T> void guardarObjeto(T objeto, String filePath) {
        List<T> objetos = listarObjetos(filePath);
        objetos.add(objeto);
        guardarObjetosEnArchivo(objetos, filePath);
    }

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

    public <T> void guardarObjetosEnArchivo(List<T> objetos, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(objetos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}