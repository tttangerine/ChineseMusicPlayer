package com.tttangerine.chinesemusicplayer.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.exoplayer2.ui.PlayerView;
import com.tttangerine.chinesemusicplayer.R;
import com.tttangerine.chinesemusicplayer.service.PlayerService;

public class PlayerActivity extends AppCompatActivity {

    public PlayerView mPlayerView;

    private int MusicUri = 0;

    PlayerService mService;

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = ((PlayerService.MyBinder)service).getService();
            if (mService.CURRENT_MUSIC_URI != MusicUri){
                mService.mExoPlayer.setPlayWhenReady(false);
                mPlayerView.setPlayer(mService.mExoPlayer);
                mService.buildUri(MusicUri);
                mService.CURRENT_MUSIC_URI = MusicUri;
            } else {
                mService.mExoPlayer.setPlayWhenReady(false);
                mPlayerView.setPlayer(mService.mExoPlayer);
                mService.mExoPlayer.setPlayWhenReady(true);
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        mPlayerView = findViewById(R.id.view_player);

        MusicUri = getIntent().getIntExtra("MusicUri", 0);

        Intent intent = new Intent(PlayerActivity.this, PlayerService.class);
        bindService(intent, conn, BIND_AUTO_CREATE);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        unbindService(conn);
    }

}
