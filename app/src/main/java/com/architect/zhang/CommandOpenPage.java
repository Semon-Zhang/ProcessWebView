package com.architect.zhang;

import android.content.Intent;

import com.archiect.zhang.base.BaseApplication;
import com.architect.zhang.web.ICallbackMainToWebProcessInterface;
import com.architect.zhang.web.command.Command;
import com.google.auto.service.AutoService;

import java.util.Map;

/**
 * created by zhangxiao on 2020/8/24
 */
@AutoService(Command.class)
public class CommandOpenPage implements Command {
    @Override
    public String name() {
        return "openPage";
    }

    @Override
    public void execute(Map param, ICallbackMainToWebProcessInterface callback) {
        BaseApplication.sApp.startActivity(new Intent(BaseApplication.sApp, MainProcessTestActivity.class));
    }
}
