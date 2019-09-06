package com.example.p353;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Intent intent;
    private TextView textView;
    private ImageView imageView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);
        progressBar = findViewById(R.id.progressBar);
    }

    public void clickBtn(View view) {
        intent = new Intent(MainActivity.this, MyService.class);
        startService(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        process(intent);
    }

    public void process(Intent intent) {
        if (intent != null) {
            int data = intent.getIntExtra("cmd", 0);

            if (data % 2 == 0) {
                imageView.setImageResource(R.drawable.icon1);
            }

            else {
                imageView.setImageResource(R.drawable.icon3);
            }

            textView.setText(data + "");

            progressBar.setProgress(data);
        }
    }
}
