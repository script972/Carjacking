package com.script972.carjacking.ui.acitivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Bundle;
import android.widget.TextView;

import com.script972.carjacking.R;


public class SplashScreenActivity extends BaseActivity {

    private TextView txtVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        initView();

        waiter();
    }

    private void initView() {
        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            txtVersion.setText(getResources().getString(R.string.txt_version_bottom) + " " + pInfo.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method wich provide freeze window on fix time
     */
    private void waiter() {
        long delay = 3000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                openNextActivty();
            }
        }, delay);
    }

    /**
     * Method wich decided what activity is next
     */
    private void openNextActivty() {

        openLoginActivity();
/*
        if (PrefHelper.isAuthorized(getApplicationContext())) {
            openMainActivity();
        } else{
            openLoginActivity();
        }*/

    }

    private void openLoginActivity() {
        Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
