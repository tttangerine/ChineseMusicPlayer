package com.tttangerine.chinesemusicplayer.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.tttangerine.chinesemusicplayer.R;
import com.tttangerine.chinesemusicplayer.fragment.MusicListFragment;

public class MusicListActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);

        MusicListFragment fragment = (MusicListFragment) getSupportFragmentManager().findFragmentById(R.id.music_list_tag);
        if (fragment != null) {
            fragment.currentListType = getIntent().getIntExtra("list_type", 0);
        }
    }

}
