/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.mirea.EncoderDecoder;

import ru.mirea.task.Task;
/**
 *
 * @author msi 111
 */
public class Encoder {
    public static byte[] encodedTaskClient(Task task){
        return (task.getArea() + ';' + task.getCity()).getBytes();
    }
    
    public static byte[] encodedTaskServer(Task task){
        return (task.getArea() + ';' + task.getCity() + ';' + task.getWeather()).getBytes();
    }
}
