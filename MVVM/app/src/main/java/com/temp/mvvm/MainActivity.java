package com.temp.mvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.temp.mvvm.databinding.ActivityMainBinding;
import com.temp.mvvm.viewmodel.MyViewModelFactory;
import com.temp.mvvm.viewmodel.UserProfileViewModel;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;                                                                    // xml 카멜스 기법
    UserProfileViewModel model;

    private static MainActivity instance;

    public static MainActivity getInstance() {
        return instance;
    }

    public static void setInstance(MainActivity instance) {
        MainActivity.instance = instance;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setInstance(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //model = ViewModelProviders.of(this).get(UserProfileViewModel.class);
        model = ViewModelProviders.of(this, new MyViewModelFactory(this.getApplication())).get(UserProfileViewModel.class);

        binding.setLifecycleOwner(this);
        binding.setViewModel(model);
    }
    public ActivityMainBinding getBinding(){ return binding;}

}


