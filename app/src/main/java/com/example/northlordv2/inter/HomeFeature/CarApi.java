package com.example.northlordv2.inter.HomeFeature;

;

import com.example.northlordv2.HomeFeature.CarFeature.Result;

import io.reactivex.Observable;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;

public interface CarApi {
    @GET("car")
    Observable<Result> getCars(@Header("login")String l, @Header("password")String p);
    @FormUrlEncoded
    @PUT("car")
    Observable<Result> putCar(@Field("login")String login,
                           @Field("password")String password,
                           @Field("label")String label,
                           @Field("model")String model,
                           @Field("cost")String cost,
                           @Field("rentcost")String rent);
    @DELETE("car")
    Observable<Result> deleteCars(@Header("login")String login,@Header("password")String password,@Header("mas")String mas);
}
