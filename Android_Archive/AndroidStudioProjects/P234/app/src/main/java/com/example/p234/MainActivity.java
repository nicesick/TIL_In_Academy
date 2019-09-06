package com.example.p234;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    private Button dialogMakeButton;
    private ImageView resultImg;
    private ProgressBar progressBar;
    private View dialogView;
    private Button resultMakeButton;
    private RadioGroup imgChooseRadio;
    private EditText progressNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialogMakeButton = findViewById(R.id.dialogMakeButton);
        resultImg = findViewById(R.id.resultImg);
        progressBar = findViewById(R.id.progressBar);

        dialogMakeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                LayoutInflater inflater = getLayoutInflater();
                dialogView = inflater.inflate(R.layout.dialoglayout, (ViewGroup) findViewById(R.id.dialogLayer));

                builder.setView(dialogView);

                final AlertDialog dialog = builder.create();

                resultMakeButton = dialogView.findViewById(R.id.resultMakeButton);
                resultMakeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        imgChooseRadio = dialogView.findViewById(R.id.imgChooseRadio);
                        progressNum = dialogView.findViewById(R.id.progressNum);

                        String resultNum = progressNum.getText().toString();
                        int resultNumInteger = 0;

                        if (resultNum.equals("")) {
                            resultNumInteger = 0;
                        }

                        else {
                            resultNumInteger = Integer.parseInt(resultNum.toString());
                        }

                        if (resultNumInteger < 0) {
                            resultNumInteger = 0;
                        }

                        else if (resultNumInteger > 100) {
                            resultNumInteger = 100;
                        }

                        progressBar.setProgress(resultNumInteger);

                        int radioId = imgChooseRadio.getCheckedRadioButtonId();

                        switch (radioId) {
                            case R.id.img1Radio:
                                resultImg.setImageResource(R.drawable.icon1);
                                break;
                            case R.id.img2Radio:
                                resultImg.setImageResource(R.drawable.icon2);
                                break;
                            case R.id.img3Radio:
                                resultImg.setImageResource(R.drawable.icon3);
                                break;

                                default:
                                    break;
                        }

                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }
}
