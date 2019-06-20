package com.example.northlordv2.LastRentsFeature;

import android.content.Context;

import com.example.northlordv2.ContextModule;
import com.example.northlordv2.inter.ApplicationContext;

import dagger.Module;
import dagger.Provides;

@Module(includes = ContextModule.class)
public class LastRentsFragmentModule {
    @Provides
    public LastRentsAdapter getAdapter(@ApplicationContext Context c){
        return new LastRentsAdapter(c);
    }

}
