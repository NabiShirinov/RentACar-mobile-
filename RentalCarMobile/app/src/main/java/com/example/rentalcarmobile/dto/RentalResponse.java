package com.example.rentalcarmobile.dto;

import com.example.rentalcarmobile.entities.Rental;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RentalResponse {

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("data")
    @Expose
    private List<Rental> data;

    // Getter methods...

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    public List<Rental> getData() {
        return data;
    }
}