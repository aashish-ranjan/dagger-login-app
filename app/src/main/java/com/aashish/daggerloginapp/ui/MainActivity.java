package com.aashish.daggerloginapp.ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.aashish.daggerloginapp.R;

public class MainActivity extends BaseActivity{
    private static final String TAG = "MainActivity";

    private TextView mTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String userInfo = getIntent().getStringExtra("user_info");
        mTextView = findViewById(R.id.textView);
        if (userInfo != null) {
            mTextView.setText(userInfo);
        }
    }
}
