# Android 3

## I. 안드로이드 구성요소?

- 액티비티 : 화면에 보여지는 판때기
- 서비스 : 백그라운드
- 브로드캐스트 리시버 : 수신된거를 알려주는거? (ex. 전화?, 밧데리?, 네트워크 상태)
- 컨텐트 프로바이더 : 전화번호부?, 저장소에 저장되어 있는 정보를 끌어다 쓰고 싶을 때



## II. 액티비티 간 화면 이동

- 새로운 액티비티(Empty Activity)를 만든다.
- AndroidManifest.xml 파일에 activity 가 등록된다.

- Intent 를 이용해 액티비티 간 이동을 할 수 있다.

```java
public void clickBtn(View view) {
    Intent intent = new Intent(MainActivity.this, Main2Activity.class);
    // MainActivity > Main2Activity
    startActivity(intent);
}

public void clickBtn(View view) {
    finish();
    // finish 를 하면 해당 액티비티가 끝난다.
}

// 요즘엔 Native App 이 많이 없어서
// 화면이 와따리 갔다리 하는 게 많이 없다.
```



- 인텐트에 값을 넣어서 보내도 된다.
- 보낼 때

```java
Intent intent = new Intent(MainActivity.this, Main2Activity.class);
intent.putExtra("num",100);
intent.putExtra("str","Hi, sy");

startActivity(intent);
```

- 받을 때

```java
Intent intent = getIntent();
int num = intent.getIntExtra("num", 0);
String str = intent.getStringExtra("str");
```



- 인텐트 : startActivityForResult

```java
Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
startActivityForResult(intent, 100);
```

```java
@Override
// 이 함수를 Override 해야 한다!
protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
}
```

- Main3Activity 에서는...

```java
public void clickBtn(View view) {
    Intent intent = new Intent();
    intent.putExtra("nm", 1000);
    intent.putExtra("st", "Hi, SS");

    setResult(RESULT_OK, intent);
// 값을 넣어서 setResult 로 반환해준다.
    finish();
}
```

- Main2Activity 에서 데이터를 받으면

```java
@Override
protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == 100 && resultCode == RESULT_OK) {
        int nm = data.getIntExtra("nm", 0);
        String st = data.getStringExtra("st");

        textView2.setText(nm + " " + st);
    }
    
    // 이러한 처리를 해줄 수 있다.
}
```



## III. 외부 앱 실행

```java
public void clickBtn(View view) {
    Intent intent = null;

    switch (view.getId()) {
        case R.id.button:
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.naver.com"));
            break;
        case R.id.button2:
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:010-5028-8137"));
            break;
        case R.id.button3:
            intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:010-5028-8137"));
            // 이거 오류난다!!
            // 해결하려면??
            break;
    }

    startActivity(intent);
}
```

- AndroidManifest.xml 에서

```xml
<uses-permission android:name="android.permission.CALL_PHONE"></uses-permission>

<!-- 이것뿐만 아니라 아쉽지만 사용자에게 따로 허락을 받아야 한다. -->
```



## IV. 수명주기

- onCreate
- onResume
- onPause
- onStop
- onDestroy



## V. 프래그먼트

- 여러개의 화면을 한 화면에 넣는다!
- activity_main.xml 에 <fragment> 를 추가하면 추가가 안된다...
- 따라서 java > com.example.p287 에 fragment > blank 를 추가한다.

```java
public class View1Fragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view1, container, false);
    }
    
    // 이것만 남겨놓고 다 지우면 됨
}
```

- 이제 activity_main.xml 에 fragment를 추가할 수 있다.
  - 단, fragment를 담고 있는 Layout 이 하나 필요하다.
  - 그리고 fragment 에 weight 를 꼭 주자!!!

- MainActivity 에서 프래그먼트를 바꿀 수 있게 코드를 추가한다.

```java
public class MainActivity extends AppCompatActivity {
    private View1Fragment view1Fragment;
    private View2Fragment view2Fragment;
    private View3Fragment view3Fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view1Fragment = new View1Fragment();
        view2Fragment = new View2Fragment();
        view3Fragment = new View3Fragment();
    }

    public void clickBtn(View view) {
        if (view.getId() == R.id.button) {
            onFragmentChange(1);
        }

        else if (view.getId() == R.id.button2) {
            onFragmentChange(2);
        }

        else if (view.getId() == R.id.button3) {
            onFragmentChange(3);
        }
    }

    public void onFragmentChange(int index) {
        if (index == 1) {
            Toast.makeText(this, index + "", Toast.LENGTH_LONG).show();
            getSupportFragmentManager().beginTransaction().replace(R.id.container, view1Fragment).commit();
        }

        else if (index == 2) {
            Toast.makeText(this, index + "", Toast.LENGTH_LONG).show();
            getSupportFragmentManager().beginTransaction().replace(R.id.container, view2Fragment).commit();
        }

        else if (index == 3) {
            Toast.makeText(this, index + "", Toast.LENGTH_LONG).show();
            getSupportFragmentManager().beginTransaction().replace(R.id.container, view3Fragment).commit();
        }
    }
}
```

- 그럼 프래그먼트에서 이벤트가 발생하면 액티비티의 뭔가를 바꿔줄 수도 있을까?

```java
public void setBtn() {
    // MainActivity 에 조정할 수 있는 함수를 만들어 준다.
    button1.setBackgroundColor(Color.RED);
    button2.setBackgroundColor(Color.BLUE);
    button3.setBackgroundColor(Color.BLACK);
}

// View1Fragment
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
    ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_view1, container, false);

    button4 = viewGroup.findViewById(R.id.button4);
    button5 = viewGroup.findViewById(R.id.button5);
    textView = viewGroup.findViewById(R.id.textView);

    button4.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity main = (MainActivity) getActivity();
            main.setBtn();
            // 바깥의 액티비티를 받아와서 해당 함수를 실행시킨다!!
        }
    });

    button5.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            textView.setText("View1 Fragment");
            // 물론 프래그먼트 안에 있는 값들은 자연스럽게 바꿀 수 있다.
        }
    });

    return viewGroup;
}
```

