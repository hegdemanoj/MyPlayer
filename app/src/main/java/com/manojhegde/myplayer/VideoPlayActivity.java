package com.manojhegde.myplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.Arrays;


public class VideoPlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);


        Bundle bundle = getIntent().getExtras();
        String videoFile = bundle.getString("videofile");

        VideoView videoview= (VideoView)findViewById(R.id.videoView);
        videoview.setVideoPath(videoFile);

        MediaController mediaController=new MediaController(this);
        mediaController.setAnchorView(videoview);
        videoview.setMediaController(mediaController);
        videoview.start();





    }
}
