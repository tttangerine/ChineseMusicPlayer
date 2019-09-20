package com.tttangerine.chinesemusicplayer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.tttangerine.chinesemusicplayer.R;
import com.tttangerine.chinesemusicplayer.activity.PlayerActivity;
import com.tttangerine.chinesemusicplayer.db.MusicListItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MusicListFragment extends Fragment {

    /**
     * 注册列表、适配器
     */
    private ListView mListView;
    private ArrayAdapter mAdapter;
    private List<MusicListItem> mMusicListItems = new ArrayList<>();

    public int currentListType = 0;  //当前列表
    private static final int MUSIC_ALL = 0;  //所有歌曲列表
    private static final int MUSIC_FRIEND = 1;
    private static final int MUSIC_LOVE = 2;

    /**
     * 初始化列表、适配器
     * @return 返回视图
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music_list, container, false);
        mListView = view.findViewById(R.id.music_list);

        //noinspection unchecked
        mAdapter = new ArrayAdapter(Objects.requireNonNull(getContext()),
                android.R.layout.simple_list_item_2, android.R.id.text1, mMusicListItems){
            @NonNull
            @Override
            public View getView(int position, View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = view.findViewById(android.R.id.text1);
                TextView text2 = view.findViewById(android.R.id.text2);

                text1.setText(mMusicListItems.get(position).getName());
                text2.setText(mMusicListItems.get(position).getLabel());
                return view;
            }
        };

        mListView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initList();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (mMusicListItems.get(position).getName()){
                    case "平凡之路":
                        Intent intent1 = new Intent(getActivity(), PlayerActivity.class);
                        intent1.putExtra("MusicUri", R.raw.music_test1);
                        startActivity(intent1);
                        break;
                    case "龙战骑士":
                        Intent intent2 = new Intent(getActivity(), PlayerActivity.class);
                        intent2.putExtra("MusicUri", R.raw.music_test2);
                        startActivity(intent2);
                        break;
                }
            }
        });
    }

    private void initList(){

        MusicListItem item1 = new MusicListItem("平凡之路", "朴树");
        MusicListItem item2 = new MusicListItem("龙战骑士", "周杰伦");

        if (mMusicListItems.size() != 0)
            mMusicListItems.clear();

        if (currentListType == MUSIC_ALL){
            mMusicListItems.add(item1);
            mMusicListItems.add(item2);
        } else if (currentListType == MUSIC_FRIEND){
            mMusicListItems.add(item2);
        } else if (currentListType == MUSIC_LOVE){
            mMusicListItems.add(item1);
        }

        mAdapter.notifyDataSetChanged();
        mListView.setSelection(0);
    }

}
