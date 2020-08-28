package com.architect.zhang;

import com.archiect.zhang.base.BaseApplication;
import com.archiect.zhang.base.loadsir.CustomCallback;
import com.archiect.zhang.base.loadsir.EmptyCallback;
import com.archiect.zhang.base.loadsir.ErrorCallback;
import com.archiect.zhang.base.loadsir.LoadingCallback;
import com.archiect.zhang.base.loadsir.TimeoutCallback;
import com.kingja.loadsir.core.LoadSir;

public class ArchitectApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new TimeoutCallback())
                .addCallback(new CustomCallback())
                .setDefaultCallback(LoadingCallback.class)
                .commit();
    }
}
