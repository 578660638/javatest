package com.kanxue.xposed01;

import android.util.Log;

import dalvik.system.DexClassLoader;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class Hookdexclassloader implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        Log.i("Xposed01", loadPackageParam.packageName);
        XposedBridge.log("Xposed01->app packagename" + loadPackageParam.packageName);
        // public DexClassLoader(String dexPath, String optimizedDirectory,
        //            String librarySearchPath, ClassLoader parent) {
        //        super(dexPath, null, librarySearchPath, parent);
        if (loadPackageParam.packageName.equals("com.kanxue.loaddex")){
            XposedHelpers.findAndHookConstructor(DexClassLoader.class, String.class, String.class, String.class, ClassLoader.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    Object[] argsall = param.args;
                    String dexpath = (String)argsall[0];
                    String optpaht = (String)argsall[1];
                    String library =(String)argsall[2];
                    XposedBridge.log("DexClassLoader--"+dexpath+"--"+optpaht+"--"+library);


                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    DexClassLoader dexClassLoader = (DexClassLoader) param.thisObject;
                    XposedBridge.log("dexClassLoader"+dexClassLoader);
                }
            });

        }

        }
}
