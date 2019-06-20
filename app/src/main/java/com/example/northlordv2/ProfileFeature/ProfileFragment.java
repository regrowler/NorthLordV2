package com.example.northlordv2.ProfileFeature;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.northlordv2.InitialData;
import com.example.northlordv2.ProfileFeature.ChangePictureFeature.PictureChanger;
import com.example.northlordv2.ProfileFeature.ChangePictureFeature.PictureSender;
import com.example.northlordv2.R;
import com.example.northlordv2.application.Northlord;
import com.example.northlordv2.application.NumberPointMaker;
import com.example.northlordv2.inter.ProfileFeature.PictureApi;
import com.example.northlordv2.inter.ProfileFeature.ProfileApi;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static android.app.Activity.RESULT_OK;

public class ProfileFragment extends Fragment {

    private final int PICK_IMAGE_REQUEST = 1;
    static final int REQUEST_IMAGE_CAPTURE = 2;

    @BindView(R.id.ProfileName)
    TextView profileName;
    @BindView(R.id.ProfileEmail)
    TextView profileEmail;
    @BindView(R.id.ProfileCount)
    TextView profileCount;
    @BindView(R.id.ProfileMoney)
    TextView profileMoney;
    @BindView(R.id.editprofilebutton)
    Button editprofilebutton;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipeContainer;
    @BindView(R.id.image)
    CircleImageView image;
    Unbinder unbinder;

    @Inject
    ProfileApi api;
    @Inject
    PictureApi pictureApi;
    @Inject
    PictureChanger pictureChanger;
    @Inject
    PictureSender pictureSender;


    Northlord app;
    DialogFragment dialogFragment;
    int id;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        unbinder = ButterKnife.bind(this, view);
        app = Northlord.getApplication(getActivity());
        id = app.getData().getId();
        Log.d("id", id + "");
        return view;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Northlord.getApplication(getActivity()).getMainActivityComponent().getProfileFragmentComponent().injectProfileFragment(this);
        pictureChanger.loadImageWithCache(image,Northlord.getApplication(getActivity()).getData().getId());
        updateProfile();
        swipeContainer.setOnRefreshListener(() -> {
            updateProfile();
            pictureChanger.loadImageWithoutCache(image,Northlord.getApplication(getActivity()).getData().getId());
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        updateProfile();
        pictureChanger.loadImageWithoutCache(image,Northlord.getApplication(getActivity()).getData().getId());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @SuppressLint("CheckResult")
    void updateProfile() {
        try {
            String l = Northlord.getApplication(getActivity()).getData().getLogin();
            String p = Northlord.getApplication(getActivity()).getData().getPassword();
            String log = URLEncoder.encode(l, "UTF-8");
            String pass = URLEncoder.encode(p, "UTF-8");
            api
                    .getProfile(l, p)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError(s -> {
                        swipeContainer.setRefreshing(false);
                    })
                    .subscribe(s -> {
                        if (s.getResult().equals("success")) {
                            s.decode();
                            swipeContainer.setRefreshing(false);
                            profileName.setText(URLDecoder.decode(s.getName() + " " + s.getSurname(), "UTF-8"));
                            profileEmail.setText(URLDecoder.decode(s.getEmail(), "UTF-8"));
                            profileCount.setText(NumberPointMaker.makePoints(s.getCars() + ""));
                            profileMoney.setText(NumberPointMaker.makePoints(s.getProfit() + ""));

                        }
                        //s.getRes();
                    }, Throwable::printStackTrace);
        } catch (Exception e) {
            swipeContainer.setRefreshing(false);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap;
        switch (requestCode) {
            case PICK_IMAGE_REQUEST:
                if (data != null && data.getData() != null) {
                    Uri uri = data.getData();
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                        pictureSender.sendPicture(bitmap,image,Northlord.getApplication(getActivity()).getData().getId());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case REQUEST_IMAGE_CAPTURE:
                Bundle extras = data.getExtras();
                bitmap = (Bitmap) extras.get("data");
                pictureSender.sendPicture(bitmap,image,Northlord.getApplication(getActivity()).getData().getId());
                break;

        }

    }



    @OnClick({R.id.image, R.id.editprofilebutton})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image:
                dialogFragment = new ChangePictureDialog();
                ((ChangePictureDialog) dialogFragment).setListeners(
                        new ChangePictureDialog.Listener() {

                            @Override
                            public void onClick(View view) {
                                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                                }
                            }
                        }, new ChangePictureDialog.Listener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent();
                                intent.setType("image/*");
                                intent.setAction(Intent.ACTION_GET_CONTENT);
                                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
                            }
                        }
                );
                dialogFragment.show(getFragmentManager(), "");
                break;
            case R.id.editprofilebutton:
                startActivity(new Intent(getContext(), EditProfileActivity.class));
                break;
        }
    }
}
