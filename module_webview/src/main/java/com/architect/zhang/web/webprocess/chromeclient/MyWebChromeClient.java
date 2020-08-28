package com.architect.zhang.web.webprocess.chromeclient;

import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.architect.zhang.web.IWebClientListener;

public class MyWebChromeClient extends WebChromeClient {
    private String TAG = "MyWebChromeClient";
    private IWebClientListener mClientListener;

    public MyWebChromeClient(IWebClientListener clientListener) {
        this.mClientListener = clientListener;
    }


    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
        if (mClientListener != null) {
            mClientListener.updateTitle(title);
        } else {
            Log.e(TAG, "mClientListener = null");
        }
    }

    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        Log.d(TAG, consoleMessage.message());
        return super.onConsoleMessage(consoleMessage);
    }
}
