package com.temp.mvvm.repository;

import android.app.Application;
import android.net.DnsResolver;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.temp.mvvm.room.MyDatabase;
import com.temp.mvvm.room.User;
import com.temp.mvvm.room.UserDao;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


/**
 * ViewModel의 scope는 Activity나 lifecycle에 묶임
 * SO -> Data fetching하는 부분은 repository에 위임하는게 좋다.
 *
 * 역할  Data Operation을 Handling
 * So -> 명확한 API를 제공해야한다
 * */
public class UserRepository {
    //private WebService mWebService;


    // Room 연결
     private final UserDao mUserDao;                                            // Room에서 가져올 데이터
     private LiveData<List<User>> mUser;

     // 싱글스레드
     private final Executor executor;


     public UserRepository(Application application){
         // 추후 Network 사용하는 mWebService와 executor이용해야함 (백그라운드)
         mUserDao = MyDatabase.getInstance(application).userDao();
         executor= Executors.newSingleThreadExecutor();                         // 싱글쓰레드 executor

         mUser = mUserDao.loadAll();

     }
     /**
      * ? Q. Repository 인스턴스 마다 관찰하는게다를듯..
      * 이를 해결하기위해 싱글톤으로 해야하나?
      * -> 우선그렇게 이해하고 단위 테스트 (독립성) 공부하면서 확인
      * */


    public LiveData<List<User>> getUser(){
        //refreshUser(userId);
        //return mUserDao.loadAll();
        return mUser;
    }

    public void insertRecord(final String name, final String lastName){
       // this.name =name;
        //this.lastName=lastName;
        executor.execute(new Runnable() {
            @Override
            public void run() {
                Log.d("TEST", "레포짓토리: 쓰레드실행" );

                User user = new User(name, lastName);
                mUserDao.save(user);
            }
        });
    }


    // DB나 네트워크는 UI-Thread에서 하면안되므로 백그라운드에서 해야함

    /*
    private void refreshUser(final String userId){
        executor.execute(()->{
            boolean userExists = mUserDao.존재하는가?
            if(!userExists){
                Response response = webService.getUser(userId).execute();           // Network로 부터 가져옴
                mUserDao.save(response.body());
            }
        });
    }
    */


    /*
    // private UserCache userCache;                                    // 캐쉬해두어 Network사용 낭비 줄이기위해
    // if Web Service를 통해 받는 형태
    public LiveData<User> getUser(int userId){
        LiveData<User> cached = userCache.get(userId);
        if(cached != null) return cached;

        final MutableLiveData<User> data = new MutableLiveData<>();
        userCache.put(userId, data);

        webService.getUser(userId).enqueue(new DnsResolver.Callback<>{
            @Override
            public void onResponse(Call<User> call, Response<User> response){
                // network responses 처리
                data.setValue(response.body());
            }
        });
        return data;
    }*/
}
/**
 *  Repository를 사용함으로써 data가 추상화됨
 *  -> ViewModel은 data가 어떻게 fetch되었는지 알 필요 없어짐
 *  -> ViewModel과 Data가 독립된다.
 *  -> 어느 하나 기능이 바뀌어도 ㄱㅊ
 * */


/**
 * data가 변경될때마다 백그라운드에서 업데이트 된다.
 * */


