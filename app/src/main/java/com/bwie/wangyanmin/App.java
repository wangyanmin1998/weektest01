package com.bwie.wangyanmin;

import android.app.Application;

/*
 *@auther:王彦敏
 *@Date: 2019/12/31
 *@Time:9:58
 *@Description:
 * */
public class App extends Application {
    public static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
    }
}
