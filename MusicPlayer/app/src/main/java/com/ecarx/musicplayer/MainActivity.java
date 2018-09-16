package com.ecarx.musicplayer;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    //public static final String mp3dir = Environment.getExternalStorageDirectory()+"/download/";
    public static final String mp3dir = "/mnt/sdcard/Download/";

    /**
     * SD卡全部音乐文件的路径集合
     */
    private List<String> mp3List;
    private ListView lv;
    private IMusicService iMusicService;
    private MyConn conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println(mp3dir);
        lv = (ListView) findViewById(R.id.lv);
        performCodeWithPermission("sd卡权限", new PermissionCallback() {
            @Override
            public void hasPermission() {
                initPlayList();
            }

            @Override
            public void noPermission() {

            }
        }, Manifest.permission.READ_EXTERNAL_STORAGE);

        Intent intent = new Intent(this, MusicPlayerService.class);
        startService(intent);
        conn = new MyConn();
        bindService(intent, conn,BIND_AUTO_CREATE);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(iMusicService!=null){
                    iMusicService.callPlay(mp3List,position);
                }else{
                    Toast.makeText(MainActivity.this, "还没有绑定服务吧", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private class MyConn implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iMusicService = (IMusicService) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    /**
     * 初始化播放列表
     */
    private void initPlayList() {
        File file = new File(mp3dir);
        File[] files = file.listFiles();
        mp3List = new ArrayList<>();
        for(File f : files){
            if(f.getName().endsWith(".mp3")){
                mp3List.add(f.getAbsolutePath());
            }
        }
        lv.setAdapter(new MusicListAdapter());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.activity_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.item_setting){
            //启动一个设置界面
            Intent intent = new Intent(this, SettingActivity.class);
            startActivity(intent);
        }else if(item.getItemId()==R.id.item_exit){
            //退出
        }else if(item.getItemId()==R.id.item_stop){
            //停止播放
            if(iMusicService!=null){
                iMusicService.callStop();
            }
            if(conn!=null){
                unbindService(conn);
                conn = null;
            }
            Intent intent = new Intent(this, MusicPlayerService.class);
            stopService(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        if(conn!=null){
            unbindService(conn);
            conn = null;
        }
        if(MusicPlayerService.playingStatus == MusicPlayerService.MUSIC_STOP){
            Intent intent = new Intent(this, MusicPlayerService.class);
            stopService(intent);
        }
        super.onDestroy();
    }

    private class MusicListAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mp3List.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(MainActivity.this, R.layout.item_music, null);
            TextView tv_name = (TextView) view.findViewById(R.id.tv_item_name);
            String path = mp3List.get(position);
            tv_name.setText(path.substring(path.lastIndexOf("/")+1));
            return view;
        }

        @Override
        public String getItem(int position) {
            return mp3List.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
    }
}
