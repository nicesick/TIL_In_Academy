package com.example.p436;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ArrayList<Integer> list;
    private Spinner spinner;
    private ImageView imageView;
    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        spinner = findViewById(R.id.spinner);
        ratingBar = findViewById(R.id.ratingBar);

        getData();

        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, list);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);
    }

    private void getData() {
        list = new ArrayList<>();

        list.add(R.drawable.ic_launcher_foreground);
        list.add(R.drawable.ic_launcher_foreground);
        list.add(R.drawable.ic_launcher_foreground);
        list.add(R.drawable.ic_launcher_foreground);
        list.add(R.drawable.ic_launcher_foreground);
        list.add(R.drawable.ic_launcher_foreground);
        list.add(R.drawable.ic_launcher_foreground);
        list.add(R.drawable.ic_launcher_foreground);
        list.add(R.drawable.ic_launcher_foreground);
        list.add(R.drawable.ic_launcher_foreground);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        int str = list.get(i);
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();

        imageView.setImageResource(str);
        ratingBar.setRating((float) (ratingBar.getRating() + 1.0));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
