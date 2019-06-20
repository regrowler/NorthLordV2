package com.example.northlordv2.LastRentsFeature;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.northlordv2.R;
import com.example.northlordv2.RentsFeature.LastRent;
import com.example.northlordv2.RentsFeature.Rent;
import com.example.northlordv2.RentsFeature.Result;
import com.example.northlordv2.application.DataModule;
import com.example.northlordv2.application.Northlord;
import com.example.northlordv2.inter.RentsFeature.RentApi;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LastRentsFragment extends Fragment {
    @BindView(R.id.list_rent)
    RecyclerView listRent;
    @BindView(R.id.deletebutton)
    ImageButton deletebutton;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipeContainer;
    Unbinder unbinder;

    @Inject
    LastRentsAdapter adapter;
    @Inject
    RentApi rentApi;

    DataModule data;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_last_rents, container, false);
        unbinder = ButterKnife.bind(this, view);
        Northlord.getApplication(getActivity()).getMainActivityComponent().getLastRentsComponent().injectLastRentsFragment(this);
        return view;
    }

    @SuppressLint("CheckResult")
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        data=Northlord.getApplication(getActivity()).getData();
        updateRents();
        swipeContainer.setOnRefreshListener(()->{updateRents();});
        listRent.setAdapter(adapter);
        adapter.setDeleteButton(deletebutton);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @SuppressLint("CheckResult")
    @OnClick(R.id.deletebutton)
    public void onClick() {
        JSONArray array1=adapter.getSelectedRents();
        String a=array1.toString();
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setTitle(R.string.dialog_delete_rents_title);
        builder.setMessage(R.string.dialog_delete_rents_message);
        builder.setPositiveButton(R.string.dialog_delete_rents_yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                rentApi.deleteRents(data.getLogin(), data.getPassword(),a)
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
        builder.setNegativeButton(R.string.dialog_delete_rents_no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
        updateRents();
        deletebutton.setVisibility(View.GONE);
    }
    @SuppressLint("CheckResult")
    void updateRents() {
        //adapter.init(new ArrayList<>(),getSupportFragmentManager());
        deletebutton.setVisibility(View.GONE);
        List<LastRent> mas = new ArrayList<>();
        adapter.init(mas, getFragmentManager());
        rentApi.getLastRents(data.getLogin(), data.getPassword())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(s -> {
                    s.printStackTrace();
                })
                .subscribe(s -> {
                    s.decode();
                    s.decode();
                    swipeContainer.setRefreshing(false);
                    if (s.getResult().equals("success")) {
                        Observable<Object> f = Observable.fromArray(s.getRents().toArray());
                        f.doOnError(p -> {
                            p.printStackTrace();
                        })
                                .map(p -> {
                                    return new LastRent((Result) p);
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
}
