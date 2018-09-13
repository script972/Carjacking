package com.script972.carjacking.ui.acitivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.script972.carjacking.R;
import com.script972.carjacking.helpers.ValidatorHelper;
import com.script972.carjacking.mvp.contracts.AuthFirabaseContract;
import com.script972.carjacking.mvp.imp.AuthFirabasePresenterImpl;

public class SignUpActivity extends BaseActivity implements AuthFirabaseContract.View{

    //outlets
    private Button btnSignUp;
    private EditText edtEmail;
    private EditText edtPassword;
    private EditText edtPasswordRep;

    private AuthFirabaseContract.Presenter presenter = new AuthFirabasePresenterImpl(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        presenter.onCreate();



        initView();
    }

    private void initView() {
        btnSignUp = findViewById(R.id.btn_registration_one);
        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);
        edtPasswordRep = findViewById(R.id.edt_password_rep);

        btnSignUp.setOnClickListener(clicker);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();

    }


    private void registration() {
        if(!validateFields()){
            return;
        }

        presenter.signUp(edtEmail.getText().toString(), edtPassword.getText().toString());


    }

    private boolean validateFields() {
        if(ValidatorHelper.validateEmail(edtEmail, this) && ValidatorHelper.validatePassword(edtPassword, this) &&
                ValidatorHelper.validatePassword(edtPasswordRep, this)){
            return edtPassword.getText().toString().equals(edtPasswordRep.getText().toString());
        } else{
            return false;
        }

    }


    private View.OnClickListener clicker = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_registration_one : registration(); break;
            }
        }
    };


    @Override
    public void alreadySignIn(FirebaseUser currentUser) {

    }

    @Override
    public void signInSuccess(FirebaseUser user) {

    }

    @Override
    public void signInFailed(Task<AuthResult> task) {
        Toast.makeText(this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void signUpSuccess(FirebaseUser user) {
        Toast.makeText(this, user.getEmail(), Toast.LENGTH_LONG).show();

    }

    @Override
    public void signUpFailed() {

    }
}
