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

- 퍼미션은.. 짜증난다ㅎㅎ

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



## III. 리스트 뷰 만들기

- Legacy 에서 listView 를 추가한다.
- 셀에 들어갈 정보를 새로운 클래스로 생성했다.
- 셀을 위한 xml 파일을 만들어 준다.
- 형식을 맞추기 위해 Item 을 위한 adapter 를 만들어 준다.

```java
private class ItemAdapter extends BaseAdapter {
    ArrayList<Item> adapterLists;

    public ItemAdapter() {
        adapterLists = new ArrayList<>();
    }

    public ItemAdapter(ArrayList<Item> lists) {
        this.adapterLists = lists;
    }

    @Override
    public int getCount() {
        return adapterLists.size();
    }

    @Override
    public Object getItem(int i) {
        return adapterLists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    // list 의 아이템 갯수만큼 이 함수가 호출된다.
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View myView = null;
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        myView = inflater.inflate(R.layout.layout, container, true);

        ImageView imageView = myView.findViewById(R.id.imageView);
        TextView textView = myView.findViewById(R.id.textView);
        TextView textView2 = myView.findViewById(R.id.textView2);
        imageView.setImageResource(adapterLists.get(i).getImgId());
        textView.setText(adapterLists.get(i).getName());
        textView2.setText(adapterLists.get(i).getPhone());

        return myView;
    }
}

...

    public void clickBtn(View view) {
    ArrayList<Item> lists = getData();

    ItemAdapter itemAdapter = new ItemAdapter(lists);
    listView.setAdapter(itemAdapter);

    // 이렇게 어탭터를 listView 에 넣어준다.
}

```

- 추가는?

```java
public void clickBtn2(View view) {
        itemAdapter.addItem(new Item("임자","010-5028-8137",R.drawable.icon1));
        itemAdapter.notifyDataSetChanged();
    // 이걸 해보면 어댑터를 update 해야 한다.
    }
```

- 리스트뷰에 이벤트를 추가시켜보자!

```java
// onCreate 에서 이벤트를 listView 에 등록한다.
listView.setOnItemClickListener(this);

// implements AdapterView.OnItemClickListener 를 하게되면
// 오버라이드 할 수 있는 함수다!
@Override
public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
    String phone = itemAdapter.adapterLists.get(i).getPhone();
    Log.d("[onItemClick]", phone);

    int checkPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);

    if (checkPermission == PackageManager.PERMISSION_GRANTED) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
        startActivity(intent);
    }

    else {
        Toast.makeText(this, "permission is needed", Toast.LENGTH_LONG).show();
    }
}
```

## IV. 스피너

- 역시 스피너에도 어댑터를 달아준다.
- 하지만 이번엔 UI 를 직접 만드는 것이 아니기 때문에 ArrayAdapter 를 사용한다.

```java
ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item);

spinner.setAdapter(adapter);
```

- 스피너의 이벤트는 OnItemClick 또는 OnItemSelected 둘 중 하나를 쓰면 된다.

```java
// implements AdapterView.OnItemSelectedListener 를 추가한다.

// onCreate 에서 리스너를 등록하고
spinner.setOnItemSelectedListener(this);

// 두 함수에 기능을 추가한다.
@Override
public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
	String str = list.get(i);
    Toast.makeText(this, str, Toast.LENGTH_LONG).show();
}

@Override
public void onNothingSelected(AdapterView<?> adapterView) {

}
```



## V. 리스트 뷰 코드

- 리스트뷰가 2개 이상부터 자꾸 깨지는 현상이 발생했다
- LinearLayout 선언을 밖으로 했어야 했다.

```java
public class MainActivity extends AppCompatActivity {
    private Button addButton;
    private Spinner spinner;
    private ListView listView;
    private int spinnerIndex;
    private ArrayList<Integer> spinnerMenu;
    private CustomerAdapter customerAdapter;
    private LinearLayout linearLayout;
	// linearLayout 을 여기에 밖으로 뺐다.
    
    private class CustomerAdapter extends BaseAdapter {
        ArrayList<Customer> customers;

        public CustomerAdapter() {
            customers = new ArrayList<Customer>();
        }

        @Override
        public int getCount() {
            return customers.size();
        }

        @Override
        public Object getItem(int i) {
            return customers.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View customersView;

            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            customersView = inflater.inflate(R.layout.customerlist_layout, linearLayout,true);
			// linearLayout 을 여기서 다시 선언해 주는 것이 아니었음!!
            TextView customerName = customersView.findViewById(R.id.customerName);
            TextView customerPhone = customersView.findViewById(R.id.customerPhone);
            ImageView customerImg = customersView.findViewById(R.id.customerImg);

            customerImg.setImageResource(customers.get(i).getImgid());
            customerName.setText(customers.get(i).getName());
            customerPhone.setText(customers.get(i).getPhone());

            return customersView;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mappingValues();

        makeSpinner();
        makeSpinnerEvent();
        makeListView();
        makeButtonEvent();
    }

    private void makeSpinnerEvent() {
        AdapterView.OnItemSelectedListener spinnerListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerIndex = spinnerMenu.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };

        spinner.setOnItemSelectedListener(spinnerListener);
    }

    private void makeButtonEvent() {
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText nameText = findViewById(R.id.nameText);
                EditText phoneText = findViewById(R.id.phoneText);

                String name = nameText.getText().toString();
                String phone = phoneText.getText().toString();

                if (!name.equals("") && !phone.equals("")) {
                    Customer customer = new Customer(name, phone, spinnerIndex);

                    customerAdapter.customers.add(customer);
                    customerAdapter.notifyDataSetChanged();

                    TextView customerCountText = findViewById(R.id.customerCountText);
                    customerCountText.setText(customerAdapter.customers.size() + " 명");
                }
            }
        });
    }

    private void makeListView() {
        customerAdapter = new CustomerAdapter();
        listView.setAdapter(customerAdapter);
    }

    private void mappingValues() {
        spinner = findViewById(R.id.spinner);
        listView = findViewById(R.id.listView);
        linearLayout = findViewById(R.id.container);
        // 여기에 linearLayout 을 선언해 줬다.
        addButton = findViewById(R.id.addButton);
    }

    private void makeSpinner() {
        spinnerMenu = new ArrayList<>();
        spinnerMenu.add(R.drawable.ic_launcher_foreground);
        spinnerMenu.add(R.drawable.ic_launcher_background);

        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this, R.layout.support_simple_spinner_dropdown_item, spinnerMenu);
        spinner.setAdapter(arrayAdapter);
    }
}
```

