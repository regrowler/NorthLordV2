package com.example.northlordv2.inter.RentsFeature;

import com.example.northlordv2.RentsFeature.AddEndEditRentActivityModule;
import com.example.northlordv2.RentsFeature.AddRentActivity;
import com.example.northlordv2.RentsFeature.RentApiModule;
import com.example.northlordv2.inter.ApplicationScope;

import dagger.Component;

@Component(modules = {AddEndEditRentActivityModule.class})
@ApplicationScope
public interface AddAndEditRentActivityComponent {
    void injectAddRentActivity(AddRentActivity addRentActivity);
}
