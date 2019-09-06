package com.example.p499;

import android.os.AsyncTask;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class CarInfoTask extends AsyncTask<Integer ,Integer, Integer> {
    private TextView targetText;
    private Button controlButton;

    private int min = 0;
    private int max = 0;

    private int timeInterval = 0;

    public  CarInfoTask(TextView textView, Button controlButton, int timeInterval) {
        this.targetText = textView;
        this.controlButton = controlButton;
        this.timeInterval = timeInterval;
    }

    public void setMinMax(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    protected void onPreExecute() {
        controlButton.setText("STOP");
    }

    @Override
    protected Integer doInBackground(Integer... integers) {
        Random r = new Random();
        int randomNum = 0;

        while(isCancelled() == false) {
            try {
                Thread.sleep(timeInterval);
                randomNum = r.nextInt(max - min) + min;

                publishProgress(randomNum);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return randomNum;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        targetText.setText(values[0] + "");
    }

    @Override
    protected void onPostExecute(Integer integer) {
        targetText.setText(integer + "");
        controlButton.setText("START");
    }

    @Override
    protected void onCancelled(Integer integer) {
        targetText.setText(integer + "");
        controlButton.setText("START");
    }
}
