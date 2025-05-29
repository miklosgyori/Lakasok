package utils;

import model.Lakas;
import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Utils {
    /**
     * adatok beolvasasa a json filebol
     * @param pathToDataFile az adatfile helye a futtatasi konyvtarbol relativan
     * @return arraylist, a beolvasott Lakas objketumokkal
     */
    public static List<Lakas> betolt(String pathToDataFile){

        List<Lakas> lakasokBeolvasva = new ArrayList<>();

        try {
            // Teljes fájl beolvasása karakterláncként
            String jsonString = new String(Files.readAllBytes(Paths.get(pathToDataFile)));

            // Fő JSON objektum
            JSONObject root = new JSONObject(jsonString);

            // "lakasok" tömb
            JSONArray lakasokArray = root.getJSONArray("lakasok");

            // Iterálunk a tömb elemein, Lakas objektum letrehozasa, listahoz adas
            for (int i = 0; i < lakasokArray.length(); i++) {
                JSONObject obj = lakasokArray.getJSONObject(i);

                Lakas lakas = new Lakas(
                        obj.getInt("kerulet"),
                        obj.getInt("terulet"),
                        obj.getInt("szobak_szama"),
                        obj.getInt("emelet"),
                        obj.getInt("ar"),
                        obj.getString("allapot")
                );
                lakasokBeolvasva.add(lakas);
                System.out.println("Beolvasva a fajlbol: " + lakasokBeolvasva.size() + ". " + lakas);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lakasokBeolvasva;
    }

    public static List<Lakas> legdragabb(List<Lakas> lakasLista) {

        List<Lakas> legdragabbLista = new ArrayList<>();

        if ( !lakasLista.isEmpty() ){

            int maxAr = 0;
            for (Lakas lakas : lakasLista) {
                int ar = lakas.getAr();
                if (ar > maxAr) {
                    legdragabbLista.clear();
                    legdragabbLista.add(lakas);
                    maxAr = ar;
                } else if (ar == maxAr) {
                    legdragabbLista.add(lakas);
                }
            }
        } else {
            System.err.println("Nem tudok legdragabb lakast keresni, mert ures a lista!");
        }
        return legdragabbLista;
    }

    public static Map<Integer,Integer> keruletDarab(List<Lakas> lakasLista)
    {
        Map<Integer,Integer> darabszamok = new TreeMap<>();

        if ( !lakasLista.isEmpty() ){

            for (Lakas lakas : lakasLista) {
                int ker = lakas.getKerulet();
                if (darabszamok.containsKey(ker)) {
                    int aktualisDarab = darabszamok.get(ker);
                    darabszamok.put(ker, aktualisDarab + 1);
                } else {
                    darabszamok.put(ker, 1);
                }
            }
        } else {
            System.err.println("Nem tudok keruletenkenti darabszamot szamolni, mert ures a lista!");
        }
        return darabszamok;
    }

}
