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
        myHelper.readCustomer();

        System.out.println("\nread Customer by ID");
        Kunde k1 = myHelper.getKundeById(12);
        System.out.println("Kunde mit KDNR 1");
        System.out.println(k1);
        myHelper.closeConnection();


    }
}