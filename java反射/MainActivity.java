package com.example.hook_ndk_so;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.hook_ndk_so.databinding.ActivityMainBinding;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    private static final String TAG ="test" ;

    // Used to load the 'hook_ndk_so' library on application startup.
    static {
        System.loadLibrary("hook_ndk_so");
    }

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Example of a call to a native method
        TextView tv = binding.sampleText;
        tv.setText(stringFromJNI());
        testmethod();
    }
    public void testfield(){
        Test_reflect a_obj  = new Test_reflect("test1");

        try {
            Class testclazz1 = MainActivity.class.getClassLoader().loadClass("com.example.hook_ndk_so.Test_reflect");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Class cls2 = Class.forName("com.example.hook_ndk_so.Test_reflect");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Class test_reflect3 = Test_reflect.class;
        try {
            Field publicstaticfield = test_reflect3.getDeclaredField("publicstaticfield");
            String obj = null;
            try {//
                obj = (String) publicstaticfield.get(null);

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            Log.i(TAG, "publicstaticfield:--> "+obj);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        try {
           Field privateStaticfield = test_reflect3.getDeclaredField("privateStaticfield");
           privateStaticfield.setAccessible(true);
            try {
                privateStaticfield.set(null,"woshinidie");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            try {

                Log.i(TAG, "privateStaticfield:--> "+ privateStaticfield.get(null));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

//        Field[] fields = test_reflect3.getDeclaredFields();
//        for(Field i : fields){
//            Log.i(TAG, "getDeclaredFields field:-> "+i);
//        }
//        Field[] fieldss = test_reflect3.getFields();
//        for(Field i : fieldss){
//            Log.i(TAG, "getFields field:-> "+i);
//        }

    }
    public void testmethod(){
       //
        Class testclazz= Test_reflect.class;
        Method md1 = null;
        Method md2 = null;
        Constructor c1 = null;
        try {
            c1 = testclazz.getDeclaredConstructor(String.class,String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        Object clz1 = null;
        try {
            clz1 = c1.newInstance("woshicon","woyeshicon");
            Field f1 = null;
            try {
                f1 = testclazz.getDeclaredField("publicnotstaticfield");
                f1.set(clz1,"asd");
                Log.i(TAG, "testmethod:-->"+f1.get(clz1));

            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        Test_reflect t1 = new Test_reflect("ar1","ar2");
        try {
            md1 = testclazz.getDeclaredMethod("publicnostaticfunc");
            md2 = testclazz.getDeclaredMethod("privatestaticfunc");
            md2.setAccessible(true);
            try {
                md1.invoke(clz1);
                md2.invoke(null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            Log.i(TAG, "testmethod: "+md1);
            Log.i(TAG, "testmethod: "+md2);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

//        Method[] ms= testclazz.getDeclaredMethods();
//        for (Method i:ms){
//            Log.i(TAG, "getDeclaredMethods: i-->"+i);
//        }
//        Method[] ms1 = testclazz.getMethods();
//        for(Method i:ms1){
//            Log.i(TAG, "getMethods: i-->"+i);
//        }

    }

    /**
     * A native method that is implemented by the 'hook_ndk_so' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}