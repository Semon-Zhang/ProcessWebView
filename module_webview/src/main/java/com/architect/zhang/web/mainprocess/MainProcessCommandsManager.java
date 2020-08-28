package com.architect.zhang.web.mainprocess;

import android.os.RemoteException;
import android.util.Log;

import com.architect.zhang.web.ICallbackMainToWebProcessInterface;
import com.architect.zhang.web.IWebToMainProcessInterface;
import com.architect.zhang.web.command.Command;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * created by zhangxiao on 2020/8/24
 */
public class MainProcessCommandsManager extends IWebToMainProcessInterface.Stub {
    private static MainProcessCommandsManager sInstance;
    private static String TAG = "MainProcessCommandsManager";
    private HashMap<String, Command> mCommands = new HashMap<>();

    public static MainProcessCommandsManager getInstance() {
        if (sInstance == null) {
            synchronized (MainProcessCommandsManager.class) {
                sInstance = new MainProcessCommandsManager();
            }
        }
        return sInstance;
    }

    private MainProcessCommandsManager() {
        ServiceLoader<Command> serviceLoader = ServiceLoader.load(Command.class);
        for (Command command : serviceLoader) {
            if (!mCommands.containsKey(command.name())) {
                mCommands.put(command.name(), command);
            }
        }
    }

    public void executeCommand(String commandName, Map params,ICallbackMainToWebProcessInterface callback) {
        Command command = mCommands.get(commandName);
        if (command != null) {
            Log.d(TAG, "command != null");
            command.execute(params,callback);
        }
        Log.d(TAG, commandName);
    }

    @Override
    public void handlerWebCommand(String commandName, String jsonParams, ICallbackMainToWebProcessInterface callback) throws RemoteException {
        Log.d(TAG,commandName+jsonParams);
        MainProcessCommandsManager.getInstance().executeCommand(commandName, new Gson().fromJson(jsonParams, Map.class),callback);
    }
}
