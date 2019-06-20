package com.example.northlordv2.HomeFeature.CarFeature;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.northlordv2.HomeFeature.CarAddFeature.CarAddActivity;
import com.example.northlordv2.R;
import com.example.northlordv2.RentsFeature.AddRentActivity;
import com.example.northlordv2.RentsFeature.Rent;
import com.example.northlordv2.RentsFeature.Result;
import com.example.northlordv2.application.DataModule;
import com.example.northlordv2.application.Northlord;
import com.example.northlordv2.application.NumberPointMaker;
import com.example.northlordv2.inter.HomeFeature.CarApi;
import com.example.northlordv2.inter.RentsFeature.RentApi;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CarIndoActivity extends AppCompatActivity {

    @BindView(R.id.CarInfoLabel)
    TextView CarInfoLabel;
    @BindView(R.id.CarInfoModel)
    TextView CarInfoModel;
    @BindView(R.id.CarInfoC)
    TextView CarInfoC;
    @BindView(R.id.CarInfoCost)
    TextView CarInfoCost;
    @BindView(R.id.CarInfoR)
    TextView CarInfoR;
    @BindView(R.id.CarInfoRent)
    TextView CarInfoRent;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.list_rent)
    RecyclerView listRent;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipeContainer;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.fab2)
    FloatingActionButton fab2;
    @BindView(R.id.del)
    FloatingActionButton del;
    @BindView(R.id.deletebutton)
    View deletebutton;


    @Inject
    RentApi rentApi;
    @Inject
    CarApi carApi;
    @Inject
    CarRentDataAdapter adapter;
    Car car;
    DataModule data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_indo);
        ButterKnife.bind(this);
        Northlord.getApplication(this).getCarInfoActivityComponent().injectCarInfoActivity(this);
        initViews();
        data = Northlord.getApplication(this).getData();
    }

    @SuppressLint("CheckResult")
    void updateRents() {
        //adapter.init(new ArrayList<>(),getSupportFragmentManager());
        deletebutton.setVisibility(View.GONE);
        List<Rent> mas = new ArrayList<>();
        adapter.init(mas, getSupportFragmentManager());
        rentApi.getRents(data.getLogin(), data.getPassword(), car.getId() + "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(s -> {
                    s.printStackTrace();
                })
                .subscribe(s -> {
                    swipeContainer.setRefreshing(false);
                    s.decode();
                    s.decode();
                    if (s.getResult().equals("success")) {
                        Observable<Object> f = Observable.fromArray(s.getRents().toArray());
                        f.doOnError(p -> {
                                    p.printStackTrace();
                                })
                                .map(p -> {
                                    return new Rent((Result) p);
                                })
                                .doOnError(e -> {
                                    e.printStackTrace();
                                })
                                .subscribe(r -> {
                                    adapter.addRent(r);
                                });

                    }
                });

    }

    void initViews() {
        Intent i = getIntent();
        String ids = i.getStringExtra("id");
        String label = i.getStringExtra("label");
        String model = i.getStringExtra("model");
        String costs = i.getStringExtra("cost");
        String rents = i.getStringExtra("rent");
        int id = Integer.parseInt(ids);
        int cost = Integer.parseInt(costs);
        int rent = Integer.parseInt(rents);
        car = new Car(id,
                label,
                model,
                cost,
                rent);
        CarInfoLabel.setText(car.getLabel());
        CarInfoModel.setText(" " + car.getModel());
        CarInfoCost.setText("" + NumberPointMaker.makePoints(car.getCost()+""));
        CarInfoRent.setText("" + NumberPointMaker.makePoints(car.getRentcost()+""));
        listRent.setAdapter(adapter);
        adapter.setDeleteButton(deletebutton);
        swipeContainer.setOnRefreshListener(()->{
            updateRents();
        });
    }

    @OnClick({R.id.fab, R.id.fab2, R.id.del,R.id.deletebutton})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                Intent intent = new Intent(this, AddRentActivity.class);
                intent.putExtra("rentcost", car.getRentcost());
                intent.putExtra("carid", car.getId());
                startActivity(intent);
                break;
            case R.id.fab2:
                Intent intent1=new Intent(this, CarAddActivity.class);
                intent1.putExtra("id",car.getId()+"");
                intent1.putExtra("model",car.getModel());
                intent1.putExtra("rent",car.getRentcost());
                intent1.putExtra("cost",car.getCost());
                intent1.putExtra("label",car.getLabel());
                startActivityForResult(intent1,0);
                break;
            case R.id.del:
                JSONArray array=new JSONArray();
                android.app.AlertDialog.Builder builder=new android.app.AlertDialog.Builder(this);
                builder.setTitle(R.string.dialog_delete_car_title);
                builder.setMessage(R.string.dialog_delete_cars_message);
                CarIndoActivity activity=this;
                builder.setPositiveButton(R.string.dialog_delete_cars_yes, new DialogInterface.OnClickListener() {
                    @SuppressLint("CheckResult")
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        array.put(car.getId());
                        carApi.deleteCars(Northlord.getApplication(activity).getData().getLogin(),
                                Northlord.getApplication(activity).getData().getPassword(),
                                array.toString())
                                .subscribeOn(Schedulers.io())
                                .subscribeOn(AndroidSchedulers.mainThread())
                                .subscribe(s->{
                                    if (s.getRes().equals("success")) {
                                        finish();
                                    }
                                });
                    }
                });
                builder.setNegativeButton(R.string.dialog_delete_cars_no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create().show();
                break;
            case R.id.deletebutton:
                JSONArray array1=adapter.getSelectedRents();
                AlertDialog.Builder builder1=new AlertDialog.Builder(this);
                builder1.setTitle(R.string.dialog_delete_rents_title);
                builder1.setMessage(R.string.dialog_delete_rents_message);
                builder1.setPositiveButton(R.string.dialog_delete_rents_yes, new DialogInterface.OnClickListener() {
                    @SuppressLint("CheckResult")
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        rentApi.deleteRents(data.getLogin(), data.getPassword(),adapter.getSelectedRents().toString())
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .doOnError(s->s.printStackTrace())
                                .subscribe(p->{
                                    swipeContainer.setRefreshing(false);
                                    if (p.getResult().equals("success")) {
                                        updateRents();
                                    }
                                });
                    }
                });
                builder1.setNegativeButton(R.string.dialog_delete_rents_no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder1.create().show();
                updateRents();
                deletebutton.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateRents();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data!=null){
            car.setCost(data.getIntExtra("cost",-1));
            car.setRentcost(data.getIntExtra("rent",-1));
            car.setLabel(data.getStringExtra("label"));
            car.setModel(data.getStringExtra("model"));
            CarInfoLabel.setText(car.getLabel());
            CarInfoModel.setText(" " + car.getModel());
            CarInfoCost.setText("" + car.getCost());
            CarInfoRent.setText("" + car.getRentcost());
        }

    }
}
