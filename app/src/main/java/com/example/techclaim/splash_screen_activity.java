package com.example.techclaim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class splash_screen_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the window to fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        final ImageView imageView = findViewById(R.id.splash_image);

        Glide.with(this)
                .load(R.drawable.sss)  // Replace with your GIF resource ID
                .into(imageView);

        // Start your main activity after a delay (optional)
        new Handler(getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(splash_screen_activity.this, login_activity.class));
                finish();
            }
        }, 10000);
    }
}