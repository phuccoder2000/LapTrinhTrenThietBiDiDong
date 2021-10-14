package com.example.lab03_bai03;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    SeekBar sbRed,sbGreen,sbBlue;
    TextView tvRed,tvGreen,tvBlue,tvRGB,tvCMY;
    Integer r=0,g=0,b=0,c=0,m=0,y=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Mapped();
    }
    protected void Mapped(){
        tvRGB=findViewById(R.id.tvRGB);
        tvCMY=findViewById(R.id.tvCMY);

        sbRed=findViewById(R.id.sbRed);
        sbGreen=findViewById(R.id.sbGreen);
        sbBlue=findViewById(R.id.sbBlue);

        tvRed=findViewById(R.id.tvRed);
        tvGreen=findViewById(R.id.tvGreen);
        tvBlue=findViewById(R.id.tvBlue);

        tvRed.setText("R = "+0);
        tvGreen.setText("G = "+0);
        tvBlue.setText("B = "+0);

        sbRed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvRed.setText("R = "+String.valueOf(progress));
                r=progress;
                c=255-progress;
                tvRGB.setBackgroundColor(Color.rgb(r,g,b));
                tvCMY.setBackgroundColor(Color.rgb(c,m,y));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        sbGreen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvGreen.setText("G = "+String.valueOf(progress));
                g=progress;
                m=255-progress;
                tvRGB.setBackgroundColor(Color.rgb(r,g,b));
                tvCMY.setBackgroundColor(Color.rgb(c,m,y));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sbBlue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvBlue.setText("B = "+String.valueOf(progress));
                b=progress;
                y=255-progress;
                tvRGB.setBackgroundColor(Color.rgb(r,g,b));
                tvCMY.setBackgroundColor(Color.rgb(c,m,y));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}