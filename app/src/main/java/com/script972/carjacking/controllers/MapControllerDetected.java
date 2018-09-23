package com.script972.carjacking.controllers;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.script972.carjacking.controllers.callbacks.GetAddressPosition;
import com.script972.carjacking.helpers.PrefHelper;
import com.script972.carjacking.model.PositionInfo;

import java.io.IOException;
import java.util.List;

public class MapControllerDetected implements OnMapReadyCallback {

    private GoogleMap mMap;
    private GetAddressPosition cameraPosition;
    private Context context;


    public MapControllerDetected(SupportMapFragment mapFragment, GetAddressPosition cameraPosition, Context context) {
        mapFragment.getMapAsync(this);
        this.cameraPosition = cameraPosition;
        this.context = context;

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnCameraIdleListener(onCameraIdleList);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(PrefHelper.getPosition(context),15));

    }


    //listeners

    private final GoogleMap.OnCameraIdleListener onCameraIdleList = new GoogleMap.OnCameraIdleListener() {
        @Override
        public void onCameraIdle() {
            try {
                cameraPosition.mapStopGetAddress(convertPositionToAddress(mMap.getCameraPosition().target));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    private PositionInfo convertPositionToAddress(LatLng target) throws IOException {
        Geocoder geocoder = new Geocoder(context);
        List<Address> addresses = geocoder.getFromLocation(target.latitude, target.longitude, 1);
        if(addresses==null || addresses.size()==0)
            return null;
        String addressFull = addresses.get(0).getAddressLine(0);
        String city = addresses.get(0).getAdminArea();
        String adminArea = addresses.get(0).getAdminArea();
        String country = addresses.get(0).getCountryName();
        String homeNumber = addresses.get(0).getFeatureName();
        String streat = addresses.get(0).getThoroughfare();
        return new PositionInfo(target.latitude, target.longitude, addressFull, adminArea, country, streat, homeNumber);
    }

}
