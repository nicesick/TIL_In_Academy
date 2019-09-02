# Android Fragment

## I. 액티비티에서 프래그먼트 변경하기

- 해당 프래그먼트에서 값을 변경 시킬 수 있는 함수를 만든다.

```java
public void setTextView() {
    // View1Fragment 에서
    textView.setText("Main...");
}
```

- 해당 함수를 MainActivity 적절한 곳에 배치한다.

```java
view1Fragment = (View1Fragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
// onCreate 에서...
// 근데 Button 을 누르면 터진다... 왜이러지?
...
    
if (index == 1) {
    Toast.makeText(this, index + "", Toast.LENGTH_LONG).show();
    getSupportFragmentManager().beginTransaction().replace(R.id.container, view1Fragment).commit();
    view1Fragment.setTextView();
    // 위처럼 View1Fragment 를 생성해줘야 프래그먼트의 함수를 쓸 수 있다.
}

```

## II. 프래그먼트 및 메뉴 만들기

- MainActivity에서 해당 함수는 override 한다.

```java
@Override
public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
}
```

- menu > main.xml 에서 item을 추가한다.

```xml
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android"
      xmlns:app="http://schemas.android.com/apk/res-auto">
    <item
          android:id="@+id/action_settings"
          android:orderInCategory="100"
          android:title="@string/action_settings"
          app:showAsAction="never" />

    <item
          android:id="@+id/action_register"
          android:orderInCategory="100"
          android:title="REGISTER"
          app:showAsAction="never" />
</menu>
```

- 그럼 메뉴가 똭



- 실제로

- res > new > new resource directory
  - directory name : menu
  - resource type : menu

- menu > menu resource file
  - file name : mymenu



- menu 에 item 들을 추가한다.

- menu 를 클릭했을 때 처리하는 함수를 override 한다.

```java
@Override
public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    if (item.getItemId() == R.id.mSetting) {
        Toast.makeText(this, "setting", Toast.LENGTH_LONG).show();
    }

    else if (item.getItemId() == R.id.mLogin) {
        Toast.makeText(this, "login", Toast.LENGTH_LONG).show();
    }

    return super.onOptionsItemSelected(item);
}
```



## III. 액션바

- 액션바를 받아온다.

```java
actionBar = getSupportActionBar();
actionBar.setLogo(R.drawable.icon1);
actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_USE_LOGO);

// ... 별루...?
```



- onAttach (신기한거)

```java
// 프래그먼트가 실행될 때 자동으로 실행됨

@Override
public void onAttach(Context context) {
    super.onAttach(context);

    if (context instanceof FragmentCallback) {
        callback = (FragmentCallback) context;
        // 이렇게 하면 액티비티의 값들을 가지고 올 수 있다
    }
}
```



## IV. 시계를 표시해 보자!

- 시계표시는 CalenderView 와 TimePicker 를 이용한다.

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
              xmlns:app="http://schemas.android.com/apk/res-auto"
              xmlns:tools="http://schemas.android.com/tools"
              android:layout_width="match_parent"
              android:layout_height="match_parent"
              android:orientation="vertical"
              tools:context=".MainActivity" >

    <TextView
              android:id="@+id/textView"
              android:layout_width="match_parent"
              android:layout_height="wrap_content"
              android:layout_weight="1"
              android:text="TextView" />

    <CalendarView
                  android:id="@+id/calendarView"
                  android:layout_width="match_parent"
                  android:layout_height="wrap_content"
                  android:layout_weight="1" />

    <TimePicker
                android:id="@+id/timePicker"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_weight="1" />
</LinearLayout>
```

- MainActivity 에서 이벤트를 추가할 수도 있다!

```java
package com.example.p351;

public class MainActivity extends AppCompatActivity {
    private TimePicker timePicker;
    private CalendarView calendarView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timePicker = findViewById(R.id.timePicker);
        calendarView = findViewById(R.id.calendarView);
        textView = findViewById(R.id.textView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String date = i + "/" + i1 + "/" + i2;
                textView.setText(date);
            }
        });

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                String date = i + ":" + i1;
                textView.setText(date);
            }
        });
    }
}

```

## V. 서비스

- 서비스도 똑같다! 인텐트를 이용하여 만드는거야!
- 대신 서비스는 화면이 없는 액티비티일 뿐이다!

```java
public class MyService extends Service {
    private boolean flag = false;

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d("[MyService]", "onCreate...................");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d("[MyService]", "onDestroy...................");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 서비스도 다 onCreate 같은 거 있다!
        // 대신 onStartCommand 라는 함수가 1번은 꼭 실행된다.
        // 여기서 Intent 를 받는다.
        
        flag = true;

