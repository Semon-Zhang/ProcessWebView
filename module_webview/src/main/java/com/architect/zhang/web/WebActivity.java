package com.architect.zhang.web;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.provider.SyncStateContract;
import android.webkit.WebSettings;

import com.architect.zhang.common.config.Constans;
import com.architect.zhang.web.databinding.ActivityWebBinding;


public class WebActivity extends AppCompatActivity {
    ActivityWebBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_web);

//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        transaction.replace(R.id.activity_web_fragment, WebViewFragment.newInstance("")).commit();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragment = WebViewFragment.newInstance(getIntent().getStringExtra(Constans.URL));
        transaction.replace(R.id.web_view_fragment, fragment).commit();
    }
}
