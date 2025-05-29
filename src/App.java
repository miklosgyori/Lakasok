import model.Lakas;
import utils.Utils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class App {

    private static final String PATH_TO_DATAFILE = "data/hasznalt.json";
    private static List<Lakas> lakasLista;

    public App() {
        this.lakasLista = Utils.betolt(PATH_TO_DATAFILE);
    }

    public static void main(String[] args) {

        App lakasok = new App();
       
    }
}
