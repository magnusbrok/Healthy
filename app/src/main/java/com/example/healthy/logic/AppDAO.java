package com.example.healthy.logic;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.healthy.logic.Items.Item;
import com.example.healthy.logic.Items.Reward;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AppDAO {

    private static AppLogic appLogic = AppLogic.getInstance();
    private static AppDAO instance = new AppDAO();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Gson gson = new Gson();
    ArrayList<String> rewardAmount = new ArrayList<>();
    ArrayList<String> rewardName = new ArrayList<>();
    ArrayList<String> rewardsWon = new ArrayList<>();


    public static AppDAO getInstance() {
        return instance;
    }


    public void getFoodLog () {
        DocumentReference userLog = db.collection("Brugere med point").document("1").collection("FoodLog").document("2");
        userLog.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>()
        {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful())
                {
                    DocumentSnapshot doc = task.getResult();
                    StringBuilder foods = new StringBuilder();
                    foods.append(doc.get("Food added"));
                    String gsonString = foods.toString();

                    Type type = new TypeToken<ArrayList<Item>>(){}.getType();
                    if (!gsonString.equals("")) {

                        ArrayList<Item> loadedFood = gson.fromJson(gsonString, type);
                        appLogic.setFoodItemList(loadedFood);
                        System.out.println(loadedFood);
                        appLogic.computePoints();
                    }
                }
            }
        });
    }

    public void addFoodToLog() {
        Map<String, Object> updateFoodLog = new HashMap<>();
        // TODO use appLogic.getFoodItemList()
        ArrayList<Item> newFoodList = appLogic.getFoodItemList();
        String gsonString = gson.toJson(newFoodList);
        if (!gsonString.equals("")) {

            updateFoodLog.put("Food added", gsonString);
        }

        db.collection("Brugere med point").document("1").collection("FoodLog").document("2") // This is the ID of the document in the db. (Could be nothing - then it generates a random and unique ID)
                .set(updateFoodLog)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("FEJL - redigeringerne blev ikke gemt", e.getMessage()); }
                });
    }

    public void getUser () {
        DocumentReference user = db.collection("Brugere med point").document("1").collection("Rediger profil").document("1");
        user.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot doc = task.getResult();
                    StringBuilder navn = new StringBuilder();
                    StringBuilder alder = new StringBuilder();
                    StringBuilder mail = new StringBuilder();
                    StringBuilder årgang = new StringBuilder();
                    StringBuilder skole = new StringBuilder();
                    navn.append(doc.get("Name"));
                    alder.append(doc.get("Age"));
                    mail.append(doc.get("Email"));
                    årgang.append(doc.get("Year"));
                    skole.append(doc.get("School"));

                    User user = appLogic.getUser();
                    user.setName(navn.toString());
                    user.setAge(alder.toString());
                    user.setMail(mail.toString());
                    user.setYear(årgang.toString());
                    user.setSchool(skole.toString());
                }
            }

        });
    }

    public void updateUser() {
        User user = appLogic.getUser();
        // Updating user
        Map<String, Object> updateUser = new HashMap<>();
        updateUser.put("Name", user.getName());
        updateUser.put("Age", user.getAge());
        updateUser.put("Email", user.getMail());
        updateUser.put("Year", user.getYear());
        updateUser.put("School", user.getSchool());

        db.collection("Brugere med point").document("1").collection("Rediger profil").document("1")
                .set(updateUser)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("FEJL - redigeringerne blev ikke gemt", e.getMessage());
                        e.printStackTrace();
                    }
                });
    }

    public void updateRewardsWon() {
        Map<String, Object> updateRewardsWon = new HashMap<>();
        // TODO use appLogic.getFoodItemList()
        ArrayList<Item> rewardsWon = appLogic.getUser().getRewardsWon();
        String gsonString = gson.toJson(rewardsWon);
        if (!gsonString.equals("")) {

            updateRewardsWon.put("Rewards Won", gsonString);
        }

        db.collection("Brugere med point").document("1").collection("Rewards won").document("1") // This is the ID of the document in the db. (Could be nothing - then it generates a random and unique ID)
                .set(updateRewardsWon)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("FEJL - redigeringerne blev ikke gemt", e.getMessage()); }
                });
    }

    public void loadRewardsWon () {
        DocumentReference userRewards = db.collection("Brugere med point").document("1").collection("Rewards won").document("1");
        userRewards.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>()
        {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful())
                {
                    DocumentSnapshot doc = task.getResult();
                    StringBuilder rewardsWon = new StringBuilder();
                    rewardsWon.append(doc.get("Rewards Won"));
                    String gsonString = rewardsWon.toString();

                    Type type = new TypeToken<ArrayList<Item>>(){}.getType();
                    if (!gsonString.equals("")) {

                        ArrayList<Item> pricesWon = gson.fromJson(gsonString, type);
                        appLogic.getUser().setRewardsWon(pricesWon);
                        System.out.println(pricesWon);
                    }
                }
            }
        });
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

        appLogic.setTotalPrizes(Integer.parseInt(rewardAmount.get(0)));


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