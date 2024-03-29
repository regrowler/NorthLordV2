package com.example.northlordv2.RentsFeature;

import java.util.GregorianCalendar;

public class Rent {
    int id;
    int carid;
    String name;
    GregorianCalendar start;
    GregorianCalendar end;
    int cost;
    boolean checked = false;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean in) {
        checked = in;
    }


    public int id() {
        return id;
    }

    public Rent() {
        id = -1;
        carid=-1;
        name = "";
        start = new GregorianCalendar();
        end = new GregorianCalendar();
    }

    public Rent(String name) {
        this.name = name;
    }

    public Rent(String name, GregorianCalendar start, GregorianCalendar end) {
        this.start = start;
        this.end = end;
        this.name = name;
    }

    public Rent(String name, GregorianCalendar start, GregorianCalendar end, int cost, int id) {
        this.start = start;
        this.end = end;
        this.cost = cost;
        this.name = name;
        this.id = id;
    }

    public Rent(Result result) {
        name = result.name;
        id = result.id;
        cost = result.cost;
        String[] sd = result.startdate.split(" ");
        String[] st = result.starttime.split(" ");
        String[] ed = result.enddate.split(" ");
        String[] et = result.endtime.split(" ");
        GregorianCalendar s = new GregorianCalendar();
        s.set(Integer.parseInt(sd[2]), Integer.parseInt(sd[1]), Integer.parseInt(sd[0]), Integer.parseInt(st[0]), Integer.parseInt(st[1]));
        GregorianCalendar e = new GregorianCalendar();
        e.set(Integer.parseInt(ed[2]), Integer.parseInt(ed[1]), Integer.parseInt(ed[0]), Integer.parseInt(et[0]), Integer.parseInt(et[1]));
        start = s;
        end = e;
        carid=result.carid;
    }

    public int getCarid() {
        return carid;
    }

    public void setCarid(int carid) {
        this.carid = carid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GregorianCalendar getStart() {
        return start;
    }

    public void setStart(GregorianCalendar start) {
        this.start = start;
    }

    public GregorianCalendar getEnd() {
        return end;
    }

    public void setEnd(GregorianCalendar end) {
        this.end = end;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
