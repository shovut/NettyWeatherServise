package ru.mirea.wserver;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PC
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiGetRequest {
    public static String get (String city, String region)
    {
        String key = "b5d9b8d4c3348e73f4f7e102a82c55b3";
        //String city = "Moscow";
        //String region = "ru";
        String query = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "," + region + "&units=metric&APPID=" + key;
        
        HttpURLConnection connection = null;
        
        String result = "\n";
        
        try
        {
            connection = (HttpURLConnection) new URL(query).openConnection();
            
            connection.setRequestMethod("GET"); //тип запроса
            connection.setUseCaches(false); // использование кеша
            connection.setConnectTimeout(3000); //время подключения
            connection.setReadTimeout(3000); //время чтения
            
            connection.connect(); //выполняем подключение
            
            //StringBuilder sb = new StringBuilder();
            
            if (HttpURLConnection.HTTP_OK == connection.getResponseCode())
            {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream())); //получаем поток ответа
                
                String line;
                String[] str;
                
                while ((line = in.readLine()) != null )  //Проверка "не пустой ли ответ?"
                {
                    //sb.append(line);
                    //sb.append("\n");
                    str = line.split(",");
                    for (int i = 0 ; i< str.length; i++)
                    {
                        if (str[i].contains("\"temp\":"))
                            result = result + str[i] + "\n";
                        if (str[i].contains("\"pressure\":"))
                            result = result + str[i] + "\n";
                        if (str[i].contains("\"humidity\":"))
                            result = result + str[i] + "\n";
                        if (str[i].contains("\"temp_min\":"))
                            result = result + str[i] + "\n";
                        if (str[i].contains("\"temp_max\":")) {
                            result = result + str[i] + "\n";
                        }
                        if (str[i].contains("\"sea_level\":")) {
                            result = result + str[i] + "\n";
                        }
                        if (str[i].contains("\"grnd_level\":")) {
                            result = result + str[i] + "\n";
                        }
                    }
                }
                result = result.substring(result.indexOf('{') + 1, result.indexOf('}'));
                
                //System.out.println(result);
                //System.out.println(sb.toString());
            }
            else 
            {
                result = "fail " + connection.getResponseCode() + ", " + connection.getResponseMessage();
                //System.out.println();
            }
        }
        catch (Throwable cause) 
        {
             cause.printStackTrace();       
        }
        finally
        {
            if (connection != null)
                connection.disconnect(); 
        }
        return result;
    }
}
