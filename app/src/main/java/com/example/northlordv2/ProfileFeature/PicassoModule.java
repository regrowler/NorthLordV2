package com.example.northlordv2.ProfileFeature;

import android.content.Context;

import com.example.northlordv2.OkHttpClientModule;
import com.example.northlordv2.inter.ApplicationContext;
import com.example.northlordv2.inter.ApplicationScope;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;


@Module(includes = OkHttpClientModule.class)
//@Module
public class PicassoModule {

//    @ApplicationScope
    @Provides
    public Picasso picasso( @ApplicationContext Context context, OkHttp3Downloader okHttp3Downloader){
        return new Picasso.Builder(context).
                downloader(okHttp3Downloader)
                .loggingEnabled(true)
                .build();
    }

    @Provides
    public OkHttp3Downloader okHttp3Downloader(OkHttpClient okHttpClient){
        return new OkHttp3Downloader(okHttpClient);
    }

}
