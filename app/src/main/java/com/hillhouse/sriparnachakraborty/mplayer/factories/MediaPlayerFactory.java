package com.hillhouse.sriparnachakraborty.mplayer.factories;

import android.content.Context;
import android.view.View;

import com.hillhouse.sriparnachakraborty.mplayer.constants.MediaConstants;
import com.hillhouse.sriparnachakraborty.mplayer.players.MediaPlayer;
import com.hillhouse.sriparnachakraborty.mplayer.players.SongPlayer;
import com.hillhouse.sriparnachakraborty.mplayer.players.VideoPlayer;

/**
 * Created by sriparnachakraborty on 30/03/16.
 */
public class MediaPlayerFactory {
    public static MediaPlayer getMediaplayer(int type, View view){
        switch (type){
            case MediaConstants.MEDIA_TYPE_AUDIO:
                return new SongPlayer(view);

            case MediaConstants.MEDIA_TYPE_VIDEO:
                return new VideoPlayer();

            default:
                return null;
        }
    }
}
