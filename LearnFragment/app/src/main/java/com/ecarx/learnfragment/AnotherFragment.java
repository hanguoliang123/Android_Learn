package com.ecarx.learnfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Lenovo on 2018/8/23.
 */
public class AnotherFragment extends Fragment{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("onCreate.");
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        System.out.println("onCreateView.");
        View rootView = inflater.inflate(R.layout.fragment_another,container,false);

//        rootView.findViewById(R.id.btnShowAnotherFragment).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getFragmentManager().beginTransaction().replace(R.id.container,new AnotherFragment()).commit();
//            }
//        });

        rootView.findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
        System.out.println("onPause.");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("onDestroy.");
    }
}
