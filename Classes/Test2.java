import java.util.*;
public class Test2 {
    public static void main(String[] args) throws InterruptedException {
        int sizeOfQueue = 1;
        CustomQueue inQueue = new CustomQueue(sizeOfQueue);
        Date dateTest = new Date();
        Task taskTest = new Task(0,"Paris", dateTest);
        inQueue.setQeue(taskTest); 
        String weatherForTest = "weather:+19";
        
        System.out.println(taskTest.getId());
        System.out.println(taskTest.getDate());
        System.out.println(taskTest.getCity());
        System.out.println(weatherForTest);
        
        CustomQueue outQueue = new CustomQueue(sizeOfQueue);
        TaskExecutor te = new TaskExecutor(inQueue, outQueue);
        ThreadExecutor thExecutor = new ThreadExecutor(te);
        Thread threadExecutor1 = new Thread(thExecutor);
        threadExecutor1.start();
        Thread.sleep(10);
        threadExecutor1.interrupt();
        
        Task taskOut = outQueue.getQeue().poll();
        String weatherOut = taskOut.getWeather();
        if( "Paris".equals(taskOut.getCity()) && 0 == taskOut.getId() && dateTest.equals(taskOut.getDate())) System.out.println("OK");
        else System.out.println("ERROR");
        if(weatherOut.equals(weatherForTest)) System.out.println("OK");
        else System.out.println("ERROR");
        
        
    }
}
