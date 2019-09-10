package com.example.p537;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    private EditText editTextForId;
    private EditText editTextForPwd;
    private Button loginButton;
    private ProgressDialog progressDialog;
    private LoginRequestTask loginRequestTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progressDialog = new ProgressDialog(LoginActivity.this);

        mapValues();
        makeEvent();
    }

    private void makeEvent() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = editTextForId.getText().toString();
                String pwd = editTextForPwd.getText().toString();

                loginRequestTask = new LoginRequestTask(LoginActivity.this, progressDialog, id, pwd);
                loginRequestTask.execute();
            }
        });
    }

    private void mapValues() {
        editTextForId = findViewById(R.id.editTextForId);
        editTextForPwd = findViewById(R.id.editTextForPwd);
        loginButton = findViewById(R.id.loginButton);
    }

    @Override
    protected void onDestroy() {
        progressDialog.dismiss();
        super.onDestroy();
    }
}
