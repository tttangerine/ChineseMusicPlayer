package com.tttangerine.chinesemusicplayer.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.RawResourceDataSource;

public class PlayerService extends Service {

    public ExoPlayer mExoPlayer;

    public int CURRENT_MUSIC_URI = 0;

    MyBinder mBinder = new MyBinder();

    public class MyBinder extends Binder{
        public PlayerService getService(){
            return PlayerService.this;
        }
    }

    /**
     * 创建一个SimpleExoPlayer实例，SimpleExoPlayer是ExoPlayer接口的一个默认的通用实现。
     */
    @Override
    public void onCreate(){
        super.onCreate();
        mExoPlayer = ExoPlayerFactory.newSimpleInstance(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onRebind(Intent intent){
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return true;
    }

    public void buildUri(int uri){

        try{

            DataSpec dataSpec = new DataSpec(RawResourceDataSource.buildRawResourceUri(uri));
            final RawResourceDataSource rawResourceDataSource = new RawResourceDataSource(this);
            rawResourceDataSource.open(dataSpec);
            DataSource.Factory factory = new DataSource.Factory() {
                @Override
                public DataSource createDataSource() {
                    return rawResourceDataSource;
                }
            };
            ProgressiveMediaSource.Factory PMFactory = new ProgressiveMediaSource.Factory(factory);
            ProgressiveMediaSource progressiveMediaSource = PMFactory.createMediaSource(rawResourceDataSource.getUri());

            mExoPlayer.prepare(progressiveMediaSource, true, false);
            mExoPlayer.setPlayWhenReady(true);

        } catch (RawResourceDataSource.RawResourceDataSourceException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        releasePlayer();
    }

    private void releasePlayer(){
        if (mExoPlayer != null){
            mExoPlayer.stop();
            mExoPlayer.release();
            mExoPlayer = null;
        }
    }
}
