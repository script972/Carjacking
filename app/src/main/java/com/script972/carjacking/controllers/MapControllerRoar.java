package com.script972.carjacking.controllers;

import android.content.Context;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.script972.carjacking.controllers.callbacks.GetAddressPosition;
import com.script972.carjacking.helpers.PrefHelper;
import com.script972.carjacking.model.PositionInfo;

import java.util.ArrayList;
import java.util.List;

public class MapControllerRoar implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Context context;

    private final List<PositionInfo> positionInfos = new ArrayList<>();

    public MapControllerRoar(SupportMapFragment mapFragment, Context context) {
        mapFragment.getMapAsync(this);
        this.context = context;
        addRoad(new PositionInfo(PrefHelper.getPosition(context)));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(PrefHelper.getPosition(context),15));

    }

    public void addRoad(PositionInfo positionInfo) {
        positionInfos.add(positionInfo);
        rebuildRoat();
    }

    //TODO
    private void rebuildRoat() {

    }
}
