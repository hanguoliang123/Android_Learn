package com.ecarx.learncontext;

import android.app.Application;
import android.content.res.Configuration;

/**
 * Created by Lenovo on 2018/8/22.
 */
public class App extends Application{

    private String textData = "default";

    public void setTextData(String textData) {
        this.textData = textData;
    }

    public String getTextData() {
        return textData;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("App onCreate");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        System.out.println("App onTerminate");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();

    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
