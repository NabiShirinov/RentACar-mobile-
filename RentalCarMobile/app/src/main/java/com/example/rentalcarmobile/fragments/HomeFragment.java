package com.example.rentalcarmobile.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rentalcarmobile.adapters.All_Rentals_RecycleViewAdapter;
import com.example.rentalcarmobile.R;
import com.example.rentalcarmobile.interfaces.RecyclerViewInterface;
import com.example.rentalcarmobile.screens.RentalSeperateActivity;
import com.example.rentalcarmobile.Service.MySharedPreferences;
import com.example.rentalcarmobile.api.ApiClient;
import com.example.rentalcarmobile.api.ApiInterface;
import com.example.rentalcarmobile.dto.RentalResponse;
import com.example.rentalcarmobile.entities.Rental;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements RecyclerViewInterface {

    private All_Rentals_RecycleViewAdapter adapter;
    private RecyclerView recyclerView;

    private MySharedPreferences mySharedPreferences;

    public void onAttach(@NonNull Context context) {
            super.onAttach(context);
            mySharedPreferences = new MySharedPreferences(requireContext());
        }
    private String getAccessTokenFromSharedPreferences(Context context) {
        // Assuming MySharedPreferences class is available
        MySharedPreferences mySharedPreferences = new MySharedPreferences(requireContext());
        return mySharedPreferences.getApiResponse();
    }
    private void getAllRentals() {
        ApiInterface apiInterface = ApiClient.getApiInterface();
        Call<RentalResponse> call = apiInterface.getAllRentals("Bearer " + getAccessTokenFromSharedPreferences(requireContext()));

        call.enqueue(new Callback<RentalResponse>() {
            @Override
            public void onResponse(Call<RentalResponse> call, Response<RentalResponse> response) {
                if (response.isSuccessful()) {
                    RentalResponse rentalResponse = response.body();

                    if (rentalResponse != null) {
                        // Access various parts of the response
                        List<Rental> rentalsList = rentalResponse.getData();

                        // Initialize the adapter with the list of rentals
                        adapter = new All_Rentals_RecycleViewAdapter(getContext(), rentalResponse, HomeFragment.this);

                        // Set the adapter to the RecyclerView
                        recyclerView.setAdapter(adapter);
                    } else {
                        // Handle null response body
                        showToast("Null response body");
                    }
                } else {
                    // Handle error
                    handleErrorResponse(response);
                }
            }

            @Override
            public void onFailure(Call<RentalResponse> call, Throwable t) {
                // Handle failure
                showToast("Request failed. Please try again.");
            }
        });
    }

    private void handleErrorResponse(Response<RentalResponse> response) {
        // Handle different HTTP error codes
        switch (response.code()) {
            case 401:
                showToast("Unauthorized access. Please login again.");
                // Handle unauthorized access, maybe redirect to login screen
                break;
            case 404:
                showToast("Resource not found.");
                // Handle resource not found error
                break;
            case 500:
                showToast("Internal server error. Please try again later.");
                // Handle internal server error
                break;
            default:
                showToast("Error: " + response.code());
                // Handle other errors
                break;
        }
    }

    private void showToast(String message) {
        // You can customize how you want to display the error message to the user,
        // for example, using a Toast message, Snackbar, or any other UI element.
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.mRecycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Call the method to fetch and display rentals
        getAllRentals();

        return view;
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getContext(), RentalSeperateActivity.class);
            List<Rental> retrievedRentals = mySharedPreferences.getRentalsList();
              if (position >= 0 && position < retrievedRentals.size()) {
                  Rental selectedRental = retrievedRentals.get(position);

                  // Add rental details to the intent
                  intent.putExtra("CarName", selectedRental.getCarName());
                  intent.putExtra("CarModel", selectedRental.getCarModel());
                  intent.putExtra("CarYear", selectedRental.getCarYear());
                  intent.putExtra("CarKm", selectedRental.getDrivenKm());
                  intent.putExtra("CarPrice", selectedRental.getPrice());
                  intent.putExtra("Email", selectedRental.getUserEmail());

                  // Start the activity
                  startActivity(intent);
              }
    }
}
