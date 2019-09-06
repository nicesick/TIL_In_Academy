package com.example.p285;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final String DEFAULT_ID = "id01";
    private final String DEFAULT_PWD = "pwd01";

    private SharedPreferences sp;
    private EditText idText;
    private EditText pwText;
    private Button loginButton;
    private CheckBox autoLoginBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idText = findViewById(R.id.idText);
        pwText = findViewById(R.id.pwText);
        loginButton = findViewById(R.id.loginButton);
        autoLoginBox = findViewById(R.id.autoLoginBox);

        if (restoreInfo()) {
            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
            startActivity(intent);
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = idText.getText().toString();
                String pwd = pwText.getText().toString();

                if (id.equals("") || pwd.equals("")) {
                    Toast.makeText(MainActivity.this, "Please check your id or pwd", Toast.LENGTH_LONG).show();
                }

                else {
                    if (id.equals(DEFAULT_ID) && pwd.equals(DEFAULT_PWD)) {
                        if (autoLoginBox.isChecked()) {
                            saveInfo(id, pwd);
                        }

                        else {
                            clearInfo();
                        }

                        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                        startActivity(intent);
                    }

                    else {
                        Toast.makeText(MainActivity.this, "Please check your id or pwd",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private void clearInfo() {
        sp = getSharedPreferences("userInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.clear();
        editor.commit();
    }

    private boolean restoreInfo() {
        sp = getSharedPreferences("userInfo", MODE_PRIVATE);

        if (sp != null && sp.contains("id")) {
            return true;
        }

        return false;
    }

    private void saveInfo(String id, String pwd) {
        sp = getSharedPreferences("userInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putString("id", id);
        editor.putString("pwd", pwd);

        editor.commit();
    }
}
