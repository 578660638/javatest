package com.example.ceshi;

import android.util.Log;

import java.util.ArrayList;
//子类继承父类的属性和方法，子类就省事啦
public class Manager  extends  User{
    //
    private static final String TAG = "test";
    //两个构造方法
    public Manager() {
    }

    public Manager(String name, int money) {
        super(name, money);
        }
        //范
 public ArrayList<Integer> send(int totalmoney,int count) {
     ArrayList<Integer> redlist = new ArrayList<>();
     int leftmoney = super.getMoney();
     if (totalmoney > leftmoney) {
         Log.i(TAG, "余额不足");
         return redlist;
     }
     //调用父类的super方法
     super.setMoney(leftmoney - totalmoney);
     int avg = totalmoney / count;
     int mod = totalmoney % count;
     for (int i = 0; i < count - 1; i++) {
         redlist.add(avg);
     }
     int last = avg+mod;
     redlist.add(last);
     return redlist;
 }
}


