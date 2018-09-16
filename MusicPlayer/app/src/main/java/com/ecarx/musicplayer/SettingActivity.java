package com.ecarx.musicplayer;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SettingActivity extends AppCompatActivity {

    private static final int CYCLE = 1;
    private static final int NEXT = 2;
    private static final int STOP = 3;
    private RadioGroup rg_mode;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        rg_mode = (RadioGroup) findViewById(R.id.rg_mode);
        sp = getSharedPreferences("config", MODE_PRIVATE);

        int mode = sp.getInt("mode", 0);
        RadioButton rb;
        switch (mode){
            case CYCLE:
                rb = (RadioButton) findViewById(R.id.rb_cycle);
                rb.setChecked(true);
                break;
            case NEXT:
                rb = (RadioButton) findViewById(R.id.rb_next);
                rb.setChecked(true);
                break;
            case STOP:
                rb = (RadioButton) findViewById(R.id.rb_stop);
                rb.setChecked(true);
                break;
            default:
                break;
        }


        rg_mode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                SharedPreferences.Editor editor = sp.edit();
                switch (checkedId){
                    case R.id.rb_cycle:
                        editor.putInt("mode",CYCLE);
                        break;
                    case R.id.rb_next:
                        editor.putInt("mode",NEXT);
                        break;
                    case R.id.rb_stop:
                        editor.putInt("mode",STOP);
                        break;

                    default:
                        break;
                }
                editor.commit();
            }
        });
    }
}
