# Android DB

## I. WEB 에서의 DB

- IndexedDB : JSON 형식을 이용한 DB 저장방식 (mongoDB 처럼)
- Web SQL : SQLite 를 이용한 DB 저장방식(mariaDB 처럼)
  - DB 저장방식이 표준화가 안되어 있기에 이렇게 나뉘어있다.



## II. Android 에서의 DB

- SQLite 를 이용해 DB 다!

- SQLiteOpenHelper, SQLiteDatabase 클래스를 이용하여 구현한다.

```java
public class DatabaseHelper extends SQLiteOpenHelper {
    public static String NAME = "employee.db";
    public static int VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        println("onCreate 호출됨");

        String sql = "create table if not exists emp("
            + " _id integer PRIMARY KEY autoincrement, "
            + " name text, "
            + " age integer, "
            + " mobile text)";

        db.execSQL(sql);
    }

    public void onOpen(SQLiteDatabase db) {
        println("onOpen 호출됨");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        println("onUpgrade 호출됨 : " + oldVersion + " -> " + newVersion);

        if (newVersion > 1) {
            db.execSQL("DROP TABLE IF EXISTS emp");
        }
    }

    public void println(String data) {
        Log.d("DatabaseHelper", data);
    }
}
```



- Helper는 Database 를 추가하는 것을 도와주는 것이고,
- 나머지 테이블을 만들고 DML 을 사용하는 건 SQLiteDatabase 를 사용해서 한다.



- 생성

```java
println("createDatabase 호출됨.");

databaseHelper = new DatabaseHelper(MainActivity.this);
sqLiteDatabase = databaseHelper.getWritableDatabase();

if (sqLiteDatabase == null) {
    println("데이터베이스를 먼저 생성하세요.");
    return;
}

sqLiteDatabase.execSQL("create table if not exists " + name + "("
                       + " _id integer PRIMARY KEY autoincrement, "
                       + " name text, "
                       + " age integer, "
                       + " mobile text)");

println("테이블 생성함 : " + name);
```



- 입력

```java
println("insertRecord 호출됨.");

if (sqLiteDatabase == null) {
    println("데이터베이스를 먼저 생성하세요.");
    return;
}

sqLiteDatabase.execSQL("insert into " + name
                        + "(name, age, mobile) "
                       + " values "
                       + "('John', 20, '010-1000-1000')");

println("레코드 추가함.");
```



- 검색

```java
println("executeQuery 호출됨.");

Cursor cursor = sqLiteDatabase.rawQuery("select _id, name, age, mobile from emp", null);
int recordCount = cursor.getCount();
println("레코드 개수 : " + recordCount);

for (int i = 0; i < recordCount; i++) {
    cursor.moveToNext();

    int id = cursor.getInt(0);
    String name = cursor.getString(1);
    int age = cursor.getInt(2);
    String mobile = cursor.getString(3);

    println("레코드 #" + i + " : " + id + ", " + name + ", " + age + ", " + mobile);
}

cursor.close();
// 안드로이드에서는 SELECT 로 Cursor 클래스를 사용한다.
```



## III. 컨텐트 프로바이더

- 다른 앱으로부터 값들을 받아 올 수 있는 방법
- 예를 들어, 연락처, 전화번호부 등의 값들을 가지고 올 수 있다!

```java
public void openGallery() {
    Intent intent = new Intent();
    intent.setType("image/*");
    intent.setAction(Intent.ACTION_GET_CONTENT);

    startActivityForResult(intent, 101);
    // intent 를 이용해 값을 받아온다.
}

@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if(requestCode == 101) {
        if(resultCode == RESULT_OK) {
            Uri fileUri = data.getData();

            ContentResolver resolver = getContentResolver();

            try {
                InputStream instream = resolver.openInputStream(fileUri);
                Bitmap imgBitmap = BitmapFactory.decodeStream(instream);
                imageView.setImageBitmap(imgBitmap);
// Uri 를 이용해서 inputStream 을 통해 값을 받아온다.
                instream.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}


```

