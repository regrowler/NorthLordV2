package com.example.northlordv2.HomeFeature;

import android.content.Context;

import com.example.northlordv2.InitialData;
import com.example.northlordv2.OkHttpClientModule;
import com.example.northlordv2.inter.ApplicationContext;
import com.example.northlordv2.inter.ApplicationScope;
import com.example.northlordv2.inter.HomeFeature.CarApi;
import com.example.northlordv2.inter.HomeFeature.HomeFragmentScope;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class HomeFragmentModule {

    @Provides
    @ApplicationScope
    public HomeAdapter getAdapter(@ApplicationContext Context context){
        return new HomeAdapter(context);
    }

}
