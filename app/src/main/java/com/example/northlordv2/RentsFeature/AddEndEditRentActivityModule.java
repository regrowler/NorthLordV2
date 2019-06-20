package com.example.northlordv2.RentsFeature;

import android.content.Context;

import com.example.northlordv2.ContextModule;
import com.example.northlordv2.inter.ApplicationContext;
import com.example.northlordv2.inter.ApplicationScope;
import com.example.northlordv2.inter.RentsFeature.RentApi;

import dagger.Module;
import dagger.Provides;

@Module(includes = {ContextModule.class,RentApiModule.class})
public class AddEndEditRentActivityModule {
    @Provides
    public CalendarSetter getSetter(@ApplicationContext Context context){
        return new CalendarSetter(context);
    }
    @Provides
    @ApplicationScope
    public RentSender rentSender(RentApi api){
        return new RentSender(api);
    }
}
