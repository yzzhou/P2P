package myapplication.p2p.common;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by zhouzhou on 2017/6/21.
 */

public class AppManager {
    private AppManager(){

    }
    private static AppManager appManager = new AppManager();

    public static AppManager getInstance(){
        return appManager;
    }

    private Stack<Activity> stack = new Stack<>();

    public  void addActivity(Activity activity ){
        if(activity !=null){
            stack.add(activity);
        }

    }
    public  void removeActivity(Activity activity) {
        if(activity!=null){
            for(int i = stack.size() - 1; i>=0;i--){
                Activity currentActivity = stack.get(i);
                if(currentActivity.getClass().equals(activity.getClass())){

                    currentActivity.finish();
                    stack.remove(i);
                }
            }

        }
    }
    public  void removeAll(){
        for(int i = stack.size()-1;i>=0;i ++){
            Activity currentActivity = stack.get(i);
            if(currentActivity !=null){
                currentActivity.finish();
                stack.remove(i);
            }
        }
    }

}
