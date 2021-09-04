package com.example.ceshi;

import android.util.Log;

public interface MyFirstInterface {
     String TAG = "test";
     //可以省略public abstart
    void hit();
    //假如我有新需求需要额外定义一个方法就使用默认方法，不对已经写的产生影响
    default void fangfa(){
        Log.i(TAG, "我是新添加的默认方法不会对已经实现接口的方法进行要求");
    }
   

}
