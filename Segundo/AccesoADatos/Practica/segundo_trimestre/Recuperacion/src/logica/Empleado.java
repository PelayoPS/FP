package logica;

/**
 * Clase que representa a un empleado.
 */
public class Empleado {
    private int id;
    private String nombre;
    private String apellidos;
    private char genero; // F o M
    private String categoriaProfesional; // PE o CA
    private double salarioAnual;

    /**
     * Constructor de la clase Empleado.
     * 
     * @param id                   Identificador del empleado.
     * @param nombre               Nombre del empleado.
     * @param apellidos            Apellidos del empleado.
     * @param genero               Género del empleado (F o M).
     * @param categoriaProfesional Categoría profesional del empleado (PE o CA).
     * @param salarioAnual         Salario anual del empleado.
     */
    public Empleado(int id, String nombre, String apellidos, char genero, String categoriaProfesional,
            double salarioAnual) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.genero = genero;
        this.categoriaProfesional = categoriaProfesional;
        this.salarioAnual = salarioAnual;
    }

    /**
     * Obtiene el identificador del empleado.
     * 
     * @return Identificador del empleado.
     */
    public int getId() {
        return id;
    }

    /**
     * Obtiene el nombre del empleado.
     * 
     * @return Nombre del empleado.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene los apellidos del empleado.
     * 
     * @return Apellidos del empleado.
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Obtiene el género del empleado.
     * 
     * @return Género del empleado.
     */
    public char getGenero() {
        return genero;
    }

    /**
     * Obtiene la categoría profesional del empleado.
     * 
     * @return Categoría profesional del empleado.
     */
    public String getCategoriaProfesional() {
        return categoriaProfesional;
    }

    /**
     * Obtiene el salario anual del empleado.
     * 
     * @return Salario anual del empleado.
     */
    public double getSalarioAnual() {
        return salarioAnual;
    }

    /**
     * Serializa el objeto Empleado a un string.
     * 
     * @return String que representa al objeto Empleado.
     */
    public String serializar() {
        return id + "," + nombre + "," + apellidos + "," + genero + "," + categoriaProfesional + "," + salarioAnual;
    }

    /**
     * Deserializa un string a un objeto Empleado.
     * 
     * @param datos String que representa al objeto Empleado.
     * @return Objeto Empleado.
     */
    public static Empleado deserializar(String datos) {
        String[] partes = datos.split(",");
        int id = Integer.parseInt(partes[0]);
        String nombre = partes[1];
        String apellidos = partes[2];
        char genero = partes[3].charAt(0);
        String categoriaProfesional = partes[4];
        double salarioAnual = Double.parseDouble(partes[5]);
        return new Empleado(id, nombre, apellidos, genero, categoriaProfesional, salarioAnual);
    }

    /**
     * Sobreescribe el método toString.
     * 
     * @return String que representa al objeto Empleado.
     */
    @Override
    public String toString() {
        return "Empleado{" + "id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", genero=" + genero
                + ", categoriaProfesional=" + categoriaProfesional + ", salarioAnual=" + salarioAnual + '}';
    }
}
