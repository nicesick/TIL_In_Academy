package com.example.p535;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private Button makeListButton;
    private LinearLayout linearLayout;
    private ListView listView;
    private MovieDataTask movieDataTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = findViewById(R.id.container);
        listView = findViewById(R.id.listView);

        makeListButton = findViewById(R.id.makeListButton);
        makeListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movieDataTask = new MovieDataTask(getApplicationContext(), listView, linearLayout);
                movieDataTask.execute();
            }
        });
    }
}
