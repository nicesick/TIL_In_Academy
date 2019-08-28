# Android Event

## I. 신기한 거

- 이미지에도 이벤트를 붙일 수 있따.

- id 는 굉장히 중요하다.



## II. Buttons

- Switch
- RadioGroup
  - RadioButton
- CheckBox
- Button

- ToggleButton



## III. Text

- textView
- editText
  - password
  - number
  - date
  - time



```java
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
```



## IV. 이벤트 (onclick 은 제외)

- 현재 onclick 이벤트는 xml 에서 처리가 가능하다.

```java
package com.example.p178;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity
implements View.OnClickListener {
// 이렇게 리스너를 상속받아야 한다.
	private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btn = findViewById(R.id.button);
        btn.setOnClickListener(this);
        // 이벤트가 들어오면 this 가 처리해 주겠다!!
    }

    @Override
    public void onClick(View view) {
		Toast.makeText(this, "RADIO : " + radio.isChecked() + " CHECK : " + check.isChecked(),Toast.LENGTH_LONG).show();

// 여기서 처리를 한다.
// radio, check 버튼들은 isChecked() 함수를 이용하면 된다.
    }
}
```

- 만약 this 가 아닌 괄호 안에서 한번에 처리해 주고 싶다면,

```java
sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        // 이래하면 된다.
        // boolean b 를 이용해서 on 인지 off 인지 확인할 수 있다.
        if (b) {
            btn1.setBackgroundColor(Color.RED);
            Toast.makeText(MainActivity.this, "...", Toast.LENGTH_LONG).show();
            
            // 이와 같이 하면 this 를 못쓰기 때문에
            // MainActivity.this 라고 써야한다.
        }

        else {
            btn1.setBackgroundColor(Color.BLUE);
        }
    }
});
```



- 터치 리스너(onTouch, GestureDetector)

```java
view.setOnTouchListener(new View.OnTouchListener() {
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        float curX = motionEvent.getX();
        float curY = motionEvent.getY();

        if (action == MotionEvent.ACTION_DOWN) {
            printMeg("DOWN : " + curX + " " + curY);
        }

        else if (action == MotionEvent.ACTION_UP) {
            printMeg("UP : " + curX + " " + curY);
        }

        else if (action == MotionEvent.ACTION_MOVE) {
            printMeg("MOVE : " + curX + " " + curY);
        }

        return true;
    }
});
```

```java
gestureDetector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
    @Override
    public boolean onDown(MotionEvent motionEvent) {
        printMeg("onDown : " + motionEvent.getAction());
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {
        printMeg("onShowPress : " + motionEvent.getAction());
    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        printMeg("onSingleTapUp : " + motionEvent.getAction());
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        printMeg("onScroll : " + motionEvent.getAction());
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        printMeg("onLongPress : " + motionEvent.getAction());
    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        printMeg("onFling : " + motionEvent.getAction());
        return false;
    }
});

view.setOnTouchListener(new View.OnTouchListener() {
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        gestureDetector.onTouchEvent(motionEvent);
        return true;
    }
});
```



## V. 키 이벤트

- 하드웨어의 버튼들을 말하는 거다!
  - 중앙 버튼
  - 통화 버튼
  - 뒤로 가기 버튼 등등...

```java
@Override
// 오버라이드 해서 써야한다.
public boolean onKeyDown(int keyCode, KeyEvent event) {
    if (keyCode == KeyEvent.KEYCODE_BACK) {
        Toast.makeText(this, "BACK BUTTON", Toast.LENGTH_LONG).show();

        return true;
    }

    return false;
}
```



## VI. 방향 이벤트

- 가로, 세로로 방향이 바뀔 때 발생한다.
- 키보드가 숨겨질 때같은 다른 조건도 쓸 수 있었다.

```xml
<activity android:name=".MainActivity" android:configChanges="orientation|screenSize|keyboardHidden">
    <!-- 단 android:configChanges 라는 것을 설정해줘야 한다. -->
    <intent-filter>
        <action android:name="android.intent.action.MAIN" />

        <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
</activity>
```

```java
@Override
public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);

    if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        printMeg("LANDSCAPE");
    }

    else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
        printMeg("PORTRAIT");
    }
}
```



## VII. 번들을 이용한 값 저장

- 번들을 이용해서
  - 가로, 세로로 바꾼다던지
  - 홈을 누르고 다시 켜본다던지
