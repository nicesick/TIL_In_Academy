package com.example.p360;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class ProgressBarService extends Service {
    public class MyBinder extends Binder {
        public ProgressBarService getService() {
            return ProgressBarService.this;
        }
    }

    IBinder iBinder = new MyBinder();
    private Runnable run;
    private Thread t = null;
    private Boolean status = false;
    private Intent intentForProgressBar;

    public ProgressBarService() {
        Log.d("[progressBar]","ProgressBarService");
    }

    @Override
    public IBinder onBind(final Intent intent) {
        Log.d("[progressBarService]","onBind...................");

        intentForProgressBar = new Intent(ProgressBarService.this, MainActivity.class);
        intentForProgressBar.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_SINGLE_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TOP);

        run = new Runnable() {

            @Override
            public void run() {
                for (int i = 0 ; i < 100 ; i++) {
                    try {
                        Thread.sleep(1000);

                        while(status);

                        intentForProgressBar.putExtra("type", "progressBar");
                        intentForProgressBar.putExtra("status", i);

                        startActivity(intentForProgressBar);

                        Log.d("[progressBarService]", "run" + i + "...........");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                t = null;
            }
        };

        return iBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("[progressBarService]","onStartCommand....................");

        return super.onStartCommand(intent, flags, startId);
    }

    public void btn1() {
        Log.d("[progressBarService]", "btn1........................");

        if (t == null) {
            t = new Thread(run);
            t.start();
        }

        else {
            status = false;
        }
    }

    public void btn2() {
        Log.d("[progressBarService]", "btn2........................");
        status = true;
    }
}
