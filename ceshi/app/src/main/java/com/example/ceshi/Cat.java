package com.example.ceshi;

import android.util.Log;

public class Cat extends Anmial {
    @Override
    public void Eat() {
        Log.i("test", "Eat: cat");
    }
    public void Shangshu(){
        Log.i("test", "Shangshu: cat zhuanshu");
    }
}
