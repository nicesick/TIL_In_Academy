package com.example.p485;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Button button;
    private MyThread myThread;
    private MainHandler mainHandler;

    private class MainHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            int data = (int) msg.obj;
            textView.setText("" + data);
        }
    }

    private class MyThread extends Thread {
        int cnt = 0;

        public void setCnt(int cnt) {
            this.cnt = cnt;
        }

        @Override
        public void run() {
            for (int i = 0 ; i < cnt ; i++) {
                try {
                    Thread.sleep(1000);
                    Log.d("[T]", "-----------" + i);

                    Message message = mainHandler.obtainMessage();
                    message.obj = i;
                    mainHandler.sendMessage(message);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        myThread = new MyThread();
        mainHandler = new MainHandler();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myThread.setCnt(10);
                myThread.start();
            }
        });
    }
}
