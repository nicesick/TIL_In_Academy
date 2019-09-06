package com.example.p488;

import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class CarInfoHandler extends Handler {
    private TextView targetText;
    private int info;

    public int getTargetNum() {
        return info;
    }

    public CarInfoHandler(TextView targetText) {
        this.targetText = targetText;
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
        info = (int) msg.obj;
        targetText.setText("" + info);
    }
}
