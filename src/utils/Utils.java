package utils;

import model.Lakas;
import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static List<Lakas> betolt(String pathToDataFile){

        List<Lakas> lakasokBeolvasva = new ArrayList<>();

        try {
            // Teljes fájl beolvasása karakterláncként
            String jsonString = new String(Files.readAllBytes(Paths.get(pathToDataFile)));

            // Fő JSON objektum
            JSONObject root = new JSONObject(jsonString);

            // "lakasok" tömb
            JSONArray lakasokArray = root.getJSONArray("lakasok");

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
                lakasokBeolvasva.add(lakas);
                System.out.println("Beolvasva a fajlbol: " + lakasokBeolvasva.size() + ". " + lakas);
            }

            //TODO: végén törlendő
            // Teszt: első lakás kiírása
            System.out.println("Elso lakas: kerulet " + lakasokBeolvasva.get(0).getKerulet()
                    + ", ar: " + lakasokBeolvasva.get(0).getAr());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lakasokBeolvasva;
    }
}
