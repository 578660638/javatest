package com.kanxue.xposed01;

import android.telephony.TelephonyManager;
import android.util.Log;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class Xposedjavamethod implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
//        Log.i("Xposed01", loadPackageParam.packageName);
//        XposedBridge.log("Xposed01->app packagename" + loadPackageParam.packageName);
        if (loadPackageParam.packageName.equals("com.example.myapplication1")) {

            ClassLoader classLoader1 = loadPackageParam.classLoader;
//            Class stuclass = classLoader1.loadClass("com.example.testxp.student");
            Class stuclasspersosn = classLoader1.loadClass("com.example.myapplication1.student$persosn");

            //公有的函数参数，返回值的修改
//            XposedHelpers.findAndHookMethod(stuclass, "privatestatcifunc", String.class, int.class, new XC_MethodHook() {
//                @Override
//                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                    super.beforeHookedMethod(param);
//                    Object[] args_all =  param.args;
//                    String a1 = (String) args_all[0];
//                    int a2 = (int) args_all[1];
//                    args_all[0] ="我是修改之后的";
//                    args_all[1]=66666;
//                    XposedBridge.log("---a1---a2"+a1+a2);
//
//                }
//
//                @Override
//                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                    super.afterHookedMethod(param);
//                    String result = (String) param.getResult();
//                    XposedBridge.log("privatestatcifunc - result-------"+result);
//
//                }
//            });


           //内部类hook,同时也可以进行匿名内部类和jni函数的hook都可以实现，java函数和jni函数最终对应的都是artmethod
            XposedHelpers.findAndHookMethod(stuclasspersosn, "getpersonname", String.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    XposedBridge.log("beforeHookedMethod getpersonname->"+param.args[0]);
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    XposedBridge.log("afterHookedMethod getpersonname->"+param.getResult());
                }
            });
        }

    }
}
