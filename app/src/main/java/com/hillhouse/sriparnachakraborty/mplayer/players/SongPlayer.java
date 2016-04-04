package com.hillhouse.sriparnachakraborty.mplayer.players;

import com.hillhouse.sriparnachakraborty.mplayer.models.Media;
import android.content.Context;
import android.media.AudioManager;
import android.net.Uri;
import android.widget.MediaController;
import android.view.View;

import java.io.IOException;
import java.util.List;

/**
 * Created by sriparnachakraborty on 30/03/16.
 */
public class SongPlayer implements MediaPlayer {
    private Context mContext = null;
    private View mAnchorView = null;
    private List<Media> mMediaList = null;
    private MediaController mMediaController = null;
    private MediaPlayerController mPlayerController = null;
    private android.media.MediaPlayer mMediaPlayer = null;
    private boolean mIsPlaying = false;
    private int mIndexToBePlayed = 0;

    public SongPlayer(View anchorView){
        mAnchorView = anchorView;
    }

    @Override
    public void playMedia(Context context, final List<Media> mediaList) {
        mContext = context;
        mMediaList = mediaList;

        if(mIsPlaying){
            if(mMediaPlayer.isPlaying()){
                mMediaPlayer.stop();
            }
            mMediaPlayer.reset();
        }
        else{
            mIsPlaying = true;
            mMediaPlayer = new android.media.MediaPlayer();
            mPlayerController = new MediaPlayerController(mMediaPlayer);
            mMediaController = new MediaController(mContext){
                @Override
                public void hide(){

                }
            };

            mMediaController.setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                    View.SYSTEM_UI_FLAG_FULLSCREEN |
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

            mMediaController.setAnchorView(mAnchorView);
            mMediaController.setMediaPlayer(mPlayerController);

            if(mediaList != null && mediaList.size() > 1){
                mMediaController.setPrevNextListeners(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mIndexToBePlayed = (mIndexToBePlayed + 1) % mMediaList.size();
                                initializeMediaPlayer();
                            }
                        },
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mIndexToBePlayed = ((mIndexToBePlayed + 1) % mMediaList.size()) - 1;
                                initializeMediaPlayer();
                            }
                        }
                );
            }

        }
        initializeMediaPlayer();
    }

    private Uri getUriFromIndex(int index){
        return Uri.parse(mMediaList.get(index).getPath());
    }


    private void initializeMediaPlayer(){
        mMediaPlayer.reset();

        //TODO: proper handling
        AudioManager audioManager = (AudioManager)mContext.getSystemService(Context.AUDIO_SERVICE);
        audioManager.requestAudioFocus(null, AudioManager.STREAM_MUSIC , AudioManager.AUDIOFOCUS_GAIN);


        try {
            mMediaPlayer.setDataSource(mContext, getUriFromIndex(mIndexToBePlayed));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mMediaPlayer.setOnPreparedListener(new android.media.MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(android.media.MediaPlayer mp) {
                mp.start();
                mMediaController.show();
            }
        });
        mMediaPlayer.prepareAsync();
    }

    private class MediaPlayerController implements MediaController.MediaPlayerControl, android.media.MediaPlayer.OnBufferingUpdateListener{

        private android.media.MediaPlayer mMediaPlayer = null;
        private int mBufferPercent = 0;

        public MediaPlayerController(android.media.MediaPlayer mediaPlayer){
            mMediaPlayer = mediaPlayer;
            mMediaPlayer.setOnBufferingUpdateListener(this);
        }

        @Override
        public void start() {
            mMediaPlayer.start();
        }

        @Override
        public void pause() {
            mMediaPlayer.pause();
        }

        @Override
        public int getDuration() {
            return mMediaPlayer.getDuration();
        }

        @Override
        public int getCurrentPosition() {
            return mMediaPlayer.getCurrentPosition();
        }

        @Override
        public void seekTo(int pos) {
            mMediaPlayer.seekTo(pos);
        }

        @Override
        public boolean isPlaying() {
            return mMediaPlayer.isPlaying();
        }

        @Override
        public int getBufferPercentage() {
            return mBufferPercent;
        }

        @Override
        public boolean canPause() {
            return true;
        }

        @Override
        public boolean canSeekBackward() {
            return true;
        }

        @Override
        public boolean canSeekForward() {
            return true;
        }

        @Override
        public int getAudioSessionId() {
            return mMediaPlayer.getAudioSessionId();
        }

        @Override
        public void onBufferingUpdate(android.media.MediaPlayer mp, int percent) {
            mBufferPercent = percent;
        }
    }
}
