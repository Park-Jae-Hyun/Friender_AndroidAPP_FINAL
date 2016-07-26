package com.example.jteam.friender;


import java.util.ArrayList;

/**
 * Created by flag on 2016-07-18.
 */
public class CityList {

    ArrayList<String> city_list = new ArrayList<String>();

    public CityList(){

        city_list.add("London");
        city_list.add("Bangkok");
        city_list.add("Paris");
        city_list.add("Singapore");
        city_list.add("Dubai");
        city_list.add("New York");
        city_list.add("Istanbul");
        city_list.add("Kuala Lumpur");
        city_list.add("Hong Kong");
        city_list.add("Seoul");
        city_list.add("Daegu");
        city_list.add("Pusan");
        city_list.add("Dokyo");


    }

    public void setCity_list(){

    }

    public ArrayList getCity_list(){
        return city_list;
    }


}
