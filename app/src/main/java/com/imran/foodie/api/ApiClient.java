package com.imran.foodie.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final  String url="base_url_link";
    private static Retrofit retrofit=null;

    public static Retrofit getClient(){
        if (retrofit==null) {
            OkHttpClient okHttpClient = new OkHttpClient().newBuilder().build();
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();


            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson)).build();

        }
        return retrofit;
    }
}
