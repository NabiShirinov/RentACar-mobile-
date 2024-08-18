package com.example.rentalcarmobile.screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rentalcarmobile.MainActivity;
import com.example.rentalcarmobile.R;
import com.example.rentalcarmobile.Service.MySharedPreferences;
import com.example.rentalcarmobile.DBHelper.UserDBHelper;
import com.example.rentalcarmobile.api.ApiClient;
import com.example.rentalcarmobile.api.ApiInterface;
import com.example.rentalcarmobile.entities.User;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {
    private TextView createAccount;
    Button loginButton;
    EditText emailEditText,passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        createAccount = findViewById(R.id.dont_have_account);
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
                finish();
            }
        });

    }

    public void login(View view) {
        EditText emailEditText = findViewById(R.id.user_email);
        EditText passwordEditText = findViewById(R.id.user_password);

        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (email.equals("")) {
            android.widget.Toast.makeText(this, "Enter Email", android.widget.Toast.LENGTH_SHORT).show();
        } else if (password.equals("")) {
            android.widget.Toast.makeText(this, "Enter Password", android.widget.Toast.LENGTH_SHORT).show();
        } else {
            Retrofit retrofit = new ApiClient().getClient();
            ApiInterface apiInterface = retrofit.create(ApiInterface.class);

            User user = new User();
            user.setEmail(email);
            user.setPassword(password);

            apiInterface.loginUser(user).enqueue(new retrofit2.Callback<User>() {
                @Override
                public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        User userResponse = response.body();
                        User.UserData userData = userResponse.getData();
                        if (userData != null) {

                            saveLoggedInUser(LoginActivity.this, userData);

                            String accessToken = userData.getAccessToken();

                            if (accessToken != null && !accessToken.isEmpty()) {
                                // Save the access_token to SharedPreferences
                                MySharedPreferences mySharedPreferences = new MySharedPreferences(LoginActivity.this);
                                mySharedPreferences.saveApiResponse(accessToken);


                                // Start the MainActivity
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            } else {
                                // Handle the case where the access_token is missing or empty
                                Toast.makeText(LoginActivity.this, "Access token not received", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // Handle the case where data is null
                            Toast.makeText(LoginActivity.this, "Invalid response format", Toast.LENGTH_SHORT).show();
                        }
                    } else if (response.code() != 200) {
                        Toast.makeText(LoginActivity.this, "Wrong email or password", Toast.LENGTH_SHORT).show();
                    }
                }


                @Override
                public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                    Log.d("nspDebug", t.getMessage() != null ? t.getMessage() : "");
                }
            });
        }
    }
    public void saveLoggedInUser(Context context, User.UserData userData) {
        UserDBHelper dbHelper = new UserDBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            if (userData != null) {
                values.put("email", userData.getEmail());
                values.put("access_token", userData.getAccessToken());
                db.insert("user_db", null, values);
            } else {
                // Handle the case where user.getData() is null
            }
        } catch (Exception e) {
            // Handle the exception (e.g., log it)
        } finally {
            db.close();
        }
    }

}