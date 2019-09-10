package com.example.p576;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    private EditText idEditText;
    private EditText pwdEditText;
    private Button loginButton;

    private ProgressDialog progressDialog;
    private LoginRequestTask loginRequestTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressDialog = new ProgressDialog(this);

        mapValues();
        makeEvent();
    }

    private void makeEvent() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = idEditText.getText().toString();
                String pwd = pwdEditText.getText().toString();

                loginRequestTask = new LoginRequestTask(LoginActivity.this, progressDialog, id, pwd);
                loginRequestTask.execute();
            }
        });
    }

    private void mapValues() {
        idEditText = findViewById(R.id.idEditText);
        pwdEditText = findViewById(R.id.pwdEditText);
        loginButton = findViewById(R.id.loginButton);
    }

    @Override
    protected void onDestroy() {
        progressDialog.dismiss();

        super.onDestroy();
    }
}
