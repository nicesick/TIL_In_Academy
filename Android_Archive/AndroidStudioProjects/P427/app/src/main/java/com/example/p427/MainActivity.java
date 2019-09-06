package com.example.p427;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.util.JsonReader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView listView;
    private ItemAdapter itemAdapter;
    private LinearLayout container;
    private ProgressDialog progressDialog;
    private HttpTask httpTask;

    private class HttpTask extends AsyncTask<String, Void, String> {
        private String url;

        public HttpTask(String url) {
            this.url = url;
        }

        @Override
        protected void onPreExecute() {
            progressDialog.setMessage("please, wait....");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            String result = HttpHandler.getString(url);
            return result;
        }

        @Override
        protected void onProgressUpdate(Void... values) {

        }

        @Override
        protected void onPostExecute(String string) {
            progressDialog.dismiss();

            Log.d("[onPostExecute]", string);

            ArrayList<Item> lists = getData(string);

            itemAdapter = new ItemAdapter(lists);
            listView.setAdapter(itemAdapter);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(MainActivity.this);

        listView = findViewById(R.id.listView);
        container = findViewById(R.id.container);

        String[] permissions = {
                Manifest.permission.CALL_PHONE
        };

        listView.setOnItemClickListener(this);
        checkPermissions(permissions);
    }

    private void checkPermissions(String[] permissions) {
        ArrayList<String> targetPermissions = new ArrayList<>();

        for (int i = 0 ; i < permissions.length; i++) {
            String curPermission = permissions[i];
            int checkPermission = ContextCompat.checkSelfPermission(this, curPermission);

            if (checkPermission == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, curPermission + " is granted", Toast.LENGTH_LONG).show();
            }

            else {
                Toast.makeText(this, curPermission + " is needed", Toast.LENGTH_LONG).show();

                targetPermissions.add(curPermission);
            }
        }
        
        if (targetPermissions.size() > 0) {
            String[] tarPermissions = new String[targetPermissions.size()];
            targetPermissions.toArray(tarPermissions);

            ActivityCompat.requestPermissions(this, tarPermissions, 101);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String phone = itemAdapter.adapterLists.get(i).getPhone();
        Log.d("[onItemClick]", phone);

        int checkPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);

        if (checkPermission == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
            startActivity(intent);
        }

        else {
            Toast.makeText(this, "permission is needed", Toast.LENGTH_LONG).show();
        }
    }

    private class ItemAdapter extends BaseAdapter {
        ArrayList<Item> adapterLists;

        public ItemAdapter() {
            adapterLists = new ArrayList<>();
        }

        public ItemAdapter(ArrayList<Item> lists) {
            this.adapterLists = lists;
        }

        public void addItem(Item item) {
            adapterLists.add(item);
        }

        @Override
        public int getCount() {
            return adapterLists.size();
        }

        @Override
        public Object getItem(int i) {
            return adapterLists.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View myView = null;
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

            myView = inflater.inflate(R.layout.layout, container, true);

            ImageView imageView = myView.findViewById(R.id.imageView);
            //imageView.setImageResource(adapterLists.get(i).getImg());

            String imgName = adapterLists.get(i).getImg();
            String url = "http://70.12.60.99/WebView/" + imgName;

            setImg(imageView, url);

            TextView textView = myView.findViewById(R.id.textView);
            TextView textView2 = myView.findViewById(R.id.textView2);

            textView.setText(adapterLists.get(i).getName());
            textView2.setText(adapterLists.get(i).getPhone());

            return myView;
        }
    }

    private void setImg(final ImageView imgView, final String urlSource) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                URL url = null;
                InputStream inputStream = null;

                try {
                    url = new URL(urlSource);
                    inputStream = url.openStream();

                    final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            imgView.setImageBitmap(bitmap);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        t.start();
    }

    public void clickBtn2(View view) {
        itemAdapter.addItem(new Item("임자","010-5028-8137","sample"));
        itemAdapter.notifyDataSetChanged();
    }

    public void clickBtn(View view) {
        httpTask = new HttpTask("http://70.12.60.99/WebView/item.jsp");
        httpTask.execute();
    }

    private ArrayList<Item> getData(String result) {
        ArrayList<Item> lists = new ArrayList<>();

        JSONArray ja = null;

        try {
            ja = new JSONArray(result);

            for (int i = 0 ; i < ja.length() ; i++) {
                JSONObject jo = ja.getJSONObject(i);

                String name = jo.getString("name");
                String phone = jo.getString("phone");
                String img = jo.getString("img");

                lists.add(new Item(name, phone, img));

                Log.d("[getData]",name + " " + phone + " " + img);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return lists;
    }
}
