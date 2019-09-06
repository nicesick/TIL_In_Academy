package com.example.p285;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main4Activity extends AppCompatActivity {
    private Button salesToLoginButton;
    private Button salesToMenuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        salesToMenuButton = findViewById(R.id.salesToMenuButton);
        salesToLoginButton = findViewById(R.id.salesToLoginButton);

        salesToLoginButton.setOnClickListener(clickListener);
        salesToMenuButton.setOnClickListener(clickListener);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int buttonId = view.getId();
            Intent intent = new Intent();

            switch (buttonId) {
                case R.id.salesToMenuButton:
                    intent.putExtra("isToLogin", false);

                    break;
                case R.id.salesToLoginButton:
                    intent.putExtra("isToLogin", true);

                    break;
            }

            setResult(RESULT_OK, intent);

            finish();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Intent intent = new Intent();
        intent.putExtra("isToLogin", false);

        setResult(RESULT_OK, intent);

        finish();
    }
}
