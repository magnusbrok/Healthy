package com.example.healthy.Reward;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.healthy.ObserverPattern.Observer;
import com.example.healthy.R;
import com.example.healthy.logic.AppLogic;
import com.example.healthy.logic.Items.Item;
import com.example.healthy.logic.Items.Reward;
import com.example.healthy.logic.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

/**
 * A simple {@link Fragment} subclass.
 */
public class RewardPageFragment extends Fragment implements View.OnClickListener, Observer {
    Button seGevinster, buyPrize;
    TextView rewardPoints;
    AppLogic appLogic = AppLogic.getInstance();
    PieChartView rewardPie;

    private SliceValue activitySlice, nutritionSlice, soicalSlice;
    List<SliceValue> rewardData = new ArrayList<>();
    ArrayList<String> rewardAmount = new ArrayList<>();
    ArrayList<String> rewardName = new ArrayList<>();
    ArrayList<String> rewardsWon = new ArrayList<>();
    LottieAnimationView loading;
    RecyclerView rewardView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View root = inflater.inflate(R.layout.fragment_reward_page, container, false);
        appLogic.attachObserverToRewardPoints(this);
        rewardPie = root.findViewById(R.id.rewardPagePie);
        rewardPoints = root.findViewById(R.id.rewardPoints);
        rewardPoints.setText("" + appLogic.getRewardPoints());
        loading = root.findViewById(R.id.loadingAnimation);
        loading.bringToFront();
        rewardView = root.findViewById(R.id.fragment_rewardpage_reward_list);


        new AsyncTask() {

            @Override
            protected Object doInBackground(Object[] objects) {
                try {
                    getAmountFromSheet("123456789101112131415161718192021222324252627282930");
                    return "Mængderne blev hentet korrekt";
                } catch (Exception e) {
                    e.printStackTrace();
                    return "Mængderne blev ikke hentet korrekt";
                }
            }

            @Override
            protected void onPostExecute(Object o) {
                try {
                    appLogic.setTotalPrizes(Integer.parseInt(rewardAmount.get(0)));
                    loading.cancelAnimation();
                    loading.setVisibility(View.GONE);


                    CustomHorizontalRewardAdapter adapter = new CustomHorizontalRewardAdapter(getActivity(),appLogic.getRewards());

                    RecyclerView recyclerView = root.findViewById(R.id.fragment_rewardpage_reward_list);
                    recyclerView.setAdapter(adapter);



                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Opret forbindelse til internettet", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }
        }.execute(100);



        seGevinster = root.findViewById(R.id.showRewardButton);
        seGevinster.setOnClickListener(this);

        buyPrize = root.findViewById(R.id.buyRewardButton);
        buyPrize.setOnClickListener(this);

        activitySlice = new SliceValue(1, ContextCompat.getColor(getContext(),R.color.colorStep));
        nutritionSlice = new SliceValue(1,ContextCompat.getColor(getContext(), R.color.nutritionPrimary));
        soicalSlice = new SliceValue(1, ContextCompat.getColor(getContext(), R.color.socialPrimary));

        activitySlice.setValue(appLogic.getActivityPoints());
        nutritionSlice.setValue(appLogic.getNutritionPoints());
        //TODO: change this to appLogic.getSocialPoints() when it's implemented.
        soicalSlice.setValue(appLogic.getHighIntensityPoints());


        rewardData.add(activitySlice);
        rewardData.add(nutritionSlice);
        rewardData.add(soicalSlice);

        PieChartData rewardPieData = new PieChartData(rewardData);
        rewardPieData.setHasCenterCircle(true).setCenterCircleScale(0.1f);
        rewardPie.setPieChartData(rewardPieData);

        updateView();

        return root;
    }

    @Override
    public void onClick(View v) {
        if (v == seGevinster) {
            final YourPrizePageFragment yourPrizes = new YourPrizePageFragment();
            getFragmentManager().beginTransaction().replace(R.id.bottom_menu_fragment_View, yourPrizes).addToBackStack(null).commit();
            }


        if (v == buyPrize) {

            if (appLogic.canBuyPrize()) {
                Reward prize = appLogic.buyPrize();

                User user = appLogic.getUser();

                ArrayList<Item> addedRewards = user.getRewardsWon();
                addedRewards.add(0, prize);
                user.setRewardsWon(addedRewards);

                Toast.makeText(getActivity(), "Du vandt: "+   prize.getName(), Toast.LENGTH_LONG).show();

            } else Toast.makeText(getActivity(), "Du har ikke nok point", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void updateView() {
        rewardPoints.setText(""+ appLogic.getRewardPoints());

        activitySlice.setValue(appLogic.getActivityPoints());
        nutritionSlice.setValue(appLogic.getNutritionPoints());
        //TODO: change this to appLogic.getSocialPoints() when it's implemented.
        soicalSlice.setValue(appLogic.getHighIntensityPoints()/2);

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

    }
    //From Galgelogik made by Jacob Nordfalk (It has been altered to fit our project)
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
    //From Galgelogik made by Jacob Nordfalk (It has been altered to fit  our project)
    public void getAmountFromSheet (String i) throws Exception {
        String data = getUrl("https://docs.google.com/spreadsheets/d/e/2PACX-1vRi5GKSK4AqGux2T6lpeLHB9YvY1QY_YY5Xqy6rDjOfBlsdrveUgZqljFOVxSab6WOvGZnwj6camSvz/pub?output=csv");
        int lineNr = 0;

        for (String line : data.split("\n")) {
            if (lineNr < 30) System.out.println("line: " + line);
            if (lineNr++ <1) continue;
            String[] spaces = line.split(",", -1);
            String index = spaces[0].trim();
            String amount = spaces[1].trim();
            String name = spaces[2].trim();
            if (amount.isEmpty()) continue;
            if (!i.contains(index)) continue;
            rewardAmount.add(amount);
            rewardName.add(name);
           // System.out.println(rewardAmount);
            //System.out.println(rewardName);
        }

        System.out.println("NAVNE"+rewardName);
        System.out.println("MÆNGDER"+rewardAmount);


        ArrayList<Item> rewards = new ArrayList<>();
        for (int j = 1; j < rewardName.size(); j++) {
            Reward reward = new Reward(rewardName.get(j));
            reward.setAmount(Integer.parseInt((rewardAmount.get(j))));
            rewards.add(reward);

        }
        appLogic.setRewards(rewards);
        System.out.println("REWARDS"+appLogic.getRewards().toString());
    }
}
