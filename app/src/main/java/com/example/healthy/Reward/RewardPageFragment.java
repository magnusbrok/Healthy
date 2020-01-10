package com.example.healthy.Reward;


import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthy.ObserverPattern.Observer;
import com.example.healthy.R;
import com.example.healthy.logic.AppLogic;
import com.example.healthy.logic.Reward;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

import static android.content.Context.MODE_PRIVATE;
import static com.example.healthy.MainActivity.SHARED_PREFS;

/**
 * A simple {@link Fragment} subclass.
 */
public class RewardPageFragment extends Fragment implements View.OnClickListener, Observer {
    private static final String POINTS = "rewardPoints";
    Button seGevinster, buyPrize;
    TextView rewardPoints;
    AppLogic appLogic = AppLogic.getInstance();
    PieChartView rewardPie;
    private SliceValue activitySlice, nutritionSlice, soicalSlice;
    List<SliceValue> rewardData = new ArrayList<>();
    ArrayList<String> rewardAmount = new ArrayList<>();
    TextView amountTV1, amountTV2, amountTV3, amountTV4;


    public RewardPageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_reward_page, container, false);

        appLogic.attachObserverToRewardPoints(this);
        //loadPoints();
        rewardPie = root.findViewById(R.id.rewardPagePie);
        rewardPoints = root.findViewById(R.id.rewardPoints);
        rewardPoints.setText("Belønningspoint: " + appLogic.getRewardPoints()+"");
        amountTV1 = root.findViewById(R.id.amountTV1);
        amountTV2 = root.findViewById(R.id.amountTV2);
        amountTV3 = root.findViewById(R.id.amountTV3);
        amountTV4 = root.findViewById(R.id.amountTV4);

        //TODO: add a loading animation (that stops when amount is recieved)
        new AsyncTask() {

            @Override
            protected Object doInBackground(Object[] objects) {
                try {
                    getAmountFromSheet("123");
                    return "Mængderne blev hentet korrekt";
                } catch (Exception e) {
                    e.printStackTrace();
                    return "Mængderne blev ikke hentet korrekt";
                }
            }

            @Override
            protected void onPostExecute(Object o) {
                amountTV1.setText(rewardAmount.get(0));
                amountTV2.setText(rewardAmount.get(1));
                amountTV3.setText(rewardAmount.get(2));
                amountTV4.setText(rewardAmount.get(3));
            }
        }.execute(100);

        seGevinster = root.findViewById(R.id.showRewardButton);
        seGevinster.setOnClickListener(this);

        buyPrize = root.findViewById(R.id.buyRewardButton);
        buyPrize.setOnClickListener(this);

        activitySlice = new SliceValue(1, ContextCompat.getColor(getContext(),R.color.colorLightBlue));
        nutritionSlice = new SliceValue(1,ContextCompat.getColor(getContext(), R.color.colorAccent));
        soicalSlice = new SliceValue(1, ContextCompat.getColor(getContext(), R.color.colorLightPurple));

        activitySlice.setValue(appLogic.getActivityPoints());
        //TODO: change this to appLogic.getNutritionPoints() when it's implemented.
        nutritionSlice.setValue(appLogic.getStepPoints());
        //TODO: change this to appLogic.getNutritionPoints() when it's implemented.
        soicalSlice.setValue(appLogic.getHighIntensityPoints());


        rewardData.add(activitySlice);
        rewardData.add(nutritionSlice);
        rewardData.add(soicalSlice);

        PieChartData rewardPieData = new PieChartData(rewardData);
        rewardPieData.setHasCenterCircle(true).setCenterCircleScale(0.8f);
        rewardPie.setPieChartData(rewardPieData);

        updateView();

        return root;
    }

    @Override
    public void onClick(View v) {
        if (v == seGevinster) {
            getFragmentManager().popBackStack();
            Fragment yourPrizes = new YourPrizePageFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction().addToBackStack(null);
            transaction.replace(R.id.fragmentView, yourPrizes);
            transaction.commit();
            }


        if (v == buyPrize) {

            if (appLogic.canBuyPrize()) {
                Reward prize = appLogic.buyPrize();
                Toast.makeText(getActivity(), "Du vandt: "+   prize.getName(), Toast.LENGTH_LONG).show();
            } else Toast.makeText(getActivity(), "Du har ikke nok point", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void updateView() {
        rewardPoints.setText("Bellønningspoint: " + appLogic.getRewardPoints());

        activitySlice.setValue(appLogic.getActivityPoints());
        //TODO: change this to appLogic.getNutritionPoints() when it's implemented.
        nutritionSlice.setValue(appLogic.getStepPoints());
        //TODO: change this to appLogic.getNutritionPoints() when it's implemented.
        soicalSlice.setValue(appLogic.getHighIntensityPoints());

        if (appLogic.getRewardPoints() == 0) {
            activitySlice.setValue(1);
            nutritionSlice.setValue(1);
            soicalSlice.setValue(1);
        }

        rewardData.clear();
        rewardData.add(activitySlice);
        rewardData.add(nutritionSlice);
        rewardData.add(soicalSlice);

        PieChartData rewardPieData = new PieChartData(rewardData);
        rewardPieData.setHasCenterCircle(true).setCenterCircleScale(0.8f);
        rewardPie.setPieChartData(rewardPieData);

        //savePoints();

    }
    //From Galgelogik made by Jacob Nordfalk
    public static String getUrl(String url) throws IOException {
        System.out.println("Henter data fra " + url);
        BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
        StringBuilder sb = new StringBuilder();
        String linje = br.readLine();
        while (linje != null) {
            sb.append(linje + "\n");
            linje = br.readLine();
        }
        return sb.toString();
    }

    public void getAmountFromSheet (String i) throws Exception {
        String data = getUrl("https://docs.google.com/spreadsheets/d/e/2PACX-1vRi5GKSK4AqGux2T6lpeLHB9YvY1QY_YY5Xqy6rDjOfBlsdrveUgZqljFOVxSab6WOvGZnwj6camSvz/pub?output=csv");
        int lineNr = 0;

        for (String line : data.split("\n")) {
            if (lineNr < 20) System.out.println("line: " + line);
            if (lineNr++ <1) continue;
            String[] spaces = line.split(",", -1);
            String index = spaces[0].trim();
            String amount = spaces[1].trim();
            if (amount.isEmpty()) continue;
            if (!i.contains(index)) continue;
            rewardAmount.add(amount);
            System.out.println(rewardAmount);

        }
    }
/**
    public void savePoints() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(POINTS, appLogic.getRewardPoints());
        editor.apply();
    }

    public int loadPoints() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        int loadedPoints = sharedPreferences.getInt(POINTS, 0);

        return loadedPoints;
    }
 **/
}
