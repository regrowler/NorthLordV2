package com.example.northlordv2.inter.ProfileFeature;

import com.example.northlordv2.ProfileFeature.EditProfileActivity;
import com.example.northlordv2.ProfileFeature.ProfileFeatureModule;

import dagger.Component;

//@Component(modules = ProfileFeatureModule.class)
public interface EditProfileActivityComponent {
    void injectEditProfileActivity(EditProfileActivity activity);
}
