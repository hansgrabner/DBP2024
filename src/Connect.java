
//https://www.sqlitetutorial.net/sqlite-java/sqlite-jdbc-driver/
//download driver von Moodle auf Festplatte sqlite-jdbc-3.14.2.jar
//Menü file - Project Structure -- Modules - Dependency -- ClassPath wird angepasst

import java.sql.*;

public class Connect {
    /**
     * Connect to a sample database
     */
    public static void connect() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:C:/LVs/DBP2024/Johann.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }


    public static void readCustomer() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:C:/LVs/DBP2024/Johann.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
            String selectString = "SELECT KDNR, Vorname, Bonuspunkte FROM Kunden;";

            Statement readStmt = conn.createStatement();

            ResultSet rs =  readStmt.executeQuery(selectString);

            while (rs.next()){
                System.out.printf( "Kdnr: %d, Vorname: %s, Punkte: %d%n", rs.getInt(1),
                        rs.getString(2), rs.getInt(3) );
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
