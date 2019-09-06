package com.example.p169;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText inputText;
    private TextView byteText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = findViewById(R.id.inputText);
        byteText = findViewById(R.id.byteText);

        inputText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int length = charSequence.toString().getBytes().length;
                byteText.setText(length + " 바이트");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void toastMessage(View view) {
        String message = inputText.getText().toString();

        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }
}
