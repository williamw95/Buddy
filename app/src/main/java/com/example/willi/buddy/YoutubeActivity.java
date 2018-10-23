package com.example.willi.buddy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;

import com.google.android.youtube.player.YouTubeBaseActivity;

public class YoutubeActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private static final String TAG = "YoutubeActivity";
    static final String GOOGLE_API_KEY = "AIzaSyC6RAXW1vTvzY18HGf_XvKPrIn7oDqgPqw";
   // static final String YOUTUBE_VIDEO_ID = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        Log.d(TAG,"provider is " + provider.getClass().toString());
        Toast.makeText(this,"Initialized youtube player successfully", Toast.LENGTH_LONG).show();

        if(!wasRestored){
           // youTubePlayer.cueVideo();
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }
}
