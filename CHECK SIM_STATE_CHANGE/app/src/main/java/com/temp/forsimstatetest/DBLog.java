package com.temp.forsimstatetest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DBLog implements BaseColumns {
    private static final String DATABASE_NAME = "log.db";
    private static final int DATABASE_VERSION = 100000;

    private static final  String TABLE_NAME = "log";
    private static final  String FIELD_TIME = "TIME";
    private static final  String FIELD_RESULT = "RESULT";

    private static DBLog mInstance;

    static {
        mInstance = new DBLog();
    }

    public static DBLog getInstance() {
        return mInstance;
    }



    public static long insert(Context context, String time, String result) {

        ContentValues values = new ContentValues();
        values.put(FIELD_TIME, time);
        values.put(FIELD_RESULT, result);

        return insert(context, values);
    }

    private static long insert(Context context, ContentValues values) {

        DatabaseHelper helper = new DatabaseHelper(context);
        SQLiteDatabase db = null;

        long id = -1;
        try {
            db = helper.getWritableDatabase();
            id = db.insert(TABLE_NAME, "", values);
        } catch (Exception e) {
            Logger.e(DBLog.class, e);
        } finally {
            db.close();
        }
        return id;
    }

    public static void query(Context context, StringBuilder str){
        DatabaseHelper helper = new DatabaseHelper(context);
        SQLiteDatabase db = null;

        try {
            db = helper.getWritableDatabase();
            Cursor cursor = db.rawQuery("select * from " + TABLE_NAME, null);

            while(cursor.moveToNext()){
                str.append("[" + cursor.getString(1) +"]    "+ "Id : " + cursor.getString(0) + "   "+cursor.getString(2) + "\n");
            }
        } catch (Exception e) {
            Logger.e(DBLog.class,e);
        } finally {
            db.close();
        }
    }


    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Logger.d(getClass(), "디비 테이블 생성됨");
            db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY," + FIELD_TIME + " TEXT," + FIELD_RESULT + " TEXT" + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }
}
