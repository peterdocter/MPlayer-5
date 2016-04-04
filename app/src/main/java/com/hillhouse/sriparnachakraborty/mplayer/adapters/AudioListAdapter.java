package com.hillhouse.sriparnachakraborty.mplayer.adapters;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hillhouse.sriparnachakraborty.mplayer.R;
import com.hillhouse.sriparnachakraborty.mplayer.constants.MediaConstants;
import com.hillhouse.sriparnachakraborty.mplayer.factories.MediaPresenterFactory;
import com.hillhouse.sriparnachakraborty.mplayer.models.Media;
import com.hillhouse.sriparnachakraborty.mplayer.presenters.MediaPresenter;

import java.util.List;

/**
 * Created by sriparnachakraborty on 31/03/16.
 */
public class AudioListAdapter extends RecyclerView.Adapter<AudioListAdapter.AudioListViewHolder> {

    private FragmentActivity mContext = null;
    private MediaPresenter mMediaPresenter = null;
    private List<Media> mMediaItems = null;
    private LayoutInflater mLayoutInflater = null;

    public AudioListAdapter(FragmentActivity context){
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        mMediaPresenter = MediaPresenterFactory.getMediaPresenter(mContext, MediaConstants.MEDIA_TYPE_AUDIO);
        mMediaPresenter.getMediaItems(new MediaPresenter.Callback() {
            @Override
            public void onItemsAvailable(List<Media> items) {
                mMediaItems = items;
                AudioListAdapter.this.notifyDataSetChanged();
            }
        });
    }

    @Override
    public AudioListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.media_item_layout, parent, false);
        return new AudioListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AudioListViewHolder holder, final int position) {
        holder.mTitleView.setText(mMediaItems.get(position).getTitle());
        holder.mArtist.setText("sri");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMediaPresenter.onMediaItemClicked(mMediaItems.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mMediaItems == null){
            return 0;
        }

        return mMediaItems.size();
    }

    static class AudioListViewHolder extends RecyclerView.ViewHolder{

        private TextView mTitleView = null;
        private TextView mArtist = null;

        public AudioListViewHolder(View view){
            super(view);
            mTitleView = (TextView)view.findViewById(R.id.title);
            mArtist = (TextView)view.findViewById(R.id.artist);
        }
    }
}
