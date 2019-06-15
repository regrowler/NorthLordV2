package com.example.northlordv2.HomeFeature.CarFeature;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.northlordv2.R;
import com.example.northlordv2.application.Northlord;
import com.example.northlordv2.inter.HomeFeature.CarApi;
import com.example.northlordv2.inter.RentsFeature.RentApi;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    @Inject
    RentApi rentApi;
    @Inject
    CarApi carApi;
    Car car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_indo);
        ButterKnife.bind(this);
        initViews();
        Northlord.getApplication(this).getCarInfoActivityComponent().injectCarInfoActivity(this);

    }
    void initViews (){
        Intent i=getIntent();
        String ids=i.getStringExtra("id");
        String label=i.getStringExtra("label");
        String model=i.getStringExtra("model");
        String costs=i.getStringExtra("cost");
        String rents=i.getStringExtra("rent");
        int id=Integer.parseInt(ids);
        int cost=Integer.parseInt(costs);
        int rent=Integer.parseInt(rents);
        car=new Car(id,
                label,
                model,
                cost,
                rent);
        CarInfoLabel.setText(car.getLabel());
        CarInfoModel.setText(" "+car.getModel());
        CarInfoCost.setText(""+car.getCost());
        CarInfoRent.setText(""+car.getRentcost());
    }
    @OnClick({R.id.fab, R.id.fab2, R.id.del})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                break;
            case R.id.fab2:
                break;
            case R.id.del:
                break;
        }
    }
}
