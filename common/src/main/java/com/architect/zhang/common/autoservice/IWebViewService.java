package com.architect.zhang.common.autoservice;

import android.content.Context;

import androidx.fragment.app.Fragment;

/**
 * created by zhangxiao on 2020/8/10
 */
public interface IWebViewService {

    public void startWebViewActivity(Context context, String url, String title);

    public Fragment getWebFragment(String url);
}
