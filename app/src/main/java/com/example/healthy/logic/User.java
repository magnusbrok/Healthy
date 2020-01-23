package com.example.healthy.logic;

import com.example.healthy.ObserverPattern.Subject;
import com.example.healthy.logic.Items.Item;
import java.util.ArrayList;

public class User extends Subject {
    private String name;
    private String school;
    private String mail;
    private String year;
    private String age;
    private ArrayList<Item> rewardsWon = new ArrayList<>();


    public ArrayList<Item> getRewardsWon() {
        return rewardsWon;
    }

    public void setRewardsWon(ArrayList<Item> rewardsWon) {
        this.rewardsWon = rewardsWon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyChangeToObservers();
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
        notifyChangeToObservers();
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
        notifyChangeToObservers();
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
        notifyChangeToObservers();
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
        notifyChangeToObservers();
    }
}