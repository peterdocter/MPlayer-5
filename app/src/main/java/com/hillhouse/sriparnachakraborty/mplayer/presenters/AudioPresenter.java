package com.hillhouse.sriparnachakraborty.mplayer.presenters;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.hillhouse.sriparnachakraborty.mplayer.constants.MediaConstants;
import com.hillhouse.sriparnachakraborty.mplayer.factories.MediaPlayerFactory;
import com.hillhouse.sriparnachakraborty.mplayer.models.Media;
import com.hillhouse.sriparnachakraborty.mplayer.models.Song;
import com.hillhouse.sriparnachakraborty.mplayer.players.MediaPlayer;
import com.hillhouse.sriparnachakraborty.mplayer.players.SongPlayer;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by sriparnachakraborty on 30/03/16.
 */

public class AudioPresenter implements MediaPresenter, LoaderManager.LoaderCallbacks<Cursor> {
    private static final int SONG_LOADER_ID = 2;

    private Callback mCallback = null;
    private  FragmentActivity mActivity = null;
    private MediaPlayer mSongPlayer = null;

    public AudioPresenter(FragmentActivity activity){
        mActivity = activity;
        View decorView = mActivity.getWindow().getDecorView();
        mSongPlayer = MediaPlayerFactory.getMediaplayer(MediaConstants.MEDIA_TYPE_AUDIO,decorView);
    }

    @Override
    public void getMediaItems(Callback callback) {
        mCallback = callback;
        mActivity.getSupportLoaderManager().initLoader(SONG_LOADER_ID, null, this);
    }

    @Override
    public void onMediaItemClicked(Media media) {
        LinkedList<Media> songs = new LinkedList<>();
        songs.add(media);
        mSongPlayer.playMedia(mActivity, songs);
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        switch (id){
            case SONG_LOADER_ID:
                if(mActivity != null){
                    CursorLoader loader = new CursorLoader(mActivity);
                    loader.setUri(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
                    loader.setProjection(null);
                    loader.setSelection(null);
                    loader.setSelectionArgs(null);
                    loader.setSortOrder(null);

                    return loader;

                }
                return null;

            default:
                return null;

        }

    }

    @Override
    public void onLoaderReset(Loader loader){
        if(loader.getId() == SONG_LOADER_ID){
            if(mCallback != null){
                mCallback.onItemsAvailable(new LinkedList<Media>());
            }
        }
    }

    @Override
    public void onLoadFinished(Loader loader, Cursor cursor){
        if(loader.getId() == SONG_LOADER_ID){
            List<Media> mediaList = new ArrayList<>();

            if(cursor != null && cursor.getCount() > 0){
                int mediaIdIndex = cursor.getColumnIndex(MediaStore.Audio.Media._ID);
                int mediaNameIndex = cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME);
                int mediaArtistName = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
                int mediaPath = cursor.getColumnIndex(MediaStore.Audio.Media.DATA);

                if(cursor.moveToFirst()){
                    do{
                        long id = cursor.getLong(mediaIdIndex);
                        String title = cursor.getString(mediaNameIndex);
                        String artist = cursor.getString(mediaArtistName);
                        String path = cursor.getString(mediaPath);

                        Song song = new Song(id, title, artist, path);
                        mediaList.add(song);
                    }while (cursor.moveToNext());
                }
            }

            if(mCallback != null){
                mCallback.onItemsAvailable(mediaList);
            }
        }
    }
}