- 하는 상황에서 값을 저장해본다.

```java
@Override
protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    editText = findViewById(R.id.editText);
    button = findViewById(R.id.button);
    button2 = findViewById(R.id.button2);

    button2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String data = savedInstanceState.getString("ID");
            
            // 여기서 Bundle 을 이용해 값을 가지고 올 수 있다.
            Toast.makeText(MainActivity.this, data, Toast.LENGTH_LONG).show();
        }
    });
}

@Override
// 오버라이드 해서 써야한다.
protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);

    Toast.makeText(this, "onSave",Toast.LENGTH_LONG).show();
    outState.putString("ID","id01");
}
```



## VIII. 번들과는 다른 저장 방법?

- SharedPreferences 를 이용한 방법
  - 번들과는 다른 점 : 앱을 삭제하기 전까지 값을 저장하고 있음 ㄷㄷ;;
  - 번들 : 프로그램이 종료되면 값이 지워짐

```java
private SharedPreferences sp;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
}

public void clickBtn(View view) {
    if (view.getId() == R.id.button) {
        sp = getSharedPreferences("ma", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putString("KEY01", "ID01");
        editor.commit();
    }

    else if (view.getId() == R.id.button2) {
        sp = getSharedPreferences("ma", MODE_PRIVATE);
        String id = sp.getString("KEY01","DEFAULT");

        Toast.makeText(this, id, Toast.LENGTH_LONG).show();
    }
}
```



## X. 사용자정의 토스트, 스냅, 다이얼로그, 프로그래스

- 모든 디자인은 xml 로부터 시작된다.
  - toast.xml 를 생성해준다.
    - tLayout(Id of LinearLayout)
    - ImageView
    - textView
- 토스트(inflater)

```java
public void toast(View view) {
    LayoutInflater inflater = getLayoutInflater();
    View tView = inflater.inflate(R.layout.toast, (ViewGroup) findViewById(R.id.tLayout));
	// inflator 를 통해 view 를 정의해 주고
    
    Toast toast = new Toast(this);
    toast.setGravity(Gravity.CENTER, 0, -100);
    toast.setDuration(Toast.LENGTH_LONG);
    toast.setView(tView);
	// 뷰를 설정해 주기만 하면 끝!
    
    toast.show();
}
```

- 다이얼로그(inflater, builder, AlertDialog)

```java
public void dialog(View view) {
    LayoutInflater inflater = getLayoutInflater();
    View dView = inflater.inflate(R.layout.dialog, (ViewGroup) findViewById(R.id.dLayout));

    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle("My Dialog");
    builder.setMessage("Do you wanna exit?");
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
```

- 더 중요한 점

```java
TextView textView = dView.findViewById(R.id.textView2);
// 새로 생성된 dView 를 통해 값들을 가져올 수 있다!!

textView.setText("tell me what you got");
```



## XI. 프로그래스 바

- 진행과정을 바처럼 보여주는 거

```java
public void bar(View view) {
    if (view.getId() == R.id.button) {
        progressBar.setProgress(progressBar.getProgress() + 10);
    }

    else if (view.getId() == R.id.button2) {
        progressBar.setProgress(progressBar.getProgress() - 10);
    }
}
```



## XII. 프로그래스 다이얼로그

- 만약 쓰레드가 있을 때, 해당 작업이 끝날 때까지 기다리는 창을 생성

```java
public void dialog(View view) {
    if (view.getId() == R.id.button2) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Progress...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    else if (view.getId() == R.id.button) {

    }
}
```

- 여기서는 xml 을 통한 사용자정의가 안됨...

```java
if (view.getId() == R.id.button2) {
    LayoutInflater inflater = getLayoutInflater();
    View pdView = inflater.inflate(R.layout.progressdialog, (ViewGroup) findViewById(R.id.pdLayer));

    progressDialog = new ProgressDialog(this);
    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    progressDialog.setCancelable(false);

    Button cancelButton = pdView.findViewById(R.id.button5);
    cancelButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            progressDialog.dismiss();
        }
    });

    progressDialog.setView(pdView);
    // 안먹히는듯...
    progressDialog.show();
}
```



- setButton 을 이용한 2차 시도

```java
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
	// 되지만 위험할 수도 있다.
    
    progressDialog.show();
}
```

