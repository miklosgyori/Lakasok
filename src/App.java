import model.Lakas;
import utils.Utils;

import java.util.List;
import java.util.Map;

import static utils.Utils.*;

/**
 * Az alkalmazas belepesi pontjat tartalmazo osztaly
 */
public class App {

    private static final String PATH_TO_DATAFILE = "data/hasznalt.json";
    private static List<Lakas> lakasLista;

    public App() {
        lakasLista = Utils.betolt(PATH_TO_DATAFILE);
    }

    /**
     * Alkalmazas belepesi pont
     * @param args a szokasos, ures
     */
    public static void main(String[] args) {

        App lakasok = new App();

        // 1. feladat
        System.out.println("\n1. feladat:\nA legdragabb lakas(ok) a teljes listabol:");
        for (Lakas lakas : legdragabb(lakasLista)) {
            System.out.println(lakas.toString());
        }

        //2. feladat
        System.out.println("\n2. feladat:\nAz eloado ingatlanok darabszama keruletenkent:");
        Map<Integer,Integer> darabszamok = keruletDarab(lakasLista);
        for (Map.Entry<Integer, Integer> tetel : darabszamok.entrySet()) {
            int ker = tetel.getKey();
            int darab = tetel.getValue();
            System.out.println( ker + ". kerulet: " + darab + "db. elado ingatlan.");
        }

        //3. feladat
        System.out.println("\n3. feladat:\nAz atlagos lakasmeret a teljes listaban: " + atlagMeret(lakasLista, 0) + " m2");

        //4. feladat
        int kerulet = 6;
        System.out.println("\n4. feladat:\nAz atlagos lakasmeret a " + kerulet + ". keruletben: " + atlagMeret(lakasLista, kerulet)  + " m2");
    }
}
