package com.example.northlordv2.inter.ProfileFeature;

;

import com.example.northlordv2.ProfileFeature.Result;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;

public interface ProfileApi {
    @GET("profile")
    Observable<Result> getProfile(@Header("login") String login, @Header("password") String password);

    @PUT("profile")
    @FormUrlEncoded
    Observable<Result> updateProfile(@Field("login") String login,
                                     @Field("password") String password,
                                     @Field("name") String name,
                                     @Field("surname") String surname,
                                     @Field("email") String email);
}
