package com.yyf.jiakeliucheng;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.FileUtils;
import android.util.ArrayMap;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class MainActivity extends AppCompatActivity {

    public  static void replaceclassloader(Context context,ClassLoader dexclassloader) throws ClassNotFoundException {
        ClassLoader pathclassloader = MainActivity.class.getClassLoader();
        Class Activitymethod = pathclassloader.loadClass("android.app.ActivityThread");
        try {
            Method currentactivitymethod = Activitymethod.getDeclaredMethod("currentActivityThread");
            Object activityThreadobj = currentactivitymethod.invoke(null);
            // final ArrayMap<String, WeakReference<LoadedApk>> mPackages = new ArrayMap<>();
            Field mPackages =Activitymethod.getDeclaredField("mPackages");
            mPackages.setAccessible(true);
            ArrayMap mPackagesobj = (ArrayMap) mPackages.get(activityThreadobj);
            String packagename = context.getPackageName();
            WeakReference wr = (WeakReference) mPackagesobj.get(packagename);
            Object loadedobj= wr.get();
            // android/app/LoadedApk.java
            Class loadapkclass = pathclassloader.loadClass("android.app.LoadedApk");
            Field mclassloaderfield = loadapkclass.getDeclaredField("mClassLoader");
            mclassloaderfield.setAccessible(true);
           Object mclassloader = mclassloaderfield.get(loadedobj);
            Log.e("yyf", "replaceclassloader:mclassloader "+mclassloader.toString());
            mclassloaderfield.set(loadedobj,dexclassloader);


        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | NoSuchFieldException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String dexpath = "/data/local/tmp/classes2.dex";
        ClassLoader pathclassloader = MainActivity.class.getClassLoader();
            DexClassLoader dexClassLoader = new DexClassLoader(dexpath,this.getApplication().getCacheDir().getAbsolutePath(),null,pathclassloader);
        try {
            replaceclassloader(this.getApplicationContext(),dexClassLoader);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Class Secondactivityclass = null;
        //android/app/ActivityThread.java

        try {
            Secondactivityclass = dexClassLoader.loadClass("com.roysue.suanfatest.MainActivity");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.startActivity(new Intent(MainActivity.this,Secondactivityclass));
    }
}