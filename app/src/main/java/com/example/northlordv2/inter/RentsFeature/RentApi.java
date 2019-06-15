package com.example.northlordv2.inter.RentsFeature;

import com.example.northlordv2.HomeFeature.CarFeature.Result;
import com.example.northlordv2.RentsFeature.ResultSet;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface RentApi {
    @GET("rent")
    Observable<ResultSet> getRents(@Header("login")String login, @Header("password")String password,@Header("id")String id);
    @GET("rent")
    Observable<ResultSet> getLastRents(@Header("login")String login, @Header("password")String password);
}
