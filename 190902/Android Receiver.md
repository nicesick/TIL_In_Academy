# Android Receiver

## I. Inner Receiver

- 액티비티 안에 내부 클래스로 리시버를 만들거다.
  - Why? 리시버 정보를 다시 액티비티로 보내주기 넘.. ㅎㅎ

```java
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");

        BroadcastReceiver broadcastReceiver;
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                ConnectivityManager connectivityManager = null;
                NetworkInfo mobile = null;
                NetworkInfo wifi = null;
                // 네트워크 상태를 받을 수 있는 ConnectivityManager 와 NetworkInfo 를 사용한다.

                if (action.equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                    connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

                    mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                    wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

                    if (mobile != null && mobile.isConnected()) {
                        Toast.makeText(context, "MOBILE", Toast.LENGTH_LONG).show();
                    }

                    else if (wifi != null && wifi.isConnected()) {
                        Toast.makeText(context, "WIFI", Toast.LENGTH_LONG).show();
                    }

                    else {
                        Toast.makeText(context, "NOT_CONN", Toast.LENGTH_LONG).show();
                    }
                }
            }
        };

        registerReceiver(broadcastReceiver, intentFilter);
    }
}
```



## II. Permissions

- 퍼미션은.. 짜증난다.

```java
// MainActivity
String[] permissions = {
    Manifest.permission.CALL_PHONE
};

checkPermissions(permissions);

// 필요한 permission만 요청하게된다.
private void checkPermissions(String[] permissions) {
    ArrayList<String> targetList = new ArrayList<>();

    for (int i = 0; i < permissions.length; i++) {
        String curPermission = permissions[i];

        int permissionCheck = ContextCompat.checkSelfPermission(this, curPermission);

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permission_granted", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Permission_denied", Toast.LENGTH_LONG).show();

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, curPermission)) {
                // 이상한 점은 한 번 거부하게 되면 이쪽으로 들어가게 된다는 것!
                Toast.makeText(this, curPermission + " need to be introduced", Toast.LENGTH_LONG).show();
            } else {
                // 이 else 를 빼고 모두 넣으면.. 거부했다 켜도 체크를 한다!
                targetList.add(curPermission);
            }
        }
    }

    if (targetList.size() > 0) {
        String[] targets = new String[targetList.size()];
        targetList.toArray(targets);

        ActivityCompat.requestPermissions(this, targets, 101);
    }
}

@SuppressLint("MissingPermission")
public void clickText(View view) {
    int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);

    // intent 를 실행하기 전에 퍼미션 체크를 해서 권한이 있는지 체크한다.
    if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:010-5028-8137"));
        startActivity(intent);
    }

    else {
        Toast.makeText(this, "permission is needed", Toast.LENGTH_LONG).show();
    }
}
```

