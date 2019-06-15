package com.example.northlordv2.ProfileFeature.ChangePictureFeature;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;

import com.example.northlordv2.application.Northlord;
import com.example.northlordv2.inter.ProfileFeature.PictureApi;

import java.io.ByteArrayOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PictureSender {
    PictureApi pictureApi;
    PictureChanger changer;

    public PictureSender(PictureApi pictureApi, PictureChanger changer) {
        this.pictureApi = pictureApi;
        this.changer = changer;
    }

    @SuppressLint("CheckResult")
    public void sendPicture(Bitmap imageBitmap, CircleImageView imageView,int id) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        pictureApi.send(Base64.encodeToString(byteArray, Base64.DEFAULT), id + "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(s -> {
                    s.printStackTrace();
                })
                .subscribe(s -> {
                    Log.d("send", "succ");
                    changer.loadImageWithoutCache(imageView,id);
                }, Throwable::printStackTrace);
    }
}
