package com.hillhouse.sriparnachakraborty.mplayer.factories;

import android.support.v4.app.FragmentActivity;

import com.hillhouse.sriparnachakraborty.mplayer.constants.MediaConstants;
import com.hillhouse.sriparnachakraborty.mplayer.presenters.AudioPresenter;
import com.hillhouse.sriparnachakraborty.mplayer.presenters.MediaPresenter;
import com.hillhouse.sriparnachakraborty.mplayer.presenters.VideoPresenter;

/**
 * Created by sriparnachakraborty on 30/03/16.
 */
public class MediaPresenterFactory {


    public static MediaPresenter getMediaPresenter(FragmentActivity activity, int type) {
        switch (type){
            case MediaConstants.MEDIA_TYPE_AUDIO :
                return new AudioPresenter(activity);

            case MediaConstants.MEDIA_TYPE_VIDEO:
                return new VideoPresenter(activity);

            default:
                return null;

        }
    }
}
