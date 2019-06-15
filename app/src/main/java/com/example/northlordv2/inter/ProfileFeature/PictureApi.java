package com.example.northlordv2.inter.ProfileFeature;



import com.example.northlordv2.ProfileFeature.ChangePictureFeature.ResultPicture;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.PUT;

public interface PictureApi {
    @FormUrlEncoded
    @PUT("pic")
    Observable<ResultPicture> send(@Field("pic64")String pic,
                                   @Field("id")String id);
}
