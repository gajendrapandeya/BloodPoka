package com.codermonkeys.bloodpoka;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Toolbar;

import com.codermonkeys.bloodpoka.Util.JournalApi;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseUser currentUser;

    private DatabaseReference databaseUser;


    private EditText emailEditText, passwordEditText, usernameEditText;
    private ProgressBar progressBar;
    private Button createAcctButton;


    private ConstraintLayout constraintLayoutSignUp;
    private EditText userNameSignup, userEmailSignup, userPasswordSignup;

    private ProgressDialog progressDialog;
    private Button signupButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initWidgets();


        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayoutSignUp.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        progressDialog = new ProgressDialog(this);
        databaseUser = FirebaseDatabase.getInstance().getReference("USERS");

        firebaseAuth = FirebaseAuth.getInstance();

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = userEmailSignup.getText().toString().trim();
                String passoword = userPasswordSignup.getText().toString().trim();
                String username = userNameSignup.getText().toString().trim();
                createUserEmailAccount(email, passoword, username);



            }
        });



    }

    private void createUserEmailAccount(final String email, String passoword, final String username) {

        progressDialog.setMessage("Signing Up");
        progressDialog.show();

        if(!TextUtils.isEmpty(username)) {

            if(!TextUtils.isEmpty(email)) {

                if(!TextUtils.isEmpty(passoword)) {

                    firebaseAuth.createUserWithEmailAndPassword(email, passoword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()) {

                                currentUser = firebaseAuth.getCurrentUser();
                                final String uid = currentUser.getUid();

                                UserInfo info = new UserInfo() {
                                    @Override
                                    public String getUid() {
                                        return uid;
                                    }

                                    @Override
                                    public String getProviderId() {
                                        return null;
                                    }

                                    @Nullable
                                    @Override
                                    public String getDisplayName() {
                                        return username;
                                    }

                                    @Nullable
                                    @Override
                                    public Uri getPhotoUrl() {
                                        return null;
                                    }

                                    @Nullable
                                    @Override
                                    public String getEmail() {
                                        return email;
                                    }

                                    @Nullable
                                    @Override
                                    public String getPhoneNumber() {
                                        return null;
                                    }

                                    @Override
                                    public boolean isEmailVerified() {
                                        return false;
                                    }
                                };

                                databaseUser.child(uid).setValue(info).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        progressDialog.dismiss();

                                        if(task.isSuccessful()) {
                                            FancyToast.makeText(getApplicationContext(), "Successful", FancyToast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                                finish();

                            } else {
                                FancyToast.makeText(getApplicationContext(), (CharSequence) task.getResult(), FancyToast.LENGTH_SHORT).show();

                            }
                        }
                    });

                } else {

                    FancyToast.makeText(getApplicationContext(), "Password Required", FancyToast.LENGTH_LONG).show();
                }

            } else  {
                FancyToast.makeText(getApplicationContext(), "Email Required", FancyToast.LENGTH_LONG).show();
            }

        } else {
            FancyToast.makeText(getApplicationContext(), "Username Required", FancyToast.LENGTH_LONG).show();
        }
    }

    private void initWidgets() {

        constraintLayoutSignUp = findViewById(R.id.signupLayout);
        userNameSignup  = findViewById(R.id.signup_et_username);
        userEmailSignup = findViewById(R.id.signup_et_useremail);
        userPasswordSignup = findViewById(R.id.signup_et_userpassword);
        signupButton = findViewById(R.id.signup_button);
    }



}
