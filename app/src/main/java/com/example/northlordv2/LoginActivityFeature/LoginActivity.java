package com.example.northlordv2.LoginActivityFeature;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.example.northlordv2.MainActivityFeature.MainActivity;
import com.example.northlordv2.Observables.EditTextObservable;
import com.example.northlordv2.R;
import com.example.northlordv2.application.Northlord;
import com.example.northlordv2.inter.LoginFeature.Api;
import com.example.northlordv2.application.DataModule;

import java.net.URLEncoder;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {

    @Inject
    Api api;

    @BindView(R.id.login_progress)
    ProgressBar loginProgress;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.email_sign_in_button)
    Button emailSignInButton;
    @BindView(R.id.signer)
    TextView signer;
    @BindView(R.id.email_login_form)
    ConstraintLayout emailLoginForm;

    String log = "";
    String pass = "";


    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        init();
        EditTextObservable
                .getObservable(email)
                .subscribe(s -> {
                    log = URLEncoder.encode(s, "UTF-8");
                });
        EditTextObservable
                .getObservable(password)
                .subscribe(s -> {
                    pass = URLEncoder.encode(s, "UTF-8");
                });

    }

    void init() {
        Northlord.getApplication(this)
                .getLoginerComponent()
                .injectLoginActivity(this);
    }

    @SuppressLint("CheckResult")
    @OnClick(R.id.email_sign_in_button)
    public void onClick() {
        showProgress(true);
        Log.d("log", log);
        Log.d("pas", pass);
        api
                .login(log, pass)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(s -> {
                    showProgress(false);
                })

                .subscribe(s -> {
                    if (s.getRes().equals("success")) {
                        showProgress(false);
                        Northlord.getApplication(this).setDataModule(new DataModule(s.getId(), s.getLog(), s.getPas()));
                        startActivity(new Intent(this, MainActivity.class));
                        finish();
//                        Snackbar.make(emailLoginForm, "good", Snackbar.LENGTH_LONG)
//                                .setAction("Action", null).show();
                    }
                    showProgress(false);
                }, Throwable::printStackTrace);

    }

    @OnClick(R.id.signer)
    public void onClicksign() {
        startActivity(new Intent(this, SignUpActivity.class));
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)

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
