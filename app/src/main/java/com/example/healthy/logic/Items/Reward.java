package com.example.healthy.logic.Items;

import com.example.healthy.R;

public class Reward extends Item {

    private int price;
    private int amount;

    public Reward(String name) {
        super(name);
        collectImageRes(name);

    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void collectImageRes(String name){
        if (name.equalsIgnoreCase("æble")) {
            setResID(R.drawable.reward_page_prize_fruit);
        }
        if (name.equalsIgnoreCase("telefon")) {
            setResID(R.drawable.reward_page_prize_phone);
        }
        if (name.equalsIgnoreCase("basketbold")) {
            setResID(R.drawable.reward_page_your_prize_basketball);
        }
        if (name.equalsIgnoreCase("gavekort")) {
            setResID(R.drawable.reward_page_your_prize_giftcard);
        }
        if (name.equalsIgnoreCase("peanut")) {
            setResID(R.drawable.reward_page_your_prize_peanut);
        }
        if (name.equalsIgnoreCase("blyant")) {
            setResID(R.drawable.reward_page_your_prize_pencil);
        }
        if (name.equalsIgnoreCase("fodbold")) {
            setResID(R.drawable.reward_page_prize_football);
        }
    }
}