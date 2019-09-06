package com.example.p246;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }

    public void clickBtn(View view) {
        Intent intent = new Intent();
        intent.putExtra("nm", 1000);
        intent.putExtra("st", "Hi, SS");

        setResult(RESULT_OK, intent);

        finish();
    }
}
