package com.hillhouse.sriparnachakraborty.mplayer.listfragments;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;

import com.hillhouse.sriparnachakraborty.mplayer.R;
import com.hillhouse.sriparnachakraborty.mplayer.adapters.AudioListAdapter;

/**
 * Created by sriparnachakraborty on 31/03/16.
 */
public class AudioListFragment extends Fragment{
    private AudioListAdapter mAdapter = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        RecyclerView recyclerView = (RecyclerView)inflater.inflate(R.layout.media_list_layout, parent, false);
        mAdapter = new AudioListAdapter(getActivity());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;
    }
}
