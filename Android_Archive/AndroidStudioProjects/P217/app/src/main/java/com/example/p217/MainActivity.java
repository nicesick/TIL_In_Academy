package com.example.p217;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toast(View view) {
        LayoutInflater inflater = getLayoutInflater();
        View tView = inflater.inflate(R.layout.toast, (ViewGroup) findViewById(R.id.tLayout));

        Toast toast = new Toast(this);
        toast.setGravity(Gravity.CENTER, 0, -100);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(tView);

        toast.show();
    }

    public void snack(View view) {

    }

    public void dialog(View view) {
        LayoutInflater inflater = getLayoutInflater();
        View dView = inflater.inflate(R.layout.dialog, (ViewGroup) findViewById(R.id.dLayout));

        TextView textView = dView.findViewById(R.id.textView2);
        textView.setText("갈꺼임 ㄹㅇ?");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("My Dialog");
        //builder.setMessage("Do you wanna exit?");
        builder.setIcon(R.drawable.icon1);
        builder.setCancelable(false);
        builder.setView(dView);
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "OK...", Toast.LENGTH_LONG).show();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void progress(View view) {

    }
}
