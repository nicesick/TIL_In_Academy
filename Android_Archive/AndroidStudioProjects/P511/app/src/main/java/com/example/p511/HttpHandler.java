package com.example.p511;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpHandler {
    public static String getString(String urlSource) {
        String result = null;

        URL url = null;
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        // Http 에서 데이터를 받아오는 통로를 생성

        try {
           url = new URL(urlSource);
           httpURLConnection = (HttpURLConnection) url.openConnection();
           httpURLConnection.setConnectTimeout(10000);
           httpURLConnection.setRequestMethod("GET");

           inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
           result = convertStream(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpURLConnection.disconnect();
        }

        return result;
    }

    private static String convertStream(InputStream inputStream) throws IOException {
        String result = null;
        BufferedReader bufferedReader = null;
        // 통로에서 데이터를 빨아오는 녀석

        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String oneLine;

        try {
            while((oneLine = bufferedReader.readLine()) != null) {
                stringBuilder.append(oneLine);
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            inputStream.close();
        }

        result = stringBuilder.toString();

        return result;
    }
}
