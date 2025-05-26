package model;

/**
 * A Lakas osztaly, mint alapveto adattipus definicioja.
 */
public class Lakas {

    private enum Allapot {
        FELUJITOTT("felujitott"),
        JOALLAPOT("jo allapot"),
        FELUJITANDO("felujitando"),
        UJEPITESU("uj epitesű");

        private final String ertek;

        Allapot(String ertek) {
            this.ertek = ertek;
        }
    }

    private int kerulet;
    private int terulet;
    private int szobaSzama;
    private int emelet;
    private int ar;
    private Allapot allapot;

    public Lakas(int kerulet, int terulet, int szobaSzama, int emelet, int ar, Allapot allapot) {
        this.kerulet = kerulet;
        this.terulet = terulet;
        this.szobaSzama = szobaSzama;
        this.emelet = emelet;
        this.ar = ar;
        this.allapot = allapot;
    }

    public int getKerulet() {
        return kerulet;
    }

    public void setKerulet(int kerulet) {
        this.kerulet = kerulet;
    }

    public int getTerulet() {
        return terulet;
    }

    public void setTerulet(int terulet) {
        this.terulet = terulet;
    }

    public int getSzobaSzama() {
        return szobaSzama;
    }

    public void setSzobaSzama(int szobaSzama) {
        this.szobaSzama = szobaSzama;
    }

    public int getEmelet() {
        return emelet;
    }

    public void setEmelet(int emelet) {
        this.emelet = emelet;
    }

    public int getAr() {
        return ar;
    }

    public void setAr(int ar) {
        this.ar = ar;
    }

    public Allapot getAllapot() {
        return allapot;
    }

    public void setAllapot(Allapot allapot) {
        this.allapot = allapot;
    }
}
