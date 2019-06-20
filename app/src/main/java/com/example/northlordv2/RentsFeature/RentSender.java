package com.example.northlordv2.RentsFeature;

import android.annotation.SuppressLint;

import com.example.northlordv2.inter.RentsFeature.RentApi;

import java.net.URLDecoder;
import java.util.Calendar;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RentSender {
    @Inject
    RentApi api;

    public RentSender(RentApi api) {
        this.api = api;
    }
    @SuppressLint("CheckResult")
    public void sendRent(String login, String password, Rent rent,int id, Listener listener){
        if(rent.getCost()>0&&!rent.name.equals("")){
            try {
                api.addRent(URLDecoder.decode(login,"UTF-8"),
                        URLDecoder.decode(password,"UTF-8"),
                        ""+rent.start.get(Calendar.DAY_OF_MONTH)+" "+rent.start.get(Calendar.MONTH)+" "+rent.start.get(Calendar.YEAR),
                        ""+rent.start.get(Calendar.HOUR_OF_DAY)+" "+rent.start.get(Calendar.MINUTE),
                        ""+rent.end.get(Calendar.DAY_OF_MONTH)+" "+rent.end.get(Calendar.MONTH)+" "+rent.end.get(Calendar.YEAR),
                        ""+rent.end.get(Calendar.HOUR_OF_DAY)+" "+rent.end.get(Calendar.MINUTE),
                        rent.cost+"",
                        id+"",
                        rent.name)
                        .subscribeOn(Schedulers.io())
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .doOnError(s->{s.printStackTrace();})
                        .subscribe(s->listener.action());
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
    @SuppressLint("CheckResult")
    public void updateRent(String login, String password, Rent rent, int id, Listener listener){
        if(rent.getCost()>0&&!rent.name.equals("")){
            try {
                api.updateRent(URLDecoder.decode(login,"UTF-8"),
                        URLDecoder.decode(password,"UTF-8"),
                        ""+rent.start.get(Calendar.DAY_OF_MONTH)+" "+rent.start.get(Calendar.MONTH)+" "+rent.start.get(Calendar.YEAR),
                        ""+rent.start.get(Calendar.HOUR_OF_DAY)+" "+rent.start.get(Calendar.MINUTE),
                        ""+rent.end.get(Calendar.DAY_OF_MONTH)+" "+rent.end.get(Calendar.MONTH)+" "+rent.end.get(Calendar.YEAR),
                        ""+rent.end.get(Calendar.HOUR_OF_DAY)+" "+rent.end.get(Calendar.MINUTE),
                        rent.cost+"",
                        id+"",
                        rent.name)
                        .subscribeOn(Schedulers.io())
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .doOnError(s->{s.printStackTrace();})
                        .subscribe(s->listener.action());
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
    public static abstract class Listener{
        public abstract void action();
    }
}
