package com.example.northlordv2.HomeFeature;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.northlordv2.HomeFeature.CarFeature.Car;
import com.example.northlordv2.HomeFeature.CarFeature.CarIndoActivity;
import com.example.northlordv2.R;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter {
    private final LayoutInflater inflater;
    private final List<Car> values;
    private Context context;
    ImageButton button=null;

    public HomeAdapter(Context context) {
        this.values = new ArrayList<>();
        this.inflater = LayoutInflater.from(context);

    }
    public void setData(List<Car> mas,ImageButton imageButton){
        values.clear();
        values.addAll(mas);
        notifyDataSetChanged();
        button=imageButton;

    }
    @NonNull
    @Override
    public HomeAdapter.ViewH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_home_list, viewGroup, false);
        return new ViewH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        final Car card = values.get(i);
        final Car car=(Car)card;
        ViewH viewHolder1 =(ViewH)viewHolder;
        viewHolder1.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                card.setChecked(!card.isChecked());
//                card.checked=!card.checked;
                if(button!=null){
                    if(getChecked().size()>0){
                        button.setVisibility(View.VISIBLE);
                    }else button.setVisibility(View.GONE);
                }
            }
        });
        viewHolder1.gen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(context, CarIndoActivity.class);
                i.putExtra("label",car.getLabel());
                i.putExtra("model",car.getModel());
                i.putExtra("cost",car.getCost()+"");
                i.putExtra("rent",car.getRentcost()+"");
                i.putExtra("id",car.getId()+"");

                context.startActivity(i);
            }
        });
        viewHolder1.label.setText(car.getLabel());
        viewHolder1.model.setText(" "+car.getModel());
        viewHolder1.rent.setText(""+car.getRentcost());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        context = recyclerView.getContext();
    }
    public List<Car> getChecked(){
        ArrayList<Car>list=new ArrayList<>();
        for (Car car:values) {
            if(car.isChecked()){
                list.add(car);
            }
        }
        return list;
    }
    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ViewH extends RecyclerView.ViewHolder {

        final TextView label, model, rent;
        final RelativeLayout gen;
        final CheckBox check;

        ViewH(View view) {
            super(view);
            gen=view.findViewById(R.id.itemgeneral);
            check = view.findViewById(R.id.itemcheck);
            label = view.findViewById(R.id.itemlabel);
            model = view.findViewById(R.id.itemmodel);
            rent = view.findViewById(R.id.itemrent);
        }
    }
    public List<Integer> getSelected(){
        List<Integer> selected=new ArrayList<>();
        for(int i=0;i<values.size();i++){
            if(values.get(i).isChecked()){
                selected.add(values.get(i).getId());
            }
        }
        return selected;
    }
}
