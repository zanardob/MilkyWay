package com.astrocows.milkyway;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_register_screen);

        setButtonsActions();
    }

    private void setButtonsActions() {
        final Button register = (Button) findViewById(R.id.registerButton);

        register.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Pressed
                    register.setBackgroundColor(Color.parseColor("#F8BBD0"));

                    FirebaseAuth auth;

                    auth = FirebaseAuth.getInstance();

                    final EditText emailField = (EditText) findViewById(R.id.emailField);
                    final EditText passwordField = (EditText) findViewById(R.id.passwordField);
                    String email = "", password = "";

                    email = emailField.getText().toString().toLowerCase();
                    password = passwordField.getText().toString();

                    auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(RegisterScreen.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (!task.isSuccessful()) {

                                        Toast.makeText(RegisterScreen.this, "Registration failed!\n" +
                                                       getString(R.string.invalid_email) + "\nor\n" +
                                                       getString(R.string.minimum_password),
                                                       Toast.LENGTH_LONG).show();
                                    }
                                    else
                                    {
                                        Toast.makeText(RegisterScreen.this, "Registration complete!", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(RegisterScreen.this, LoginScreen.class);
                                        startActivity(intent);
                                    }
                                }
                            });
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    // Released
                    register.setBackgroundColor(Color.parseColor("#E91E63"));
                }
                return true;
            }
        });
    }
}