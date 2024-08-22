public class Kunde {

    private int KDNR;
    private String Vorname;
    private int Bonuspunkte;

    public int getKDNR() {
        return KDNR;
    }

    public void setKDNR(int KDNR) {
        this.KDNR = KDNR;
    }

    public String getVorname() {
        return Vorname;
    }

    public void setVorname(String vorname) {
        Vorname = vorname;
    }

    public Kunde() {
    }

    @Override
    public String toString() {
        return "Kunde{" +
                "KDNR=" + KDNR +
                ", Vorname='" + Vorname + '\'' +
                ", Bonuspunkte=" + Bonuspunkte +
                '}';
    }

    public Kunde(int KDNR, String vorname, int bonuspunkte) {
        this.KDNR = KDNR;
        Vorname = vorname;
        Bonuspunkte = bonuspunkte;
    }

    public int getBonuspunkte() {
        return Bonuspunkte;
    }

    public void setBonuspunkte(int bonuspunkte) {
        Bonuspunkte = bonuspunkte;
    }

    private String KundenArt;

    public String getKundenArt() {
        return KundenArt;
    }

    public Kunde(String vorname, String kundenArt) {
        Vorname = vorname;
        KundenArt = kundenArt;
    }

    public void setKundenArt(String kundenArt) {
        KundenArt = kundenArt;
    }
}
