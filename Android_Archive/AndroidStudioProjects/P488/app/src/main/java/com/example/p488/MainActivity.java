package com.example.p488;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button controlButton;
    private boolean statusButton = true;

    private TextView speed;
    private TextView rpm;
    private TextView averageRate;

    private CarInfoHandler handlerForSpeed;
    private CarInfoHandler handlerForRpm;

    private CarInfoThread carInfoThreadForSpeed;
    private CarInfoThread carInfoThreadForRpm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapValues();
        makeEvents();
    }

    private void makeEvents() {
        controlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (statusButton) {
                    initValues();
                    startThreads();

                    statusButton = !statusButton;
                    controlButton.setText("STOP");
                }

                else {
                    stopThreads();
                    eraseValues();

                    statusButton = !statusButton;
                    controlButton.setText("START");
                }
            }
        });

        rpm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int speedInfo = handlerForSpeed.getTargetNum();
                int rpmInfo = handlerForRpm.getTargetNum();

                setAverageRate(speedInfo, rpmInfo);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        speed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int speedInfo = handlerForSpeed.getTargetNum();
                int rpmInfo = handlerForRpm.getTargetNum();

                setAverageRate(speedInfo, rpmInfo);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    private void setAverageRate(int speedInfo, int rpmInfo) {
        averageRate.setText(rpmInfo / speedInfo + "");
    }

    private void stopThreads() {
        carInfoThreadForSpeed.changeStatus();
        carInfoThreadForRpm.changeStatus();
    }

    private void startThreads() {
        carInfoThreadForSpeed.start();
        carInfoThreadForRpm.start();
    }

    private void eraseValues() {
        handlerForSpeed = null;
        handlerForRpm = null;

        carInfoThreadForSpeed = null;
        carInfoThreadForRpm = null;
    }

    private void initValues() {
        handlerForSpeed = new CarInfoHandler(speed);
        handlerForRpm = new CarInfoHandler(rpm);

        carInfoThreadForSpeed = new CarInfoThread();
        carInfoThreadForRpm = new CarInfoThread();

        carInfoThreadForSpeed.setMinMax(0, 200);
        carInfoThreadForSpeed.setHandler(handlerForSpeed);

        carInfoThreadForRpm.setMinMax(1700, 6700);
        carInfoThreadForRpm.setHandler(handlerForRpm);
    }

    private void mapValues() {
        controlButton = findViewById(R.id.controllButton);
        speed = findViewById(R.id.speed);
        rpm = findViewById(R.id.rpm);
        averageRate = findViewById(R.id.averageRate);
    }
}