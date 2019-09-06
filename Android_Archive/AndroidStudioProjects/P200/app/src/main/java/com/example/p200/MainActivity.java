package com.example.p200;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private View view;
    private View view2;
    private TextView textView;
    private GestureDetector gestureDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUi();
        setEvent();

    }

    private void setEvent() {
        gestureDetector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent motionEvent) {
                printMeg("onDown : " + motionEvent.getAction());
                return true;
            }

            @Override
            public void onShowPress(MotionEvent motionEvent) {
                printMeg("onShowPress : " + motionEvent.getAction());
            }

            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                printMeg("onSingleTapUp : " + motionEvent.getAction());
                return true;
            }

            @Override
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                printMeg("onScroll : " + motionEvent.getAction());
                return true;
            }

            @Override
            public void onLongPress(MotionEvent motionEvent) {
                printMeg("onLongPress : " + motionEvent.getAction());
            }

            @Override
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                printMeg("onFling : " + motionEvent.getAction());
                return true;
            }
        });

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gestureDetector.onTouchEvent(motionEvent);
                return true;
            }
        });
    }

    private void setUi() {
        view = findViewById(R.id.view);
        view2 = findViewById(R.id.view2);
        textView = findViewById(R.id.textView);
    }

    private void printMeg(String str) {
        textView.setText(str + "\n" + textView.getText());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Toast.makeText(this, "BACK BUTTON", Toast.LENGTH_LONG).show();

            return false;
        }

        return false;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            printMeg("LANDSCAPE");
        }

        else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            printMeg("PORTRAIT");
        }
    }
}
