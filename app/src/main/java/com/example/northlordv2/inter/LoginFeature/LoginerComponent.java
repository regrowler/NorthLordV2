package com.example.northlordv2.inter.LoginFeature;

import com.example.northlordv2.LoginActivityFeature.LoginActivity;
import com.example.northlordv2.LoginActivityFeature.LoginerModule;
import com.example.northlordv2.LoginActivityFeature.SignUpActivity;
import com.example.northlordv2.inter.ApplicationContext;
import com.example.northlordv2.inter.ApplicationScope;

import dagger.Component;

@ApplicationScope
@Component(modules  = {LoginerModule.class})
public interface LoginerComponent {
    Api getApi();
    void injectLoginActivity(LoginActivity activity);
    void injectSignActivity(SignUpActivity activity);
}
