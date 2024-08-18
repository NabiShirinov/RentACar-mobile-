package com.example.rentalcarmobile.screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rentalcarmobile.MainActivity;
import com.example.rentalcarmobile.R;
import com.example.rentalcarmobile.api.ApiClient;
import com.example.rentalcarmobile.api.ApiInterface;
import com.example.rentalcarmobile.entities.User;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignUpActivity extends AppCompatActivity {

    private TextView haveAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        haveAccount = findViewById(R.id.have_account);
        haveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    public void sign_up(View view) {
        EditText fullNameEditText = findViewById(R.id.user_fullName);
        EditText emailEditText = findViewById(R.id.user_email);
        EditText passwordEditText = findViewById(R.id.user_password);
        EditText confirmEditText = findViewById(R.id.re_user_password);


        String fullName = fullNameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String confirm = confirmEditText.getText().toString();

        if (fullName.equals("")) {
            android.widget.Toast.makeText(this, "Enter Fullname", android.widget.Toast.LENGTH_SHORT).show();
        } else if (email.equals("")) {
            android.widget.Toast.makeText(this, "Enter Email", android.widget.Toast.LENGTH_SHORT).show();
        } else if (password.equals("")) {
            android.widget.Toast.makeText(this, "Enter Password", android.widget.Toast.LENGTH_SHORT).show();
        } else if (confirm.equals("")) {
            android.widget.Toast.makeText(this, "Confirm Password", android.widget.Toast.LENGTH_SHORT).show();
        } else if (!password.equals(confirm)) {
            android.widget.Toast.makeText(this, "Passwords do not match", android.widget.Toast.LENGTH_SHORT).show();
        } else {
            User user = new User();
            user.setFullName(fullName);
            user.setEmail(email);
            user.setPassword(password);

            Log.d("UserObject", user.toString());

            Retrofit retrofit = new ApiClient().getClient();
            ApiInterface apiInterface = retrofit.create(ApiInterface.class);

            Call<User> call = apiInterface.saveUser(user);

            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        User savedUser = response.body();

                        savedUser.setFullName(fullName);
                        savedUser.setEmail(email);
                        savedUser.setPassword(password);

                        startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                    }
                    else if(response.code() != 200) {
                        android.widget.Toast.makeText(SignUpActivity.this, "This email is already registered", android.widget.Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                    Log.d("Error on failure", Objects.requireNonNull(t.getMessage()));
                }
            });

        }
    }
}