package com.example.p168;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import static java.sql.Types.NULL;

public class MainActivity extends AppCompatActivity {
    private ImageView upImg;
    private ImageView downImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        upImg = findViewById(R.id.upImg);
        downImg = findViewById(R.id.downImg);
    }

    public void clickUpDownButton(View view) {
        if (view.getId() == R.id.upButton) {
            upImg.setImageResource(R.drawable.icon1);
            downImg.setImageResource(NULL);
        }

        else if (view.getId() == R.id.downButton) {
            upImg.setImageResource(NULL);
            downImg.setImageResource(R.drawable.icon1);
        }
    }
}
