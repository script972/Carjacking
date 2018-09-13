package com.script972.carjacking.ui.acitivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.script972.carjacking.R;
import com.script972.carjacking.mvp.contracts.AuthFirabaseContract;
import com.script972.carjacking.mvp.imp.AuthFirabasePresenterImpl;


public class SplashScreenActivity extends BaseActivity implements AuthFirabaseContract.View {

    private TextView txtVersion;
    //TODO set false/ this true just for testing
    private boolean access=true;

    private AuthFirabaseContract.Presenter presenter = new AuthFirabasePresenterImpl(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        initView();

        waiter();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
    }

    private void initView() {
        txtVersion = findViewById(R.id.version_show);
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

        if(access){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }else
            openLoginActivity();
/*
        if (PrefHelper.isAuthorized(getApplicationContext())) {
            openMainActivity();
        } else{
            openLoginActivity();
        }*/

    }

    private void openLoginActivity() {
        Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void alreadySignIn(FirebaseUser currentUser) {
        this.access=true;
    }

    @Override
    public void signInSuccess(FirebaseUser user) {

    }

    @Override
    public void signInFailed(Task<AuthResult> task) {

    }

    @Override
    public void signUpSuccess(FirebaseUser user) {

    }

    @Override
    public void signUpFailed() {

    }
}
