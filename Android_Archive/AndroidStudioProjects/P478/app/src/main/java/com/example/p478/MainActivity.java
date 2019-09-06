package com.example.p478;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Button button;
    private Thread t;
    private Runnable r;
    private CountHandler countHandler;
    private Handler handler;
    private Button button2;
    private ProgressDialog progressDialog;

    private class CountHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            Bundle bundle = msg.getData();
            int value = bundle.getInt("cnt");

            textView.setText(value + "");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countHandler = new CountHandler();
        handler = new Handler();

        r = new Runnable() {
            @Override
            public void run() {
                for (int i = 0 ; i < 10; i++) {
                    Message message = countHandler.obtainMessage();
                    Bundle bundle = new Bundle();
                    bundle.putInt("cnt", i);
                    bundle.putBoolean("status", false);

                    message.setData(bundle);
                    countHandler.sendMessage(message);

                    try {
                        Thread.sleep(1000);
                        Log.d("[Thread]", "" + i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                t = null;

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        button.setEnabled(true);
                    }
                });
            }
        };

        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (t == null) {
                    t = new Thread(r);
                    t.start();

                    button.setEnabled(false);
                }
            }
        });

        progressDialog = new ProgressDialog(MainActivity.this);

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Dialog");
                builder.setMessage("5 seconds....");
                builder.setPositiveButton("NEXT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        progressDialog.show();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                textView.setText("NEXT OK");
                                progressDialog.dismiss();
                            }
                        }, 5000);
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}
