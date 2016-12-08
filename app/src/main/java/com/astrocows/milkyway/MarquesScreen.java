package com.astrocows.milkyway;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * Created by Gustavo on 08/12/2016.
 */

public class MarquesScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_marques_screen);

        ImageView marques = (ImageView) findViewById(R.id.marques);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.marques);
        marques.startAnimation(animation);
    }

}
