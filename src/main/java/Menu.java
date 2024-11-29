import controller.MenuController;
import database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Menu{
    public static void main(String[] args) {
        MenuController menuController = new MenuController();
        menuController.ejecutarMenu();

    }
}



