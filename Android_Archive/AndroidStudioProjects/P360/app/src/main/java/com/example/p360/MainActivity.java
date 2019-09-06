package com.example.p360;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private Button buttonForProgressBar;
    private Button buttonForSeekBar;
    private Boolean statusForProgressBar;
    private Boolean statusForSeekBar;

    private ProgressBar progressBar;

    private ProgressBarService progressBarService;
    private SeekBarService seekBarService;

    private ServiceConnection connForProgressBar = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            ProgressBarService.MyBinder mb = (ProgressBarService.MyBinder) iBinder;
            progressBarService = mb.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            progressBarService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(MainActivity.this, ProgressBarService.class);
        bindService(intent, connForProgressBar, BIND_AUTO_CREATE);

        buttonForProgressBar = findViewById(R.id.buttonForProgressBar);
        buttonForSeekBar = findViewById(R.id.buttonForSeekBar);

        statusForProgressBar = true;
        statusForSeekBar = true;

        progressBar = findViewById(R.id.progressBar);

        buttonForProgressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (statusForProgressBar) {
                    progressBarService.btn1();
                    statusForProgressBar = false;
                }

                else {
                    progressBarService.btn2();
                    statusForProgressBar = true;
                }
            }
        });

        buttonForSeekBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.d("[MainActivity]", "onNewIntent......................");
        process(intent);
    }

    private void process(Intent intent) {
        if (intent != null) {
            String type = intent.getStringExtra("type");
            int status = intent.getIntExtra("status", 0);

            Log.d("[MainActivity]", "type : " + type);
            Log.d("[MainActivity]", "status : " + status);

            if (type.equals("progressBar")) {
                progressBar.setProgress(status);
            }
        }
    }
}
