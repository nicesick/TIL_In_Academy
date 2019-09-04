# Android AsyncTask

## I. 소개

- 메인 스레드
  - onPreExecute
  - onProgressUpdate
  - onPostExecute
- 자식 스레드
  - doInBackground



- AsyncTask 를 상속받아서 구현하는데

```java
private class MyTask extends AsyncTask<Integer, Integer, Integer> {
    // <> 안에 있는 값은
    // 시작 전 넣어주고 싶은 param의 자료형 (Constructor)
    // 진행 도중에 받고 싶은 param의 자료형 (onProgressUpdate)
    // 끝나고 나서 받고 싶은 param의 자료형 (onPostExecute)

    private int cnt;

    public MyTask(int cnt) {
        this.cnt = cnt;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Integer... integers) {
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}

...

    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            myTask = new MyTask(50);
            myTask.execute();
            
            // execute 로 실행시켜줄 수 있다.
            // execute 안에 param (지금은 int) 값들을 넣어 줄 수 있다.
        }
    });
```

- 기본 코드

```java
private class MyTask extends AsyncTask<Integer, Integer, String> {
    private int cnt = 0;

    public MyTask(int cnt) {
        this.cnt = cnt;
    }

    @Override
    protected void onPreExecute() {
        // 메인 스레드에서 필요한 작업들을 처리
        progressBar.setMax(cnt);
        button.setEnabled(false);
        textView.setText("START TASK");
    }

    @Override
    protected String doInBackground(Integer... integers) {
        String result = "";
        int sum = 0;

        for (int i = 0 ; i < cnt ; i++) {
            try {
                Thread.sleep(500);
                sum += i;

                publishProgress(i);
                // publishProgress 함수로 onProgressUpdate 에 값을 전달해 줄 수 있따.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        result = sum + "";

        return result;
        // 값을 return 하면 onPostExecute 에 전달된다.
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        // 실행 도중 필요한 작업
        progressBar.setProgress(values[0].intValue());
        textView.setText(values[0].intValue() + "");
    }

    @Override
    protected void onPostExecute(String s) {
        // 끝나고 나서 필요한 작업
        button.setEnabled(true);
        textView.setText("END TASK : " + s);

    }
}

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    button = findViewById(R.id.button);
    textView = findViewById(R.id.textView);
    progressBar = findViewById(R.id.progressBar);
    progressDialog = new ProgressDialog(this);

    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            myTask = new MyTask(20);
            myTask.execute();
            // execute 로 AsyncTask 를 실행시킬 수 있다.
        }
    });
}
```



- AsyncTask 를 도중에 취소하고 싶을 때?

```java
myTask.cancel(true);
// cancel 을 한다!!

...

@Override
protected void onCancelled(String s) {
	// 이 함수를 오버라이드하면 onPostExecute 대신 이 함수로 들어오게 된다!
}
```

- 실습 중에서 AysncTask 두개를 돌리는 실습이 있었다.
- 그런데, 두개가 동시에 안 도는 거 같다.

```java
carInfoTaskForSpeed.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
// execute 대신 executeOnExecutor 를 사용하면
// default 값으로 5개까지 같이 돌릴 수 있다고 한다.
```



## II. 네트워크를 통해 데이터 가지고 오기

- 네트워크는 무조건 쓰레드영역에서 처리해야 한다.

```java
// 이 클래스는 static 으로 쓰이고, MainActivity 에서 AsyncTask 에서 사용될 것이다.

public class HttpHandler {
    public static String getString(String urlSource) {
        String result = null;

        URL url = null;
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        // Http 에서 데이터를 받아오는 통로를 생성

        try {
            url = new URL(urlSource);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setRequestMethod("GET");

            inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
            result = convertStream(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpURLConnection.disconnect();
        }

        return result;
    }

    private static String convertStream(InputStream inputStream) throws IOException {
        String result = null;
        BufferedReader bufferedReader = null;
        // 통로에서 데이터를 빨아오는 녀석

        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String oneLine;

        try {
            while((oneLine = bufferedReader.readLine()) != null) {
                stringBuilder.append(oneLine);
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            inputStream.close();
        }

        result = stringBuilder.toString();

        return result;
    }
}
```



## III. 네트워크를 통해 JSON 을 받아서 리스트뷰로 뿌리기

- HttpHandler 를 통해 받아온 JSON 파일을 파싱해 준다.

```java
private ArrayList<Item> getData(String result) {
    ArrayList<Item> lists = new ArrayList<>();

    JSONArray ja = null;

    try {
        ja = new JSONArray(result);
		// JSONArray 를 만들어 String 을 JSON 으로 재생성
        for (int i = 0 ; i < ja.length() ; i++) {
            JSONObject jo = ja.getJSONObject(i);

            String name = jo.getString("name");
            String phone = jo.getString("phone");
            String img = jo.getString("img");

            lists.add(new Item(name, phone, img));

            Log.d("[getData]",name + " " + phone + " " + img);
        }
    } catch (JSONException e) {
        e.printStackTrace();
    }

    return lists;
}
```

- 이미지를 서버에서 다운받아서 ImageView 에 적용시키는 방법

```java
private void setImg(final ImageView imgView, final String urlSource) {
    // 네트워크를 거치는 모든 작업은 쓰레드를 이용해서 해야한다.
    
    Thread t = new Thread(new Runnable() {
        @Override
        public void run() {
            URL url = null;
            InputStream inputStream = null;

            try {
                url = new URL(urlSource);
                inputStream = url.openStream();

                final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imgView.setImageBitmap(bitmap);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    });

    t.start();
}
```



