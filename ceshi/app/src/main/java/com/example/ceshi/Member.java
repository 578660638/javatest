package com.example.ceshi;

import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class Member extends User{
    private static final String TAG = "test";
    public Member() {
    }

    public Member(String name, int money) {
        super(name, money);
    }
    public void receive(ArrayList<Integer>list){
        int index = new Random().nextInt(list.size());
        Log.i(TAG, "index"+index);
        Integer delta = list.remove(index);
        int money = super.getMoney();
        super.setMoney(money+delta);
    }
}
