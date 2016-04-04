package com.hillhouse.sriparnachakraborty.mplayer.players;

import com.hillhouse.sriparnachakraborty.mplayer.models.Media;
import android.content.Context;
import android.view.View;
import java.util.List;

/**
 * Created by sriparnachakraborty on 30/03/16.
 */
public interface MediaPlayer {
    void playMedia(Context context, List<Media>media);
}
