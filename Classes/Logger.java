/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pupys
 */
import java.util.*;
public class Logger {
    public static void log(CustomQueue outQueue) {
        Task prinOutQueue;
        synchronized (outQueue) {
            prinOutQueue = outQueue.getQeue().poll();
        }
        if (prinOutQueue != null) {
            prinOutQueue.printTask();
            System.out.println("" + prinOutQueue.getWeather());
        }
    }
}