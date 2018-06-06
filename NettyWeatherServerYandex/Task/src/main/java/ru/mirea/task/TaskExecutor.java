/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.mirea.task;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.mirea.wserver.ApiGetRequest;

/**
 *
 * @author msi 111
 */
public class TaskExecutor implements Runnable{

    
    public Queue<Task> inQueue;
    public Queue<Task> outQueue;
    
    public TaskExecutor(Queue<Task> Queue1, Queue<Task> Queue2){
        inQueue = Queue1;
        outQueue = Queue2;
    }

    @Override
    public void run() {
        Task temp = null;
        while(!Thread.interrupted()){
            
            if (!inQueue.isEmpty()){
            synchronized (inQueue){
                if (!inQueue.isEmpty()){
                    //System.out.println("Executor inQueue! " + Thread.currentThread().getName());
                    temp = inQueue.poll();
                    temp.setWeather(ApiGetRequest.get(temp.getCity(), temp.getArea()));
                    //System.out.println(temp.getWeather());
                }
            }
            }
            if (temp != (null)){
            synchronized (outQueue){
                if (temp != (null)){
                    //System.out.println("Executor outQueue! " + Thread.currentThread().getName());
                    outQueue.add(temp);
                    temp = null;
                    //System.out.println("task2");
                }
            }
            }
        }
    }
    
    
}
