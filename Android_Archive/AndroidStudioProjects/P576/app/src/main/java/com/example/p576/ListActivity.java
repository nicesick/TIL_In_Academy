package com.example.p576;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    private ListView listView;
    private LinearLayout linearLayout;
    private ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mapValues();
        initLists();
    }

    private ArrayList<Content> getContents() {
        ArrayList<Content> contents = new ArrayList<>();

        SqlHelper sqlHelper = new SqlHelper(this);
        Cursor cursor = sqlHelper.select();

        for (int i = 0 ; i < cursor.getCount() ; i++) {
            cursor.moveToNext();

            String id = cursor.getString(1);
            String title = cursor.getString(2);
            String date = cursor.getString(3);
            String content = cursor.getString(4);

            Log.d("[getContents]", id + title + date + content);
            contents.add(new Content(id, title, date, content));
        }

        return contents;
    }

    private void initLists() {
        listAdapter = new ListAdapter(this, linearLayout);
        listAdapter.setContents(getContents());

        listView.setAdapter(listAdapter);
    }

    private void mapValues() {
        listView = findViewById(R.id.listView);
        linearLayout = findViewById(R.id.container);
    }
}
