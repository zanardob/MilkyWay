package com.astrocows.milkyway;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class LoginScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_login_screen);

        setButtonsActions();
    }

    private void setButtonsActions() {
        final Button login = (Button) findViewById(R.id.loginButton);

        login.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Pressed
                    login.setBackgroundColor(Color.parseColor("#F8BBD0"));


                    FirebaseAuth auth;

                    auth = FirebaseAuth.getInstance();

                    final EditText emailField = (EditText) findViewById(R.id.emailField);
                    final EditText passField = (EditText) findViewById(R.id.passwordField);

                    final String email = emailField.getText().toString();
                    final String password = passField.getText().toString();

                    auth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(LoginScreen.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (!task.isSuccessful()) {

                                        Toast.makeText(LoginScreen.this, "Authentication failed!\n" +
                                                        getString(R.string.icorrect_email) + "\nor\n" +
                                                        getString(R.string.icorrect_password),
                                                        Toast.LENGTH_LONG).show();
                                    }
                                    else
                                    {
                                        Toast.makeText(LoginScreen.this, "Authentication complete!", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(LoginScreen.this, MainActivity.class);
                                        startActivity(intent);
                                    }
                                }
                            });

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    // Released
                    login.setBackgroundColor(Color.parseColor("#E91E63"));
                }
                return true;
            }
        });

        final Button forgot = (Button) findViewById(R.id.forgotPassButton);

        forgot.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Pressed
                    forgot.setBackgroundColor(Color.parseColor("#F8BBD0"));
                    Intent intent = new Intent(LoginScreen.this, ForgotPasswordScreen.class);
                    startActivity(intent);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    // Released
                    forgot.setBackgroundColor(Color.parseColor("#E91E63"));
                }
                return true;
            }
        });
    }
}
