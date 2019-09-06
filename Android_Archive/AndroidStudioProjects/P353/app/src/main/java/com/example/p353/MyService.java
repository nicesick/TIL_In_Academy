package com.example.p353;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private Thread t;

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d("[MyService]", "onCreate...................");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d("[MyService]", "onDestroy...................");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        final Intent sendIntent = new Intent(MyService.this, MainActivity.class);
        sendIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_SINGLE_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TOP);

        Runnable run = new Runnable() {
            boolean flag = true;

            @Override
            public void run() {
                for (int i = 0 ; i < 30 ; i++) {
                    if (flag == false) {
                        break;
                    }

                    Log.d("[MyService]", "onStartCommand...................");
                    sendIntent.putExtra("cmd", i);
                    startActivity(sendIntent);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        t = new Thread(run);
        t.setDaemon(false);
        t.start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
