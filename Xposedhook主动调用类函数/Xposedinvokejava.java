package com.kanxue.xposed01;

import android.telephony.TelephonyManager;
import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class Xposedinvokejava implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        Log.i("Xposed01", loadPackageParam.packageName);
        XposedBridge.log("Xposed01->app packagename" + loadPackageParam.packageName);
        if (loadPackageParam.packageName.equals("com.example.testxp")) {

            ClassLoader pathclassloader1 =loadPackageParam.classLoader;
            Class stu = pathclassloader1.loadClass("com.example.testxp.student");








//            //共有静态函数主动调用--java反射
//            Method publicstatcifunc = stu.getDeclaredMethod("publicstatcifunc",String.class,int.class);
//            publicstatcifunc.invoke(null,"我是共有静态函数主动调用的",6000);
//            //私有静态函数调用,记得增加访问权限--java反射
//            Method privatestatcifunc = stu.getDeclaredMethod("privatestatcifunc",String.class,int.class);
//            privatestatcifunc.setAccessible(true);
//            privatestatcifunc.invoke(null,"我是私有静态函数主动调用的",6001);
//            //非静态共有函数主动调用--java反射
//            Method publicfunc = stu.getDeclaredMethod("publicfunc",String.class,int.class);
//            Constructor stu2 =stu.getDeclaredConstructor(String.class,String.class);
//            Object stu2impl = stu2.newInstance("我是自己构造出来的","6002");
//            publicfunc.invoke(stu2impl,"我是自己构造出来的并且主动调用的",6003);
            //非静态私有函数的主动调用--java反射
//            Method privatefunc = stu.getDeclaredMethod("privatefunc",String.class,int.class);
//            privatefunc.setAccessible(true);
//            Constructor stu2 =stu.getDeclaredConstructor(String.class,String.class);
//            Object stu2impl = stu2.newInstance("我是自己构造出来的","6002");
//            privatefunc.invoke(stu2impl,"我是私有的非静态的函数",2333);
            //student stu4 = new student("xiaobai","003",20,"zhanglaoshi","tail");
            // public static Object callMethod(Object obj, String methodName, Class<?>[] parameterTypes, Object... args)
            //  public static Object callMethod(Object obj, String methodName, Object... args)

//            java.lang.Object[] obs1 = {String.class,int.class};
//            XposedHelpers.callMethod(a1,obs1,"woshicallmethod",6666);
            //通过xposed api进行的主动调用非静态函数
            Object a1 = XposedHelpers.newInstance(stu,"我是自己构造出来的","666");
            String ASD = (String) XposedHelpers.callMethod(a1,"publicfunc","woshicallmethod",6666);
            XposedBridge.log(ASD);


        }
        ;
    }
}
