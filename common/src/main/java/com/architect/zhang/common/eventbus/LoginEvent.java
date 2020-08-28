package com.architect.zhang.common.eventbus;

/**
 * created by zhangxiao on 2020/8/26
 */
public class LoginEvent {
    public String userName;
    public LoginEvent(String userName) {
        this.userName = userName;
    }
}
