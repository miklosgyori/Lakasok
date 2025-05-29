import model.Lakas;
import utils.Utils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static utils.Utils.legdragabb;

public class App {

    private static final String PATH_TO_DATAFILE = "data/hasznalt.json";
    private static List<Lakas> lakasLista;

    public App() {
        this.lakasLista = Utils.betolt(PATH_TO_DATAFILE);
    }

    public static void main(String[] args) {

        App lakasok = new App();

        // 1. feladat
        System.out.println("\n1. feladat:\nA legdragabb lakas(ok) a teljes listabol:");
        for (Lakas lakas : legdragabb(lakasLista)) {
            System.out.println(lakas.toString());
        }

    }
}
