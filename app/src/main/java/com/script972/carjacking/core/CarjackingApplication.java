package com.script972.carjacking.core;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.artlite.bslibrary.core.BSInstance;
import com.script972.carjacking.BuildConfig;
import com.script972.carjacking.api.NetworkLoggingSettings;
import com.script972.carjacking.ui.acitivity.BaseActivity;

public class CarjackingApplication extends MultiDexApplication {

    private static BaseActivity currentActivity;
    private static CarjackingApplication application;

    /**
     * Method which provide the action when the base {@link Context} was attached
     *
     * @param base instance of the {@link Context}
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    /**
     * Method which provide the action when the application created
     */
    @Override
    public void onCreate() {
        super.onCreate();
        this.onInitLibraries();
        application=this;


        initDebugMode();

    }

    private void initDebugMode() {
        if(BuildConfig.DEBUG) {
            NetworkLoggingSettings.init(this);

        }
    }

    /**
     * Method which provide the init of the libraries
     */
    protected void onInitLibraries() {
        BSInstance.init(this);
    }

    public static CarjackingApplication getApplication() {
        return application;
    }

    public static BaseActivity getCurrentActivity() {
        return currentActivity;
    }

}
