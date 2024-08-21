
//                 O/R Mapper - Object relational Mapper
public class Produkt {

    private int ProduktNr;
    private String Bezeichnung;
    private double NettoPreis;

    public int getProduktNr() {
        return ProduktNr;
    }

    public Produkt() {
    }

    @Override
    public String toString() {
        return "Produkt{" +
                "ProduktNr=" + ProduktNr +
                ", Bezeichnung='" + Bezeichnung + '\'' +
                ", NettoPreis=" + NettoPreis +
                ", UstSatz=" + UstSatz +
                ", BruttoBetrag=" + BruttoBetrag +
                '}';
    }

    public Produkt(int produktNr) {
        ProduktNr = produktNr;
    }

    public void setProduktNr(int produktNr) {
        ProduktNr = produktNr;
    }

    private double UstSatz;

    public String getBezeichnung() {
        return Bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        Bezeichnung = bezeichnung;
    }

    public double getNettoPreis() {
        return NettoPreis;
    }

    public void setNettoPreis(double nettoPreis) {
        NettoPreis = nettoPreis;
    }

    public double getUstSatz() {
        return UstSatz;
    }

    public void setUstSatz(double ustSatz) {
        UstSatz = ustSatz;
    }

    public double getBruttoBetrag() {
        return BruttoBetrag;
    }

    public void setBruttoBetrag(double bruttoBetrag) {
        BruttoBetrag = bruttoBetrag;
    }

    public Produkt(int produktNr, String bezeichnung, double nettoPreis, double ustSatz, double bruttoBetrag) {
        ProduktNr = produktNr;
        Bezeichnung = bezeichnung;
        NettoPreis = nettoPreis;
        UstSatz = ustSatz;
        BruttoBetrag = bruttoBetrag;
    }

    private double BruttoBetrag;
}
