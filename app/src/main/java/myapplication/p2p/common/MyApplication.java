package myapplication.p2p.common;

import android.app.Application;
import android.content.Context;

/**
 * Created by zhouzhou on 2017/6/20.
 */

public class MyApplication extends Application{
    public static Context getContext() {
        return context;
    }
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化上下文
        context = this;

    }
}
