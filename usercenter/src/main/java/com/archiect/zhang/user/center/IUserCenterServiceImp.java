package com.archiect.zhang.user.center;

import android.content.Intent;
import android.util.Log;

import com.archiect.zhang.base.BaseApplication;
import com.architect.zhang.common.autoservice.IUserCenterService;
import com.google.auto.service.AutoService;

/**
 * created by zhangxiao on 2020/8/25
 */
@AutoService({IUserCenterService.class})
public class IUserCenterServiceImp implements IUserCenterService {
    @Override
    public boolean isLogined() {
        return false;
    }

    @Override
    public void login() {
        Log.d("IUserCenterServiceImp", "login");
        Intent intent = new Intent(BaseApplication.sApp, LoginActivity.class);
        BaseApplication.sApp.startActivity(intent);
    }
}
