package com.script972.carjacking.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.script972.carjacking.model.PositionInfo;

public class PrefHelper {

    private static final String PREFS_NAME = "com.script972.carjacking.MAIN_PREFS";
    private static final String MAP_POSITION = "MAP_POSITION";



    /**
     * Method wich create SharedPreferences containcer
     *
     * @param context
     * @return
     */
    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(context.getPackageName() + PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static void setMapPosition(Context context, LatLng position){
        String jsonPosition = new Gson().toJson(position);
        String positionEncode = Base64.encodeToString(jsonPosition.getBytes(), Base64.DEFAULT);
        SharedPreferences.Editor prefEditor = getSharedPreferences(context).edit();
        prefEditor.putString(MAP_POSITION, positionEncode);
        prefEditor.apply();
    }

    public static LatLng getPosition(Context context){
        String encodePosition = getSharedPreferences(context).getString(MAP_POSITION, null);
        if(encodePosition == null)
            return null;

        String jsonPosition = new String(Base64.decode(encodePosition, Base64.DEFAULT));
        return new Gson().fromJson(jsonPosition, LatLng.class);

    }




}
