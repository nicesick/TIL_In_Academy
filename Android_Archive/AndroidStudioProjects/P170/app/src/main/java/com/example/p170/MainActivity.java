package com.example.p170;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import static java.sql.Types.NULL;

public class MainActivity extends AppCompatActivity {
    private EditText inputText;
    private TextView byteText;
    private ImageView upImg;
    private ImageView downImg;
    private LinearLayout imgLayer;
    private LinearLayout textLayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = findViewById(R.id.inputText);
        byteText = findViewById(R.id.byteText);

        upImg = findViewById(R.id.upImg);
        downImg = findViewById(R.id.downImg);

        imgLayer = findViewById(R.id.imgLayer);
        textLayer = findViewById(R.id.textLayer);

        imgLayer.setVisibility(View.GONE);
        textLayer.setVisibility(View.GONE);

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

    public void changeLayer(View view) {
        if (view.getId() == R.id.changeImg) {
            imgLayer.setVisibility(View.VISIBLE);
            textLayer.setVisibility(View.GONE);
        }

        else if (view.getId() == R.id.changeText) {
            imgLayer.setVisibility(View.GONE);
            textLayer.setVisibility(View.VISIBLE);
        }
    }

    public void toastMessage(View view) {
        String message = inputText.getText().toString();

        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

    public void clickUpDownButton(View view) {
        if (view.getId() == R.id.upButton) {
            upImg.setImageResource(R.drawable.icon1);
            downImg.setImageResource(NULL);
        }

        else if (view.getId() == R.id.downButton) {
            upImg.setImageResource(NULL);
            downImg.setImageResource(R.drawable.icon1);
        }
    }
}