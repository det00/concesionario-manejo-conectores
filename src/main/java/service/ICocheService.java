package service;

import models.Coche;

import java.sql.SQLException;
import java.util.List;
//INTERFACE CON LOS MEDOTOS DE COCHE
public interface ICocheService {
    boolean insertarCoche(Coche coche) throws SQLException;
    Boolean borrarCoche(long id) throws SQLException;
    Coche buscarCoche(long id) throws SQLException;
    boolean modificarCoche(long id, Coche coche) throws SQLException;
    List<Coche> listarCoches() throws SQLException;
    List<Coche> cochesDisponibles() throws SQLException;
}
