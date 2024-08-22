import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Press Alt+Eingabe with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.

        System.out.printf("Hello and welcome!");

        //Connect.connect();
      //  Connect.readCustomer();
        DBHelper myHelper = new DBHelper();
        myHelper.openConnection();
        System.out.println("\nread all Customers");

         /*
        myHelper.readCustomer();
*/
        //System.out.println("\nread Customer by ID");
       // Kunde k1 = myHelper.getKundeById(12);
        //System.out.println("Kunde mit KDNR 1");
        //System.out.println(k1);


       // Kunde neuerKunde =new Kunde(-1, "Jessica",70);

        //myHelper.insertKunde(neuerKunde);

        //Produkt pNeu =new Produkt(-1,"Tastatur",20,20,-1);

       // myHelper.insertProdukt(pNeu);

     //   Produkt p1 = myHelper.gerProduktById(1);
//        System.out.println(p1);

       // System.out.println("Produkte Preis kleiner 100");
        //System.out.println(myHelper.getProdukteWherePriceSmallerThan(100));

        /*
        Produkt p1 = myHelper.gerProduktById(1);
        p1.setBezeichnung("Tastatur mit Kabel");
        p1.setNettoPreis(25);

        myHelper.updateProdukt(p1);
*/

        /*
        int produktDeleted = myHelper.deleteProdukt(2);
        System.out.println("\nDeleted Products");
        System.out.println(produktDeleted);
*/
       // myHelper.demoTransaction();
        //myHelper.createKundenArtTable();
        //myHelper.insertKundenArt("Silver");
        //myHelper.insertKundenArt("Gold");
        //myHelper.insertKundenArt("Diamant");
      //  myHelper.addColumnToTable();
        //myHelper.readKundenInklKundenArt();
       // List<Kunde> meineSilverKunden = myHelper.getKundenByKundenArt("Silver");
        //System.out.println(meineSilverKunden);
        //myHelper.getSummeBonuspunkteProKundenArt();
        //myHelper.getSummeBonuspunkteProKundenArtMeta();


        Kunde kundeEva =new Kunde(-1, "Eva",90);
        myHelper.insertKunde(kundeEva);

        myHelper.closeConnection();

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


}