package controller;

import Utils.Utils;
import dao.CocheDao;
import dao.PasajeroDao;
import models.Coche;
import models.Pasajero;

import java.sql.SQLException;
import java.util.Scanner;

public class PasajeroController {
    Utils utils = new Utils();
    PasajeroDao pasajeroDao;
    CocheDao cocheDao = new CocheDao();
    Scanner scanner = new Scanner(System.in);

    public PasajeroController() {
        pasajeroDao = new PasajeroDao();
    }

    public void insertarPasajero() {
        Pasajero p = new Pasajero();
        System.out.print("Nombre: ");
        p.setNombre(scanner.nextLine());
        System.out.print("Edad: ");
        p.setEdad((int) utils.validarNumero());
        System.out.print("Peso: ");
        p.setPeso(utils.validarNumero());
        System.out.print("Coche: ");
        String coche = scanner.nextLine();
        if (coche.isEmpty()) {
            p.setCoche(null);
        } else {
            p.setCoche(Integer.valueOf(coche));
        }
        try {
            if (pasajeroDao.insertarPasajero(p)) {
                System.out.println("Insertado pasajero " + p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void listarPasajeros() {
        try {
            if (!pasajeroDao.listarPasajeros().isEmpty()) {
                for (Pasajero p : pasajeroDao.listarPasajeros()) {
                    System.out.println(p);
                }
            } else {
                System.out.println("Lista de pasajeros vacia");
            }
        } catch (SQLException e) {
            System.out.println("Error en la conexion");
        }
    }

    public void borrarPasajeroCoche() throws SQLException {
        System.out.print("ID del pasajero a borrar: ");
        long id = Long.parseLong(scanner.nextLine());
        if (pasajeroDao.buscarPasajero(id) != null) {

        } else {
            System.out.println("No hay pasajero con id " + id);
        }
    }

    public void buscarPasajero() {
        System.out.println("ID del pasajero: ");
        long id = Long.parseLong(scanner.nextLine());
        try {
            if (pasajeroDao.buscarPasajero(id) != null) {
                System.out.println(pasajeroDao.buscarPasajero(id));
            } else {
                System.out.println("No se ha encontrado pasajero con ID " + id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void eliminarPasajero() {
        System.out.print("ID del pasajero a borrar: ");
        long id = Long.parseLong(scanner.nextLine());
        try {
            if (pasajeroDao.buscarPasajero(id) != null) {
                pasajeroDao.borrarPasajero(id);
                System.out.println("Pasajero borrado correctamente");
            } else {
                System.out.println("No hay coche con id " + id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void modificarPasajeroCoche() {

        System.out.print("ID del pasajero a asignar: ");
        long id = Long.parseLong(scanner.nextLine());
        try {
            Pasajero p = pasajeroDao.buscarPasajero(id);
            if (p != null) {
                System.out.println("COCHES DISPONIBLES");
                for (Coche c: cocheDao.cochesDisponibles()){
                    if (c.ge)
                }
                System.out.println("¿En que coche lo quieres asignar?");

            } else {
                System.out.println("No hay pasajero con id " + id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
