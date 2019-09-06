package com.example.p158;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    protected ImageView img;
    protected ConstraintLayout loginLayer;
    protected ConstraintLayout registerLayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUi();
    }

    private void setUi() {
        img = findViewById(R.id.img);

        loginLayer = findViewById(R.id.loginLayer);
        registerLayer = findViewById(R.id.registerLayer);

        disable();
    }

    private void disable() {
        loginLayer.setVisibility(View.INVISIBLE);
        registerLayer.setVisibility(View.INVISIBLE);
    }

    public void clickBtn(View view) {
        if (view.getId() == R.id.btn1) {
            disable();
            img.setImageResource(R.drawable.icon1);
        }

        else if (view.getId() == R.id.btn2) {
            disable();
            img.setImageResource(R.drawable.icon2);
        }

        else if (view.getId() == R.id.btn3) {
            loginLayer.setVisibility(View.VISIBLE);
            registerLayer.setVisibility(View.INVISIBLE);

            img.setImageResource(R.drawable.icon3);
        }

        else if (view.getId() == R.id.btn4) {
            loginLayer.setVisibility(View.INVISIBLE);
            registerLayer.setVisibility(View.VISIBLE);

            img.setImageResource(R.drawable.icon4);
        }
    }
}
