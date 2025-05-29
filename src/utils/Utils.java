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
            // File beolvasasa karakterlanckent
            String jsonString = new String(Files.readAllBytes(Paths.get(pathToDataFile)));

            // A teljes JSON objektum
            JSONObject root = new JSONObject(jsonString);

            // "lakasok" tomb
            JSONArray lakasokArray = root.getJSONArray("lakasok");

            // Tomb elemein iteralas, Lakas objektum letrehozasa, listahoz adas
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

    /**
     * A legdragabb lakas(ok) kivalasztasa, maximumkeresessel
     * @param lakasLista
     * @return ArrayList, amelyben a legdragabb lakas(oka)t taroljuk
     */
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

    /**
     * A listan levo lakasok keruletenkenti darabszamanak kiszamitasa
     * @param lakasLista a lakasokat tartalmazo ArrayList
     * @return a keruleteket es a darabszamokat tartalmazo TreeMap
     */
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

    /**
     * Atlagos lakasmeretet szamol
     * @param lakasLista a lakasokat tartalmazo ArrayList
     * @param kerulet a kerulet szama vagy 0; utobbi esetben a teljes listara atlagol
     * @return
     */
    public static int atlagMeret(List<Lakas> lakasLista, int kerulet) {
        int total = 0;
        int atlag = 0;
        int szamlalo = 0;

        if ( !lakasLista.isEmpty() ){

            for (Lakas lakas : lakasLista) {

                if ( kerulet == 0 ) {
                    total += lakas.getTerulet();
                } else {
                    if ( kerulet == lakas.getKerulet() ) {
                        total += lakas.getTerulet();
                        szamlalo += 1;
                    }
                }
            }

            if ( kerulet == 0 ){
                atlag = total / lakasLista.size();
            } else {
                atlag = total / szamlalo;
            }

        } else {
            System.err.println("Nem tudok atlagmereteket szamolni, mert ures a lista!");
        }
        return atlag;
    }
}
