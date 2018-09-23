package com.script972.carjacking.ui.adapter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.script972.carjacking.R;
import com.script972.carjacking.callbacks.MapCallbacksReady;
import com.script972.carjacking.helpers.ImageHelper;
import com.script972.carjacking.model.Road;
import com.script972.carjacking.ui.acitivity.DetailActivity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.artlite.bslibrary.managers.BSContextManager.getApplicationContext;

public class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
    // Set numbers of Card in RecyclerView.

    private Context context;
    private List<Road> roads = new ArrayList<>();


    public ContentAdapter(Context context, FragmentManager childFragmentManager, List<Road> road) {
        Resources resources = context.getResources();
        this.roads = road;
        this.context = context;
    }

    public void setRoads(List<Road> roads) {
        this.roads = roads;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()), parent, context);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(String.valueOf(roads.get(position).getGeneralPrice()));
        holder.track = roads.get(position).getLatLngs();
        //holder.description.setText();
        GoogleMap map = holder.gMap;
        if(map!=null){
        //    map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(46.965358, 32.013203), 15));
        }

    }



    @Override
    public int getItemCount() {
        return roads.size();
    }
}


class ViewHolder extends RecyclerView.ViewHolder implements OnMapReadyCallback {
    public MapView mapView;
    public TextView name;
    public TextView description;
    public GoogleMap gMap;
    public List<LatLng> track;
    public Context context;
    public ImageButton detailsList;

    ViewHolder(LayoutInflater inflater, ViewGroup parent, Context context) {
        super(inflater.inflate(R.layout.item_card, parent, false));
        this.context = context;
        mapView = (MapView) itemView.findViewById(R.id.map);
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }

        name = (TextView) itemView.findViewById(R.id.card_title);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDetailsActivity(v);
            }
        });
        detailsList = itemView.findViewById(R.id.btn_details);
        detailsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDetailsActivity(v);
            }
        });

        Button button = (Button) itemView.findViewById(R.id.action_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Action is pressed",
                        Snackbar.LENGTH_LONG).show();
            }
        });


    }

    private void startDetailsActivity(View v){
        Context context = v.getContext();
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_POSITION, getAdapterPosition());
        context.startActivity(intent);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getApplicationContext());
        gMap = googleMap;
        gMap.getUiSettings().setMyLocationButtonEnabled(false);
        gMap.getUiSettings().setCompassEnabled(false);
        gMap.getUiSettings().setTiltGesturesEnabled(false);
        gMap.getUiSettings().setZoomControlsEnabled(false);
        gMap.getUiSettings().setZoomGesturesEnabled(false);
        gMap.getUiSettings().setMapToolbarEnabled(false);

        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(46.965358, 32.013203), 13));

        gMap.addMarker(new MarkerOptions().position(new LatLng(46.965358, 32.013203)));
        drawRoad(gMap);

        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        gMap.setMyLocationEnabled(true);
     }

    private void drawRoad(GoogleMap map) {
        PolylineOptions rectOptions = new PolylineOptions();
        LatLngBounds.Builder latLngBuilder = new LatLngBounds.Builder();
        for (int i = 0; i < this.track.size(); i++) {
            LatLng pos = this.track.get(i);
            rectOptions.add(pos);
            latLngBuilder.include(pos);
        }

        Polyline polyline = map.addPolyline(rectOptions);
        polyline.setColor(Color.argb(255, 5, 200, 0));
        LatLngBounds latLngBounds = latLngBuilder.build();
        CameraUpdate track = CameraUpdateFactory.newLatLngBounds(latLngBounds, 120);
        map.moveCamera(track);

        map.addMarker(new MarkerOptions().position(this.track.get(0))).setIcon(ImageHelper.bitmapSizeByScall(context, R.drawable.pick_up_pos, 0.08f));
        map.addMarker(new MarkerOptions().position(this.track.get(this.track.size()-1))).setIcon(ImageHelper.bitmapSizeByScall(context, R.drawable.finish_pos, 0.08f));
    }






}