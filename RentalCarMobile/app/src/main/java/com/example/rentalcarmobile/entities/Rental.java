package com.example.rentalcarmobile.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rental {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("carName")
    @Expose
    private String carName;

    @SerializedName("carYear")
    @Expose
    private String carYear;

    @SerializedName("carModel")
    @Expose
    private String carModel;

    @SerializedName("drivenKm")
    @Expose
    private String drivenKm;

    @SerializedName("price")
    @Expose
    private int price;

    @SerializedName("userEmail")
    @Expose
    private String userEmail;

    // Getter methods...


    public int getId() {
        return id;
    }

    public String getCarName() {
        return carName;
    }

    public String getCarYear() {
        return carYear;
    }

    public String getCarModel() {
        return carModel;
    }

    public String getDrivenKm() {
        return drivenKm;
    }

    public int getPrice() {
        return price;
    }

    // Setter methods...

    public void setId(int id) {
        this.id = id;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public void setCarYear(String carYear) {
        this.carYear = carYear;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public void setDrivenKm(String drivenKm) {
        this.drivenKm = drivenKm;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}