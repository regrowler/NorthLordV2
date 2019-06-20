package com.example.northlordv2.HomeFeature.CarFeature;

import android.content.Context;

import com.example.northlordv2.ContextModule;
import com.example.northlordv2.InitialData;
import com.example.northlordv2.OkHttpClientModule;
import com.example.northlordv2.ProfileFeature.ChangePictureFeature.PictureChanger;
import com.example.northlordv2.ProfileFeature.ChangePictureFeature.PictureSender;
import com.example.northlordv2.inter.ApplicationContext;
import com.example.northlordv2.inter.ApplicationScope;
import com.example.northlordv2.inter.ProfileFeature.PictureApi;
import com.example.northlordv2.inter.ProfileFeature.ProfileApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ContextModule.class)
public class CarInfoModule {
    @Provides
    @ApplicationScope
    public CarRentDataAdapter getAdapter(@ApplicationContext Context context){
        return new CarRentDataAdapter(context);
    }
}
