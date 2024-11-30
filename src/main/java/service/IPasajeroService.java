package service;

import models.Coche;
import models.Pasajero;

import java.sql.SQLException;
import java.util.List;

public interface IPasajeroService {
    boolean insertarPasajero(Pasajero pasajero) throws SQLException;
    boolean borrarPasajero(long id) throws SQLException;
    Pasajero buscarPasajero(long id) throws SQLException;
    List<Pasajero> listarPasajeros() throws SQLException;
    boolean insertarPasajeroCoche(Pasajero pasajero, Coche coche) throws SQLException;
    boolean borrarPasajeroCoche(Pasajero pasajero) throws SQLException;
    List<Pasajero> listarPasajerosCoche(Coche coche);
}
