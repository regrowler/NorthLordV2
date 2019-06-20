package com.example.northlordv2.RentsFeature;

import java.util.GregorianCalendar;

public class LastRent {
    int id;
    int carid;
    String name;
    GregorianCalendar start;
    GregorianCalendar end;
    int cost;
    boolean checked = false;
    String label;
    String model;

    public LastRent(int id, String name, GregorianCalendar start, GregorianCalendar end, int cost, boolean checked, String label, String model) {
        this.id = id;
        this.name = name;
        this.start = start;
        this.end = end;
        this.cost = cost;
        this.checked = checked;
        this.label = label;
        this.model = model;
        carid=-1;
    }
    public LastRent(Result result){
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
        model=result.getModel();
        label=result.getLabel();
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

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
