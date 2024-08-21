
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
        //Geschäftslogik / Business
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:C:/LVs/DBP2024/Johann.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
            //Anpassung - Bonuspunkte > 20
            String selectString = "SELECT KDNR, Vorname, Bonuspunkte FROM Kunden";
            //selectString += " WHERE Bonuspunkte > 20; ";
            //Anpassung: alle Kunden mit E oder e im Namen
            // selectString += " WHERE Vorname like '%e%'; ";
            String vorname="Eva";
           // vorname=";DROP TABLE Kunden";
            //SQL Injection
            //eher vermeiden
            //selectString += " WHERE Vorname = '" + vorname + "'";


            System.out.println(selectString);


            Statement readStmt = conn.createStatement();

            //rs ist ein Cursor mit next() wird auf die nächste Zeile gewechselt
            ResultSet rs =  readStmt.executeQuery(selectString);

            while (rs.next()){

                System.out.printf( "Kdnr: %d, Vorname: %s, Punkte: %d%n", rs.getInt(1),
                        rs.getString(2), rs.getInt(3) );

                int bonuspunkte = rs.getInt(3);
                if (rs.wasNull()){
                    System.out.println("Bonuspunkte undefiniert");
                }
            }
            rs.close();

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

    public void readAllProducts(){
        /* 20 Minuten Beispiel, danach 20 Minuten Pause, Treffen um 09:50 Uhr
        1. Create Table SQLite Studio -- ProduktNr, Bezeichnung NettoPreis UstSatz
        2. ein paar Produkte einfügen, teilweise auch mit NULL Werten
        3. Mit Hilfe von JDBC alle Proukte absteigen sortiert nach Preis ausgeben
        4. zusätzlich eine berechnet Spalte mit BruttoBetrag    Netto: 200 UstSatzProz: 20 Bruttobetrag: 240
        5. Berechnung von Brutto entwedwer im SELECT-Statement oder mit "Java"
         */
    }
}
