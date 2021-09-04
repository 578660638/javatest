package com.example.ceshi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Anmial dongwu = new Cat();
//        dongwu.Eat();
//        Cat cat1 = (Cat) dongwu;
//        cat1.Shangshu();
        Manager manager = new Manager("群主",100);
        Member one = new Member("成员1",0);
        Member two = new Member("成员2",0);
        Member three = new Member("成员3",0);
        manager.show();
        one.show();
        two.show();
        three.show();
        ArrayList<Integer> redlist = manager.send(20,3);
        one.receive(redlist);
        two.receive(redlist);
        three.receive(redlist);
        manager.show();
        one.show();
        two.show();
        three.show();
        shixianjiekou impl =new  shixianjiekou();
        impl.hit();
        impl.fangfa();
    }
}