package com.example.p213;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickBtn(View view) {
        if (view.getId() == R.id.button) {
            sp = getSharedPreferences("ma", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();

            editor.putString("KEY01", "ID01");
            editor.commit();
        }

        else if (view.getId() == R.id.button2) {
            sp = getSharedPreferences("ma", MODE_PRIVATE);
            String id = sp.getString("KEY01","DEFAULT");

            Toast.makeText(this, id, Toast.LENGTH_LONG).show();
        }
    }
}