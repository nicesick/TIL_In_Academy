package com.example.p287;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private View1Fragment view1Fragment;
    private View2Fragment view2Fragment;
    private View3Fragment view3Fragment;
    private Button button1;
    private Button button2;
    private Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view1Fragment = (View1Fragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        view2Fragment = new View2Fragment();
        view3Fragment = new View3Fragment();

        button1 = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, view1Fragment).commit();
    }

    public void setBtn() {
        button1.setBackgroundColor(Color.RED);
        button2.setBackgroundColor(Color.BLUE);
        button3.setBackgroundColor(Color.BLACK);
    }

    public void clickBtn(View view) {
        if (view.getId() == R.id.button) {
            onFragmentChange(1);
        }

        else if (view.getId() == R.id.button2) {
            onFragmentChange(2);
        }

        else if (view.getId() == R.id.button3) {
            onFragmentChange(3);
        }
    }

    public void onFragmentChange(int index) {
        if (index == 1) {
            Toast.makeText(this, index + "", Toast.LENGTH_LONG).show();
            getSupportFragmentManager().beginTransaction().replace(R.id.container, view1Fragment).commit();
            view1Fragment.setTextView();
        }

        else if (index == 2) {
            Toast.makeText(this, index + "", Toast.LENGTH_LONG).show();
            getSupportFragmentManager().beginTransaction().replace(R.id.container, view2Fragment).commit();
        }

        else if (index == 3) {
            Toast.makeText(this, index + "", Toast.LENGTH_LONG).show();
            getSupportFragmentManager().beginTransaction().replace(R.id.container, view3Fragment).commit();
        }
    }
}
