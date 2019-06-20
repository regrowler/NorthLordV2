package com.example.northlordv2.inter;

import com.example.northlordv2.ContextModule;
import com.example.northlordv2.HomeFeature.HomeFragment;
import com.example.northlordv2.LastRentsFeature.LastRentsFragment;
import com.example.northlordv2.MainActivityFeature.MainActivity;
import com.example.northlordv2.MainActivityFeature.MainActivityModule;
import com.example.northlordv2.OkHttpClientModule;
import com.example.northlordv2.ProfileFeature.ProfileFragment;
import com.example.northlordv2.inter.HomeFeature.HomeFragmentComponent;
import com.example.northlordv2.inter.ProfileFeature.ProfileFragmentComponent;
import com.example.northlordv2.inter.RentsFeature.LastRentsFragmentComponent;

import javax.inject.Inject;

import dagger.Component;

@ApplicationScope
@Component(dependencies = {},modules = {MainActivityModule.class})
public interface MainActivityComponent {
    void injectMainActivity(MainActivity activity);
    HomeFragment getHomeFragment();
    HomeFragmentComponent getHomeFragmentComponent();
    ProfileFragment getProfileFragment();
    ProfileFragmentComponent getProfileFragmentComponent();
    LastRentsFragment getLastRentsFragment();
    LastRentsFragmentComponent getLastRentsComponent();
}
