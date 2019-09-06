package com.example.p490;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private Button button2;
    private Button button3;

    private TextView textView;
    private ProgressBar progressBar;
    private ProgressDialog progressDialog;
    private MyTask myTask;

    private class MyTask extends AsyncTask<Integer, Integer, String> {
        private int cnt = 0;

        public MyTask(int cnt) {
            this.cnt = cnt;
        }

        @Override
        protected void onPreExecute() {
            progressBar.setMax(cnt);
            button.setEnabled(false);
            button2.setEnabled(true);
            button3.setEnabled(true);
            //progressDialog.show();
            textView.setText("START TASK");
        }

        @Override
        protected String doInBackground(Integer... integers) {
            String result = "";
            int sum = 0;

            for (int i = 0 ; i < cnt ; i++) {
                if (isCancelled()) {
                    break;
                }

                try {
                    Thread.sleep(500);
                    sum += i;

                    publishProgress(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            result = sum + "";

            return result;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0].intValue());
            textView.setText(values[0].intValue() + "");
        }

        @Override
        protected void onPostExecute(String s) {
            button.setEnabled(true);
            textView.setText("END TASK : " + s);
            button2.setEnabled(false);
            //progressDialog.dismiss();
        }

        @Override
        protected void onCancelled(String s) {
            button.setEnabled(true);
            textView.setText("CANCEL TASK : " + s);
            button2.setEnabled(false);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button2.setEnabled(false);
        button3 = findViewById(R.id.button3);
        button3.setEnabled(false);

        textView = findViewById(R.id.textView);
        progressBar = findViewById(R.id.progressBar);

        /*progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please, wait...");
        progressDialog.setCancelable(false);*/

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myTask = new MyTask(20);
                myTask.execute();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myTask.cancel(true);
            }
        });
    }
}
