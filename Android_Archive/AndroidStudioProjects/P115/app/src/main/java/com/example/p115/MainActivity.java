package com.example.p115;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btclick(View view) {
        Button bt2 = findViewById(R.id.button28);
        bt2.setBackgroundColor(Color.RED);
        bt2.setText("Clicked");
    }
}
