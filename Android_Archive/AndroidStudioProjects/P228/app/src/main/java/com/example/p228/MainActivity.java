package com.example.p228;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(100);
    }

    public void bar(View view) {
        if (view.getId() == R.id.button4) {
            progressBar.setProgress(progressBar.getProgress() + 10);
        }

        else if (view.getId() == R.id.button3) {
            progressBar.setProgress(progressBar.getProgress() - 10);
        }
    }

    public void dialog(View view) {
        if (view.getId() == R.id.button2) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Progress...");
            progressDialog.setButton(progressDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    progressDialog.dismiss();
                }
            });

            progressDialog.show();
        }

        else if (view.getId() == R.id.button) {

        }
    }
}
