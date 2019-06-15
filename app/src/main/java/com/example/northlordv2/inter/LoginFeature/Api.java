package com.example.northlordv2.inter.LoginFeature;


import com.example.northlordv2.LoginActivityFeature.model.Result;


import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface Api {
    @GET("log")
    Observable<Result> login(@Header("login")String l,@Header("password")String p);
    @FormUrlEncoded
    @PUT("log")
    Observable<Result> reg(@Field("login")String l,@Field("password")String p);
}
