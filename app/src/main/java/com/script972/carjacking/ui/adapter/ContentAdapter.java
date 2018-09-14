package com.script972.carjacking.ui.adapter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
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
import com.google.firebase.auth.FirebaseAuth;
import com.script972.carjacking.R;
import com.script972.carjacking.ui.acitivity.DetailActivity;

import static com.artlite.bslibrary.managers.BSContextManager.getApplicationContext;

public class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
    // Set numbers of Card in RecyclerView.
    private static final int LENGTH = 18;

    private final String[] mPlaces;
    private final String[] mPlaceDesc;
    private final Drawable[] mPlacePictures;
    private Context context;


    public ContentAdapter(Context context, FragmentManager childFragmentManager) {
        Resources resources = context.getResources();
        this.context = context;
        mPlaces = resources.getStringArray(R.array.places);
        mPlaceDesc = resources.getStringArray(R.array.place_desc);
        TypedArray a = resources.obtainTypedArray(R.array.places_picture);
        mPlacePictures = new Drawable[a.length()];
        for (int i = 0; i < mPlacePictures.length; i++) {
            mPlacePictures[i] = a.getDrawable(i);
        }
        a.recycle();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        holder.picture.setImageDrawable(mPlacePictures[position % mPlacePictures.length]);
        holder.name.setText(mPlaces[position % mPlaces.length]);
        holder.description.setText(mPlaceDesc[position % mPlaceDesc.length]);
        GoogleMap map = holder.gMap;
        if(map!=null){
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(46.965358, 32.013203), 15));
        }

    }

    @Override
    public int getItemCount() {
        return LENGTH;
    }
}


class ViewHolder extends RecyclerView.ViewHolder implements OnMapReadyCallback {
    public ImageView picture;
    public MapView mapView;
    public TextView name;
    public TextView description;
    public GoogleMap gMap;

    ViewHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.item_card, parent, false));
        // picture = (ImageView) itemView.findViewById(R.id.card_image);
        mapView = (MapView) itemView.findViewById(R.id.map);
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }

        name = (TextView) itemView.findViewById(R.id.card_title);
        description = (TextView) itemView.findViewById(R.id.card_text);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(46.965358, 32.013203), 15));

                Context context = v.getContext();
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_POSITION, getAdapterPosition());
                context.startActivity(intent);
            }
        });

        // Adding Snackbar to Action Button inside card
        Button button = (Button) itemView.findViewById(R.id.action_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Action is pressed",
                        Snackbar.LENGTH_LONG).show();
            }
        });

        ImageButton favoriteImageButton =
                (ImageButton) itemView.findViewById(R.id.favorite_button);
        favoriteImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Added to Favorite",
                        Snackbar.LENGTH_LONG).show();
            }
        });

        ImageButton shareImageButton = (ImageButton) itemView.findViewById(R.id.share_button);
        shareImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Share article",
                        Snackbar.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getApplicationContext());
        gMap = googleMap;
        gMap.getUiSettings().setMyLocationButtonEnabled(false);

        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(46.965358, 32.013203), 15));

        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        gMap.setMyLocationEnabled(true);
     }
 }