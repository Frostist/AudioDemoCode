package com.example.audiodemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {
//making it public for all classes
    MediaPlayer mediaPlayer;
    SeekBar volSeekBar;
    SeekBar playSeekBar;
    String TAG = "Project";
    AudioManager audioManager;


    public void play(View view) {
//Starts the music
        mediaPlayer.start();
    }

    public void pause(View view) {
//pauses the music
        mediaPlayer.pause();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Locating the volume seek bar in the activity
        volSeekBar = findViewById(R.id.volSeekBar);
        //Setting the phone system services up to talk to the app
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);


        //Setting the max volume for our app
        assert audioManager != null;
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        //Getting the current volume fo the phone for the app
        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);


        //makes the media player and locates the file
        mediaPlayer = MediaPlayer.create(this, R.raw.marbles);


        //Linking max volume fo in app string to phone volume
        volSeekBar.setMax(maxVolume);
        //Sets the volume fo teh app to the system volume
        volSeekBar.setProgress(currentVolume);


        //Setting up volSeekBar variable
        volSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress1, boolean fromUser) {
                //Me Logging
                Log.w(TAG, "Value Changed: " + progress1);
                //Setting the stream up
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress1, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        //Setting up the play bar//

        //Finding the play seek bar in the activity
        playSeekBar = findViewById(R.id.playSeekBar);
        //Settign the max of the play abr to the end of the audio clip
        playSeekBar.setMax(mediaPlayer.getDuration());


        //Setting up play seek bar variable
        playSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress2, boolean fromUser) {
                Log.w(TAG, "Value Changed: " + progress2);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        new Timer();

    }
}