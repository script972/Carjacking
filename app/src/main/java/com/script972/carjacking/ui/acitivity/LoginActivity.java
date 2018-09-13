package com.script972.carjacking.ui.acitivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.script972.carjacking.R;
import com.script972.carjacking.helpers.KeyboardHelper;
import com.script972.carjacking.mvp.contracts.AuthFirabaseContract;
import com.script972.carjacking.mvp.imp.AuthFirabasePresenterImpl;

public class LoginActivity extends BaseActivity implements AuthFirabaseContract.View {

    //outlets
    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnLogin;
    private LinearLayout linkRegistration;

    private AuthFirabaseContract.Presenter presenter = new AuthFirabasePresenterImpl(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();

    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();

    }

    private void initView() {
        this.btnLogin = findViewById(R.id.btn_login);
        this.edtEmail = findViewById(R.id.edt_email);
        this.edtPassword = findViewById(R.id.edt_password);
        this.linkRegistration = findViewById(R.id.link_registration);
        btnLogin.setOnClickListener(clicker);
        linkRegistration.setOnClickListener(clicker);
    }

    @Override
    public void alreadySignIn(FirebaseUser currentUser) {

    }

    @Override
    public void signInSuccess(FirebaseUser user) {
        Toast.makeText(this, user.getEmail(), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, Main2Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
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

    /**
     * Method which controll login click
     */
    private void btnLoginClick() {
        /*if(!ValidatorHelper.validateEmail(edtEmail.getText().toString()) & edtEmail.getText().toString().length()<6){
            edtEmail.setError(getResources().getString(R.string.e_invalid_email));
            edtPassword.setError(getResources().getString(R.string.e_short_password));
            return;
        }else{

            this.presenter.login(edtEmail.getText().toString(), edtPassword.getText().toString());
        }*/

        KeyboardHelper.hideSoftKeyboard(this);
        this.presenter.signIn(edtEmail.getText().toString(), edtPassword.getText().toString());

    }

    /**
     * Method wich help move to registration activity
     */
    private void btnRegistration() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }


    /**
     * Listener click
     */
    private final View.OnClickListener clicker=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_login: btnLoginClick(); break;
                case R.id.link_registration: btnRegistration(); break;
            }
        }
    };

}
