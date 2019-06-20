package com.example.northlordv2.inter.RentsFeature;

import com.example.northlordv2.LastRentsFeature.LastRentsFragment;
import com.example.northlordv2.LastRentsFeature.LastRentsFragmentModule;
import com.example.northlordv2.RentsFeature.RentApiModule;
import com.example.northlordv2.inter.ApplicationScope;

import dagger.Component;

@Component(modules = {LastRentsFragmentModule.class, RentApiModule.class})
@ApplicationScope
public interface LastRentsFragmentComponent {
    void injectLastRentsFragment(LastRentsFragment fragment);
}
