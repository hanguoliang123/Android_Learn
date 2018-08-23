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
public class PlaceholderFragment extends Fragment {
    public PlaceholderFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        rootView.findViewById(R.id.btnShowAnotherFragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.container, new AnotherFragment())
                        .commit();
            }
        });
        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
        System.out.println("a onPause.");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.out.println("a onDestroyView.");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("a onDestroy.");
    }
}
