package com.example.p535;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class MovieDataTask extends AsyncTask<Void, Void, String> {
    private ListView listView;
    private MovieListAdapter movieListAdapter;
    private LinearLayout linearLayout;
    private Context context;

    public MovieDataTask(Context context, ListView listView, LinearLayout linearLayout) {
        this.listView = listView;
        this.linearLayout = linearLayout;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected String doInBackground(Void... voids) {
        String result = HttpHandler.getData("http://70.12.60.108/webview/movieItem.jsp");
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        ArrayList<Movie> list = HttpHandler.getDataArrayList(s);
        movieListAdapter = new MovieListAdapter(context, list,linearLayout);
        listView.setAdapter(movieListAdapter);
    }
}
