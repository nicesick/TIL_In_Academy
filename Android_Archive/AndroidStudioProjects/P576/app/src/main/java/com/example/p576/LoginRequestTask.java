package com.example.p576;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginRequestTask extends AsyncTask<Void, Void, String> {
    private String id;
    private String pwd;

    private Context context;
    private ProgressDialog progressDialog;

    private final static String URL_SOURCE = "http://70.12.60.99/WebView/login.jsp";

    public LoginRequestTask(Context context, ProgressDialog progressDialog, String id, String pwd) {
        this.context = context;
        this.progressDialog = progressDialog;
        this.id = id;
        this.pwd = pwd;
    }

    @Override
    protected void onPreExecute() {
        progressDialog.show();
    }

    @Override
    protected String doInBackground(Void... voids) {
        URL url = null;
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        String result = "";

        try {
            url = new URL(URL_SOURCE + "?id=" + id + "&pwd=" + pwd);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            inputStream = httpURLConnection.getInputStream();

            result = convertStream(inputStream);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
    }

    @Override
    protected void onPostExecute(String s) {
        progressDialog.dismiss();

        Log.d("[onPostExecute]", s);

        if (s.contains("SUCCESS")) {
            Intent intent = new Intent(context, SaveActivity.class);
            intent.putExtra("id", id);

            context.startActivity(intent);
        }

        else {
            Toast.makeText(context, "Wrong id or pwd", Toast.LENGTH_LONG).show();
        }
    }

    private String convertStream(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();

        String oneLine = "";

        try {
            while((oneLine = bufferedReader.readLine()) != null) {
                stringBuilder.append(oneLine);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }
}
