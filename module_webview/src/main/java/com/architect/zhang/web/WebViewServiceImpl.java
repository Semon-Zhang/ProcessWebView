package com.architect.zhang.web;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.architect.zhang.common.autoservice.IWebViewService;
import com.google.auto.service.AutoService;

/**
 * created by zhangxiao on 2020/8/10
 */
@AutoService({IWebViewService.class})
public class WebViewServiceImpl implements IWebViewService {
    @Override
    public void startWebViewActivity(Context context, String url, String title) {
        if (context != null) {
            context.startActivity(new Intent(context, WebActivity.class));
        }
    }

    @Override
    public Fragment getWebFragment(String url) {
        return WebViewFragment.newInstance(url);
    }
}
