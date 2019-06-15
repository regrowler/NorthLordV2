package com.example.northlordv2.HomeFeature;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.northlordv2.HomeFeature.CarAddFeature.CarAddActivity;
import com.example.northlordv2.HomeFeature.CarFeature.Car;
import com.example.northlordv2.R;
import com.example.northlordv2.application.Northlord;
import com.example.northlordv2.inter.HomeFeature.CarApi;

import org.json.JSONArray;

import java.net.URLEncoder;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.list_item)
    RecyclerView listItem;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipeContainer;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.deletebutton)
    ImageButton deletebutton;
    Unbinder unbinder;

    @Inject
    CarApi api;
    @Inject
    HomeAdapter adapter;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @SuppressLint("CheckResult")
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Northlord.getApplication(getActivity()).getMainActivityComponent().getHomeFragmentComponent().injectHomeFragment(this);
        swipeContainer.setOnRefreshListener(this);
        swipeContainer.setRefreshing(true);
        deletebutton.setVisibility(View.GONE);
//        update();

    }

    @Override
    public void onRefresh() {
        update();
    }

    @SuppressLint("CheckResult")
    void update() {
        try {
            String l = Northlord.getApplication(getActivity()).getData().getLogin();
            String p = Northlord.getApplication(getActivity()).getData().getPassword();
            String log = URLEncoder.encode(l, "UTF-8");
            String pass = URLEncoder.encode(p, "UTF-8");

            api
                    .getCars(log, pass)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError(s -> {
                        swipeContainer.setRefreshing(false);
                    })
                    .subscribe(s -> {
                        if (s.getRes().equals("success")) {
                            swipeContainer.setRefreshing(false);
                            adapter.setData(s.getCars(),deletebutton);
                            listItem.setAdapter(adapter);
                        }
                        s.getRes();
                    }, Throwable::printStackTrace);
        } catch (Exception e) {
            swipeContainer.setRefreshing(false);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        update();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @SuppressLint("CheckResult")
    @OnClick({R.id.deletebutton, R.id.fab})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.deletebutton:
                deletebutton.setVisibility(View.GONE);
                List<Car> list=adapter.getChecked();
                try {
                    String l = Northlord.getApplication(getActivity()).getData().getLogin();
                    String p = Northlord.getApplication(getActivity()).getData().getPassword();
                    String log = URLEncoder.encode(l, "UTF-8");
                    String pass = URLEncoder.encode(p, "UTF-8");
                    JSONArray array=new JSONArray();
                    for(Car car:list){
                        array.put(car.getId());
                    }
                    api
                            .deleteCars(log,pass,array.toString())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnError(s -> {
                                swipeContainer.setRefreshing(false);
                            })
                            .subscribe(s -> {
                                if (s.getRes().equals("success")) {
                                    update();

                                }
                                s.getRes();
                            }, Throwable::printStackTrace);
                } catch (Exception e) {
                    swipeContainer.setRefreshing(false);
                }
                break;
            case R.id.fab:
                getActivity().startActivity(new Intent(getContext(), CarAddActivity.class));
                break;
        }
    }
}
