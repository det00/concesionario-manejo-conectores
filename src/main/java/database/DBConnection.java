package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    //CONEXION CREADA CON PATRON SINGLETON
    private static Connection connection;

    public Connection getConnection(){
        if (connection==null){
            newConnection();
        }
        return connection;
    }

    private void newConnection() {
        String url ="jdbc:mysql://blxhn5kyfuug5es9elxa-mysql.services.clever-cloud.com:3306/"+SchemaDB.DB_NAME;
        //url de la conexion a la base de datos
        try {
            //se crea pasandole url, usuario y password
            connection = DriverManager.getConnection(url,"uvf3gy4jkvv13srv","3A0JzjKK4TFYfOha1kd2");
        } catch (SQLException e) {
            System.out.println("Error en la conexión de la base de datos");
        }
        System.out.println("Conexión creada correctamente");
    }

    public void closeConnection(){
        try {
            connection.close();
            System.out.println("Conexión cerrada correctamente");
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión");
        } finally {
            connection = null;
        }
    }
}
