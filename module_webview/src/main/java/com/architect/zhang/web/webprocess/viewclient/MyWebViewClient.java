package com.architect.zhang.web.webprocess.viewclient;

import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.architect.zhang.web.IWebClientListener;

public class MyWebViewClient extends WebViewClient {
    private IWebClientListener mClientListener;
    private String TAG = "MyWebViewClient";

    public MyWebViewClient(IWebClientListener clientListener) {
        this.mClientListener = clientListener;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (mClientListener != null) {
            mClientListener.onPageStarted(url);
        } else {
            Log.e(TAG, "IWebClientListener =null");
        }
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (mClientListener != null) {
            mClientListener.onPageFinished(url);
        } else {
            Log.e(TAG, "IWebClientListener =null");
        }
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
        if (mClientListener != null) {
            mClientListener.onError();
        } else {
            Log.e(TAG, "IWebClientListener =null");
        }
    }
}
