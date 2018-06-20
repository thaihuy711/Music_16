package com.framgia.music_16.screen.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.widget.Toast;
import com.framgia.music_16.R;
import com.framgia.music_16.data.model.Song;
import com.framgia.music_16.utils.Constant;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MusicService extends Service implements MediaPlayer.OnPreparedListener {

    private IBinder mIBinder = new MusicBinder();
    private MediaPlayer mMediaPlayer;
    private List<Song> mSongs;
    private int mSongPosition;
    private boolean mIsMediaReady;

    public static Intent getSongsIntent(Context context, List<Song> songs, int position) {
        Intent intent = new Intent(context, MusicService.class);
        intent.putParcelableArrayListExtra(Constant.EXTRAS_SONG,
                (ArrayList<? extends Parcelable>) songs);
        intent.putExtra(Constant.ARGUMENT_POSITION, position);
        return intent;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mIBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
        }
        mSongs = intent.getParcelableArrayListExtra(Constant.EXTRAS_SONG);
        mSongPosition = intent.getIntExtra(Constant.ARGUMENT_POSITION, 0);
        if (mSongs != null) {
            initMediaPlayer();
        }
        return START_STICKY;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        playMusic();
    }

    public class MusicBinder extends Binder {
        public MusicService getService() {
            return MusicService.this;
        }
    }

    public void initMediaPlayer() {
        mIsMediaReady = false;
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setOnPreparedListener(this);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.reset();
        try {
            mMediaPlayer.setDataSource(
                    mSongs.get(mSongPosition).getStreamUrl() + Constant.CLIENT_ID);
            mMediaPlayer.prepareAsync();
        } catch (IOException e) {
            Toast.makeText(this, R.string.service_has_stopped, Toast.LENGTH_SHORT).show();
            stopSelf();
        }
    }

    public String getArtistName() {
        return mSongs.get(mSongPosition) != null ? mSongs.get(mSongPosition)
                .getArtist()
                .getUsername() : "";
    }

    public String getSongName() {
        return mSongs.get(mSongPosition) != null ? mSongs.get(mSongPosition).getTitle() : "";
    }

    public String getArtwork() {
        return mSongs.get(mSongPosition) != null ? mSongs.get(mSongPosition).getArtworkUrl() : "";
    }

    public int getSongDuration() {
        return mIsMediaReady ? mMediaPlayer.getDuration() : 0;
    }

    public int getCurrentPosition() {
        return mIsMediaReady ? mMediaPlayer.getCurrentPosition() : 0;
    }

    public void fastForward(int position) {
        mMediaPlayer.seekTo(position);
    }

    public void playMusic() {
        if (!mMediaPlayer.isPlaying()) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    mMediaPlayer.start();
                }
            });
            thread.start();
            mIsMediaReady = true;
        }
    }

    public void pauseMedia() {
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
        }
    }

    public boolean isPlaying() {
        return mMediaPlayer.isPlaying();
    }

    public void nextMusic() {
        if (mSongPosition == mSongs.size() - 1) {
            mSongPosition = 0;
        } else {
            mSongPosition++;
        }
        mMediaPlayer.reset();
        initMediaPlayer();
    }

    public void previousMusic() {
        if (mSongPosition == 0) {
            mSongPosition = mSongs.size() - 1;
        } else {
            mSongPosition--;
        }
        mMediaPlayer.reset();
        initMediaPlayer();
    }
}
