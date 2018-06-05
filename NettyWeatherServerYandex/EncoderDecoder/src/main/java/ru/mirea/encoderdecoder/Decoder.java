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
public class Decoder {
    public static Task decodedTaskClient(byte[] b){
        String[] str = new String(b).split(";");
        return new Task(str[0], str[1]);
    }
    
    public static Task decodedTaskServer(byte[] b){
        String[] str = new String(b).split(";");
        Task task = new Task(str[0], str[1]);
        task.setWeather(str[2]);
        return task;
    }
}
