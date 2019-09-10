package com.example.p576;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqlHelper extends SQLiteOpenHelper {
    private static String NAME = "contents.db";
    private static int VERSION = 1;

    private static String TABLE_NAME = "contents";

    private static String ID = "_id";
    private static String ID_USER = "id";
    private static String TITLE = "title";
    private static String DATE = "date";
    private static String CONTENT = "content";

    public SqlHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlForCreateDB = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                + ID + " integer primary key autoincrement, "
                + ID_USER + " text, "
                + TITLE + " text, "
                + DATE + " text, "
                + CONTENT + " text)";

        sqLiteDatabase.execSQL(sqlForCreateDB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (newVersion > 1) {
            String sqlForDropDB = "DROP TABLE IF EXISTS " + TABLE_NAME;
            sqLiteDatabase.execSQL(sqlForDropDB);
        }
    }

    public void insert(String id, String title, String date, String content) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        String sqlForInsert = "INSERT INTO " + TABLE_NAME + " (" + ID_USER + "," + TITLE  + "," + DATE + "," + CONTENT + ") " + "VALUES" + " ("
                + id + ", " + title + ", " + date + ", " + content + ")";

        sqLiteDatabase.execSQL(sqlForInsert);
    }

    public Cursor select() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        String sqlForSelect = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(sqlForSelect, null);

        return cursor;
    }
}
