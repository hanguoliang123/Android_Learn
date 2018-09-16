package com.ecarx.musicplayer;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import java.util.List;

public class MusicPlayerService extends Service {

    public static final int PLAYING = 1;
    public static final int MUSIC_STOP = 0;
    public static final int PAUSE = 2;

    public static int playingStatus;

    private SharedPreferences sp;
    private MediaPlayer mediaplayer;

    public MusicPlayerService() {
    }

    @Override
    public void onCreate() {
        System.out.println("音乐播放服务开启了");
        sp = getSharedPreferences("config",MODE_PRIVATE);
        mediaplayer = new MediaPlayer();
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    @Override
    public void onDestroy() {
        System.out.println("音乐播放服务关闭了");
        super.onDestroy();
    }

    private class MyBinder extends Binder implements IMusicService{

        @Override
        public void callPlay(List<String> playList, int position) {
            play(playList,position);
        }

        @Override
        public void callStop() {
            stopPlayer();
        }
    }

    private void stopPlayer() {
        if(mediaplayer!=null){
            mediaplayer.stop();
            mediaplayer.release();
            mediaplayer = null;
        }
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.cancelAll();
    }

    /**
     * 播放音乐
     * @param playList:所有音频列表
     * @param position:当前的位置
     */
    private void play(final List<String> playList, final int position) {

        try {
            if(mediaplayer.isPlaying()){
                mediaplayer.stop();
            }
            mediaplayer.reset();
            mediaplayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            System.out.println(playList.get(position));
            mediaplayer.setDataSource(playList.get(position));
            mediaplayer.prepare();
            mediaplayer.start();
            String path = playList.get(position);

            showNotification(path.substring(path.lastIndexOf("/")+1));

            mediaplayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    int mode = sp.getInt("mode", 0);
                    if(mode == SettingActivity.CYCLE){
                        play(playList,position);
                    }else if(mode == SettingActivity.NEXT){
                        //播放下一曲
                        int newPosition = position + 1;
                        if(newPosition >= playList.size()){
                            newPosition = 0;
                        }
                        play(playList,newPosition);
                    }else if(mode == SettingActivity.STOP){
                        playingStatus = MUSIC_STOP;
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            playingStatus = MUSIC_STOP;
        }
    }

    /**
     * 显式播放音乐的通知提醒
     * @param filename
     */
    private void showNotification(String filename) {
        Notification notification = new Notification.Builder(this).setContentTitle("定制音乐播放器正在播放")
                .setContentText(filename)
                .setSmallIcon(R.drawable.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher))
                .build();
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(0,notification);
    }
}
