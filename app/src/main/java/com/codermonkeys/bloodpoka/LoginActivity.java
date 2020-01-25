package com.codermonkeys.bloodpoka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ConstraintLayout constraintLayout;
    private EditText userEmailLogin, userPasswordLogin;
    private Button buttonLogin, buttonSignup;


    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initWidgets();


        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();


        sharedPreferences = getSharedPreferences("LoginStatus", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();



        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        buttonSignup.setOnClickListener(this);
        buttonLogin.setOnClickListener(this);
    }

    private void initWidgets() {

        constraintLayout = findViewById(R.id.loginLayout);
        userEmailLogin = findViewById(R.id.login_et_useremail);
        userPasswordLogin = findViewById(R.id.login_et_password);
        buttonLogin = findViewById(R.id.login_btn_login);
        buttonSignup = findViewById(R.id.login_btn_register);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.login_btn_login:
                String email = userEmailLogin.getText().toString().trim();
                String password = userPasswordLogin.getText().toString().trim();
                signInUser(email, password);
                break;

            case R.id.login_btn_register:
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
                break;
        }
    }

    private void signInUser(String email, String password) {

        progressDialog.setMessage("Signing Up");
        progressDialog.show();

        if(!TextUtils.isEmpty(email)) {

            if(!TextUtils.isEmpty(password)) {

                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        progressDialog.dismiss();
                        if(task.isSuccessful()) {

                            editor.putBoolean("LOGINSTATUS", true);
                            editor.commit();

                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        } else {

                            editor.putBoolean("LOGINSTATUS", false);
                            editor.commit();

                            FancyToast.makeText(getApplicationContext(),task.getException().getMessage(), FancyToast.LENGTH_SHORT).show();
                        }
                    }
                });

            } else {

                FancyToast.makeText(getApplicationContext(), "Password Required", FancyToast.LENGTH_SHORT).show();
            }

        } else {
            FancyToast.makeText(getApplicationContext(), "Email Required", FancyToast.LENGTH_SHORT).show();
        }
    }
}
