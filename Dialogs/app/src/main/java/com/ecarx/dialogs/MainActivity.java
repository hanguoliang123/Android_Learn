package com.ecarx.dialogs;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
    }

    /**
     * 弹出确定取消对话框
     * @param view
     */
    public void click01(View view){
        //工厂设计模式，得到创建对话框的工厂
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("警告");
        builder.setMessage("若练此功,必须自宫,是否继？");
        builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "2222", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "2222", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();

    }

    /**
     * 弹出单选对话框
     * @param view
     */
    public void click02(View view){

        final String[] items = {"男","女","人妖"};

        //工厂设计模式，得到创建对话框的工厂
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择您的性别");
        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "您的性别为"+items[which], Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        builder.show();
    }

    /**
     * 弹出多选对话框
     * @param view
     */
    public void click03(View view){
        final String[] items = {"苹果","香蕉","橘子","菠萝","西瓜"};
        final boolean[] checkeds = {false,false,false,false,false};
        //工厂设计模式，得到创建对话框的工厂
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择您爱吃的水果：");
        builder.setMultiChoiceItems(items, checkeds, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                Toast.makeText(MainActivity.this, items[which]+isChecked, Toast.LENGTH_SHORT).show();
            }
        });
        builder.setPositiveButton("提交", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                StringBuffer sb = new StringBuffer();
                for(int i=0;i<checkeds.length;i++){
                    if(checkeds[i]){
                        sb.append(items[i]+" ");
                    }
                }
                Toast.makeText(MainActivity.this, "您喜欢吃的水果是："+sb.toString(), Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        builder.show();
    }

    /**
     * 弹出进度对话框
     * @param view
     */
    public void click04(View view){
        //工厂设计模式，得到创建对话框的工厂
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("请稍后");
        pd.setMessage("正在拼命加载中...");

        pd.show();

        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(10000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                pd.dismiss();
            }
        }.start();

    }

    /**
     * 弹出有具体进度的对话框
     * @param view
     */
    public void click05(View view){
        //工厂设计模式，得到创建对话框的工厂
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("请稍后");
        pd.setMessage("正在拼命加载中...");
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        // 设置进度条的最大值
        pd.setMax(10);

        pd.show();

        new Thread(){
            @Override
            public void run() {
                for(int i=0;i<10;i++){
                    try {
                        Thread.sleep(300);
                        pd.setProgress(i);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                pd.dismiss();
            }
        }.start();

    }
}
