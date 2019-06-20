package com.example.northlordv2.inter.RentsFeature;

import com.example.northlordv2.HomeFeature.CarFeature.Result;
import com.example.northlordv2.RentsFeature.ResultSet;

import io.reactivex.Observable;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface RentApi {
    @GET("rent")
    Observable<ResultSet> getRents(@Header("login")String login, @Header("password")String password,@Header("id")String id);
    @GET("rent")
    Observable<ResultSet> getLastRents(@Header("login")String login, @Header("password")String password);
    @POST("rent")
    @FormUrlEncoded
    Observable<ResultSet> addRent(@Field("login")String login,
                                  @Field("password")String password,
                                  @Field("startdate")String startdate,
                                  @Field("starttime")String starttime,
                                  @Field("enddate")String enddate,
                                  @Field("endtime")String endtime,
                                  @Field("cost")String cost,
                                  @Field("id")String id,
                                  @Field("name")String name);
    @PUT("rent")
    @FormUrlEncoded
    Observable<ResultSet> updateRent(@Field("login")String login,
                                  @Field("password")String password,
                                  @Field("startdate")String startdate,
                                  @Field("starttime")String starttime,
                                  @Field("enddate")String enddate,
                                  @Field("endtime")String endtime,
                                  @Field("cost")String cost,
                                  @Field("id")String id,
                                  @Field("name")String name);
    @DELETE("rent")
    Observable<ResultSet> deleteRents(@Header("login")String login,@Header("password")String password,@Header("mass")String mass);
}
