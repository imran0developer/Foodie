package com.imran.foodie.api;

import com.imran.foodie.models.RestaurantsModel;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiSet {


    @POST("get_resturants")
    Call<RestaurantsModel> getResturants(@Body RequestBody requestBody);
}
