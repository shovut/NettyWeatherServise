
import java.util.*;

public class TaskExecutor {

    Map<String, String> forecasts = new HashMap<>();
    
    

    Random rand = new Random();

    public final Queue<Task> inQueue;
    public final Queue<Task> outQueue;

    public int randWeather;

    public TaskExecutor(CustomQueue qu, CustomQueue outQueue) {
        forecasts.put("Moscow", "+15");
        forecasts.put("London", "+17");
        forecasts.put("Paris","+19");
        forecasts.put("Ivanovo","+11");
        forecasts.put("Tokio","+20");
        forecasts.put("Kiev","+30");
        forecasts.put("Praga","+13");
        forecasts.put("Washington","+16");
        forecasts.put("Warszawa","+12");
        forecasts.put("Ottawa","+23");
        inQueue = qu.getQeue();
        this.outQueue = outQueue.getQeue();

    }

    public void executor() {
        while (!Thread.interrupted()) {
            try {
                if (inQueue.size() > 0) {
                    Task firstElemQue;
                    synchronized (inQueue) {
                        firstElemQue = inQueue.poll();
                    }
                    if (firstElemQue != null) {
                        //System.out.println("ОК");
                        int id = firstElemQue.getId();
                        Date date = firstElemQue.getDate();
                        String city = firstElemQue.getCity();
                        firstElemQue.setWeather("weather:"+ forecasts.get(city));
                        synchronized (outQueue) {
                            //outQueue.getQeue().add(firstElemQue); 
                            outQueue.add(firstElemQue);
                        }
                    } else {
                        System.out.println("ERROR");
                    }
                }
                Thread.sleep(10);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
