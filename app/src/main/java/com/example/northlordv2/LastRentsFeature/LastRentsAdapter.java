package com.example.northlordv2.LastRentsFeature;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.northlordv2.HomeFeature.CarFeature.CarRentDataAdapter;
import com.example.northlordv2.R;
import com.example.northlordv2.RentsFeature.AddRentActivity;
import com.example.northlordv2.RentsFeature.LastRent;
import com.example.northlordv2.RentsFeature.Rent;
import com.example.northlordv2.application.NumberPointMaker;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class LastRentsAdapter extends RecyclerView.Adapter {
    private final LayoutInflater inflater;
    private final List<LastRent> values;
    private Context context;
    FragmentManager fragmentManager;
    View button;


    public LastRentsAdapter(Context context ) {
        this.inflater = LayoutInflater.from(context);
        this.context=context;
        values=new ArrayList<>();
    }
    public void init(List<LastRent> rents, FragmentManager fragmentManager){
        this.fragmentManager=fragmentManager;
        values.clear();
        values.addAll(rents);
        notifyDataSetChanged();
    }
    public void setDeleteButton(View view){
        button=view;
    }
    public void addRent(LastRent rent){
        values.add(rent);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_last_rent, viewGroup, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        final LastRent r = (LastRent) values.get(i);
//        final Card card = values.get(i);
        ViewHolder viewHolder1 = (ViewHolder) viewHolder;
        viewHolder1.name.setText(r.getName());
        viewHolder1.start.setText(r.getStart().get(Calendar.DAY_OF_MONTH) + "." + r.getStart().get(Calendar.MONTH) + "." + r.getStart().get(Calendar.YEAR) + " " + r.getStart().get(Calendar.HOUR_OF_DAY) + ":" + r.getStart().get(Calendar.MINUTE));
        viewHolder1.end.setText(r.getEnd().get(Calendar.DAY_OF_MONTH) + "." + r.getEnd().get(Calendar.MONTH) + "." + r.getEnd().get(Calendar.YEAR) + " " + r.getEnd().get(Calendar.HOUR_OF_DAY) + ":" + r.getEnd().get(Calendar.MINUTE));
        viewHolder1.cost.setText(NumberPointMaker.makePoints(r.getCost() + ""));
        viewHolder1.label.setText(r.getLabel());
        viewHolder1.model.setText(r.getModel());
        viewHolder1.check.setChecked(r.isChecked());
        viewHolder1.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r.setChecked(!r.isChecked());
                if(button!=null){
                    if(getSelected().size()>0){
                        button.setVisibility(View.VISIBLE);
                    }else button.setVisibility(View.GONE);
                }
            }
        });
        viewHolder1.gen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent args = new Intent(context,AddRentActivity.class);
                args.putExtra("name", r.getName());
                args.putExtra("id", r.getId());
                double re = r.getEnd().getTimeInMillis() - r.getStart().getTimeInMillis();
                re /= 1000;
                re /= 3600;
                re = r.getCost() / re;
                int rent = (int) Math.round(re);
                args.putExtra("rent", rent);
                args.putExtra("start",r.getStart().getTimeInMillis());
                args.putExtra("end",r.getEnd().getTimeInMillis());
                args.putExtra("carid",r.getCarid());
                args.putExtra("cost",r.getCost());
                context.startActivity(args);
//                DialogFragment dialogFragment = new UpdateRent();
//                dialogFragment.setArguments(args);
//                dialogFragment.show(fragmentManager, "d");
            }
        });
    }

    public List<Integer> getSelected() {
        List<Integer> selected = new ArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            if (values.get(i).isChecked()) {
                selected.add(values.get(i).getId());
            }
        }
        return selected;
    }

    public JSONArray getSelectedRents() {
        JSONArray selected = new JSONArray();
        for (int i = 0; i < values.size(); i++) {
            if (values.get(i).isChecked()) {
                JSONObject object = new JSONObject();
                try {
                    object.put("id", values.get(i).getId());
                    object.put("cost", ((LastRent) values.get(i)).getCost());
                    selected.put(object);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        return selected;
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        context = recyclerView.getContext();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final TextView name, start, end, cost,model,label;
        RelativeLayout gen;
        CheckBox check;
        ViewHolder(View view) {
            super(view);
            gen = view.findViewById(R.id.itemgen);
            check = view.findViewById(R.id.itemcheck);
            name = view.findViewById(R.id.rentitemName);
            start = view.findViewById(R.id.rentitemStartDate);
            end = view.findViewById(R.id.rentitemEndDate);
            cost = view.findViewById(R.id.rentitemCost);
            model=view.findViewById(R.id.rentitemModel);
            label=view.findViewById(R.id.rentitemLabel);
        }
    }
}
