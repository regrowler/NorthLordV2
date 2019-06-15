package com.example.northlordv2.ProfileFeature;

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
import com.example.northlordv2.inter.ProfileFeature.ProfileApi;

import java.net.URLEncoder;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class EditProfileActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.EditProfileName)
    EditText editProfileName;
    @BindView(R.id.EditProfileSurname)
    EditText editProfileSurname;
    @BindView(R.id.EditProfileEmail)
    EditText editProfileEmail;
    @BindView(R.id.EditProfileYes)
    Button EditProfileYes;
    @BindView(R.id.EditProfileNo)
    Button EditProfileNo;

    @Inject
    ProfileApi api;

    String name="";
    String surname="";
    String email="";

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Northlord.getApplication(this).getMainActivityComponent().getProfileFragmentComponent().injectEditProfileActivity(this);
        EditTextObservable.getObservable(editProfileEmail)
                .subscribe(s->{email= URLEncoder.encode(s,"UTF-8");
                });
        EditTextObservable.getObservable(editProfileName)
                .subscribe(s->{name= URLEncoder.encode(s,"UTF-8");
                });
        EditTextObservable.getObservable(editProfileSurname)
                .subscribe(s->{surname= URLEncoder.encode(s,"UTF-8");
                });

    }

    @SuppressLint("CheckResult")
    @OnClick({R.id.EditProfileYes, R.id.EditProfileNo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.EditProfileYes:
                try {
                    String l = Northlord.getApplication(this).getData().getLogin();
                    String p = Northlord.getApplication(this).getData().getPassword();
                    String log = URLEncoder.encode(l, "UTF-8");
                    String pass = URLEncoder.encode(p, "UTF-8");
                    api
                            .updateProfile(log, pass,name,surname,email)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnError(s -> {
                                s.printStackTrace();
                            })
                            .subscribe(s -> {
                                if (s.getResult().equals("success")) {
                                    finish();
                                }
                                //s.getRes();
                            }, Throwable::printStackTrace);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.EditProfileNo:
                finish();
                break;
        }
    }
}
