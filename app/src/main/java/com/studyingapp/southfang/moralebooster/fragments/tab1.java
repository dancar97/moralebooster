package com.studyingapp.southfang.moralebooster.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.studyingapp.southfang.moralebooster.R;
import com.studyingapp.southfang.moralebooster.writing_panel11;


public class tab1 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);


        Button clickButton = (Button) rootView.findViewById(R.id.button);
        clickButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent myIntent = new Intent(v.getContext(), writing_panel11.class);
                startActivityForResult(myIntent, 0);
            }
        });

        return rootView;



    }

}
