package com.tttangerine.chinesemusicplayer.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.tttangerine.chinesemusicplayer.R;
import com.tttangerine.chinesemusicplayer.service.PlayerService;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, HomepageActivity.class);
                //Intent intent = new Intent(SplashActivity.this, PlayerActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);

        Intent intent = new Intent(SplashActivity.this, PlayerService.class);
        startService(intent);
    }
}
