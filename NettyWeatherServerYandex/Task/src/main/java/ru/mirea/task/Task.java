/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.mirea.task;

/**
 *
 * @author msi 111
 */
public class Task {

    private final String area;
    private final String city;
    private String weather;

    public Task(String area, String city) {
        this.area = area;
        this.city = city;
    }

    public void printTask() {
        System.out.println(area);
        System.out.println(city);
        System.out.println(weather);
    }
    
    public String print() {
        return area + ' ' + city + ' ' + weather + ' ' ;
    }

    public String getArea() {
        return area;
    }

    public String getCity() {
        return city;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String tmpWeather) {
        this.weather = tmpWeather;
    }
}
