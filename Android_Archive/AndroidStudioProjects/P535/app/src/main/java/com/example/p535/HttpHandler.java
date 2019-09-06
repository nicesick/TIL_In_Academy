package com.example.p535;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.renderscript.ScriptGroup;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class HttpHandler {
    private static class GetImg extends AsyncTask<Void, Void, Bitmap> {
        private ImageView imageView;
        private String urlSource;

        public GetImg(ImageView imageView, String url) {
            this.imageView = imageView;
            this.urlSource = url;
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected Bitmap doInBackground(Void... voids) {
            URL url = null;
            InputStream inputStream = null;
            Bitmap bitmap = null;

            try {
                url = new URL(urlSource);
                inputStream = url.openStream();

                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageView.setImageBitmap(bitmap);
        }
    }

    public static void setImg(ImageView imageView, String url) {
        GetImg getImg = new GetImg(imageView, url);
    }

    public static ArrayList<Movie> getDataArrayList(String targetString) {
        ArrayList<Movie> list = new ArrayList<>();

        try {
            JSONArray ja = new JSONArray(targetString);

            for (int i = 0 ; i < ja.length() ; i++) {
                JSONObject jo = ja.getJSONObject(i);

                String title = jo.getString("title");
                String actor = jo.getString("actor");
                int rating = jo.getInt("rating");
                String img = jo.getString("img");

                list.add(new Movie(title, actor, img, rating));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static String getData(String urlSource) {
        String result = "";

        URL url = null;
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;

        try {
            url = new URL(urlSource);

            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setRequestMethod("GET");

            inputStream = new BufferedInputStream(httpURLConnection.getInputStream());

            result = convertString(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpURLConnection.disconnect();
        }

        return result;
    }

    private static String convertString(InputStream inputStream) {
        String result = "";

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String oneLine;
        StringBuilder stringBuilder = new StringBuilder();

        try {
            while ((oneLine = bufferedReader.readLine()) != null) {
                stringBuilder.append(oneLine);
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        result = stringBuilder.toString();

        return result;
    }
}
