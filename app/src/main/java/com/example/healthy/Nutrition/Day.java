package com.example.healthy.Nutrition;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.healthy.R;
public class Day extends Fragment {

    ImageView dag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_day, container, false);
        // Inflate the layout for this fragment
        dag = root.findViewById(R.id.imageView3);
        //
        return root;
    }

}
