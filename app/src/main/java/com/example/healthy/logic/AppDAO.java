package com.example.healthy.logic;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.healthy.logic.Items.Reward;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AppDAO {

    private static AppLogic appLogic = AppLogic.getInstance();
    private static AppDAO instance = new AppDAO();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

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
                    appLogic.setFoodList((ArrayList<String>) doc.get("Food added"));
                    appLogic.computePoints();
                }
            }
        });
    }

    public void addFoodToLog() {
        Map<String, Object> updateUser = new HashMap<>();
        updateUser.put("Food added", appLogic.getFoodList());

        db.collection("Brugere med point").document("1").collection("FoodLog").document("2") // This is the ID of the document in the db. (Could be nothing - then it generates a random and unique ID)
                .set(updateUser)
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

    /*
    public void saveRewards() {
        Map<String, Object> saveRewards = new HashMap<>();
        saveRewards.put("JsonString", appLogic.hej);

        db.collection("Brugere med point").document("1").collection("Rewards").document("1")
                .set(saveRewards)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("FEJL - rewardlisten blev ikke gemt", e.getMessage());
                        e.printStackTrace();
                    }
                });
    }
    
     */

    /*
    public void updateRewards() {
        Reward rewards = appLogic.getRewards();
        // Updating rewardList
        Map<String, Object> updateRewards = new HashMap<>();
        updateRewards.put("JsonString", rewards.getList());


        db.collection("Brugere med point").document("1").collection("Rewards").document("1")
                .set(updateRewards)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("FEJL - rewardlisten blev ikke hentet", e.getMessage());
                        e.printStackTrace();
                    }
                });
    }

     */
}