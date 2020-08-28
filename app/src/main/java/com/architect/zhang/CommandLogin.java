package com.architect.zhang;

import android.os.RemoteException;
import android.util.Log;

import com.archiect.zhang.base.BaseServiceLoader;
import com.architect.zhang.common.autoservice.IUserCenterService;
import com.architect.zhang.common.eventbus.LoginEvent;
import com.architect.zhang.web.ICallbackMainToWebProcessInterface;
import com.architect.zhang.web.command.Command;
import com.google.auto.service.AutoService;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

/**
 * created by zhangxiao on 2020/8/25
 */
@AutoService({Command.class})
public class CommandLogin implements Command {
    IUserCenterService iUserCenterService = BaseServiceLoader.load(IUserCenterService.class);
    private static String TAG = "CommandLogin";
    private ICallbackMainToWebProcessInterface callback;
    private String callBackName;

    public CommandLogin() {
        EventBus.getDefault().register(this);
    }

    @Override
    public String name() {
        return "login";
    }

    @Override
    public void execute(Map param, ICallbackMainToWebProcessInterface callback) {
        this.callback = callback;
        this.callBackName = param.get("callbackname").toString();
        if (iUserCenterService != null && !iUserCenterService.isLogined()) {
            iUserCenterService.login();
        }
    }

    @Subscribe
    public void onMessageEvent(LoginEvent event) {
        Log.d(TAG, event.userName);
        HashMap map = new HashMap();
        map.put("accountName", event.userName);
        if (this.callback != null) {
            try {
                this.callback.handlerCallback(callBackName, new Gson().toJson(map));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
