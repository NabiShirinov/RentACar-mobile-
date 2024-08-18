package com.example.rentalcarmobile.Service;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.rentalcarmobile.entities.Rental;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class MySharedPreferences {

    private static final String PREF_NAME = "MyAppPreferences";
    private static final String KEY_API_RESPONSE = "api_response";

    private static final String KEY_RENTALS_LIST = "rentals_list";
    private static final String KEY_MY_RENTALS_LIST = "my_rentals_list";

    private Gson gson;
    private SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    public MySharedPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        gson = new Gson();
    }

    // Save API response to SharedPreferences
    public static void saveApiResponse(String response) {
        editor.putString(KEY_API_RESPONSE, response);
        editor.apply();
    }

    // Retrieve API response from SharedPreferences
    public String getApiResponse() {
        return sharedPreferences.getString(KEY_API_RESPONSE, "");
    }

    // Clear the saved API response (optional)
    public void clearApiResponse() {
        editor.remove(KEY_API_RESPONSE);
        editor.apply();
    }
    public void saveRentalsList(List<Rental> rentals) {
        String rentalsJson = gson.toJson(rentals);
        editor.putString(KEY_RENTALS_LIST, rentalsJson);
        editor.apply();
    }

    // Retrieve list of rentals from SharedPreferences
    public List<Rental> getRentalsList() {
        String rentalsJson = sharedPreferences.getString(KEY_RENTALS_LIST, "");
        Type type = new TypeToken<List<Rental>>() {}.getType();
        return gson.fromJson(rentalsJson, type);
    }
    // Save my rentals response to SharedPreferences
    public void saveMyRentalsList(List<Rental> rentals) {
        String rentalsJson = gson.toJson(rentals);
        editor.putString(KEY_MY_RENTALS_LIST, rentalsJson);
        editor.apply();
    }

    public List<Rental> getMyRentalsList() {
        String myRentalsJson = sharedPreferences.getString(KEY_MY_RENTALS_LIST, "");
        Type type = new TypeToken<List<Rental>>() {}.getType();
        return gson.fromJson(myRentalsJson, type);
    }
}

