package com.example.hook_ndk_so;

import android.util.Log;

public class Test_reflect {
    public String flag = null;
    public Test_reflect(String arg){
        flag ="Test_reflect(string)";

    }
    public Test_reflect(String arg1,String arg2){
        flag ="Test_reflect(String arg1,String arg2)"+arg1+arg2;

    }
    private static final String TAG ="test" ;
    public static String publicstaticfield = "i am public static String publicstaticfield";
    public String publicnotstaticfield = "i am publicnotstaticfield";
    private static String privateStaticfield = "i am a privatestatic String ";
    private String privatecnotstaticfield = "i am a private no staticfield";
    public static void publicstaticfunc(){
        Log.i(TAG, "public static publicstaticfunc: ");
    }
    public  void publicnostaticfunc(){
        Log.i(TAG, "public nostatic publicnostaticfunc: "+flag);
    }
    private static void privatestaticfunc(){
        Log.i(TAG, "private static publicstaticfunc: ");
    }
    private  void privatenostaticfunc(){
        Log.i(TAG, "private nostatic publicnostaticfunc: "+flag);
    }

}
