package com.temp.mvvm.viewmodel;


import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.temp.mvvm.MainActivity;
import com.temp.mvvm.databinding.ActivityMainBinding;
import com.temp.mvvm.repository.UserRepository;
import com.temp.mvvm.room.User;
import java.util.List;

// UI에서 사용하는 Data를 준비하는 class
/**
 * Repository : Data를 준비
 * 와
 * UI(View)   :  Data를 시각화
 * 중계역할 : 가져다 전달..
 * */
public class UserProfileViewModel extends ViewModel {

    private LiveData<List<User>> mUser;                                    // View에게 보내줄, 보여줄 데이터
    private UserRepository userRepo;                                        // 가져올,,ViewModel과 Repository 연결

    private ActivityMainBinding binding;

    public UserProfileViewModel(Application application){
        super();
        userRepo = new UserRepository(application);
        mUser = userRepo.getUser();
        binding = MainActivity.getInstance().getBinding();
    }

    public LiveData<List<User>> getUser(){
        return mUser;
    }

    public void insert(){
        userRepo.insertRecord(binding.name.getText().toString(), binding.lastName.getText().toString());
    }
    // 여기서 부터 LiveData를 String으로 변환하여 text에 찍어주는거
 }
