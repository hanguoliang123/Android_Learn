package com.ecarx.learncontext;

import android.app.Application;

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
}
