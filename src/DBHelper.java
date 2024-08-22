
//https://www.sqlitetutorial.net/sqlite-java/sqlite-jdbc-driver/
//download driver von Moodle auf Festplatte sqlite-jdbc-3.14.2.jar
//Menü file - Project Structure -- Modules - Dependency -- ClassPath wird angepasst

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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


    public void readCustomer() {

        try {

            String selectString = "SELECT KDNR, Vorname, Bonuspunkte FROM Kunden";

            Statement readStmt = conn.createStatement();

            //rs ist ein Cursor mit next() wird auf die nächste Zeile gewechselt
            ResultSet rs = readStmt.executeQuery(selectString);

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

    public Kunde getKundeById(int kdnr) {
        Kunde k = null;
        try {
            PreparedStatement getKundeStmt = conn.prepareStatement(
                    "SELECT KDNR, Vorname, Bonuspunkte FROM Kunden WHERE KDNR = ?");
            getKundeStmt.setInt(1, kdnr);
            ResultSet rs = getKundeStmt.executeQuery();

            if (rs.next()) {
                k = new Kunde(rs.getInt(1), rs.getString(2), rs.getInt(3));
            } else {
                k = new Kunde(-1, "nicht gefunden", -1);
            }
            return k;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return k;
    }

    public void insertKunde(Kunde neuerKunde) {
        String insertSQL = "INSERT INTO Kunden(Vorname,Bonuspunkte) VALUES(?,?);";
        try {
            PreparedStatement pInsertKunde = conn.prepareStatement(insertSQL);
            pInsertKunde.setString(1, neuerKunde.getVorname());
            pInsertKunde.setInt(2, neuerKunde.getBonuspunkte());
            int rowaffected= pInsertKunde.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
/*
 1. class Produkt ProuktNr Bezeichnung Nettopreis UstSatz
 2. DBHelper erweitern
 --- insertNeuesProdukt(produkt)
 --- getProduktById(1) --> Produkt
 --- getProdukteWherePreisKleiner(30) -- List von Produkte
        alle IDs lesen und danach die Methode getProduktById zum Lesen verwenden
  ---updateProdukt(produkt) --- geändert wird die Bezeichnung und der Preis anahand der ProduktNr)
  Produkt p2 = myHelper.getProduktById(2);
  p2.setBezeichnung("Handy");
  p2.setPreis(40);
  myHelper.updateProdukt(p2);
  myHelper.deleteProduktById(37) ---> Anzahl gelöschter Produkte (executeUpdate -- affectedRows)

  Bearbeitung bis 11:45 Uhr, danach Mittagspause, Treffen um 12:30 Uhr

 */

    public void insertProdukt(Produkt neuesProdukt) {
        String insertSQL = "INSERT INTO Produkte(Bezeichnung, NettoPreis, UstSatz) VALUES(?,?,?);";
        try {
            PreparedStatement pInsertProdukt = conn.prepareStatement(insertSQL);
            pInsertProdukt.setString(1, neuesProdukt.getBezeichnung());
            pInsertProdukt.setDouble(2, neuesProdukt.getNettoPreis());
            pInsertProdukt.setDouble(3, neuesProdukt.getUstSatz());
            int rowaffected= pInsertProdukt.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }


    public Produkt gerProduktById(int produktNr) {
        Produkt p = null;
        try {
            PreparedStatement getProduktStmt = conn.prepareStatement(
                    "SELECT ProduktNr, Bezeichnung, NettoPreis, UstSatz ,\n" +
                            "    NettoPreis * UstSatz / 100 AS UstBetrag,\n" +
                            "  NettoPreis *  (1 + UstSatz / 100.) AS BruttoBetrag\n" +
                            "FROM Produkte WHERE ProduktNr = ?");
            getProduktStmt.setInt(1, produktNr);
            ResultSet rs = getProduktStmt.executeQuery();

            if (rs.next()) {
                String bezeichnung = rs.getString(2);
                double nettoPreis = rs.getDouble(3);
                double ustSatz = rs.getDouble(4);
                double bruttoBetrag = rs.getDouble(6);

                bruttoBetrag = nettoPreis * (1 + ustSatz / 100.);

                p =new Produkt(produktNr, bezeichnung,nettoPreis,ustSatz,bruttoBetrag);


            } else {
                p =new Produkt(produktNr, "nicht gefunden",-1,-1,-1);
            }
            return p;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return p;
    }

    public ArrayList<Produkt> getProdukteWherePriceSmallerThan(double price){
        ArrayList<Produkt> myList = new ArrayList<>();

        try {
            String selectProducts = "Select ProduktNr FROM Produkte WHERE NettoPreis < ?";
            PreparedStatement pStmt = conn.prepareStatement(selectProducts);
            pStmt.setDouble(1,price);
            ResultSet rs = pStmt.executeQuery();
            while (rs.next()){
                Produkt p = gerProduktById(rs.getInt(1));
                myList.add(p);

            }
        }
        catch ( SQLException e){
            System.out.println(e);
        }

        return  myList;

    }



    public int updateProdukt(Produkt geaendertesProdukt) {

        int rowaffected=0;
        String updateSql = "UPDATE Produkte \n" +
                "SET Bezeichnung=?, NettoPreis = ?, UstSatz = ? \n" +
                "WHERE ProduktNr = ?;";
        try {
            PreparedStatement pUpdateProdukte = conn.prepareStatement(updateSql);
            pUpdateProdukte.setString(1, geaendertesProdukt.getBezeichnung());
            pUpdateProdukte.setDouble(2, geaendertesProdukt.getNettoPreis());
            pUpdateProdukte.setDouble(3, geaendertesProdukt.getUstSatz());
            pUpdateProdukte.setInt(4, geaendertesProdukt.getProduktNr());
            rowaffected= pUpdateProdukte.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return  rowaffected;
    }

    public int deleteProdukt(int produktNr) {

        int rowaffected=0;
        String deleteSQL = "DELETE FROM Produkte \n" +
                "WHERE ProduktNr = ?;";
        try {
            PreparedStatement pDelete = conn.prepareStatement(deleteSQL);
            pDelete.setInt(1, produktNr);
            rowaffected= pDelete.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return  rowaffected;
    }

    public void demoTransaction(){
                    /*UPDATE Bankkonto
            SET Betrag = Betrag - 50
            WHERE KontoNur = 1;

            UPDATE Bankkonto
            SET Betrag = Betrag + 50
            WHERE KontoNur = 2;*
            */


        int rowaffectedAbbuchung=0;
        int rowaffectedGutbuchen=0;
        String updateSqlAbbuchen = "UPDATE Bankkonto\n" +
                "SET Betrag = Betrag - 50\n" +
                "WHERE KontoNur = ?;";

        String updateSqlGutbuchen = "UPDATE Bankkonto\n" +
                "SET Betrag = Betrag + 50\n" +
                "WHERE KontoNur = ?;";
        try {
            PreparedStatement pAbbuchen = conn.prepareStatement(updateSqlAbbuchen);
            PreparedStatement pGutbuchen = conn.prepareStatement(updateSqlGutbuchen);

            pAbbuchen.setInt(1, 1);
            pGutbuchen.setInt(1, 7);

            conn.setAutoCommit(false);

            rowaffectedAbbuchung = pAbbuchen.executeUpdate(); //Autocommit verhindern
            rowaffectedGutbuchen = pGutbuchen.executeUpdate();//Autocommit verhindern

            if (rowaffectedAbbuchung == 1 && rowaffectedGutbuchen == 1){
                //Commit
                conn.commit();
            } else {
                //Rollback
                conn.rollback();
            }

            conn.setAutoCommit(true);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    //public void transferBonuspunkte(int fromKundenNr, int toKundenNr, int amount)
    //überweist die Bonsupunkte von einem Kunden zu einem anderen Kunden
    //nur wenn beides erfolgreich ist soll die Transaktion dauerhaft gespeichert werden
    //Übung bis 09:20, danach 20 Minuten Pause, 09:40 gemeinsam Aufläsung

    public void transferBonuspunkte(int fromKundenNr, int toKundenNr, int amount) {
        String subtractPointsSql = "UPDATE Kunden SET Bonuspunkte = Bonuspunkte - ? WHERE KundenNr = ?";
        String addPointsSql = "UPDATE Kunden SET Bonuspunkte = Bonuspunkte + ? WHERE KundenNr = ?";

        String url = "jdbc:sqlite:C:/LVs/DBP2024/Johann.db";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement subtractStmt = conn.prepareStatement(subtractPointsSql);
             PreparedStatement addStmt = conn.prepareStatement(addPointsSql)) {

            conn.setAutoCommit(false);

            subtractStmt.setInt(1, amount);
            subtractStmt.setInt(2, fromKundenNr);

            addStmt.setInt(1, amount);
            addStmt.setInt(2, toKundenNr);

            int rowsSubtracted = subtractStmt.executeUpdate();
            int rowsAdded = addStmt.executeUpdate();

            if (rowsSubtracted == 1 && rowsAdded == 1) {
                conn.commit();
            } else {
                conn.rollback();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int createKundenArtTable(){
        int rowaffected=0;
        String createTable = "CREATE TABLE KundenArten\n" +
                "(\n" +
                "    KundenArtId INTEGER Primary Key,\n" +
                "    Bezeichnung TEXT\n" +
                ");";
        try {
            PreparedStatement pCreate = conn.prepareStatement(createTable);

            rowaffected= pCreate.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return  rowaffected;
    }


    /*
        1. Aufgabe
        void insertKundenArt(String bezeichnung)
        void insertKundenArt(KundenArt art) --> bevorzugt
        -- Silver, Gold, Diamant

        2. Aufgabe
        Tabelle Kunden um eine Spalte KundenArt erweitern
        Foreign Key KundenArten setzen
        Kunden mit "random" Kundenarten versehen

        3. Aufgabe (Join)
        Ausgabe Kunden inkl. KundnArt     Jessica Diamond
                                           Johann Silver

         4. getKundenByKundenArt(String bezeichnung)
          List<Kunden> getKundeByKundenArt("Silver")
          List<Kunden> getKundeByKundenArtId(1)

          5. getSummeBonuspunkteProKundenArt
           Silver 70
           Gold   110
           Diamond 240

           Übung bis 11:15 Uhr, danach Mittagspause und um 12:15 Uhr Auflösung


     */

    public void insertKundenArt(String kundenArt) {

        String insertSQL = "INSERT INTO KundenArten(Bezeichnung) VALUES (?)";
        try {
            PreparedStatement insertKundenArt = conn.prepareStatement(insertSQL);
            insertKundenArt.setString(1, kundenArt);
            insertKundenArt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void addColumnToTable() {
        String addSQLColumn = "ALTER TABLE Kunden ADD KundenArt REFERENCES KundenArten (KundenArtId)";

        try {
            PreparedStatement addColumnToTable = conn.prepareStatement(addSQLColumn);

            addColumnToTable.executeUpdate();

        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public void readKundenInklKundenArt() {
        String selectSQLStmt = "SELECT k.vorname, ka.Bezeichnung FROM Kunden k JOIN KundenArten ka ON k.KundenArt = ka.KundenArtId";
        try {
            Statement readStmt = conn.createStatement();
            ResultSet rs = readStmt.executeQuery(selectSQLStmt);

            while (rs.next()) {
                System.out.printf("Vorname: %s, Bezeichnung : %s%n", rs.getString(1), rs.getString(2));
            }
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public List<Kunde> getKundenByKundenArt(String bezeichnung) {
        ArrayList<Kunde> kunden = new ArrayList<>();
        String selectSQLStmt = "SELECT k.vorname, ka.Bezeichnung FROM Kunden k JOIN KundenArten ka ON k.KundenArt = ka.KundenArtId WHERE ka.Bezeichnung = ?";

        try {
            PreparedStatement getKunden = conn.prepareStatement(selectSQLStmt);
            getKunden.setString(1, bezeichnung);
            ResultSet rs = getKunden.executeQuery();

            while (rs.next()) {
                Kunde k = new Kunde(rs.getString(1), rs.getString(2));
                kunden.add(k);
            }

        } catch (SQLException e) {
            e.getMessage();
        }
        return kunden;
    }

    public void getSummeBonuspunkteProKundenArt() {
        String selectSQLStmt = "SELECT SUM(k.bonuspunkte) AS SummeBonuspunkte, ka.Bezeichnung FROM Kunden k JOIN KundenArten ka ON k.KundenArt = ka.KundenArtId GROUP BY Bezeichnung ORDER BY SUM(k.bonuspunkte) DESC";
        try {
            Statement readStmt = conn.createStatement();
            ResultSet rs = readStmt.executeQuery(selectSQLStmt);
            while (rs.next()) {
                System.out.printf("Bezeichnung : %s, SummeBonuspunkte: %s%n", rs.getString(2), rs.getString(1));
            }

        } catch (SQLException e) {
            e.getMessage();
        }
    }


    /* Aufgabe 16.11 Metadaten  Kapitel aufarbeiten
        Sinn - Klassen, Interfaces
        2 "sinnvolle" Beispiele
        Ausarbeitung bis 13:15 und danach 20 Minuten Pause bis 13:35 Uhr
     */


}
