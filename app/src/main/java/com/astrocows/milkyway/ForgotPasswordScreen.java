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
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordScreen extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
            getSupportActionBar().hide();

            setContentView(R.layout.activity_forgot_screen);

            setButtonsActions();
        }

        private void setButtonsActions() {

            final Button back = (Button) findViewById(R.id.backButton);
            back.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        // Pressed
                        back.setBackgroundColor(Color.parseColor("#F8BBD0"));
                        Intent intent = new Intent(ForgotPasswordScreen.this, LoginScreen.class);
                        startActivity(intent);
                    } else if (event.getAction() == MotionEvent.ACTION_UP) {
                        // Released
                        back.setBackgroundColor(Color.parseColor("#E91E63"));
                    }
                    return true;
                }
            });

            final Button reset = (Button) findViewById(R.id.resetButton);
            reset.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        // Pressed
                        reset.setBackgroundColor(Color.parseColor("#F8BBD0"));

                        FirebaseAuth auth;
                        final EditText inputEmail;
                        String email;

                        auth = FirebaseAuth.getInstance();

                        inputEmail = (EditText) findViewById(R.id.emailField);
                        email = inputEmail.getText().toString().trim();


                        auth.sendPasswordResetEmail(email)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(ForgotPasswordScreen.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(ForgotPasswordScreen.this, LoginScreen.class);
                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(ForgotPasswordScreen.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    } else if (event.getAction() == MotionEvent.ACTION_UP) {
                        // Released
                        reset.setBackgroundColor(Color.parseColor("#E91E63"));
                    }
                    return true;
                }
            });
        }
}