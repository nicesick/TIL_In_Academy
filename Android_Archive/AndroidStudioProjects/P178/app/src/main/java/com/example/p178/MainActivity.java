package com.example.p178;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import static java.sql.Types.NULL;

public class MainActivity extends AppCompatActivity
implements View.OnClickListener {
    private Button btn1;

    private RadioButton radio;
    private RadioButton radio2;
    private CheckBox check;
    private CheckBox check2;
    private Switch sw;
    private ToggleButton toggle;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.button);

        radio = findViewById(R.id.radioButton);
        radio2 = findViewById(R.id.radioButton2);

        check = findViewById(R.id.checkBox);
        check2 = findViewById(R.id.checkBox2);

        sw = findViewById(R.id.switch1);
        toggle = findViewById(R.id.toggleButton);

        editText = findViewById(R.id.editText);

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    editText.setHint("누름");
                }

                else {
                    editText.setHint("");
                }
            }
        });

        btn1.setOnClickListener(this);

        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Toast.makeText(MainActivity.this, "...", Toast.LENGTH_LONG).show();
            }
        });


        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    btn1.setBackgroundColor(Color.RED);
                }

                else {
                    btn1.setBackgroundColor(Color.BLUE);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(this, "RADIO : " + radio.isChecked() + " CHECK : " + check.isChecked(),Toast.LENGTH_LONG).show();
    }
}

