package myapplication.p2p.utils;

import android.content.Context;
import android.view.View;

import myapplication.p2p.common.MyApplication;

/**
 * Created by zhouzhou on 2017/6/20.
 */

public class UIUtils {
    public static View Infelat(int id){
        return View.inflate(getContext(),id,null);
    }
    /*
    * 返回一个上下文
    * */
    private static Context getContext() {

        return MyApplication.getContext();
    }
    public static String stringFormat(int id, String value){
        String versionName = String.format(getString(id), value);
        return versionName;
    }

    private static String getString(int id) {
        return getContext().getResources().getString(id);
    }

}
