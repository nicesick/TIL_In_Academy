package com.example.p576;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

public class CalendarActivity extends AppCompatActivity {
    private CalendarView calendarView;
    private Button decideButton;
    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        mapValues();
        makeEvent();
    }

    private void makeEvent() {
        decideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("date", date);

                setResult(RESULT_OK, intent);
                finish();
            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                date = year + "-" + month + "-" + dayOfMonth;
            }
        });
    }

    private void mapValues() {
        calendarView = findViewById(R.id.calendarView);
        decideButton = findViewById(R.id.decideButton);
    }
}
