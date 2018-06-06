/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PC
 */
public class TestOfGet {
    public static void main(String[] args)
    {
     String city = "Moscow";
     String region = "ru";
     String result = ApiGetRequest.get(city, region);
     System.out.println(result);
    }
}
