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

    private void collectImageRes(String name){
        if (name.equalsIgnoreCase("æble")) {
            setResID(R.drawable.reward_page_prize_fruit);
        }
        if (name.equalsIgnoreCase("telefon")) {
            setResID(R.drawable.reward_page_prize_phone);
        }
        if (name.equalsIgnoreCase("basketbold")) {
            setResID(R.drawable.basketball);
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
            setResID(R.drawable.fodbold);
        }

        /*

        // Opdateret præmie-liste
        if (name.equalsIgnoreCase("drikkedunk")) {
            setResID(R.drawable.drikkedunk);
        }
        if (name.equalsIgnoreCase("træningselastik")) {
            setResID(R.drawable.elastik);
        }
        if (name.equalsIgnoreCase("elcykel")) {
            setResID(R.drawable.elcykel);
        }
        if (name.equalsIgnoreCase("gavekort")) {
            setResID(R.drawable.gavekort_bio);
        }
        if (name.equalsIgnoreCase("hoodie")) {
            setResID(R.drawable.hoodie);
        }
        if (name.equalsIgnoreCase("iphone holder")) {
            setResID(R.drawable.iphone_holder);
        }
        if (name.equalsIgnoreCase("juice")) {
            setResID(R.drawable.juice);
        }
        if (name.equalsIgnoreCase("10kr til kantine")) {
            setResID(R.drawable.kantine_10_kr);
        }
        if (name.equalsIgnoreCase("løbesko")) {
            setResID(R.drawable.loebesko);
        }
        if (name.equalsIgnoreCase("protein shake")) {
            setResID(R.drawable.protein_shake);
        }
        if (name.equalsIgnoreCase("rawbar")) {
            setResID(R.drawable.rawbar);
        }
        if (name.equalsIgnoreCase("smoothie")) {
            setResID(R.drawable.smoothie);
        }

         */
    }
}