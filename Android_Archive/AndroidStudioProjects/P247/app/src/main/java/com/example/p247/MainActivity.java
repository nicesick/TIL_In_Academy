package com.example.p247;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button img1Button;
    private Button img2Button;
    private Button img3Button;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img1Button = findViewById(R.id.img1Button);
        img2Button = findViewById(R.id.img2Button);
        img3Button = findViewById(R.id.img3Button);
        resultText = findViewById(R.id.resultText);

        img1Button.setOnClickListener(clickListener);
        img2Button.setOnClickListener(clickListener);
        img3Button.setOnClickListener(clickListener);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int buttonId = view.getId();
            Intent intent = new Intent(MainActivity.this, Main2Activity.class);

            switch (buttonId) {
                case R.id.img1Button:
                    intent.putExtra("imgIndex", 1);
                    break;
                case R.id.img2Button:
                    intent.putExtra("imgIndex", 2);
                    break;
                case R.id.img3Button:
                    intent.putExtra("imgIndex", 3);
                    break;
            }

            startActivityForResult(intent, 100);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == RESULT_OK) {
            String imgName = data.getStringExtra("imgName");
            resultText.setText(imgName);
        }
    }
}

