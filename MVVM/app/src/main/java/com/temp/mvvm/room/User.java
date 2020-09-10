package com.temp.mvvm.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * data cach -> app이 kill당하면 이용불가
 * 이를막기위해 request를 cach하면? ->  다른형태 request를 보내는경우 데이터가 틀어짐
 * => Room 이용
 * */

@Entity(tableName = "user")
public class User {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String name;
    private String lastName;

    public User(String name, String lastName){
        this.name = name;
        this.lastName = lastName;
    }


    // getter 무조건 필요함(?)
    public void setId(int id) {this.id = id;}
    public int getId(){return id;}                                                                  // ? setter는 왜 ?
    public String getName(){return name;}
    public String getLastName(){return lastName;}
}



// Entitu와 DB 연결은 annotation통해 함을 알겠는데
// Dao는 어떤 db를 선택하는지? -> DAO는 그런거 상관없이 쿼린가?