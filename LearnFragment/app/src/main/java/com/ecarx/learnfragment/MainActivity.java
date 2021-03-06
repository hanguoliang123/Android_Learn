package com.ecarx.learnfragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(null==savedInstanceState){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container,new PlaceholderFragment())
                    .commit();
        }
    }

}
