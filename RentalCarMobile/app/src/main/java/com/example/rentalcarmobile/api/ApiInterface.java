package com.example.rentalcarmobile.api;

import com.example.rentalcarmobile.dto.RentalResponse;
import com.example.rentalcarmobile.entities.Rental;
import com.example.rentalcarmobile.entities.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("api/v1/auth/register")
    Call<User> saveUser(@Body User user);

    @POST("api/v1/auth/login")
    Call<User> loginUser(@Body User user);

    @GET("api/v1/rental/all")
    Call<RentalResponse> getAllRentals(@Header("Authorization") String accessToken);

    @GET("api/v1/rental/myRentals")
    Call<RentalResponse> getMyRentals(@Header("Authorization") String accessToken);

    @POST("api/v1/rental/add")
    Call<Rental> addRental(@Header("Authorization") String accessToken,@Body Rental rental);

    @DELETE("/api/v1/user/delete")
    Call<Void> deleteUser(@Header("Authorization") String accessToken);



}
