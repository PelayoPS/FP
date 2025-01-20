package logica;

import java.util.List;

import modelo.Empleado;

/**
 * Clase que gestiona las nóminas de los empleados.
 */
public class Nominas {

    private List<Empleado> empleados;

    /**
     * Constructor de la clase Nominas.
     * 
     * @param empleados Lista de empleados.
     */
    public Nominas(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    /**
     * Da de alta a un empleado.
     * 
     * @param empleado Empleado a dar de alta.
     */
    public void altaEmpleado(Empleado empleado) {
        if (empleado != null && !empleados.contains(empleado)) {
            empleados.add(empleado);
        }
    }

    /**
     * Busca un empleado por su identificador.
     * 
     * @param id Identificador del empleado.
     * @return Empleado encontrado o null si no se encuentra.
     */
    public Empleado buscarEmpleado(int id) {
        return empleados.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }

    /**
     * Borra un empleado por su identificador.
     * 
     * @param id Identificador del empleado.
     */
    public void borrarEmpleado(int id) {
        empleados.removeIf(e -> e.getId() == id);
    }

    /**
     * Lista todos los empleados.
     * 
     * @return String con la lista de empleados.
     */
    public String listarEmpleados() {
        StringBuilder sb = new StringBuilder();
        for (Empleado empleado : empleados) {
            sb.append(empleado.toString()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Calcula el salario mensual de un empleado.
     * 
     * @param id Identificador del empleado.
     * @return Salario mensual del empleado o -1 si no se encuentra.
     */
    public double calcularSalarioMensual(int id) {
        Empleado empleado = buscarEmpleado(id);
        if (empleado == null) {
            return -1;
        }
        return empleado.getCategoriaProfesional().equals("PE")
                ? empleado.getSalarioAnual() / 12
                : empleado.getSalarioAnual() / 14;
    }

}
