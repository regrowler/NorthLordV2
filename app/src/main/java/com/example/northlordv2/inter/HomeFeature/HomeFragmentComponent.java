package com.example.northlordv2.inter.HomeFeature;

import com.example.northlordv2.HomeFeature.CarFeature.CarApiModule;
import com.example.northlordv2.HomeFeature.HomeFragment;
import com.example.northlordv2.HomeFeature.HomeFragmentModule;
import com.example.northlordv2.inter.ApplicationScope;

import dagger.Component;

@Component(modules = {HomeFragmentModule.class, CarApiModule.class})
@ApplicationScope
public interface HomeFragmentComponent {
    void injectHomeFragment(HomeFragment fragment);
}
