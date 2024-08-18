package com.example.rentalcarmobile.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.rentalcarmobile.R;
import com.example.rentalcarmobile.Service.MySharedPreferences;
import com.example.rentalcarmobile.api.ApiClient;
import com.example.rentalcarmobile.api.ApiInterface;
import com.example.rentalcarmobile.screens.LoginActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SettingsFragment extends Fragment {
        private String getAccessTokenFromSharedPreferences(Context context) {
            // Assuming MySharedPreferences class is available
            MySharedPreferences mySharedPreferences = new MySharedPreferences(context);
            return mySharedPreferences.getApiResponse();
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_settings, container, false);

            Button logoutButton = view.findViewById(R.id.LogOut);
            logoutButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    logout();
                }
            });

            Button deleteAccountButton = view.findViewById(R.id.DeleteAcc);
            deleteAccountButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteAccount();
                    logout();
                }
            });

            return view;
        }

        // Method to handle logout
        private void logout() {
            Intent intent = new Intent(getContext(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            // Finish the current activity
            if (getActivity() != null) {
                getActivity().finish();
            }
        }

        private void deleteAccount() {
            Retrofit retrofit = new ApiClient().getClient();
            ApiInterface apiInterface = retrofit.create(ApiInterface.class);

            // Assuming you have an API endpoint for account deletion
            Call<Void> call = apiInterface.deleteUser("Bearer " + getAccessTokenFromSharedPreferences(getContext()));

            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(getContext(), "Account deleted successfully", Toast.LENGTH_SHORT).show();

                        // Handle any additional actions after successful account deletion
                        // For example, navigate to the login screen or perform cleanup
                    } else {
                        Toast.makeText(getContext(), "Failed to delete account", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
}