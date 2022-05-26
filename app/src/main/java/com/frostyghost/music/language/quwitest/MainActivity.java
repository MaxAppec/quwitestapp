package com.frostyghost.music.language.quwitest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.frostyghost.music.language.quwitest.core.BindingActivity;
import com.frostyghost.music.language.quwitest.databinding.ActivityMainBinding;

public class MainActivity extends BindingActivity<ActivityMainBinding> {

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}