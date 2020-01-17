package com.example.healthy.logic.Items;

import com.example.healthy.R;

public class Reward extends Item {

    private int price;
    private int amount;

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

    public void collectImageRes(){


        if (getName().equalsIgnoreCase("æble")) {
            setResID(R.drawable.reward_page_prize_fruit);
        }
        if (getName().equalsIgnoreCase("telefon")) {
            setResID(R.drawable.reward_page_prize_phone);
        }
        if (getName().equalsIgnoreCase("basketbold")) {
            setResID(R.drawable.reward_page_your_prize_basketball);
        }
        if (getName().equalsIgnoreCase("gavekort")) {
            setResID(R.drawable.reward_page_your_prize_giftcard);
        }
        if (getName().equalsIgnoreCase("peanut")) {
            setResID(R.drawable.reward_page_your_prize_peanut);
        }
        if (getName().equalsIgnoreCase("blyant")) {
            setResID(R.drawable.reward_page_your_prize_pencil);
        }
        if (getName().equalsIgnoreCase("fodbold")) {
            setResID(R.drawable.reward_page_prize_football);
        }
    }
}
