package com.hillhouse.sriparnachakraborty.mplayer.presenters;

import android.content.Context;

import com.hillhouse.sriparnachakraborty.mplayer.models.Media;

import java.util.List;

/**
 * Created by sriparnachakraborty on 30/03/16.
 */
public interface MediaPresenter {
    //TODO: allow pagination

    void getMediaItems(Callback callback);
    void onMediaItemClicked(Media media);

    public interface Callback{
        void onItemsAvailable(List<Media> items);
    }
}
