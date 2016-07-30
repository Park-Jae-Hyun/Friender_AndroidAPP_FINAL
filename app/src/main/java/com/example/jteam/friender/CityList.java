package com.example.jteam.friender;


import java.util.ArrayList;

/**
 * Created by flag on 2016-07-18.
 */
public class CityList {

    ArrayList<Integer> city_plist = new ArrayList<Integer>(); //city picturelist
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

        //Picture리스트에 사진 리소스 담기
        city_plist.add(R.mipmap.p01london);
        city_plist.add(R.mipmap.p02bankok);
        city_plist.add(R.mipmap.p03paris);
        city_plist.add(R.mipmap.p04singapore);
        city_plist.add(R.mipmap.p05dubai);
        city_plist.add(R.mipmap.p06newyork);
        city_plist.add(R.mipmap.p07istanbul);
        city_plist.add(R.mipmap.p08kualalumpur);
        city_plist.add(R.mipmap.p09hongkong);
        city_plist.add(R.mipmap.p10seoul);
        city_plist.add(R.mipmap.p11barcelona);
        city_plist.add(R.mipmap.p12amsterdam);
        city_plist.add(R.mipmap.p13milan);
    }



    public void setCity_list(){

    }

    public ArrayList getCity_list(){
        return city_list;
    }

    public ArrayList getCity_plist() { return city_plist;}//

}

