package com.architect.zhang.web.webprocess;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.archiect.zhang.base.BaseApplication;
import com.architect.zhang.web.ICallbackMainToWebProcessInterface;
import com.architect.zhang.web.IWebToMainProcessInterface;
import com.architect.zhang.web.mainprocess.MainProcessCommandService;

/**
 * created by zhangxiao on 2020/8/24
 */
public class WebViewProcessCommandDispatcher implements ServiceConnection {
    private static WebViewProcessCommandDispatcher sInstance;
    private IWebToMainProcessInterface iWebToMainProcessInterface;
    private static String TAG = "WebViewProcessCommandDispatcher";

    public static WebViewProcessCommandDispatcher getInstance() {
        if (sInstance == null) {
            synchronized (WebViewProcessCommandDispatcher.class) {
                sInstance = new WebViewProcessCommandDispatcher();
            }
        }
        return sInstance;
    }

    public void initAidlConnection() {
        Intent intent = new Intent(BaseApplication.sApp, MainProcessCommandService.class);
        BaseApplication.sApp.bindService(intent, this, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        iWebToMainProcessInterface = IWebToMainProcessInterface.Stub.asInterface(service);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        iWebToMainProcessInterface = null;
        initAidlConnection();
    }

    @Override
    public void onBindingDied(ComponentName name) {
        iWebToMainProcessInterface = null;
        initAidlConnection();
    }

    public void executeCommand(String commandName, String params, ICallbackMainToWebProcessInterface callback) {
        Log.d(TAG, commandName + params);
        if (iWebToMainProcessInterface != null) {
            try {
                iWebToMainProcessInterface.handlerWebCommand(commandName, params,callback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
