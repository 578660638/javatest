package com.example.ceshi;

import android.util.Log;
//定义一个父类
public class User {
    private static final String TAG = "test";
    private  String name;
    private int money;
    public User(){}
    public User(String name,int money){
        this.name = name;
        this.money=money;
    }
    public  void show (){
        Log.i(TAG, "我是: "+name+"我有"+money);
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
