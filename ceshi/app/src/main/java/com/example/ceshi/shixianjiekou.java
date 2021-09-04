package com.example.ceshi;

import android.util.Log;
//实现接口的类
public class shixianjiekou implements MyFirstInterface{
    private static final String TAG = "test";

    @Override
    public void hit() {
        Log.i(TAG, "这是我抽象方法的实现");
    }

    @Override
    public void fangfa() {
        Log.i(TAG, "fuck: 这是我第二个抽象方法实现对默认接口的重写");
    }
}
