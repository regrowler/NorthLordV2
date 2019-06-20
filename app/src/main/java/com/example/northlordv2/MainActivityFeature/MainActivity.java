package com.example.northlordv2.MainActivityFeature;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.example.northlordv2.HomeFeature.HomeFragment;
import com.example.northlordv2.LastRentsFeature.LastRentsFragment;
import com.example.northlordv2.ProfileFeature.ProfileFragment;
import com.example.northlordv2.R;
import com.example.northlordv2.application.Northlord;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.main_frame)
    FrameLayout mainFrame;
    @BindView(R.id.nav_view)
    BottomNavigationView navView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    HomeFragment homeFragment;
    @Inject
    ProfileFragment profileFragment;
    @Inject
    LastRentsFragment lastRentsFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
        setSupportActionBar(toolbar);
        showFragment(R.id.navigation_home);
        navView.setSelectedItemId(R.id.navigation_home);
        //ButterKnife.bind(this);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(s -> {
            showFragment(s.getItemId());
            return true;
        });
    }

    void init() {
        Northlord.getApplication(this).getMainActivityComponent().injectMainActivity(this);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data!=null){
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

    @Override
    public void onBackPressed() {

    }

    public void showFragment(int itemId) {
        Fragment fragment = null;
        switch (itemId) {
            case R.id.navigation_last_rents:
                fragment=lastRentsFragment;
                break;
            case R.id.navigation_home:
                fragment = homeFragment;
                break;
            case R.id.navigation_profile:
                fragment=profileFragment;
                break;

        }
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_frame, fragment);
            fragmentTransaction.commit();
        }

    }

}
