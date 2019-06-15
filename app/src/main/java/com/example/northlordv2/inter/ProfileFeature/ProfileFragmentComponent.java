package com.example.northlordv2.inter.ProfileFeature;

import com.example.northlordv2.ProfileFeature.EditProfileActivity;
import com.example.northlordv2.ProfileFeature.PicassoModule;
import com.example.northlordv2.ProfileFeature.ProfileFeatureModule;
import com.example.northlordv2.ProfileFeature.ProfileFragment;
import com.example.northlordv2.inter.ApplicationScope;

import dagger.Component;

@Component(modules = { ProfileFeatureModule.class})
@ApplicationScope
public interface ProfileFragmentComponent {
    void injectProfileFragment(ProfileFragment fragment);
    void injectEditProfileActivity(EditProfileActivity activity);
}
