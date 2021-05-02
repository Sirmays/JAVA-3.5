import java.util.ArrayList;
import java.util.Arrays;

public class Race {
    //Race - коллекция объектов типа Stage
    private ArrayList<Stage> stages;


    public ArrayList<Stage> getStages() { return stages; }

    //конструктор
    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }
}