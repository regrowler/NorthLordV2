package com.example.northlordv2;

import android.content.Context;

import com.example.northlordv2.inter.ApplicationContext;
import com.example.northlordv2.inter.ApplicationScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Hari on 23/11/17.
 */
@Module
public class ContextModule {

    Context context;

    public ContextModule(Context context){
        this.context = context;
    }

    @ApplicationContext
    //@ApplicationScope
    @Provides
    public Context context(){ return context.getApplicationContext(); }
}
