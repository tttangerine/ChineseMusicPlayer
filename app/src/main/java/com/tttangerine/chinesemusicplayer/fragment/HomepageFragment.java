package com.tttangerine.chinesemusicplayer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.tttangerine.chinesemusicplayer.R;
import com.tttangerine.chinesemusicplayer.activity.MusicListActivity;

import java.util.Random;

public class HomepageFragment extends Fragment implements View.OnClickListener {

    private Button bt_friend;
    private Button bt_love;
    private TextView tv_card_text;
    private ImageView iv_card_img;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_homepage, container, false);
        bt_friend = view.findViewById(R.id.bt_friend);
        bt_love = view.findViewById(R.id.bt_love);
        tv_card_text = view.findViewById(R.id.tv_card_text);
        iv_card_img = view.findViewById(R.id.iv_card_img);
        refreshCard();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        bt_friend.setOnClickListener(this);
        bt_love.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.bt_friend:
                Intent intent1 = new Intent(getActivity(), MusicListActivity.class);
                intent1.putExtra("list_type", 1);
                startActivity(intent1);
                break;
            case R.id.bt_love:
                Intent intent2 = new Intent(getActivity(), MusicListActivity.class);
                intent2.putExtra("list_type", 2);
                startActivity(intent2);
                break;
        }
    }

    private void refreshCard(){
        Random random = new Random();
        int n = random.nextInt() + 1;
        if (n <= 5){
            tv_card_text.setText(R.string.card_text1);
            iv_card_img.setImageResource(R.mipmap.bg1);
        } else {
            tv_card_text.setText(R.string.card_text2);
            iv_card_img.setImageResource(R.mipmap.bg2);
        }
    }

}
