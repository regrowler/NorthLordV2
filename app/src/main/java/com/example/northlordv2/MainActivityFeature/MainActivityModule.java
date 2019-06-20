package com.example.northlordv2.MainActivityFeature;

import android.content.Context;

import com.example.northlordv2.ContextModule;
import com.example.northlordv2.HomeFeature.HomeFragment;
import com.example.northlordv2.LastRentsFeature.LastRentsFragment;
import com.example.northlordv2.ProfileFeature.ProfileFragment;
import com.example.northlordv2.inter.ApplicationContext;
import com.example.northlordv2.inter.ApplicationScope;
import com.example.northlordv2.inter.HomeFeature.DaggerHomeFragmentComponent;
import com.example.northlordv2.inter.HomeFeature.HomeFragmentComponent;
import com.example.northlordv2.inter.ProfileFeature.DaggerProfileFragmentComponent;
import com.example.northlordv2.inter.ProfileFeature.ProfileFragmentComponent;
import com.example.northlordv2.inter.RentsFeature.DaggerLastRentsFragmentComponent;
import com.example.northlordv2.inter.RentsFeature.LastRentsFragmentComponent;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = ContextModule.class)
public class MainActivityModule {
    @Provides
    public HomeFragment get(){
        return new HomeFragment();
    }
    @Provides
    public ProfileFragment getProfileFragment(){return new ProfileFragment();}
    @Provides
    public LastRentsFragment getLastRentsFragment(){
        return new LastRentsFragment();
    }
    @Provides
    @ApplicationScope
    public LastRentsFragmentComponent getLastRentsFragmentComponent(ContextModule module){
        return  DaggerLastRentsFragmentComponent.builder().contextModule(module).build();
    }
    @Provides
    @ApplicationScope
    public ContextModule contextModule(@ApplicationContext Context context){
        return new ContextModule(context);
    }
    @Provides
    @ApplicationScope
    public HomeFragmentComponent getComponent(ContextModule module){
//        return DaggerHomeFragmentComponent.builder().contextModule(module).build();
        return DaggerHomeFragmentComponent.builder().contextModule(module).build();
//        return null;
    }
    @Provides
    @ApplicationScope
    public ProfileFragmentComponent getProfileComponent(ContextModule module){
        return DaggerProfileFragmentComponent.builder().contextModule(module).build();
//        return null;
    }
}
