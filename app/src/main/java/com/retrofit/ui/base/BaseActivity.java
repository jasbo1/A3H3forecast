package com.retrofit.ui.base;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {


    protected abstract int getLayoutId();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
    }

    protected void toast(String msg) {
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();

    }
    /*protected  void replaceFragment(int containerId, Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(containerId,fragment)
                .commit();*/

    }
