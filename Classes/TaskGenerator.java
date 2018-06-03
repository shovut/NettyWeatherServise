
import java.util.*;

public class TaskGenerator {

    private List<String> cities = new ArrayList<String>();
    public final CustomQueue queue;
    public final int count;

    TaskGenerator(int count, CustomQueue queue) {
        this.queue = queue;
        this.count = count;
        cities.add("Moscow");
        cities.add("London");
        cities.add("Paris");
        cities.add("Ivanovo");
        cities.add("Tokio");
        cities.add("Kiev");
        cities.add("Praga");
        cities.add("Washington");
        cities.add("Warszawa");
        cities.add("Ottawa");
    }

    public void generate(int i) {

        int currentPosition = i % cities.size();
        Task task = new Task(i, cities.get(currentPosition), new Date());
        synchronized (queue) {
            queue.setQeue(task);
        }

    }
}
