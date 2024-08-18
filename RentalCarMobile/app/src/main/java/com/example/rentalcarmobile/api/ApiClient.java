package com.example.rentalcarmobile.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String BASE_URL = "http://192.168.124.49:8081/";

    private static Retrofit retrofit;

    static {
        OkHttpClient client = new OkHttpClient.Builder().build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    public static Retrofit getClient() {
        return retrofit;
    }

    public static ApiInterface getApiInterface() {
        return retrofit.create(ApiInterface.class);
    }
}
