package com.ecarx.musicplayer;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MusicPlayerService extends Service {
    public MusicPlayerService() {
    }

    @Override
    public void onCreate() {
        System.out.println("音乐播放服务开启了");
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        System.out.println("音乐播放服务关闭了");
        super.onDestroy();
    }
}
