package com.example.musicbox;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Thread thread;
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


        seekBar.setMax(mediaPlayer.getDuration());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress);
                }

                SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
//
//                int currenPos = mediaPlayer.getCurrentPosition();
//                int duration = mediaPlayer.getDuration();
//
//                leftTime.setText(
//                        String.valueOf(
//                                dateFormat.format(new Date(currenPos))
//                        )
//                      );
//                rightTime.setText(
//                        String.valueOf(
//                                dateFormat.format(new Date(duration - currenPos))
//                        )
//                        );

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {


            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }


        });
    }

    public void updateThread() {
        thread = new Thread() {
            @Override
            public void run() {
                try {
                    while (mediaPlayer != null && mediaPlayer.isPlaying()) {
                        Thread.sleep(50);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                int newPos = mediaPlayer.getCurrentPosition();
                                int newMax = mediaPlayer.getDuration();
                                seekBar.setMax(newMax);
                                seekBar.setProgress(newPos);

                                leftTime.setText(
                                        String.valueOf(
                                                new SimpleDateFormat("mm:ss")
                                                        .format(new Date(mediaPlayer.getCurrentPosition())

                                                        ).substring(1)
                                        )

                                );

                                rightTime.setText(
                                        String.valueOf(

                                                new SimpleDateFormat("mm:ss")
                                                        .format(new Date(mediaPlayer.getDuration() - mediaPlayer.getCurrentPosition()))
                                        ).substring(1)
                                );

                            }
                        });
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();

    }


    public void setUpUI() {

        mediaPlayer = new MediaPlayer();
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.song);
        artistImage = (ImageView) findViewById(R.id.imageView);
        leftTime = (TextView) findViewById(R.id.leftTime);
        rightTime = (TextView) findViewById(R.id.rightTime);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        prevBtn = (Button) findViewById(R.id.prev);
        playBtn = (Button) findViewById(R.id.pause);
        nextBtn = (Button) findViewById(R.id.next);
        prevBtn.setOnClickListener(this);
        playBtn.setOnClickListener(this);
        nextBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.prev:

                backMusic();

                break;
            case R.id.pause:
                if (mediaPlayer.isPlaying()) {
                    pauseMuisc();
                } else {
                    startMusic();
                }
                break;
            case R.id.next:
                nextMusic();
                break;
        }
    }

    public void pauseMuisc() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
            playBtn.setBackgroundResource(android.R.drawable.ic_media_pause);
        }
    }

    public void startMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.start();
            updateThread();
            playBtn.setBackgroundResource(android.R.drawable.ic_media_play);
        }
    }

    public void backMusic(){
        if(mediaPlayer.isPlaying()){
            mediaPlayer.seekTo(0);
        }
    }

    public void nextMusic(){
        if(mediaPlayer.isPlaying()){
            mediaPlayer.seekTo(mediaPlayer.getDuration()-1000);
        }
    }

    @Override
    protected void onDestroy() {
        if(mediaPlayer != null && mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer =null;
        }
        thread.interrupt();
        thread = null;
        super.onDestroy();
    }
}