package com.example.p576;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {
    private ArrayList<Content> contents;
    private Context context;
    private LinearLayout container;

    public void setContents(ArrayList<Content> contents) {
        this.contents = contents;
    }

    public ListAdapter(Context context, LinearLayout container) {
        this.context = context;
        this.container = container;
    }

    @Override
    public int getCount() {
        return contents.size();
    }

    @Override
    public Object getItem(int i) {
        return contents.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.layout_list, viewGroup, false);
            // 밖에서 Layout 을 가지고 오지 말고 viewGroup 만으로 해결해보자!
        }

        TextView title = view.findViewById(R.id.titleTextViewInList);
        TextView date = view.findViewById(R.id.dateTextViewInList);

        title.setText(contents.get(i).getTitle());
        date.setText(contents.get(i).getDate());

        return view;
    }
}
