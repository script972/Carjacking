package com.script972.carjacking.model;

import android.util.Base64;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;

public class PositionInfo {
    private String adminArea;
    private String country;
    private String street;
    private String homeNumber;
    private double lat;
    private double lng;
    private String addressFull;




    public PositionInfo(double latitude, double longitude, String addressFull, String adminArea, String country, String street, String homeNumber) {
        this.lat = latitude;
        this.lng = longitude;
        this.addressFull = addressFull;
        this.adminArea = adminArea;
        this.country = country;
        this.street = street;
        this.homeNumber = homeNumber;

    }

    public PositionInfo(LatLng position) {
        this.lat = position.latitude;
        this.lng = position.longitude;
    }

    public PositionInfo() {
    }

    public String getAdminArea() {
        return adminArea;
    }

    public void setAdminArea(String adminArea) {
        this.adminArea = adminArea;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getAddressFull() {
        return addressFull;
    }

    public void setAddressFull(String addressFull) {
        this.addressFull = addressFull;
    }

    public String convertToJSON(PositionInfo finalPosition) {
        String jsonPosition = new Gson().toJson(finalPosition);
        String positionEncode = Base64.encodeToString(jsonPosition.getBytes(), Base64.DEFAULT);
        return positionEncode;
    }

    public PositionInfo convertFromJSON(String returnStr) {
        String jsonPosition = new String(Base64.decode(returnStr, Base64.DEFAULT));
        return new Gson().fromJson(jsonPosition, PositionInfo.class);
    }
}
