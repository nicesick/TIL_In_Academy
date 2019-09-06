package com.example.p499;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button controlButton;
    private TextView speed;
    private TextView rpm;
    private ImageView statusImg;
    private CarInfoTask carInfoTaskForSpeed;
    private CarInfoTask carInfoTaskForRpm;
    private boolean taskStatus = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapValues();
        makeEvent();
    }

    private void stopTasks() {
        carInfoTaskForSpeed.cancel(true);
        carInfoTaskForRpm.cancel(true);
    }

    private void startTasks() {
        carInfoTaskForSpeed = new CarInfoTask(speed, controlButton, 1000);
        carInfoTaskForSpeed.setMinMax(0, 200);

        carInfoTaskForRpm = new CarInfoTask(rpm, controlButton, 2500);
        carInfoTaskForRpm.setMinMax(1700, 6200);

        carInfoTaskForSpeed.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        carInfoTaskForRpm.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private void makeEvent() {
        controlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (taskStatus == false) {
                    startTasks();
                    taskStatus = true;
                }

                else {
                    stopTasks();
                    taskStatus = false;
                }
            }
        });

        speed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int speedInfo = Integer.parseInt(charSequence.toString());

                if (speedInfo < 30 && speedInfo >= 0) {
                    statusImg.setImageResource(R.drawable.icon8);
                }

                else if (speedInfo >= 30 && speedInfo < 100) {
                    statusImg.setImageResource(R.drawable.icon9);
                }

                else {
                    statusImg.setImageResource(R.drawable.icon10);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    private void mapValues() {
        controlButton = findViewById(R.id.contolButton);
        speed = findViewById(R.id.speed);
        rpm = findViewById(R.id.rpm);
        statusImg = findViewById(R.id.statusImg);
    }
}
