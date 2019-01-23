package com.example.q.week4;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextToSpeech tts;
    SpeechRecognizer spr;
    ImageButton imageButton;
    final int PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= 23) {
            // 퍼미션 체크
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET,
                    Manifest.permission.RECORD_AUDIO}, PERMISSION);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this,LoadingActivity.class);
        startActivity(intent);

        imageButton = findViewById(R.id.mic);

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                tts.setLanguage(new Locale("ko", "KOR", "variant"));
                tts.setPitch(2.0f);
                tts.setSpeechRate(1.0f);
            }
        });
    }
    public void listen(View v) {
        final LottieAnimationView lottieAnimationView = (LottieAnimationView)findViewById(R.id.doggie);
        lottieAnimationView.setAnimation("dog.json");
        lottieAnimationView.setRepeatCount(1);
        spr = SpeechRecognizer.createSpeechRecognizer(MainActivity.this);
        spr.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {
            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float rmsdB) {

            }

            @Override
            public void onBufferReceived(byte[] buffer) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int error) {

            }

            @Override
            public void onResults(Bundle results) {
                ArrayList<String> msg = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                tts.speak(msg.get(0), TextToSpeech.QUEUE_FLUSH, null);
                lottieAnimationView.playAnimation();
            }

            @Override
            public void onPartialResults(Bundle partialResults) {
            }

            @Override
            public void onEvent(int eventType, Bundle params) {

            }
        });
        Intent recogniserIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        recogniserIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, "ko");
        recogniserIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, MainActivity.this.getPackageName());
        recogniserIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        recogniserIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 5);
        spr.startListening(recogniserIntent);
    }

    public void stopListening(View v) {
        if (spr != null) {
            spr.stopListening();
        }
    }
}
