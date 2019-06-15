package com.example.northlordv2.ProfileFeature;

import android.content.Context;

import com.example.northlordv2.InitialData;
import com.example.northlordv2.OkHttpClientModule;
import com.example.northlordv2.ProfileFeature.ChangePictureFeature.PictureChanger;
import com.example.northlordv2.ProfileFeature.ChangePictureFeature.PictureSender;
import com.example.northlordv2.inter.ApplicationContext;
import com.example.northlordv2.inter.ApplicationScope;
import com.example.northlordv2.inter.HomeFeature.CarApi;
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

@Module(includes = {OkHttpClientModule.class,PicassoModule.class})
public class ProfileFeatureModule {
    @Provides
    public ProfileApi randomUsersApi(Retrofit retrofit){
        return retrofit.create(ProfileApi.class);
    }
    @Provides
    public PictureApi pictureApi(Retrofit retrofit){
        return retrofit.create(PictureApi.class);
    }
    @Provides
    @ApplicationScope
    public PictureChanger pictureChanger(@ApplicationContext Context context, Picasso picasso){
        return new PictureChanger(context,picasso);
    }
    @Provides
    public PictureSender pictureSender(PictureChanger changer,PictureApi api){
        return new PictureSender(api,changer);
    }
    @ApplicationScope
//    @Singleton
    @Provides
    public Retrofit retrofit(OkHttpClient okHttpClient,
                             GsonConverterFactory gsonConverterFactory, Gson gson){
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(InitialData.url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

    @Provides
    public Gson gson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides
    public GsonConverterFactory gsonConverterFactory(Gson gson){
        return GsonConverterFactory.create(gson);
    }
}
