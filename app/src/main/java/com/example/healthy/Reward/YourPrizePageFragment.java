package com.example.healthy.Reward;


import android.os.Bundle;

import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.healthy.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class YourPrizePageFragment extends Fragment{
    String [] namesOfPrizes= {"En blyant", "En fodbold", "Valgfrit frugt","En basketball", "..."};
    public YourPrizePageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.activity_your_prize_page, container, false);
        ListAdapter listAdapter = new CustomAdapter(getActivity(), namesOfPrizes);
        ListView listView = root.findViewById(R.id.listviewYourPrize);
        listView.setAdapter(listAdapter);
        return root;
    }
}
