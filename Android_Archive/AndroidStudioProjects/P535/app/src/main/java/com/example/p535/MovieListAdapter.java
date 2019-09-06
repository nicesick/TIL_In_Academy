package com.example.p535;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MovieListAdapter extends BaseAdapter {
    private ArrayList<Movie> list;
    private LinearLayout linearLayout;
    private Context context;

    public MovieListAdapter(Context context, ArrayList<Movie> list, LinearLayout linearLayout) {
        this.list = list;
        this.linearLayout = linearLayout;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View movieView;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        movieView = inflater.inflate(R.layout.list_layout, linearLayout, true);

        TextView title = movieView.findViewById(R.id.title);
        TextView actor = movieView.findViewById(R.id.actor);
        RatingBar rating = movieView.findViewById(R.id.rating);
        ImageView img = movieView.findViewById(R.id.img);

        title.setText(list.get(i).getTitle());
        actor.setText(list.get(i).getActor());
        rating.setRating(list.get(i).getRating());

        String imgUrl = "http://70.12.60.108/webview/" + list.get(i).getImg();
        HttpHandler.setImg(img, imgUrl);

        return movieView;
    }
}
