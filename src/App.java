import model.Lakas;
import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class App {

    private static final String PATH_TO_DATAFILE = "data/hasznalt.json";
    // private static List<Lakas> lakasLista;

    public static void main(String[] args) {
        try {
            // Teljes fájl beolvasása karakterláncként
            String jsonString = new String(Files.readAllBytes(Paths.get(PATH_TO_DATAFILE)));

            // Fő JSON objektum
            JSONObject root = new JSONObject(jsonString);

            // "lakasok" tömb
            JSONArray lakasokArray = root.getJSONArray("lakasok");

            List<Lakas> lakasok = new ArrayList<>();

            // Iterálunk a tömb elemein
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
                lakasok.add(lakas);
            }

            //TODO: végén törlendő
            // Teszt: első lakás kiírása
            System.out.println("Elso lakas: kerulet " + lakasok.get(0).getKerulet()
                    + ", ar: " + lakasok.get(0).getAr());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
