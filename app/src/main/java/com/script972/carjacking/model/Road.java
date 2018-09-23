package com.script972.carjacking.model;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class Road {

    //удалить
    private String temp;

    private boolean actual;

    private double generalPrice;

    private boolean justForFuel;

    private double priceWithEvery;

    private List<LatLng> latLngs = new ArrayList<>();

    private long startTime;

    public Road() {
    }

    public Road(boolean actual, double generalPrice, boolean justForFuel, double priceWithEvery, List<LatLng> latLngs, long startTime) {
        this.actual = actual;
        this.generalPrice = generalPrice;
        this.justForFuel = justForFuel;
        this.priceWithEvery = priceWithEvery;
        this.latLngs = latLngs;
        this.startTime = startTime;
    }

    public boolean isActual() {
        return actual;
    }

    public void setActual(boolean actual) {
        this.actual = actual;
    }

    public double getGeneralPrice() {
        return generalPrice;
    }

    public void setGeneralPrice(double generalPrice) {
        this.generalPrice = generalPrice;
    }

    public boolean isJustForFuel() {
        return justForFuel;
    }

    public void setJustForFuel(boolean justForFuel) {
        this.justForFuel = justForFuel;
    }

    public double getPriceWithEvery() {
        return priceWithEvery;
    }

    public void setPriceWithEvery(double priceWithEvery) {
        this.priceWithEvery = priceWithEvery;
    }

    public List<LatLng> getLatLngs() {
        return latLngs;
    }

    public void setLatLngs(List<LatLng> latLngs) {
        this.latLngs = latLngs;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }



    @Override
    public String toString() {
        return "Road{" +
                "actual=" + actual +
                ", generalPrice=" + generalPrice +
                ", justForFuel=" + justForFuel +
                ", priceWithEvery=" + priceWithEvery +
                ", latLngs=" + latLngs +
                ", startTime=" + startTime +
                '}';
    }
}
