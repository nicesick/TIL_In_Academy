package com.example.p247;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Main2Activity extends AppCompatActivity {
    private ImageView imageView;
    private int imgId;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        imageView = findViewById(R.id.imageView);
        backButton = findViewById(R.id.backButton);

        Intent intent = getIntent();

        imgId = intent.getIntExtra("imgIndex", 0);

        switch(imgId) {
            case 1:
                imageView.setImageResource(R.drawable.icon1);
                break;
            case 2:
                imageView.setImageResource(R.drawable.icon3);
                break;
            case 3:
                imageView.setImageResource(R.drawable.icon4);
                break;
                default:
                    break;
        }

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentForResult = new Intent();

                switch(imgId) {
                    case 1:
                        intentForResult.putExtra("imgName", "icon1");
                        break;
                    case 2:
                        intentForResult.putExtra("imgName", "icon3");
                        break;
                    case 3:
                        intentForResult.putExtra("imgName", "icon4");
                        break;
                        default:
                            intentForResult.putExtra("imgName", "");
                            break;
                }

                setResult(RESULT_OK, intentForResult);
                finish();
            }
        });
    }
}
