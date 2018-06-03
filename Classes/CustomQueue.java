import java.util.*;
public class CustomQueue{
    private int size;
    private Queue<Task> Queue = new LinkedList<Task>();
    CustomQueue(int size){
	this.size = size;	
    }
    
    public void setQeue(Task task) {
        if (Queue.size()<size) Queue.add(task);
    }
    
    public Queue<Task> getQeue(){
        return Queue;
    }
}		



//inQueue = new CustomQueue();
//outQueue = new CustomQueue()
//synchronized(inQueue);
