package com.example.p488;

import android.os.Message;

import java.util.Random;

public class CarInfoThread extends Thread {
    private int min = 0;
    private int max = 0;
    private boolean status = true;
    private CarInfoHandler carInfoHandler;

    public void changeStatus() {
        status = !status;
    }

    public void setMinMax(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public void setHandler(CarInfoHandler carInfoHandler) {
        this.carInfoHandler = carInfoHandler;
    }

    @Override
    public void run() {
        Random r = new Random();

        while (status) {
            int randomNum = r.nextInt(max - min) + min;

            Message message = carInfoHandler.obtainMessage();
            message.obj = randomNum;
            carInfoHandler.sendMessage(message);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
