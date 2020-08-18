package com.example.bg_stats;

import android.os.SystemClock;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Chronometer;
import android.widget.Toast;

public class ChronometerActivity extends AppCompatActivity{
    private Chronometer chronometer;
    private long pauseOffset;
    private boolean running;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide the title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_chronometer);

        chronometer = findViewById(R.id.timer);
        chronometer.setFormat("%s");
        chronometer.setBase(SystemClock.elapsedRealtime());

    }
    public void startChronometer(View v) {
        if (!running) {
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            running = true;
        }
    }
    public void pauseChronometer(View v) {
        if (running) {
            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
            running = false;
        }
    }
    public void resetChronometer(View v) {
        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 0;
    }
}
