package dao;

import database.DBConnection;
import database.SchemaDB;
import models.Coche;
import service.ICocheService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CocheDao implements ICocheService {
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Connection connection;

    public CocheDao() {
         connection =  new DBConnection().getConnection();
    }

    @Override
    public boolean insertarCoche(Coche coche) throws SQLException {
        String query = String.format("INSERT INTO %s (%s, %s, %s, %s) VALUES (?, ?, ?, ?)",
                SchemaDB.TAB_CAR,
                SchemaDB.COL_CAR_MAT, SchemaDB.COL_CAR_MARC, SchemaDB.COL_CAR_MOD, SchemaDB.COL_CAR_COLOR);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, coche.getMatricula());
        preparedStatement.setString(2, coche.getMarca());
        preparedStatement.setString(3, coche.getModelo());
        preparedStatement.setString(4, coche.getColor());
        return preparedStatement.execute();
    }

    @Override
    public Boolean borrarCoche(long id) throws SQLException {
        String query = String.format("DELETE FROM %s WHERE %s = ?",
                SchemaDB.TAB_CAR, SchemaDB.COL_ID);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setLong(1, id);
        return preparedStatement.executeUpdate() > 0;
    }

    @Override
    public Coche buscarCoche(long id) throws SQLException {
        String query = String.format("SELECT * FROM %s WHERE %s = ?",
                SchemaDB.TAB_CAR, SchemaDB.COL_ID);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setLong(1, id);
        resultSet = preparedStatement.executeQuery();
        Coche c = null;
        if (resultSet.next()) {
            c = new Coche(
                    resultSet.getLong(SchemaDB.COL_ID),
                    resultSet.getString(SchemaDB.COL_CAR_MAT),
                    resultSet.getString(SchemaDB.COL_CAR_MARC),
                    resultSet.getString(SchemaDB.COL_CAR_MOD),
                    resultSet.getString(SchemaDB.COL_CAR_COLOR));
        }
        return c;
    }

    @Override
    public boolean modificarCoche(long id, Coche coche) throws SQLException {
        String query = String.format("UPDATE %s SET %s = ?, %s = ?, %s = ?, %s = ? WHERE %s = ?",
                SchemaDB.TAB_CAR,
                SchemaDB.COL_CAR_MAT,
                SchemaDB.COL_CAR_MARC,
                SchemaDB.COL_CAR_MOD,
                SchemaDB.COL_CAR_COLOR,
                SchemaDB.COL_ID);
        PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, coche.getMatricula());
            preparedStatement.setString(2, coche.getMarca());
            preparedStatement.setString(3, coche.getModelo());
            preparedStatement.setString(4, coche.getColor());
            preparedStatement.setLong(5, id);
        return preparedStatement.executeUpdate() > 0;
    }

    @Override
    public List<Coche> listarCoches() throws SQLException {
        List<Coche> listaCoches = new ArrayList<>();
        String query = String.format("SELECT %s.*, (5 - COUNT(%s.%s)) AS %s " +
                                     "FROM %s " +
                                     "LEFT JOIN %s ON %s.%s = %s.%s " +
                                     "GROUP BY %s.%s ",
                SchemaDB.TAB_CAR, SchemaDB.TAB_PAS, SchemaDB.COL_ID, SchemaDB.COL_PLA_DISP,
                SchemaDB.TAB_CAR,
                SchemaDB.TAB_PAS, SchemaDB.TAB_CAR, SchemaDB.COL_ID, SchemaDB.TAB_PAS, SchemaDB.COL_PAS_CAR,
                SchemaDB.TAB_CAR, SchemaDB.COL_ID
        );
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Coche c = new Coche(
                    resultSet.getLong(SchemaDB.COL_ID),
                    resultSet.getString(SchemaDB.COL_CAR_MAT),
                    resultSet.getString(SchemaDB.COL_CAR_MARC),
                    resultSet.getString(SchemaDB.COL_CAR_MOD),
                    resultSet.getString(SchemaDB.COL_CAR_COLOR),
                    resultSet.getInt(SchemaDB.COL_PLA_DISP));
            listaCoches.add(c);
        }
        return listaCoches;
    }

    @Override
    public List<Coche> cochesDisponibles() throws SQLException {
        List<Coche> listaCoches = new ArrayList<>();
        String query = String.format("SELECT %s.*, (5 - COUNT(%s.%s)) AS %s " +
                                     "FROM %s " +
                                     "LEFT JOIN %s ON %s.%s = %s.%s " +
                                     "GROUP BY %s.%s " +
                                     "HAVING COUNT(%s.%s) < 5;",
                SchemaDB.TAB_CAR, SchemaDB.TAB_PAS, SchemaDB.COL_ID, SchemaDB.COL_PLA_DISP,
                SchemaDB.TAB_CAR,
                SchemaDB.TAB_PAS, SchemaDB.TAB_CAR, SchemaDB.COL_ID, SchemaDB.TAB_PAS, SchemaDB.COL_PAS_CAR,
                SchemaDB.TAB_CAR, SchemaDB.COL_ID,
                SchemaDB.TAB_PAS, SchemaDB.COL_PAS_CAR
                );
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Coche c = new Coche(
                    resultSet.getLong(SchemaDB.COL_ID),
                    resultSet.getString(SchemaDB.COL_CAR_MAT),
                    resultSet.getString(SchemaDB.COL_CAR_MARC),
                    resultSet.getString(SchemaDB.COL_CAR_MOD),
                    resultSet.getString(SchemaDB.COL_CAR_COLOR),
                    resultSet.getInt(SchemaDB.COL_PLA_DISP));
            listaCoches.add(c);
        }
        return listaCoches;
    }
}






















