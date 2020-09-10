package com.temp.mvvm.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


// Room Database는 앞서 생성한 Entity와 ado를 한 곳에 묶고, 이전에 SQLiteOpenHelper와 같은 역할,
/**
 * !주의점
 * 1. 반드시 추상클래스 -> 실제 SQLite처리시 아래 클래스가아닌 아래클래스를 상속하여 실행시 자동실행되는 [아래클래스명]_Impl 클래스 이기땜누
 *
 * 변경될 일이없음.
 * */


// DB 생성하는거 비용 크기 때문에, 반드시 싱글톤으로 생성
@Database(entities = {User.class}, version = 1)                                                     // 해당 Db의 Entity들을 연결
public abstract class MyDatabase extends RoomDatabase {

    public abstract UserDao userDao();                                                              // DB와 연결되는 Dao로 알아서 getter가 정의된다.
    private static MyDatabase db;

    //싱글톤 생성 메소드
    public static MyDatabase getInstance(final Context context){
        if(db == null){
            synchronized (MyDatabase.class){
                if(db == null){
                    db = Room.databaseBuilder(context.getApplicationContext(),
                            MyDatabase.class, "test_db").build();
                }
            }
        }
        return db;
    }



}
