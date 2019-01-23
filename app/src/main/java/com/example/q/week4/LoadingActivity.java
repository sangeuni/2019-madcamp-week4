package com.example.q.week4;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        LottieAnimationView lottieAnimationView = (LottieAnimationView)findViewById(R.id.lottie);
        lottieAnimationView.setAnimation("confetti.json");
        lottieAnimationView.loop(true);
        lottieAnimationView.playAnimation();
        startLoading();
    }

    private void startLoading() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        },2000);
    }
    // 사랑해요 동민님 ㅎㅎ
}

