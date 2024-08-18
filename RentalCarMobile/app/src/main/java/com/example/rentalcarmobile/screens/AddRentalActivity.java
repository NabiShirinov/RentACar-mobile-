package com.example.rentalcarmobile.screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rentalcarmobile.R;
import com.example.rentalcarmobile.Service.MySharedPreferences;
import com.example.rentalcarmobile.api.ApiClient;
import com.example.rentalcarmobile.api.ApiInterface;
import com.example.rentalcarmobile.entities.Rental;
import com.example.rentalcarmobile.fragments.ProfileFragment;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AddRentalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rental);

        Button btnAddRental = findViewById(R.id.addRental_button);
        btnAddRental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRental(v);


            }
        });
    }
    public void addRental(View view) {
        EditText rentalNameEditText = findViewById(R.id.rentalName);
        EditText rentalModelEditText = findViewById(R.id.rentalModel);
        EditText rentalYearEditText = findViewById(R.id.rentalYear);
        EditText rentalKmEditText = findViewById(R.id.rentalKm);
        EditText rentalPriceEditText = findViewById(R.id.rentalPrice);

        String rentalName = rentalNameEditText.getText().toString();
        String rentalModel = rentalModelEditText.getText().toString();
        String rentalYear = rentalYearEditText.getText().toString();
        String rentalKm = rentalKmEditText.getText().toString();
        int rentalPrice = Integer.parseInt(rentalPriceEditText.getText().toString());


        if (rentalName.equals("")) {
            Toast.makeText(this, "Enter Rental Car Name", Toast.LENGTH_SHORT).show();
        } else if (rentalModel.equals("")) {
            Toast.makeText(this, "Enter Rental Car Model", Toast.LENGTH_SHORT).show();
        } else if (rentalYear.equals("")) {
            Toast.makeText(this, "Enter Rental Car Year", Toast.LENGTH_SHORT).show();
        } else if (rentalKm.equals("")) {
            Toast.makeText(this, "Enter Rental Car Driven Km", Toast.LENGTH_SHORT).show();
        }
        else {
            Rental rental = new Rental();
            rental.setCarName(rentalName);
            rental.setCarModel(rentalModel);
            rental.setCarYear(rentalYear);
            rental.setDrivenKm(rentalKm);
            rental.setPrice(rentalPrice);


            Retrofit retrofit = new ApiClient().getClient();
            ApiInterface apiInterface = retrofit.create(ApiInterface.class);

            Call<Rental> call = apiInterface.addRental("Bearer " + getAccessTokenFromSharedPreferences(), rental);

            call.enqueue(new Callback<Rental>() {
                @Override
                public void onResponse(@NonNull Call<Rental> call, @NonNull Response<Rental> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        Rental addedRental = response.body();

                        addedRental.setCarName(rentalName);
                        addedRental.setCarModel(rentalModel);
                        addedRental.setCarYear(rentalYear);
                        addedRental.setDrivenKm(rentalKm);
                        addedRental.setPrice(rentalPrice);

                        showMessage("Rental Successfully added, you can go back");
                        // Optionally, you can start a new activity or perform other actions upon successful addition
                    } else if (response.code() != 200) {
                    }
                }

                @Override
                public void onFailure(@NonNull Call<Rental> call, @NonNull Throwable t) {
                    Log.d("Error on failure", Objects.requireNonNull(t.getMessage()));
                }
            });
        }
    }
    private String getAccessTokenFromSharedPreferences() {
        // Assuming MySharedPreferences class is available
        MySharedPreferences mySharedPreferences = new MySharedPreferences(getApplicationContext());
        return mySharedPreferences.getApiResponse();
    }
    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}