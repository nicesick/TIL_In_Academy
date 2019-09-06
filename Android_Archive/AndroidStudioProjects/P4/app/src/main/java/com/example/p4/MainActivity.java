package com.example.p4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private TextView textView2;
    private Thread t1;
    private Thread t2;
    private Runnable r1;
    private Runnable r2;
    private Button b1;
    private Button b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);

        b1 = findViewById(R.id.button);
        b2 = findViewById(R.id.button2);

        r1 = new Runnable() {
            @Override
            public void run() {
                for (int i = 0 ; i < 10; i++) {
                    try {
                        Thread.sleep(1000);
                        Log.d("[T]","----------" + i);

                        final int i2 = i;

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textView.setText(i2 + "");
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        b1.setEnabled(true);
                    }
                });

                t1 = null;
            }
        };

        r2 = new Runnable() {
            @Override
            public void run() {
                for (int i = 10 ; i < 20; i++) {
                    try {
                        Thread.sleep(1000);
                        Log.d("[T]","*********" + i);

                        final int i2 = i;

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textView2.setText(i2 + "");
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        b2.setEnabled(true);
                    }
                });

                t2 = null;
            }
        };
    }

    public void clickBtn(View view) {
        if (t1 == null) {
            t1 = new Thread(r1);
            t1.start();

            b1.setEnabled(false);
        }
    }

    public void clickBtn2(View view) {
        if (t2 == null) {
            t2 = new Thread(r2);
            t2.start();

            b2.setEnabled(false);
        }
    }
}
