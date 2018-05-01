package com.luo.utils;

import android.app.Application;

import luo.com.utils.Utils;

/**
 * Created by Administrator on 2018/5/1.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
    }
}
