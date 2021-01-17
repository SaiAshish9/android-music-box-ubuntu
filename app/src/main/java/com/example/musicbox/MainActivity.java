package com.example.musicbox;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MediaPlayer mediaPlayer;
    private ImageView artistImage;
    private TextView leftTime;
    private TextView rightTime;
    private SeekBar seekBar;
    private Button prevBtn;
    private Button playBtn;
    private Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpUI();
    }

    public void setUpUI() {

        mediaPlayer = new MediaPlayer();
        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.song);

        artistImage = (ImageView) findViewById(R.id.imageView);
        leftTime = (TextView) findViewById(R.id.leftTime);
        rightTime = (TextView) findViewById(R.id.rightTime);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        prevBtn = (Button) findViewById(R.id.prev);
        playBtn = (Button) findViewById(R.id.pause);
        nextBtn= (Button) findViewById(R.id.next);

        prevBtn.setOnClickListener(this);
        playBtn.setOnClickListener(this);
        nextBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.prev:
                break;
            case R.id.pause:
                if(mediaPlayer.isPlaying()){
                    pauseMuisc();
                }else{
                    startMusic();
                }
                break;
            case R.id.next:
                break;
        }
    }

    public void pauseMuisc(){
        if(mediaPlayer != null){
            mediaPlayer.pause();
            playBtn.setBackgroundResource(android.R.drawable.ic_media_pause);
        }
    }

    public void startMusic(){
        if(mediaPlayer != null){
            mediaPlayer.start();
            playBtn.setBackgroundResource(android.R.drawable.ic_media_play);
        }
    }

}