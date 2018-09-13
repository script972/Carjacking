package com.script972.carjacking.mvp.imp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.script972.carjacking.mvp.contracts.AuthFirabaseContract;
import com.script972.carjacking.ui.acitivity.SignUpActivity;

public class AuthFirabasePresenterImpl implements AuthFirabaseContract.Presenter{

    private AuthFirabaseContract.View view;

    private FirebaseAuth mAuth;

    public AuthFirabasePresenterImpl(AuthFirabaseContract.View view) {
        this.view = view;
    }

    @Override
    public void onCreate() {


    }

    @Override
    public void onStart() {
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser()!=null)
            view.alreadySignIn(mAuth.getCurrentUser());
    }

    @Override
    public void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) view, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            AuthFirabasePresenterImpl.this.view.signInSuccess(user);
                        } else {
                            AuthFirabasePresenterImpl.this.view.signInFailed(task);
                        }
                    }
                });

    }

    @Override
    public void signUp(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) view, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            view.signUpSuccess(user);
                        } else {
                            view.signInFailed(task);
                        }
                    }
                });
    }



}
