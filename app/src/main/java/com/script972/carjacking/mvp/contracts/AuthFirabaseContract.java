package com.script972.carjacking.mvp.contracts;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

public interface AuthFirabaseContract {

    interface Presenter{

        void onCreate();

        void onStart();

        void signIn(String login, String password);

        void signUp(String login, String password);
    }

    interface View{

        void alreadySignIn(FirebaseUser currentUser);

        void signInSuccess(FirebaseUser user);

        void signInFailed(Task<AuthResult> task);

        void signUpSuccess(FirebaseUser user);

        void signUpFailed();

    }

}
