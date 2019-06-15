package com.example.northlordv2.ProfileFeature.ChangePictureFeature;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.example.northlordv2.InitialData;
import com.example.northlordv2.R;
import com.example.northlordv2.application.Northlord;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


public class PictureChanger {
    Context context;
    Picasso picasso;
    public PictureChanger(Context context, Picasso picasso) {
        this.context = context;
        this.picasso = picasso;
    }
    public void loadImageWithCache(CircleImageView imageView, int id){
        StringBuilder builder = new StringBuilder(InitialData.url);
        builder.append("/pic?id=");
        builder.append(id);
        picasso.with(context)
                .load(builder.toString())
                .error(R.drawable.ic_launcher_foreground)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        Log.d("pic","succ");
                    }

                    @Override
                    public void onError() {
                        Log.d("pic","err");
                    }
                });
    }
    public void loadImageWithoutCache(CircleImageView imageView,int id){
        StringBuilder builder = new StringBuilder(InitialData.url);
        builder.append("/pic?id=");
        builder.append(id);
        picasso.with(context)
                .load(builder.toString())
                .error(R.drawable.ic_launcher_foreground)
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        Log.d("pic","succ");
                    }

                    @Override
                    public void onError() {
                        Log.d("pic","err");
                    }
                });
    }

}
