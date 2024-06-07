package src;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        // Crear un nuevo almacén
        Almacen almacen = new Almacen();

        // Crear nuevas direcciones
        Direccion direccion1 = new Direccion("Calle 1", "1", "Ciudad 1", "País 1");
        Direccion direccion2 = new Direccion("Calle 2", "2", "Ciudad 2", "País 2");
        Direccion direccion3 = new Direccion("Calle 3", "3", "Ciudad 3", "País 3");
        Direccion direccion4 = new Direccion("Calle 4", "4", "Ciudad 4", "País 4");

        // Crear nuevos paquetes
        LinkedList<Direccion> ruta1 = new LinkedList<>();
        ruta1.add(direccion1);
        ruta1.add(direccion2);
        Paquete paquete1 = new Paquete(1, ruta1);

        LinkedList<Direccion> ruta2 = new LinkedList<>();
        ruta2.add(direccion2);
        ruta2.add(direccion3);
        Paquete paquete2 = new Paquete(2, ruta2);

        LinkedList<Direccion> ruta3 = new LinkedList<>();
        ruta3.add(direccion3);
        ruta3.add(direccion4);
        Paquete paquete3 = new Paquete(3, ruta3);

        // Agregar paquetes al almacén
        almacen.addPaquete(paquete1);
        almacen.addPaquete(paquete2);
        almacen.addPaquete(paquete3);

        // Crear nuevos conductores
        Conductor conductor1 = new Conductor(1, "Conductor 1");
        Conductor conductor2 = new Conductor(2, "Conductor 2");
        Conductor conductor3 = new Conductor(3, "Conductor 3");

        // Asignar paquetes a conductores
        conductor1.addPaquete(paquete1);
        conductor2.addPaquete(paquete2);
        conductor3.addPaquete(paquete3);
        conductor3.addPaquete(paquete2);

        // Agregar conductores al almacén
        almacen.addConductor(conductor1);
        almacen.addConductor(conductor2);
        almacen.addConductor(conductor3);
        

        // Imprimir detalles del almacén
        System.out.println("Detalles del almacén:");
        System.out.println("Paquetes:");
        for (Paquete paquete : almacen.getPaquetes().values()) {
            System.out.println(paquete);
        }
        System.out.println("Conductores:");
        for (Conductor conductor : almacen.getConductores()) {
            System.out.println(conductor);
        }

        // Obtener un conductor por prioridad
        System.out.println("\nConductor con prioridad más alta:");
        Conductor conductorConMasAltaPrioridad = almacen.getConductor(3);
        System.out.println(conductorConMasAltaPrioridad);

        // Obtener un paquete por ID
        System.out.println("\nPaquete con ID 2:");
        Paquete paqueteConId2 = almacen.getPaquete(2);
        System.out.println(paqueteConId2);

        // Eliminar un paquete del almacén almacen.removePaquete(2);

        // Obtener la ruta de un paquete
        System.out.println("\nRuta del paquete con ID 1:");
        LinkedList<Direccion> rutaDelPaquete1 = almacen.getPaquete(1).getRuta();
        for (Direccion direccion : rutaDelPaquete1) {
            System.out.println(direccion);
        }

        // Obtener el conductor con la menor prioridad
        System.out.println("\nConductor con menor prioridad:");
        Conductor conductorConMenorPrioridad = almacen.getConductor(1);
        System.out.println(conductorConMenorPrioridad);

        // Obtener el número de paquetes asignados a un conductor
        System.out.println("\nNúmero de paquetes asignados al conductor con ID 1:");
        int numPaquetesConductor1 = conductorConMenorPrioridad.getPaquetesAsignados().size();
        System.out.println(numPaquetesConductor1);

        // Obtener el paquete asignado a un conductor en particular
        System.out.println("\nPaquete asignado al conductor con ID 1:");
        Paquete paqueteAsignadoConductor1 = conductorConMenorPrioridad.getPaquetesAsignados().get(0);
        System.out.println(paqueteAsignadoConductor1);

        // Eliminar un conductor del almacén
        almacen.removeConductor(conductorConMenorPrioridad);

        // Imprimir detalles del almacén después de eliminar el conductor
        System.out.println("\nDetalles del almacén después de eliminar el conductor con ID 1:");
        System.out.println("Paquetes:");
        for (Paquete paquete : almacen.getPaquetes().values()) {
            System.out.println(paquete);
        }
        System.out.println("Conductores:");
        for (Conductor conductor : almacen.getConductores()) {
            System.out.println(conductor);
        }
    }
}