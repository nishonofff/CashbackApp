package com.example.respect.cashbackapp.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.respect.cashbackapp.R;

public class WelcomeScreenActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN_DURATION=3000;
    private ImageView downImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        downImage=findViewById(R.id.down_image);
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.uptodown);
        downImage.setAnimation(animation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(WelcomeScreenActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_out,R.anim.slide_in);

            }
        },SPLASH_SCREEN_DURATION);
    }
}