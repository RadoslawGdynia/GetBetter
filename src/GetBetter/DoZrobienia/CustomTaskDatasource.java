package GetBetter.DoZrobienia;

import java.sql.*;

public class CustomTaskDatasource {

    public static final String DB_NAME = "tasks.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:N:\\Programowanie\\GitHub repozytoria\\Repozytoria Online\\GetBetter\\"+DB_NAME;
    private Connection conn;
    private static CustomTaskDatasource instance = new CustomTaskDatasource();

    private CustomTaskDatasource() {

    }

    public static CustomTaskDatasource getInstance() {
        return instance;
    }

    public boolean open () {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            return true;

        } catch (SQLException e) {
            System.out.println("Could not get connection wtith database. Message: " + e.getMessage());
            return false;
        }
    }

    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("Couldnt close connection. WHAT IS HAPPENING?!");
        }
    }
}
