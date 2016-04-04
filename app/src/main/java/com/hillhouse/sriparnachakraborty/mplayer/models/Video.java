package com.hillhouse.sriparnachakraborty.mplayer.models;

/**
 * Created by sriparnachakraborty on 30/03/16.
 */
public class Video implements Media {
    private final long mId;
    private final String mTitle;
    private final String mArtist;
    private final String mPath;

    public Video(long id, String title, String artist, String path){
        mId = id;
        mTitle = title;
        mArtist = artist;
        mPath = path;
    }

    @Override
    public long getId() {
        return mId;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public String getArtist() {
        return mArtist;
    }

    @Override
    public String getPath() {
        return mPath;
    }
}
