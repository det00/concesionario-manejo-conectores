package controller;

import dao.CocheDao;
import models.Coche;

import java.sql.SQLException;
import java.util.Scanner;

public class CocheController {
    CocheDao cocheDao = new CocheDao();
    Scanner scanner = new Scanner(System.in);

    public void insertarCoche() {
        Coche c = new Coche();
        System.out.print("Matricula: ");
        c.setMatricula(scanner.nextLine());
        System.out.print("Marca: ");
        c.setMarca(scanner.nextLine());
        System.out.print("Modelo: ");
        c.setModelo(scanner.nextLine());
        System.out.print("Color: ");
        c.setColor(scanner.nextLine());
        try {
            cocheDao.insertarCoche(c);
            System.out.println("Se ha insertado el coche: " + c);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void buscarCoche() throws SQLException {
        System.out.println("ID del coche: ");
        long id = Long.parseLong(scanner.nextLine());
        if (cocheDao.buscarCoche(id) != null) {
            System.out.println(cocheDao.buscarCoche(id));
        } else {
            System.out.println("No se ha encontrado coche con ID " + id);
        }
    }

    public void listarCoches() {
        try {
            for (Coche c : cocheDao.listarCoches()) {
                System.out.println(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void borrarCoche() throws SQLException {
        System.out.print("ID del coche a borrar: ");
        long id = Long.parseLong(scanner.nextLine());
        if (cocheDao.buscarCoche(id) != null) {
            cocheDao.borrarCoche(id);
            System.out.println("Coche borrado correctamente");
        } else {
            System.out.println("No hay coche con id " + id);
        }
    }

    public void actualizarCoche() throws SQLException {
        System.out.print("ID del coche a actualizar: ");
        long id = Long.parseLong(scanner.nextLine());
        Coche c = new Coche();
        System.out.print("Matricula: ");
        c.setMatricula(scanner.nextLine());
        System.out.print("Marca: ");
        c.setMarca(scanner.nextLine());
        System.out.print("Modelo: ");
        c.setModelo(scanner.nextLine());
        System.out.print("Color: ");
        c.setColor(scanner.nextLine());
        if (cocheDao.buscarCoche(id) != null) {
            cocheDao.modificarCoche(id, c);
            c.setId(id);
            System.out.println("Coche modificado: " + c);
        } else {
            System.out.println("No hay coche con id " + id);
        }
    }
}
