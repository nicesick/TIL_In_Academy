package com.example.p369;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");

        BroadcastReceiver broadcastReceiver;
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                ConnectivityManager connectivityManager = null;
                NetworkInfo mobile = null;
                NetworkInfo wifi = null;

                if (action.equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                    connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

                    mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                    wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

                    if (mobile != null && mobile.isConnected()) {
                        Toast.makeText(context, "MOBILE", Toast.LENGTH_LONG).show();
                    } else if (wifi != null && wifi.isConnected()) {
                        Toast.makeText(context, "WIFI", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(context, "NOT_CONN", Toast.LENGTH_LONG).show();
                    }
                }
            }
        };

        registerReceiver(broadcastReceiver, intentFilter);

        String[] permissions = {
                Manifest.permission.CALL_PHONE
        };

        checkPermissions(permissions);
    }

    private void checkPermissions(String[] permissions) {
        ArrayList<String> targetList = new ArrayList<>();

        for (int i = 0; i < permissions.length; i++) {
            String curPermission = permissions[i];

            int permissionCheck = ContextCompat.checkSelfPermission(this, curPermission);

            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission_granted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Permission_denied", Toast.LENGTH_LONG).show();

                if (ActivityCompat.shouldShowRequestPermissionRationale(this, curPermission)) {
                    Toast.makeText(this, curPermission + " need to be introduced", Toast.LENGTH_LONG).show();
                }

                targetList.add(curPermission);
            }
        }

        if (targetList.size() > 0) {
            String[] targets = new String[targetList.size()];
            targetList.toArray(targets);

            ActivityCompat.requestPermissions(this, targets, 101);
        }
    }

    public void clickText(View view) {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:010-5028-8137"));
            startActivity(intent);
        }

        else {
            Toast.makeText(this, "permission is needed", Toast.LENGTH_LONG).show();
        }
    }
}
