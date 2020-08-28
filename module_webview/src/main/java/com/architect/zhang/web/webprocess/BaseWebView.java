package com.architect.zhang.web.webprocess;

import android.content.Context;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.architect.zhang.web.ICallbackMainToWebProcessInterface;
import com.architect.zhang.web.IWebClientListener;
import com.architect.zhang.web.bean.JsParam;
import com.architect.zhang.web.webprocess.chromeclient.MyWebChromeClient;
import com.architect.zhang.web.webprocess.viewclient.MyWebViewClient;
import com.architect.zhang.web.webprocess.webviewsetting.DefaultWebViewSetting;
import com.google.gson.Gson;

/**
 * created by zhangxiao on 2020/8/21
 */
public class BaseWebView extends WebView {
    private static String TAG = "BaseWebView";

    public BaseWebView(Context context) {
        super(context);
        initView();
    }

    public BaseWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public BaseWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public BaseWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        addJavascriptInterface(this, "xiangxuewebview");
        WebViewProcessCommandDispatcher.getInstance().initAidlConnection();
        DefaultWebViewSetting.getInstance().setSettings(this);
    }

    public void registerWebViewCallBack(IWebClientListener webViewCallBack) {
        setWebViewClient(new MyWebViewClient(webViewCallBack));
        setWebChromeClient(new MyWebChromeClient(webViewCallBack));
    }

    @JavascriptInterface
    public void takeNativeAction(final String jsParam) {
        Log.i(TAG, jsParam);
        if (!TextUtils.isEmpty(jsParam)) {
            final JsParam jsParamObject = new Gson().fromJson(jsParam, JsParam.class);
            WebViewProcessCommandDispatcher.getInstance().executeCommand(jsParamObject.name, new Gson().toJson(jsParamObject.param), new ICallbackMainToWebProcessInterface.Stub() {
                @Override
                public void handlerCallback(String callBackName, String response) throws RemoteException {
                    handleCallback(callBackName,response);
                }
            });
        }
    }

    public void handleCallback(final String callbackName, final String response){
        if(!TextUtils.isEmpty(callbackName) && !TextUtils.isEmpty(response)){
            post(new Runnable() {
                @Override
                public void run() {
                    String jscode = "javascript:xiangxuejs.callback('" + callbackName + "'," + response + ")";
                    Log.e("xxxxxx", jscode);
                    evaluateJavascript(jscode, null);
                }
            });
        }
    }
}
