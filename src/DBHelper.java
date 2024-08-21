
//https://www.sqlitetutorial.net/sqlite-java/sqlite-jdbc-driver/
//download driver von Moodle auf Festplatte sqlite-jdbc-3.14.2.jar
//Menü file - Project Structure -- Modules - Dependency -- ClassPath wird angepasst

import java.sql.*;

public class DBHelper {
    Connection conn = null;
    public void openConnection() {
        try {
            String url = "jdbc:sqlite:C:/LVs/DBP2024/Johann.db";
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public  void readCustomer() {

        try {

            String selectString = "SELECT KDNR, Vorname, Bonuspunkte FROM Kunden";

            Statement readStmt = conn.createStatement();

            //rs ist ein Cursor mit next() wird auf die nächste Zeile gewechselt
            ResultSet rs =  readStmt.executeQuery(selectString);

            while (rs.next()) {

                System.out.printf("Kdnr: %d, Vorname: %s, Punkte: %d%n", rs.getInt(1),
                        rs.getString(2), rs.getInt(3));

                int bonuspunkte = rs.getInt(3);
                if (rs.wasNull()) {
                    System.out.println("Bonuspunkte undefiniert");
                }
            }
            rs.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

}
