package com.temp.mvvm.room;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

// DAO는 Entity에 접근하는 method정의 ->  CRUD
@Dao                                                                                            // Dao임을 알림
public interface UserDao {
    @Insert(onConflict = REPLACE)
    void save(User user);

    @Query("SELECT * FROM user WHERE id = :userId")                                             // 쿼리에 매개변수를 의미하고싶으면 변수앞에 :을 붙여야함
    LiveData<User> load(String userId);                                                         // LiveData반환
                                                                                                // Room은 데이터 변경시점을 알고있기때문에 변경일어나면 LiveData를 이용하여
                                                                                                // active한 observer에게 notify한다.

    @Query("SELECT * FROM user")
    LiveData<List<User>> loadAll();

}

/**
 * Room Query 2종류
 * 1. Simple Query : List형태, return List<UserEntity> -> 변화 감지못함
 * 2. Observable Query :  LiveData형태, return LiveDate<List<UserEntity>>  -> 변화감지
 *
 * Lsit<T> 는 All, T는 한개의 record(Entity)
 * */

