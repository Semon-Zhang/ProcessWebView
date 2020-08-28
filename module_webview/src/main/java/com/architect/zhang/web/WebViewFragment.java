package com.architect.zhang.web;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.archiect.zhang.base.loadsir.ErrorCallback;
import com.archiect.zhang.base.loadsir.LoadingCallback;
import com.architect.zhang.common.config.Constans;
import com.architect.zhang.web.webprocess.chromeclient.MyWebChromeClient;
import com.architect.zhang.web.databinding.FragmentWebBinding;
import com.architect.zhang.web.webprocess.viewclient.MyWebViewClient;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

public class WebViewFragment extends Fragment implements IWebClientListener, OnRefreshListener {
    String mUrl;
    FragmentWebBinding mBinding;
    private LoadService mLoadService;
    boolean mIsError;
    private String TAG = "WebViewFragment";

    public static Fragment newInstance(String url) {
        WebViewFragment fragment = new WebViewFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constans.URL, url);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mUrl = bundle.getString(Constans.URL, "");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_web, container, false);
        String url = "file:///android_asset/" + "demo.html";
        Log.d(TAG, url);
        mBinding.webViewFragment.loadUrl(url);
        mBinding.webViewFragment.registerWebViewCallBack(this);
        mLoadService = LoadSir.getDefault().register(mBinding.smartrefreshlayout, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                mLoadService.showCallback(LoadingCallback.class);
                mBinding.webViewFragment.reload();
            }
        });

        mBinding.smartrefreshlayout.setOnRefreshListener(this);
        mBinding.smartrefreshlayout.setEnableRefresh(false);
        mBinding.smartrefreshlayout.setEnableLoadMore(false);

        return mLoadService.getLoadLayout();

    }

    @Override
    public void onPageStarted(String url) {
        if (mLoadService != null) {
            mLoadService.showCallback(LoadingCallback.class);
        }
    }

    @Override
    public void onPageFinished(String url) {
        if (mIsError) {
            mBinding.smartrefreshlayout.setEnableRefresh(true);
        } else {
            mBinding.smartrefreshlayout.setEnableRefresh(false);
        }
        Log.d(TAG, "pageFinished");
        mBinding.smartrefreshlayout.finishRefresh();
        if (mLoadService != null) {
            if (mIsError) {
                mLoadService.showCallback(ErrorCallback.class);
            } else {
                mLoadService.showSuccess();
            }
        }
        mIsError = false;
    }

    @Override
    public void onError() {
        Log.e(TAG, "onError");
        mIsError = true;
        mBinding.smartrefreshlayout.finishRefresh();
    }

    @Override
    public void updateTitle(String title) {

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mBinding.webViewFragment.reload();
    }
}
