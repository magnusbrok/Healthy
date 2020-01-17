package com.example.healthy.Reward;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.healthy.R;
import com.example.healthy.logic.AppLogic;

/**
 * A simple {@link Fragment} subclass.
 */
public class YourPrizePageFragment extends Fragment{
    AppLogic appLogic =  AppLogic.getInstance();

    public YourPrizePageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.activity_your_prize_page, container, false);

        if (appLogic.getUser().getRewardsWon() != null) {

            ListAdapter listAdapter = new CustomAdapter(getActivity(), appLogic.getUser().getRewardsWon());
            ListView listView = root.findViewById(R.id.listviewYourPrize);
            listView.setAdapter(listAdapter);
        }
    return root;
    }
}
