package com.example.northlordv2.RentsFeature;

import java.util.GregorianCalendar;

public class Rent{
    public int id;
    public String name;
    public GregorianCalendar start;
    public GregorianCalendar end;
    public int cost;
    boolean checked=false;
    public boolean isChecked(){return checked;}
    public void setChecked(boolean in){checked=in;}


    public int id() {
        return id;
    }

    public Rent(){ }
    public Rent(String name){this.name=name;}
    public Rent(String name, GregorianCalendar start, GregorianCalendar end){this.start=start;this.end=end;this.name=name; }
    public Rent(String name, GregorianCalendar start, GregorianCalendar end, int cost, int id){this.start=start;this.end=end;this.cost=cost;this.name=name;this.id=id; }
}
