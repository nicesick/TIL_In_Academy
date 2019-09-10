package com.example.p576;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SaveActivity extends AppCompatActivity {
    private TextView idTextView;
    private EditText titleEditText;
    private TextView dateTextView;
    private EditText contextEditText;

    private Button calendarButton;
    private Button saveButton;
    private Button listButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);

        mapValues();
        initValues();

        makeEvents();
    }

    private void makeEvents() {
        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SaveActivity.this, CalendarActivity.class);
                startActivityForResult(intent, 100);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = "'" + idTextView.getText().toString() + "'";
                String title = "'" + titleEditText.getText().toString()  + "'";
                String date = "'" + dateTextView.getText().toString() + "'";
                String content = "'" + contextEditText.getText().toString() + "'";

                if (id.equals("") || title.equals("") || date.equals("") || content.equals("")) {
                    Toast.makeText(SaveActivity.this, "fill the boxs", Toast.LENGTH_LONG).show();
                }

                else {
                    SqlHelper sqlHelper = new SqlHelper(SaveActivity.this);
                    sqlHelper.insert(id, title, date, content);
                }
            }
        });

        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SaveActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initValues() {
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        idTextView.setText(id);
    }

    private void mapValues() {
        idTextView = findViewById(R.id.idTextView);
        titleEditText = findViewById(R.id.titleEditText);
        dateTextView = findViewById(R.id.dateTextView);
        contextEditText = findViewById(R.id.contextEditText);

        calendarButton = findViewById(R.id.calendarButton);
        saveButton = findViewById(R.id.saveButton);
        listButton = findViewById(R.id.listButton);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK && requestCode == 100) {
            String date = data.getStringExtra("date");

            dateTextView.setText(date);
        }
    }
}
