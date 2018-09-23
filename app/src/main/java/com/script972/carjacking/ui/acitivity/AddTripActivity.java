package com.script972.carjacking.ui.acitivity;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.maps.SupportMapFragment;
import com.script972.carjacking.R;
import com.script972.carjacking.controllers.MapControllerRoar;
import com.script972.carjacking.model.PositionInfo;

public class AddTripActivity extends BaseActivity {

    private final int ADD_NEW_POINT=501;
    private  MapControllerRoar mapRoad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);

        initView();
        initToolbar();
        findViewById(R.id.new_point).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTripActivity.this, EditMapActivity.class);
                AddTripActivity.this.startActivityForResult(intent, ADD_NEW_POINT);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (ADD_NEW_POINT) : {
                if (resultCode == Activity.RESULT_OK) {
                    String returnStr = data.getStringExtra("position");
                    PositionInfo positionInfo = new PositionInfo().convertFromJSON(returnStr);
                    mapRoad.addRoad(positionInfo);
                }
                break;
            }
        }

    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.toolbar_add_trip));
        toolbar.setNavigationIcon(R.drawable.ic_earth);
    }

    private void initView() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapRoad = new MapControllerRoar(mapFragment, getApplicationContext());
    }
}
