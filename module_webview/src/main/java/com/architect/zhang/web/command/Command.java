package com.architect.zhang.web.command;

import com.architect.zhang.web.ICallbackMainToWebProcessInterface;

import java.util.Map;

/**
 * created by zhangxiao on 2020/8/24
 */
public interface Command {
     String name();

     void execute(Map param, ICallbackMainToWebProcessInterface callback);
}
