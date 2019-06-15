package com.example.northlordv2.HomeFeature.CarAddFeature;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.northlordv2.Observables.EditTextObservable;
import com.example.northlordv2.R;
import com.example.northlordv2.application.Northlord;
import com.example.northlordv2.inter.HomeFeature.CarApi;

import java.net.URLEncoder;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CarAddActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.AddCarLabel)
    EditText AddCarLabel;
    @BindView(R.id.AddCarModel)
    EditText AddCarModel;
    @BindView(R.id.AddCarCost)
    EditText AddCarCost;
    @BindView(R.id.AddCarRent)
    EditText AddCarRent;
    @BindView(R.id.AddCarYes)
    Button AddCarYes;
    @BindView(R.id.AddCarNo)
    Button AddCarNo;

    @Inject
    CarApi api;

    String label = "";
    String model = "";
    String cost = "";
    String rent = "";

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_add);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        Northlord.getApplication(this).getCarAddActivityComponent().injectCarAddActivity(this);
        EditTextObservable.getObservable(AddCarLabel)
                .subscribe(s -> {
                    label = URLEncoder.encode(s, "UTF-8");
                });
        EditTextObservable.getObservable(AddCarModel)
                .subscribe(s -> {
                    model = URLEncoder.encode(s, "UTF-8");
                });
        EditTextObservable.getObservable(AddCarCost)
                .subscribe(s -> {
                    cost = URLEncoder.encode(s, "UTF-8");
                });
        EditTextObservable.getObservable(AddCarRent)
                .subscribe(s -> {
                    rent = URLEncoder.encode(s, "UTF-8");
                });
    }

    @SuppressLint("CheckResult")
    @OnClick({R.id.AddCarYes, R.id.AddCarNo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.AddCarYes:
                try{
                    String l = Northlord.getApplication(this).getData().getLogin();
                    String p = Northlord.getApplication(this).getData().getPassword();
                    String log = URLEncoder.encode(l, "UTF-8");
                    String pass = URLEncoder.encode(p, "UTF-8");
                    api.putCar(log,pass,label,model,cost,rent)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnError(s->s.printStackTrace())
                            .subscribe(s->{
                                if(s.getRes().equals("success")){
                                    finish();
                                }
                            });
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case R.id.AddCarNo:
                finish();
                break;
        }
    }
}
