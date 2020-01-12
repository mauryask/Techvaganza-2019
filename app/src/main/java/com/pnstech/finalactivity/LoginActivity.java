/*
 *@Author Shubham Maurya
 *@Date April 20, 2019
 *@Company  pnstech Inc.*/

package com.pnstech.finalactivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private TextView  tech_vaganza;
    private Typeface myfont;
    private EditText user_email, user_password;
    private Button click_to_login;
    private FirebaseAuth auth;
    private ProgressDialog progressDialog;
    private int counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

       progressDialog = new ProgressDialog(this);

        final FirebaseUser user = auth.getCurrentUser(); //if user is already signed in

        tech_vaganza= findViewById(R.id.tech_vaganza);
        myfont=Typeface.createFromAsset(this.getAssets(),"fonts/Brandywine  Normal" +
                ".ttf");
        tech_vaganza.setTypeface(myfont);

        user_email = findViewById(R.id.enter_email);
        user_password = findViewById(R.id.enter_password);
        click_to_login = findViewById(R.id.click_to_login);

        click_to_login.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick (View v){
                String email = user_email.getText().toString().trim();
                String password = user_password.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill all the details", Toast.LENGTH_SHORT).show();
                }
                else
                    {
                    if(user==null)
                    {
                        validate(email, password);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "You are alreday logged in", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    //validation of email and password!
    private void validate(String email, String password) {
      //  if (email.equals("example@gmail.com")) {
            progressDialog.setMessage("Authenticating.....");
            progressDialog.show();
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        progressDialog.dismiss();

                        Toast.makeText(getApplicationContext(), "Login successful!",
                                Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    }
                }
                //if some error occurs then following method get called!
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(getApplicationContext(), "Login failed! " + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    counter--;
                  progressDialog.dismiss();

                    if (counter == 0) {
                        Toast.makeText(getApplicationContext(), "Please click on forgot password", Toast.LENGTH_SHORT).show();
                        click_to_login.setEnabled(false);
                    } else if (counter == 3)
                        Toast.makeText(getApplicationContext(), "Only " + counter + "attempts left!", Toast.LENGTH_SHORT).show();
                }
            });
    }


    public void resetPassword(View view) {
        String email = user_email.getText().toString().trim();
        if (email.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please Enter Your Registered Email Id", Toast.LENGTH_SHORT).show();
        } else {
            auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if(task.isSuccessful())
                    {
                        Toast.makeText(getApplicationContext(), "Password reset email has been sent", Toast.LENGTH_SHORT).show();

                    }

                    else
                    {
                        Toast.makeText(getApplicationContext(), "Unable to reset password", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

}