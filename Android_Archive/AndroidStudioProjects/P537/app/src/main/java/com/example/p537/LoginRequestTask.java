package com.example.p537;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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
    private final static String URL_SOURCE = "http://70.12.60.99/WebView/login.jsp";

    private String id;
    private String pwd;

    private Context context;
    private ProgressDialog progressDialog;

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
        String result = null;

        try {
            url = new URL(URL_SOURCE + "?id=" + id + "&pwd=" + pwd);
            httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(10000);

            inputStream = httpURLConnection.getInputStream();
            result = conventStream(inputStream);

            Log.d("[doInBackground]", result);
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
    protected void onPostExecute(String result) {
        progressDialog.dismiss();

        if (result != null) {
            if (result.equals("SUCCESS")) {
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
            }

            else {
                Toast.makeText(context, "Wrong id or pwd", Toast.LENGTH_LONG).show();
            }
        }

        else {
            Toast.makeText(context, "Can't connect to the URL", Toast.LENGTH_LONG).show();
        }
    }

    private String conventStream(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();

        String oneLine;

        try {
            while((oneLine = bufferedReader.readLine()) != null) {
                if (!oneLine.contains(" ")) {
                    stringBuilder.append(oneLine);
                }

                Log.d("[convertStream]",oneLine);
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }
}
