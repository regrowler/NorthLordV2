package com.example.northlordv2.HomeFeature.CarFeature;

import com.example.northlordv2.InitialData;
import com.example.northlordv2.OkHttpClientModule;
import com.example.northlordv2.application.RetrofitModule;
import com.example.northlordv2.inter.ApplicationScope;
import com.example.northlordv2.inter.HomeFeature.CarApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = RetrofitModule.class)
public class CarApiModule {
    @Provides
    public CarApi randomUsersApi(Retrofit retrofit){
        return retrofit.create(CarApi.class);
    }

}
