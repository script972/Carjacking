package com.script972.carjacking.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import com.google.gson.Gson;

public class PrefHelper {

    private static final String PREFS_NAME = "com.script972.clutchclient.MAIN_PREFS";
    private static final String CREDENTIALS = "CREDENTIALS_DATA";
    private static final String LOCATION = "LOCATION";
    private static final String AUTHORIZED = "AUTHORIZED";
    private static final String USER_ID = "USER_ID";
    private static final String ACCESS_TOKEN = "ACCESS_TOKEN";
    private static final String REFRESH_TOKEN = "REFRESH_TOKEN";
    private static final String REMEMBER = "REMEMBER";
    private static final String DISPLAY_CARD="DISPLAY_CARD";



    /**
     * Method wich create SharedPreferences containcer
     *
     * @param context
     * @return
     */
    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(context.getPackageName() + PREFS_NAME, Context.MODE_PRIVATE);
    }

 /*   *//**
     * Method wich save credentials
     *
     * @param context
     * @param credentials
     *//*
    public static void saveCredentials(Context context, Credentials credentials) {
        String jsonCredentials = new Gson().toJson(credentials);
        String encodedCredentials = Base64.encodeToString(jsonCredentials.getBytes(), Base64.DEFAULT);
        SharedPreferences.Editor prefEditor = getSharedPreferences(context).edit();
        prefEditor.putString(CREDENTIALS, encodedCredentials);
        prefEditor.apply();
    }*/

/*    *//**
     * Method wich get credentials from storage
     *
     * @param context
     * @return
     *//*
    public static Credentials getCredentials(Context context) {
        String encodedCredentials = getSharedPreferences(context).getString(CREDENTIALS, "");
        String jsonCredentials = new String(Base64.decode(encodedCredentials, Base64.DEFAULT));
        return new Gson().fromJson(jsonCredentials, Credentials.class);
    }*/

    public static boolean clearCredentials(Context context) {
        SharedPreferences.Editor prefEditor = getSharedPreferences(context).edit();
        prefEditor.putString(CREDENTIALS, "");
        prefEditor.apply();
        return true;
    }

    /**
     * Method wich clear Shared Preferences block
     *
     * @param context
     */
    public static void clear(Context context) {
        SharedPreferences.Editor prefEditor = getSharedPreferences(context).edit();
        prefEditor.clear();
        prefEditor.apply();
    }

    /**
     * Method for set if user authorized
     *
     * @param context
     * @param isAuthorized
     */
    public static void setAuthorizedFlag(Context context, boolean isAuthorized) {
        SharedPreferences.Editor prefEditor = getSharedPreferences(context).edit();
        prefEditor.putBoolean(AUTHORIZED, isAuthorized);
        prefEditor.apply();
    }

    /**
     * Method wich get true if user authorized
     *
     * @param context
     * @return
     */
    public static boolean isAuthorized(Context context) {
        return getSharedPreferences(context).getBoolean(AUTHORIZED, false);
    }


    /**
     * Method wich save id user to storage
     *
     * @param context
     * @param userId
     */
    public static void setUserId(Context context, int userId) {
        SharedPreferences.Editor prefEditor = getSharedPreferences(context).edit();
        prefEditor.putInt(USER_ID, userId);
        prefEditor.apply();
    }

    /**
     * Method wich get id user from storage
     *
     * @param context
     * @return
     */
    public static int getUserId(Context context) {
        return getSharedPreferences(context).getInt(USER_ID, -1);
    }

    /**
     * Method wich set save AccessToken to storage
     *
     * @param context
     * @param accessToken
     */
    public static void setAccessToken(Context context, String accessToken) {
        //composit token
        accessToken = accessToken;

        SharedPreferences.Editor prefEditor = getSharedPreferences(context).edit();
        prefEditor.putString(ACCESS_TOKEN, accessToken);
        prefEditor.apply();
    }

    /**
     * Method wich get AccessToken from storage
     *
     * @param context
     * @return
     */
    public static String getAccessToken(Context context) {
        return getSharedPreferences(context).getString(ACCESS_TOKEN, null);
    }


    /**
     * Method wich set Refresh Token to storage
     *
     * @param context
     * @param accessToken
     */
    public static void setRefreshToken(Context context, String accessToken) {
        SharedPreferences.Editor prefEditor = getSharedPreferences(context).edit();
        prefEditor.putString(REFRESH_TOKEN, accessToken);
        prefEditor.apply();
    }

    /**
     * Method wich get Refresh Token from storage
     *
     * @param context
     * @return
     */
    public static String getRefreshToken(Context context) {
        return getSharedPreferences(context).getString(REFRESH_TOKEN, null);
    }

    /**
     * Method wich set information about remember current user
     *
     * @param context
     * @param isEnabled
     */
    public static void setRememberMeOptionEnabled(Context context, boolean isEnabled) {
        SharedPreferences.Editor prefEditor = getSharedPreferences(context).edit();
        prefEditor.putBoolean(REMEMBER, isEnabled);
        prefEditor.apply();
    }

    /**
     * Method wich get information about remember current user
     *
     * @param context
     * @return
     */
    public static boolean isRememberMeOptionEnabled(Context context) {
        return getSharedPreferences(context).getBoolean(REMEMBER, true);
    }
/*
    *//**
     * Save device location position
     *
     * @param context
     * @param location
     *//*
    public static void saveLocation(Context context, LocationPosition location) {
        String jsonCredentials = new Gson().toJson(location);
        String encodedLocation = Base64.encodeToString(jsonCredentials.getBytes(), Base64.DEFAULT);
        SharedPreferences.Editor prefEditor = getSharedPreferences(context).edit();
        prefEditor.putString(LOCATION, encodedLocation);
        prefEditor.apply();
    }

    *//**
     * Get Device Location
     *
     * @param context
     * @return
     *//*
    public static LocationPosition getLocation(Context context) {
        String encodedCredentials = getSharedPreferences(context).getString(LOCATION, null);
        if(encodedCredentials==null)
            return null;
        String jsonCredentials = new String(Base64.decode(encodedCredentials, Base64.DEFAULT));
        return new Gson().fromJson(jsonCredentials, LocationPosition.class);
    }*/




}
