package com.example.northlordv2.inter.HomeFeature;

import com.example.northlordv2.HomeFeature.CarAddFeature.CarAddActivity;
import com.example.northlordv2.HomeFeature.CarFeature.CarApiModule;
import com.example.northlordv2.inter.ApplicationScope;

import dagger.Component;

@Component(modules = CarApiModule.class)
@ApplicationScope
public interface CarAddActivityComponent {
    void injectCarAddActivity(CarAddActivity addActivity);
}
