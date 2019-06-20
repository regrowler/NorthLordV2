package com.example.northlordv2.ProfileFeature;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.northlordv2.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ChangePictureDialog extends DialogFragment {
    @BindView(R.id.dialogChangePictureTakePhotoButton)
    TextView dialogChangePictureTakePhotoButton;
    @BindView(R.id.dialogChangePictureGetFromGalleryButton)
    TextView dialogChangePictureGetFromGalleryButton;
    Unbinder unbinder;

    Listener takePhotoListener;
    Listener getFromGalleryListener;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.dialog_change_pic, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void setListeners(Listener takePhotoListener, Listener getFromGalleryListener) {
        this.takePhotoListener = takePhotoListener;
        this.getFromGalleryListener = getFromGalleryListener;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.dialogChangePictureTakePhotoButton, R.id.dialogChangePictureGetFromGalleryButton})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dialogChangePictureTakePhotoButton:
                if (takePhotoListener != null) {
                    takePhotoListener.onClick(view);
                    dismiss();
                }
                break;
            case R.id.dialogChangePictureGetFromGalleryButton:
                if(getFromGalleryListener!=null){
                    getFromGalleryListener.onClick(view);
                    dismiss();
                }
                break;
        }
    }

    public abstract static class Listener {
        public abstract void onClick(View view);
    }
}
