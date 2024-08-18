package com.example.rentalcarmobile.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.rentalcarmobile.R;

public class RentalSeperateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental_seperate);

        String car_name = getIntent().getStringExtra("CarName");
        String car_model = getIntent().getStringExtra("CarModel");
        String car_year = getIntent().getStringExtra("CarYear");
        String car_km = getIntent().getStringExtra("CarKm");
        int car_price = getIntent().getIntExtra("CarPrice",0);
        String email = getIntent().getStringExtra("Email");


        TextView carNameTextView = findViewById(R.id.carName);
        TextView carModelTextView = findViewById(R.id.carModel);
        TextView carYearTextView = findViewById(R.id.carYear);
        TextView carKmTextView = findViewById(R.id.carKm);
        TextView carPriceTextView = findViewById(R.id.carPrice);
        TextView emailTextView = findViewById(R.id.contactAdress);

        carNameTextView.setText(car_name);
        carModelTextView.setText(car_model);
        carYearTextView.setText(car_year);
        carKmTextView.setText(car_km);
        carPriceTextView.setText(String.valueOf(car_price));
        emailTextView.setText(email);

    }
}