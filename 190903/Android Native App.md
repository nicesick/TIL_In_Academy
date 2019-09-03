# Android Native App

## I. 웹 뷰

- 앱 안의 일정 부분에 웹이 추가되는 것

- 보통 webView 말고 다른 거 사용한다고...?

```java
// webView 와 webSettings

webView = findViewById(R.id.webView);
webView.setWebViewClient(new WebViewClient());
webView.addJavascriptInterface(new JS(), "js");
// 웹에서 안드로이드 함수를 실행시키려면 이 명령어를 사용해야 한다.

WebSettings webSettings = webView.getSettings();
webSettings.setJavaScriptEnabled(true);

webView.loadUrl("http://m.naver.com");
```

- 단, uses-permission INTERNET 를 추가해 줘야 한다.

```xml
<uses-permission android:name="android.permission.INTERNET"></uses-permission>
```



- webView 에서는 서버에 있는 스크립트 함수 또한 실행시킬 수 있다!

```java
webView.loadUrl("javascript:s()");
// 현재 떠져있는 뷰에 있는 스크립트 함수를 실행 시킬 수 있다!
```

- 또한 웹에서 안드로이드에 있는 함수를 실행시킬 수도 있다!

```java
final class Js {
    public Js() {

    }

    // 이 명령어를 통해 web 에서 접근할 수 있다.
    @android.webkit.JavascriptInterface
        public void webclick(String str) {

    }
}
```

- 웹에서는?

```java
<button onclick="window.js.webclick('something')">CLICK</button>
// 이렇게 접근을 한다... 근데 안돼...ㅎ후ㅜㅜㅠ
```



## II. 시크바? 

- 시크바를 이용해보자

```java
seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        textView.setText("SeekBar Value: :" + i);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        Toast.makeText(MainActivity.this, "Start", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        Toast.makeText(MainActivity.this, "Stop", Toast.LENGTH_LONG).show();
    }
});
```

- 이걸 이용해서 화면도 조정해 볼수 있다.

```java
// 근데 기준이 다른듯... 예상대로 안됨...

public void setBright(int value) {
    if (value < 10) {
        value = 10;
    }

    WindowManager.LayoutParams params = getWindow().getAttributes();
    params.screenBrightness = (float)value/10;

    getWindow().setAttributes(params);
}
```



## III. 키패드 제어

```xml
<activity android:name=".MainActivity"
          android:windowSoftInputMode="stateHidden">
    
<!-- 이렇게 포커스되어도 키패드에 올라오지 않는다. -->
```



## IV. 쓰레드

- 프로세스로부터 생성되는 쓰레드!

- 쓰레드는 CPU 공유? 메모리 공유?
  - 메모리 공유를 잘못하면 데드락에 걸릴 수 있다.. 조심

```java
t1 = new Thread(new Runnable() {
    @Override
    public void run() {
        for (int i = 0 ; i < 10; i++) {
            try {
                Thread.sleep(1000);
                Log.d("[T]","----------" + i);
                // textView.setText("sample");
                // 쓰레드가 메인쓰레드를 건드릴 수 없다!
                // 하지만 또 방법이 있다네... bb
                
                final int i2 = i;
                
                runOnUiThread(new Runnable() {
                    public void run() {
                        textView.setText(i2);
                        b2.setEnabled(true);
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
});
```



## V. 핸들러

- 핸들러를 이용해 쓰레드를 제어하자?
- 핸들러를 만들자
- 번들을 메시지에 저장해서 핸들러에게 보내자

```java
private class CountHandler extends Handler {
    @Override
    public void handleMessage(@NonNull Message msg) {
        Bundle bundle = msg.getData();
        int value = bundle.getInt("cnt");

        textView.setText(value + "");
        // 핸들러를 통해 받은 값으로 textView 를 바꿔준다.
    }
}

r = new Runnable() {
    @Override
    public void run() {
        for (int i = 0 ; i < 10; i++) {
            try {
                Thread.sleep(1000);
                Log.d("[Thread]", "" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Message message = countHandler.obtainMessage();
            Bundle bundle = new Bundle();
            bundle.putInt("cnt", i);

            message.setData(bundle);
            countHandler.sendMessage(message);
            // 번들과 메시지를 만들어서 핸들러를 통해 메시지를 전송한다.
        }

        t = null;
    }
};
```

- runOnUiThread 를 쓸건지 Handler 를 쓸 건지는 알아서 판단하시길!
  - 되도록이면 Handler 를 이용해서 바꿔주는 게 더 좋음!!



- 근데 핸들러로 계속 메시지를 보내는게 귀찮아져서 다른 방법을 찾았다...?

```java
handler.post(new Runnable() {
    @Override
    public void run() {
        button.setEnabled(true);
    }
});
// 이 방법은 유지 보수에는 많이 좋진 않음..ㅎㅎ


handler.postDelayed(new Runnable() {
    @Override
    public void run() {
        textView.setText("NEXT OK");
    }
}, 5000);
// 신기한 방법으로 이걸 쓰면 5초 후에 실행됨
```

- 서비스의 쓰레드와 액티비티의 쓰레드의 차이점
  - 서비스의 쓰레드 : 앱이 꺼져도 쓰레드가 계속 돌아간다.
  - 액티비티의 쓰레드 : 앱이 꺼지면 쓰레드도 같이 죽는다.



## VI. 쓰레드로 메시지를 전송하기

- 이번에도 핸들러를 이용해서 메시지를 보낸다.

```java
private class MyThread extends Thread {
    private ThreadHandler threadHandler;

    public MyThread() {
        threadHandler = new ThreadHandler();
    }

    private class ThreadHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            int data = (int) msg.obj;

            for (int i = 0 ; i < data; i++) {
                Log.d("[T]","----------------" + i);
            }
        }
    }

    @Override
    public void run() {
        Looper.prepare();
        Looper.loop();
    }
}

...

button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Message message = myThread.threadHandler.obtainMessage();
            message.obj = 10;

            myThread.threadHandler.sendMessage(message);
        }
    });
```



- handleMessage 에서 MainHandler 로 메시지를 보낼라고 했는데 한꺼번에 도착했다.. 그래서 900만 떴음..

- 결국 그냥... set 이라는 함수들을 만들어서 start 하기전에 값을 받아줬음ㅠㅠ