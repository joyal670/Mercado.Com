package com.mercado.mercadocom.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.mercado.mercadocom.R;

public class SplashScreen extends AppCompatActivity {

    Handler handler;
    ImageView imageView;
    Animation bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        try {
            ActionBar actionBar = getSupportActionBar();
            actionBar.hide();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorBlue));
            }

            imageView = findViewById(R.id.imageView);
            bottom = AnimationUtils.loadAnimation(this, R.anim.bottom);
            imageView.setAnimation(bottom);

            handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashScreen.this, Welcome_Activity.class);
                    startActivity(intent);
                    finish();
                }
            }, 3000);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
