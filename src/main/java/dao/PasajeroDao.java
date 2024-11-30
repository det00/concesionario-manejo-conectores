package dao;

import database.DBConnection;
import database.SchemaDB;
import models.Coche;
import models.Pasajero;
import service.IPasajeroService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PasajeroDao implements IPasajeroService {

    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Connection connection;

    public PasajeroDao() {
        connection = new DBConnection().getConnection();
    }

    @Override
    public boolean insertarPasajero(Pasajero pasajero) throws SQLException {
        String query = String.format("INSERT INTO %s (%s, %s, %s, %s) VALUES (?, ?, ?, ?)",
                SchemaDB.TAB_PAS,
                SchemaDB.COL_PAS_NAME, SchemaDB.COL_PAS_AGE, SchemaDB.COL_PAS_WEIGHT, SchemaDB.COL_PAS_CAR);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, pasajero.getNombre());
        preparedStatement.setInt(2, pasajero.getEdad());
        preparedStatement.setDouble(3, pasajero.getPeso());
        preparedStatement.setObject(4, pasajero.getCoche());
        return preparedStatement.executeUpdate() > 0;
    }

    @Override
    public boolean borrarPasajero(long id) throws SQLException {
        String query = String.format("DELETE FROM %s WHERE %s = ?",
                SchemaDB.TAB_PAS, SchemaDB.COL_ID);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setLong(1, id);
        return preparedStatement.executeUpdate() > 0;
    }

    @Override
    public Pasajero buscarPasajero(long id) throws SQLException {
        String query = String.format("SELECT * FROM %s WHERE %s = ?",
                SchemaDB.TAB_PAS, SchemaDB.COL_ID);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setLong(1, id);
        resultSet = preparedStatement.executeQuery();
        Pasajero p = null;
        if (resultSet.next()) {
            p = new Pasajero(
                    resultSet.getLong(SchemaDB.COL_ID),
                    resultSet.getString(SchemaDB.COL_PAS_NAME),
                    resultSet.getInt(SchemaDB.COL_PAS_AGE),
                    resultSet.getDouble(SchemaDB.COL_PAS_WEIGHT),
                    resultSet.getInt(SchemaDB.COL_PAS_CAR)
            );
        }
        return p;
    }

    @Override
    public List<Pasajero> listarPasajeros() throws SQLException {
        List<Pasajero> listaPasajeros = new ArrayList<>();
        String query = String.format("SELECT * FROM %s", SchemaDB.TAB_PAS);

        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Pasajero p = new Pasajero(
                    resultSet.getLong(SchemaDB.COL_ID),
                    resultSet.getString(SchemaDB.COL_PAS_NAME),
                    resultSet.getInt(SchemaDB.COL_PAS_AGE),
                    resultSet.getDouble(SchemaDB.COL_PAS_WEIGHT),
                    resultSet.getInt(SchemaDB.COL_PAS_CAR)
            );
            listaPasajeros.add(p);
        }
        return listaPasajeros;
    }

    @Override
    public boolean insertarPasajeroCoche(Pasajero pasajero, Coche coche) throws SQLException {
        String query = String.format("UPDATE %s SET %s = ? WHERE %s = ?",
                SchemaDB.TAB_PAS,
                SchemaDB.COL_PAS_CAR,
                SchemaDB.COL_ID);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setLong(1, coche.getId());
        preparedStatement.setLong(2, pasajero.getId());
        return preparedStatement.executeUpdate()>0;
    }

    @Override
    public boolean borrarPasajeroCoche(Pasajero pasajero) throws SQLException {
        String query = String.format("UPDATE %s SET %s = ? WHERE %s = ?",
        SchemaDB.TAB_PAS,
                SchemaDB.COL_PAS_CAR,
                SchemaDB.COL_ID);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setObject(1,null);
        preparedStatement.setLong(2, pasajero.getId());
        return preparedStatement.executeUpdate()>0;
    }

    @Override
    public List<Pasajero> listarPasajerosCoche(Coche coche) {
        List<Pasajero> listaPasajeros = new ArrayList<>();
        String query = String.format("SELECT * FROM %s WHERE %s = ?",
                SchemaDB.TAB_PAS,
                SchemaDB.COL_PAS_CAR);
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, coche.getId());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Pasajero p = new Pasajero(
                        resultSet.getLong(SchemaDB.COL_ID),
                        resultSet.getString(SchemaDB.COL_PAS_NAME),
                        resultSet.getInt(SchemaDB.COL_PAS_AGE),
                        resultSet.getDouble(SchemaDB.COL_PAS_WEIGHT),
                        resultSet.getInt(SchemaDB.COL_PAS_CAR)
                );
                listaPasajeros.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaPasajeros;
    }

}