        while(flag) {
            if (flag == false) {
                break;
            }

            Log.d("[MyService]", "onStartCommand...................");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
```

- MainActivity 에서는 Intent 로 startService 라고만 해주면 된다.

```java
private Intent intent;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
}

public void clickBtn(View view) {
    intent = new Intent(MainActivity.this, MyService.class);
    startService(intent);
    // 요기!!!
}
```

- 서비스에서 백그라운드를 실행시키려면 어떻게 해야 할까?

```java
Runnable run = new Runnable() {
    boolean flag = true;

    @Override
    public void run() {
        while(flag) {
            if (flag == false) {
                break;
            }

            Log.d("[MyService]", "onStartCommand...................");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
};

new Thread(run).start();
// 쓰레드를 만들어서 실행해 보자!
```

- 주의할점!!
  - 서비스는 홈버튼을 누르거나, 백버튼을 누른다 하더라도
  - 죽지않고 계속 돌아간다!



- 그러면 서비스에서 액티비티로 정보를 주려면 어떻게 해야할까?
- 일단 MainActivity 에 onNewIntent 를 작성한다!

```java
@Override
protected void onNewIntent(Intent intent) {
    process(intent);
}

public void process(Intent intent) {
    if (intent != null) {
        // 이건 항상 체크해 줘야함
        int data = intent.getIntExtra("cmd", 0);

        textView.setText(data);
    }
}
```

- MyService 에서는 Intent 를 이용해서 데이터를 전송한다.

```java
@Override
public int onStartCommand(Intent intent, int flags, int startId) {
    final Intent sendIntent = new Intent(MyService.this, MainActivity.class);
    sendIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                        Intent.FLAG_ACTIVITY_SINGLE_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TOP);

    Runnable run = new Runnable() {
        boolean flag = true;

        @Override
        public void run() {
            for (int i = 0 ; i < 30 ; i++) {
                if (flag == false) {
                    break;
                }

                Log.d("[MyService]", "onStartCommand...................");
                sendIntent.putExtra("cmd", i);
                startActivity(sendIntent);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    t = new Thread(run);
    t.setDaemon(false);
    t.start();

    return super.onStartCommand(intent, flags, startId);
}
```



## VI. 브로드캐스트 리시버

- 리시버를 받을라면 Manifest 먼저 바꿔야 한다.

```xml
<uses-permission android:name="android.permission.RECEIVE_SMS" />

<receiver
          android:name=".SmsReceiver"
          android:enabled="true"
          android:exported="true">
    <intent-filter>
        <action android:name="android.provider.Telephony.SMS_RECEIVED" />
    </intent-filter>
</receiver>
```

- build.gradle 에서는

```xml
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}

implementation 'com.github.pedroSG94:AutoPermissions:1.0.3'

// 이게 추가되어 있었다..?
// 개발자가 일일이 추가할 필요가 없다는데.. 오오..
```

- 그러면 MainActivity 에서는

```java
public class MainActivity extends AppCompatActivity implements AutoPermissionsListener {
// AutoPermissionsListener
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AutoPermissions.Companion.loadAllPermissions(this, 101);
        // 이렇게 필요한 퍼미션들을 자동으로 가지고 온다.
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        AutoPermissions.Companion.parsePermissions(this, requestCode, permissions, this);
    }

    @Override
    public void onDenied(int requestCode, @NotNull String[] permissions) {
        Toast.makeText(this, "permissions denied : " + permissions.length, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGranted(int requestCode, @NotNull String[] permissions) {
        Toast.makeText(this, "permissions granted : " + permissions.length, Toast.LENGTH_LONG).show();
    }
}
```



- 하지만 새로운 프로젝트에서는 안됐쥬...?ㅠㅠㅠ

```java
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] permissions = {
            Manifest.permission.CALL_PHONE
        };

        checkPermissions(permissions);
        // 이렇게 퍼미션을 요구할 수 있다..
    }

    public void checkPermissions(String[] permissions) {
        ArrayList<String> targetList = new ArrayList<String>();

        for (int i = 0; i < permissions.length; i++) {
            String curPermission = permissions[i];
            int permissionCheck = ContextCompat.checkSelfPermission(this, curPermission);
            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, curPermission + " 권한 있음.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, curPermission + " 권한 없음.", Toast.LENGTH_LONG).show();
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, curPermission)) {
                    Toast.makeText(this, curPermission + " 권한 설명 필요함.", Toast.LENGTH_LONG).show();
                } else {
                    targetList.add(curPermission);
                }
            }
        }

        String[] targets = new String[targetList.size()];
        targetList.toArray(targets);

        ActivityCompat.requestPermissions(this, targets, 101);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 101: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "첫번째 권한을 사용자가 승인함.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "첫번째 권한 거부됨.", Toast.LENGTH_LONG).show();
                }

                return;
            }
        }
    }

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
                break;
        }

        startActivity(intent);
    }
}
```



## VI. 서비스 제어

- 이번엔 Binding 을 통해 서비스를 제어해본다.
- 우선 서비스를 생성한다.
- 서비스에서 Binder 를 하나 만든다.

```java
public class ProgressBarService extends Service {
    private class MyBinder extends Binder {
        public ProgressBarService getService() {
            return ProgressBarService.this;
            // 바인더에서 서비스를 리턴해 줄 수 있다.
        }
    }

    IBinder iBinder = new MyBinder();

    public ProgressBarService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        
        // 신기한거! BindService 함수를 사용하면 이 함수가 실행되고
        return iBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // startService 함수를 사용하면 이 함수가 실행된다!!
        Log.d("[progressBarService]","onStartCommand....................");

        return super.onStartCommand(intent, flags, startId);
    }
}
```

- 액티비티에서는 ServiceConnection 과 bindService 를 이용해 액티비티와 서비스를 바인드 시켜줄 거다!

```java
private ProgressBarService progressBarService;
private SeekBarService seekBarService;

// ServiceConnection 을 만들고
private ServiceConnection connForProgressBar = new ServiceConnection() {
    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        ProgressBarService.MyBinder mb = (ProgressBarService.MyBinder) iBinder;
        progressBarService = mb.getService();
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {

    }
};

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Intent intent = new Intent(MainActivity.this, ProgressBarService.class);
    // bindService 함수를 이용해 서비스를 생성 및 연결시킨다.
    bindService(intent, connForProgressBar, BIND_AUTO_CREATE);

    buttonForProgreesBar = findViewById(R.id.buttonForProgressBar);
    buttonForSeekBar = findViewById(R.id.buttonForSeekBar);

```

