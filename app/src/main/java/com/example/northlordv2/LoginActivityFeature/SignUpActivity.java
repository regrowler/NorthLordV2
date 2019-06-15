package com.example.northlordv2.LoginActivityFeature;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.northlordv2.Observables.EditTextObservable;
import com.example.northlordv2.R;
import com.example.northlordv2.application.DataModule;
import com.example.northlordv2.application.Northlord;
import com.example.northlordv2.inter.LoginFeature.Api;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SignUpActivity extends AppCompatActivity {

    @BindView(R.id.login_progress)
    ProgressBar loginProgress;
    @BindView(R.id.login)
    EditText login;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.confirmpassword)
    EditText confirmpassword;
    @BindView(R.id.sign_in_button)
    Button signInButton;
    @BindView(R.id.email_login_form)
    ConstraintLayout emailLoginForm;

    @Inject
    Api api;

    DataModule data;

    String log = "";
    String pass = "";
    String confirm = "";


    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        init();
        EditTextObservable.getObservable(login)
                .subscribe(s -> {
                    log = s;
                }, Throwable::printStackTrace);
        EditTextObservable.getObservable(password)
                .subscribe(s -> {
                    pass = s;
                }, Throwable::printStackTrace);
        EditTextObservable.getObservable(confirmpassword)
                .subscribe(s -> {
                    confirm = s;
                }, Throwable::printStackTrace);
    }

    void init() {
        Northlord.getApplication(this)
                .getLoginerComponent()
                .injectSignActivity(this);
        //Northlord.getApplication(this).getDataComponent().injectActiity(this);
        data=Northlord.getApplication(this).getData();
        int i=data.getId();
        int y = 0;
    }

    @SuppressLint("CheckResult")
    @OnClick(R.id.sign_in_button)
    public void onClick() {
        Log.d("sign", log + " " + pass + " " + confirm);
        showProgress(true);
        if (pass.equals(confirm)) {
            api
                    .reg(log, pass)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError(s -> {
                        showProgress(false);
                    })
                    .subscribe(s -> {
                        showProgress(false);
                        if (s.getRes().equals("success")) {
                            finish();
                        }
                    }, Throwable::printStackTrace);
        }

    }

    private void showProgress(final boolean show) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            emailLoginForm.setVisibility(show ? View.GONE : View.VISIBLE);
            emailLoginForm.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    emailLoginForm.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            loginProgress.setVisibility(show ? View.VISIBLE : View.GONE);
            loginProgress.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    loginProgress.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            loginProgress.setVisibility(show ? View.VISIBLE : View.GONE);
            emailLoginForm.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
}