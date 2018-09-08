package com.ecarx.rpcalc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    private EditText et_name;
    private RadioGroup rg_sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * 人品计算器，通过输入姓名和性别，点击计算
         * 跳转到第二个页面显示人品值
         */
        //开发步骤
        //1.找到控件，并设置事件
        //2.在点击事件中获取输入的信息
        //3.将输入的信息设置到intent对象
        //4.利用startactivity方法，将intent对象的内容传递给activity2
        //5.activity2获取到intent对象传递的内容，并计算结果显示

        et_name = (EditText) findViewById(R.id.et_name);
        rg_sex = (RadioGroup) findViewById(R.id.rg_sex);

    }

    /**
     * 点击按钮进入第二个界面
     * @param view
     */
    public void click(View view){
        String name = et_name.getText().toString().trim();
        int id = rg_sex.getCheckedRadioButtonId();
        int sex;
        if(id==R.id.rb_male){
            sex = Sex.MALE;//男
        }else if(id==R.id.rb_female){
            //女
            sex = Sex.FEMALE;
        }else{
            //未知
            sex = Sex.UNKNOWN;
        }
        Intent intent = new Intent();
        intent.putExtra("name",name);
        intent.putExtra("sex",sex);
        intent.setAction("com.ecarx.rpcalc.openCalc");
        intent.addCategory("android.intent.category.DEFAULT");
        startActivity(intent);
    }
}
