package controller;

import java.util.Scanner;

public class MenuController {
    PasajeroController pasajeroController = new PasajeroController();
    CocheController cocheController = new CocheController();
    private static final Scanner scanner = new Scanner(System.in);
    private static boolean salir;

    public void ejecutarMenu() {
        gestionMenuCoches();
    }
    private void menuPrincipal() {
        System.out.println("""
                \s
                 *******************************
                 *       CONCESIONARIO         *\s
                 *******************************
                 * 1. Agregar nuevo coche      *
                 * 2. Borrar coche por ID      *
                 * 3. Consultar por ID         *
                 * 4. Modificar coche          *
                 * 5. Listado de coches        *
                 * 6. Gestion de pasajeros     *
                 * 7. Salir                    *
                 *******************************
                 *     ESCRIBE UNA OPCION:     *
                 *******************************\s
                \s""");
    }
    private void subMenu() {
        salir = false;
        System.out.println("""
                \s
                 ************************************
                 *           CONCESIONARIO          *\s
                 ************************************
                 * 1. Agregar nuevo pasajero        *
                 * 2. Borrar pasajero por ID        *
                 * 3. Consultar por ID              *
                 * 4. Listado de pasajeros          *
                 * 5. Añadir pasajero a coche       *
                 * 6. Eliminar pasajero de coche    *
                 * 7. Listar pasajeros de un coche  *
                 * 8. Volver a menu principal       *
                 ************************************
                 *       ESCRIBE UNA OPCION:        *
                 ************************************\s
                \s""");
    }
    private void gestionMenuCoches() {
        do {
            menuPrincipal();
            salir = false;
            try {
                int opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1 -> {
                        System.out.println("Insertar coche");
                        cocheController.insertarCoche();
                    }
                    case 2 -> {
                        System.out.println("Borrar coche");
                        cocheController.borrarCoche();
                    }
                    case 3 -> {
                        System.out.println("Buscar coche");
                        cocheController.buscarCoche();
                    }
                    case 4 -> {
                        System.out.println("Modificar coche");
                        cocheController.actualizarCoche();
                    }
                    case 5 -> {
                        System.out.println("Listar coches");
                        cocheController.listarCoches();
                    }
                    case 6 -> {
                        System.out.println("Gestion pasajeros");
                        gestionMenuPasajeros();
                        salir = false;
                    }
                    case 7 -> {
                        System.out.println("Salir");
                        salir = true;
                    }
                    default -> {
                        System.out.println("Opcion incorrecta");
                    }
                }
            } catch (Exception e) {
                System.out.println("Introduce un número");
            }
        } while (!salir);
    }
    private void gestionMenuPasajeros() {
        do {
            subMenu();
            try {
                int opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1 -> {
                        System.out.println("Insertar pasajero");
                        pasajeroController.insertarPasajero();
                    }
                    case 2 -> {
                        System.out.println("Borrar pasajero");
                        pasajeroController.eliminarPasajero();
                    }
                    case 3 -> {
                        System.out.println("Buscar pasajero");
                        pasajeroController.buscarPasajero();
                    }
                    case 4 -> {
                        System.out.println("Listar todos pasajeros");
                        pasajeroController.listarPasajeros();
                    }
                    case 5 -> {
                        System.out.println("Añadir pasajero a coche");
                    }
                    case 6 -> {
                        System.out.println("Eliminar pasajero de coche");
                    }
                    case 7 -> {
                        System.out.println("Listar pasajeros de un coche");
                    }
                    case 8 -> {
                        System.out.println("Volver menu principal");
                        salir = true;
                    }
                    default -> {
                        System.out.println("Opcion incorrecta");
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (!salir);

    }
}

