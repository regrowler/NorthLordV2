package com.example.northlordv2.RentsFeature;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.northlordv2.Observables.EditTextObservable;
import com.example.northlordv2.R;
import com.example.northlordv2.application.Northlord;
import com.example.northlordv2.inter.RentsFeature.RentApi;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddRentActivity extends AppCompatActivity {

    @BindView(R.id.AddRentName)
    EditText AddRentName;
    @BindView(R.id.AddRentStartDate)
    EditText AddRentStartDate;
    @BindView(R.id.AddRentEndDate)
    EditText AddRentEndDate;
    @BindView(R.id.AddRentCost)
    EditText AddRentCost;
    @BindView(R.id.AddRentYes)
    Button AddRentYes;
    @BindView(R.id.AddRentNo)
    Button AddRentNo;

    @Inject
    CalendarSetter calendarSetter;
    @Inject
    RentSender sender;

    Rent rent;
    int id = 0;
    int r = 0;
    long start=0;
    long end=0;
    boolean mode=false;//false=add true=edit
    int cost=0;


    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rent);
        Northlord.getApplication(this).getAddAndEditRentActivityComponent().injectAddRentActivity(this);
        ButterKnife.bind(this);
        rent = new Rent();
        Intent intent = getIntent();
        id = intent.getIntExtra("carid", 0);
        r = intent.getIntExtra("rentcost", 0);

        start=intent.getLongExtra("start",-1);
        if(start>0){
            rent.getStart().setTimeInMillis(start);
            end=intent.getLongExtra("end",-1);
            cost=intent.getIntExtra("cost",0);
            rent.cost=cost;
            AddRentCost.setText(cost+"");
            AddRentName.setText(intent.getStringExtra("name"));
            GregorianCalendar calendar=rent.start;
            AddRentStartDate.setText(calendar.get(Calendar.DAY_OF_MONTH) + "." + calendar.get(Calendar.MONTH) + "." + calendar.get(Calendar.YEAR) + " " + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE));
            calendar=rent.end;
            AddRentEndDate.setText(calendar.get(Calendar.DAY_OF_MONTH) + "." + calendar.get(Calendar.MONTH) + "." + calendar.get(Calendar.YEAR) + " " + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE));
            rent.setId(intent.getIntExtra("id",0));
            mode=true;
        }

        EditTextObservable.getObservable(AddRentName)
                .subscribe(s -> {
                    rent.name = s;
                });
        EditTextObservable.getObservableWithSpaces(AddRentCost).subscribe();
    }

    @OnClick({R.id.AddRentStartDate, R.id.AddRentEndDate, R.id.AddRentYes, R.id.AddRentNo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.AddRentStartDate:
                calendarSetter.setstart(this, rent.start, new CalendarSetter.CalenderSetListener() {
                    @Override
                    public void action(GregorianCalendar calendar, String date) {
                        AddRentStartDate.setText(date);
                        updateCost();
                    }
                });
                break;
            case R.id.AddRentEndDate:
                calendarSetter.setstart(this, rent.end, new CalendarSetter.CalenderSetListener() {
                    @Override
                    public void action(GregorianCalendar calendar, String date) {
                        AddRentEndDate.setText(date);
                        updateCost();
                    }
                });
                break;
            case R.id.AddRentYes:
                if(mode){
                    sender.updateRent(Northlord.getApplication(this).getData().getLogin(),
                            Northlord.getApplication(this).getData().getPassword(),
                            rent,rent.id,
                            new RentSender.Listener() {
                                @Override
                                public void action() {
                                    finish();
                                }
                            });
                }else {
                    sender.sendRent(Northlord.getApplication(this).getData().getLogin(),
                            Northlord.getApplication(this).getData().getPassword(),
                            rent,id,
                            new RentSender.Listener() {
                                @Override
                                public void action() {
                                    finish();
                                }
                            });
                }

                break;
            case R.id.AddRentNo:
                finish();
                break;
        }
    }

    void updateCost() {
        if (rent.end.after(rent.start)) {
            int c = 0;
//            int r = 50;
            c = (int) (r * (rent.end.getTimeInMillis() - rent.start.getTimeInMillis()) / 3600 / 1000);
            AddRentCost.setText(c + "");
            rent.cost = c;
        }
    }

}
