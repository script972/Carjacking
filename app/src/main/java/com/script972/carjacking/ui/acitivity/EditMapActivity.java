package com.script972.carjacking.ui.acitivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.SupportMapFragment;
import com.script972.carjacking.R;
import com.script972.carjacking.controllers.MapControllerDetected;
import com.script972.carjacking.controllers.callbacks.GetAddressPosition;
import com.script972.carjacking.model.PositionInfo;

public class EditMapActivity extends BaseActivity {

    private TextView addressPosition;
    private Button confirmPosition;
    private PositionInfo finalPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_map);

        initView();
    }

    private void initView() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        MapControllerDetected mapController= new MapControllerDetected(mapFragment, getAddressPosition, getBaseContext());
        addressPosition = findViewById(R.id.address_position);
        confirmPosition = findViewById(R.id.confirm_position);
        confirmPosition.setOnClickListener(cliker);
    }


    private void confirmPosition() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("position",  finalPosition.convertToJSON(finalPosition));
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

    //callbacks
    private final GetAddressPosition getAddressPosition = new GetAddressPosition() {
        @Override
        public void mapStopGetAddress(PositionInfo positionInfo) {
            finalPosition = positionInfo;
            addressPosition.setText(positionInfo.getStreet() + " "+ positionInfo.getHomeNumber());
        }
    };

    private final View.OnClickListener cliker =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.confirm_position: confirmPosition(); break;
            }

        }
    };


}
