package com.temp.mvvm.viewmodel;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MyViewModelFactory implements ViewModelProvider.Factory {
    private Application mApplication;
    //private String mtemp;


    /**
     * ViewModel에 arg를 넘겨주고 싶으면 팩토리를 이용해야한다.
     * 팩토리를 이용안하면 무족권 ViewModel생성자 arg없는게 필요함
     * 해당 코드에선  ...
     * */
    public MyViewModelFactory(Application application) {
        mApplication = application;
        //mtemp = temp
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new UserProfileViewModel(mApplication);

        //return(T) new UserProfileViewModel(mApplication, mtemp);
    }
}