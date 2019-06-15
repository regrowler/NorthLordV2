package com.example.northlordv2.inter.HomeFeature;

import com.example.northlordv2.HomeFeature.CarFeature.CarApiModule;
import com.example.northlordv2.HomeFeature.CarFeature.CarIndoActivity;
import com.example.northlordv2.HomeFeature.CarFeature.CarInfoModule;
import com.example.northlordv2.RentsFeature.RentApiModule;
import com.example.northlordv2.inter.ApplicationScope;

import dagger.Component;

@Component(modules = {CarInfoModule.class, CarApiModule.class, RentApiModule.class})
@ApplicationScope
public interface CarInfoActivityComponent {
    void injectCarInfoActivity(CarIndoActivity activity);
}
