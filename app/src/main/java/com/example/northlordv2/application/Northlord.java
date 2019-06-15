package com.example.northlordv2.application;

import android.app.Activity;
import android.app.Application;
import android.support.multidex.MultiDexApplication;

import com.example.northlordv2.ContextModule;
import com.example.northlordv2.inter.DaggerMainActivityComponent;
import com.example.northlordv2.inter.HomeFeature.CarAddActivityComponent;
import com.example.northlordv2.inter.HomeFeature.CarInfoActivityComponent;
import com.example.northlordv2.inter.HomeFeature.DaggerCarAddActivityComponent;
import com.example.northlordv2.inter.HomeFeature.DaggerCarInfoActivityComponent;
import com.example.northlordv2.inter.HomeFeature.DaggerHomeFragmentComponent;
import com.example.northlordv2.inter.HomeFeature.HomeFragmentComponent;
import com.example.northlordv2.inter.LoginFeature.DaggerLoginerComponent;
import com.example.northlordv2.inter.LoginFeature.LoginerComponent;
import com.example.northlordv2.inter.MainActivityComponent;
//import com.example.northlordv2.inter.ProfileFeature.DaggerEditProfileActivityComponent;
import com.example.northlordv2.inter.ProfileFeature.DaggerProfileFragmentComponent;
import com.example.northlordv2.inter.ProfileFeature.EditProfileActivityComponent;

import io.reactivex.plugins.RxJavaPlugins;

public class Northlord extends MultiDexApplication {
    private LoginerComponent loginerComponent;
    private MainActivityComponent mainActivityComponent;
    private EditProfileActivityComponent editProfileActivityComponent;
    private CarAddActivityComponent carAddActivityComponent;
    private CarInfoActivityComponent carInfoActivityComponent;

    public CarInfoActivityComponent getCarInfoActivityComponent() {
        return carInfoActivityComponent;
    }
    public void setCarInfoActivityComponent(CarInfoActivityComponent carInfoActivityComponent) {
        this.carInfoActivityComponent = carInfoActivityComponent;
    }
    public EditProfileActivityComponent getEditProfileActivityComponent() {
        return editProfileActivityComponent;
    }
    public void setEditProfileActivityComponent(EditProfileActivityComponent editProfileActivityComponent) {
        this.editProfileActivityComponent = editProfileActivityComponent;
    }
    public CarAddActivityComponent getCarAddActivityComponent() {
        return carAddActivityComponent;
    }
    public void setCarAddActivityComponent(CarAddActivityComponent carAddActivityComponent) {
        this.carAddActivityComponent = carAddActivityComponent;
    }
    public MainActivityComponent getMainActivityComponent() {
        return mainActivityComponent;
    }
    public DataModule getData() {
        return data;
    }

    public void setDataComponent(DataModule module) {
        this.data = new DataModule(0,"","");
    }
    public void setDataModule(DataModule module){
        data.setData(module);
    }
    private DataModule data;
    @Override
    public void onCreate() {
        super.onCreate();
        RxJavaPlugins.setErrorHandler(throwable -> {throwable.printStackTrace();});
        ContextModule module=new ContextModule(this);
        setDataComponent(new DataModule(0,"",""));
        loginerComponent = DaggerLoginerComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
//        HomeFragmentComponent component=DaggerHomeFragmentComponent.
        mainActivityComponent= DaggerMainActivityComponent.builder()
//                .homeFragmentComponent(DaggerHomeFragmentComponent.builder().contextModule(module).build())
//                .profileFragmentComponent(DaggerProfileFragmentComponent.builder().contextModule(module).build())
                .contextModule(new ContextModule(this))
                .build();
        carAddActivityComponent= DaggerCarAddActivityComponent.builder()
                .contextModule(module)
                .build();
        carInfoActivityComponent= DaggerCarInfoActivityComponent.builder()
                .contextModule(module)
                .build();
//        editProfileActivityComponent= DaggerEditProfileActivityComponent.builder().contextModule(new ContextModule(this)).build();
    }
    public static Northlord getApplication(Activity activity){
        return (Northlord) activity.getApplication();
    }
    public LoginerComponent getLoginerComponent(){
        return loginerComponent;
    }

}
