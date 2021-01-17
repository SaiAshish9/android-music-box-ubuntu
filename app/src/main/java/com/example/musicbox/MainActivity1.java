package com.example.musicbox;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity1 extends AppCompatActivity {


    private AnimationDrawable batAnimation;
    private ImageView batImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        batImage = (ImageView) findViewById(R.id.batID);
//        batImage.setBackgroundResource(R.drawable.bat_anim);
//        batAnimation = (AnimationDrawable) batImage.getBackground();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        batAnimation.start();

        Handler mHandler = new Handler();

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Animation startAnimation =
                        AnimationUtils.loadAnimation(getApplicationContext()
                        ,
                                R.anim.fadein_animation
                        );
                batImage.startAnimation(startAnimation);

//                batAnimation.stop();
            }
        },1000);

        return super.onTouchEvent(event);
    }


}