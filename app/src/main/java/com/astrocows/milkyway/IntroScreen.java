package com.astrocows.milkyway;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Gustavo on 08/12/2016.
 */

public class IntroScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_intro_screen);

        final ImageView backgroundOne = (ImageView) findViewById(R.id.background_one);
        final ImageView backgroundTwo = (ImageView) findViewById(R.id.background_two);

        final ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(10000L);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final float progress = (float) animation.getAnimatedValue();
                final float width = backgroundOne.getWidth();
                final float translationX = width * progress;
                backgroundOne.setTranslationX(translationX);
                backgroundTwo.setTranslationX(translationX - width);
            }
        });
        animator.start();

        setButtonsActions();
    }

    private void setButtonsActions(){
        final Button create = (Button) findViewById(R.id.createButton);

        create.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Pressed
                    create.setBackgroundColor(Color.parseColor("#F8BBD0"));
                    Intent intent = new Intent(IntroScreen.this, RegisterScreen.class);
                    startActivity(intent);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    // Released
                    create.setBackgroundColor(Color.parseColor("#E91E63"));
                }
                return true;
            }
        });

        final Button fb = (Button) findViewById(R.id.fbButton);

        fb.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Pressed
                    fb.setBackgroundColor(Color.parseColor("#F8BBD0"));
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    // Released
                    fb.setBackgroundColor(Color.parseColor("#E91E63"));
                }
                return true;
            }
        });

        final Button enter = (Button) findViewById(R.id.enterButton);

        enter.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Pressed
                    enter.setBackgroundColor(Color.parseColor("#F8BBD0"));
                    Intent intent = new Intent(IntroScreen.this, LoginScreen.class);
                    startActivity(intent);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    // Released
                    enter.setBackgroundColor(Color.parseColor("#E91E63"));
                }
                return true;
            }
        });
    }

}
