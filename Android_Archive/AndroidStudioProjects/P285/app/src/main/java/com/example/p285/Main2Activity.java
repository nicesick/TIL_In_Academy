package com.example.p285;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {
    private Button toCustomerButton;
    private Button toSalesButton;
    private Button toProductButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        toCustomerButton = findViewById(R.id.toCustomerButton);
        toSalesButton = findViewById(R.id.toSalesButton);
        toProductButton = findViewById(R.id.toProductButton);

        toCustomerButton.setOnClickListener(clickListener);
        toSalesButton.setOnClickListener(clickListener);
        toProductButton.setOnClickListener(clickListener);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int buttonId = view.getId();

            Intent intent = null;

            switch (buttonId) {
                case R.id.toCustomerButton:
                    intent = new Intent(Main2Activity.this, Main3Activity.class);
                    break;
                case R.id.toSalesButton:
                    intent = new Intent(Main2Activity.this, Main4Activity.class);
                    break;
                case R.id.toProductButton:
                    intent = new Intent(Main2Activity.this, Main5Activity.class);
                    break;
            }

            startActivityForResult(intent, 100);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == RESULT_OK) {
            boolean isToLogin = data.getBooleanExtra("isToLogin", false);

            if (isToLogin) {
                finish();
            }
        }
    }
}

