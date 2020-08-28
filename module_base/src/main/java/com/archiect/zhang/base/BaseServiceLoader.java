package com.archiect.zhang.base;

import java.util.ServiceLoader;

/**
 * created by zhangxiao on 2020/8/10
 */
public class BaseServiceLoader {
    private BaseServiceLoader() {
    }

    public static <T> T load(Class<T> service) {
        try {
            return ServiceLoader.load(service).iterator().next();
        } catch (Exception e) {
            return null;
        }
    }
}
