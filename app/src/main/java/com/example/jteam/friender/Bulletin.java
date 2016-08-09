package com.example.jteam.friender;

import android.util.Log;

import java.io.Serializable;

/**
 * Created by Jeong on 2016-08-08.
 */
public class Bulletin implements Serializable, Comparable<Bulletin> {
    private String city = null;
    private String destination = null;
    private String route1 = null;
    private String route2 = null;
    private String letter = null;
    private int date = 0;
    private int[] character = new int[3];/////////////
    private int totalnum = 0;/////////////
    private int joinednum = 0;//////////////
    private String username = null;
    private int num_bulletin = 0;


    public void setAllcomponents(int number_bulletin, String des, String writer, String rout1,String rout2, int dat, int total, int join,
                                 int char1, int char2, int char3, String let)
    {
        num_bulletin = number_bulletin;
        destination = des;
        route1 = rout1;
        route2 = rout2;
        username = writer;
        date = dat;
        totalnum = total;
        joinednum = join;
        character[0] = char1;
        character[1] = char2;
        character[2] = char3;
        letter = let;
    }

    public int getNum_bulletin() {
        return num_bulletin;
    }

    public void setNum_bulletin(int num_bulletin) {
        this.num_bulletin = num_bulletin;
    }

    public int getCharacter(int num) {
        return character[num];
    }

    public void setCharacter(int num, int a) {
        this.character[num] = a;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getJoinednum() {
        return joinednum;
    }

    public void setJoinednum(int joinednum) {
        this.joinednum = joinednum;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public String getDate() {
        return ""+date;
    }

    public int getintDate() {return date;}

    public void setDate(int p_date) {
        this.date = p_date;
    }

    public String getRoute1() {
        return route1;
    }

    public void setRoute1(String route1) {
        this.route1 = route1;
    }

    public String getRoute2() {
        return route2;
    }

    public void setRoute2(String route2) {
        this.route2 = route2;
    }

    public int getTotalnum() {
        return totalnum;
    }

    public void setTotalnum(int totalnum) {
        this.totalnum = totalnum;
    }

    public void printcontents()
    {
        Log.i("city",""+city);
        Log.i("destination",""+destination);
        Log.i("route1",""+route1);
        Log.i("route2",""+route2);
        Log.i("totalnum",""+totalnum);
        Log.i("joinednum",""+joinednum);
        Log.i("pictogram",""+character[0] +" " + character[1] + " " + character[2]);
        Log.i("date",""+date);
    }



    //날짜를 비교해서 출발날짜가 가장 일찍인 글이 가장 높은 우선순위를 가짐
    //단 출발날짜가 오늘보다 전(이미 지나간글)이면 가장 낮은 우선순위를 가짐
    @Override
    public int compareTo(Bulletin another) {

        if(this.date>another.date)
            return 1;
        else if(this.date<another.date)
            return -1;
        else
            return 0;

    }
}
