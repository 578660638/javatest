package com.kanxue.xposed01;

import android.util.Log;

import java.lang.reflect.Field;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookConstructors implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        Log.i("Xposed01", loadPackageParam.packageName);


        if (loadPackageParam.packageName.equals("com.example.testxp")) {
            //XposedBridge.log("test " + loadPackageParam.packageName);
            ClassLoader classLoader = loadPackageParam.classLoader;
            final Class student = classLoader.loadClass("com.example.testxp.student");

            XposedBridge.log("Xposed01->app packagename" + loadPackageParam.packageName);
            XposedHelpers.findAndHookConstructor(student, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    XposedBridge.log("com.example.testxp.student is called");
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                }
            });
            XposedHelpers.findAndHookConstructor(student, String.class,new XC_MethodHook() {
                //  public Member method;
                //        public Object thisObject;
                //        public Object[] args;
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    java.lang.Object[] argsobj = param.args;
                   String name =  (String) argsobj[0];

                    XposedBridge.log("com.example.testxp.student 1 args is called--name"+name);
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                }
            });
            XposedHelpers.findAndHookConstructor(student, String.class,String.class,new XC_MethodHook() {
                //  public Member method;
                //        public Object thisObject;
                //        public Object[] args;
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    java.lang.Object[] argsobj = param.args;
                    String name =  (String) argsobj[0];
                    String id =  (String) argsobj[1];

                    XposedBridge.log("com.example.testxp.student 1 args is called--name--id"+name+id);
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                }
            });

            XposedHelpers.findAndHookConstructor(student, String.class,String.class,int.class,String.class,String.class,new XC_MethodHook() {
                //  public Member method;
                //        public Object thisObject;
                //        public Object[] args;
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    java.lang.Object[] argsobj = param.args;
                    String name =  (String) argsobj[0];
                    String id =  (String) argsobj[1];
                    int age =  (int) argsobj[2];
                    String teacher =  (String) argsobj[3];
                    String nickname =  (String) argsobj[4];


                    XposedBridge.log("com.example.testxp.student 1 args is called--name--id--age"+name+id+age+"---"+teacher+"---"+nickname);
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    //改变实例的属性而不是类的属性，注意权限
                    Object classstudent = param.thisObject;
                    Field teacherFiled =student.getDeclaredField("teachername");
                    teacherFiled.setAccessible(true);
                    teacherFiled.set(classstudent,"woshinimama");
                    XposedBridge.log("teacherFiled"+teacherFiled.get(classstudent));


                }
            });
            //classlaoder的获取
//            ClassLoader pathloader = loadPackageParam.classLoader;
//            Class stuclass = pathloader.loadClass("com.example.testxp.student");
//            XposedBridge.log("stuclass->"+stuclass);
            //通过反射获取静态属性名称   私有共有都可以---对类的属性的修改
//            Field teacherField = stuclass.getDeclaredField("teachername");
//            XposedBridge.log("teacherField->"+ (String) teacherField.get(null));
//            //设置静态属性名称
//            teacherField.set(null,"teacher666");
//            //获取静态属性名称
//            String teachername = (String) teacherField.get(null);
//            XposedBridge.log("teacherField->"+teachername);
//通过xposed自带的api进行实现私有或者共有属性的获取---对类的属性的修改
//            XposedHelpers.setStaticObjectField(stuclass,"teachername","woshinidie");
//            String teacher1 = (String)XposedHelpers.getStaticObjectField(stuclass,"teachername");
//            XposedBridge.log("teacher1----"+teacher1);



     /*     public static de.robv.android.xposed.XC_MethodHook.Unhook findAndHookConstructor(java.lang.Class<?> clazz, java.lang.Object... parameterTypesAndCallback)
            public static de.robv.android.xposed.XC_MethodHook.Unhook findAndHookConstructor(java.lang.String className, java.lang.ClassLoader classLoader, java.lang.Object... parameterTypesAndCallback)
*/
//            ClassLoader classLoader = loadPackageParam.classLoader;
//            Class StudentClass = classLoader.loadClass("com.kanxue.xposedhook01.Student");
//            XposedHelpers.findAndHookConstructor(StudentClass, new XC_MethodHook() {
//                @Override
//                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                    super.beforeHookedMethod(param);
//                    XposedBridge.log("com.kanxue.xposedhook01.Student() is called!!beforeHookedMethod");
//                }
//
//                @Override
//                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                    super.afterHookedMethod(param);
//                    XposedBridge.log("com.kanxue.xposedhook01.Student() is called!!afterHookedMethod");
//                }
//            });
//
//            //    public Student(String name2) {
//            //        this.name = name2;
//            //        this.id = "default";
//            //    }
//            //       public java.lang.Object thisObject;
//            //        public java.lang.Object[] args;
//            //        private java.lang.Object result;
//            XposedHelpers.findAndHookConstructor(StudentClass, String.class, new XC_MethodHook() {
//                @Override
//                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                    super.beforeHookedMethod(param);
//                    java.lang.Object[] argsobjarray = param.args;
//                    String name = (String) argsobjarray[0];
//                    XposedBridge.log("com.kanxue.xposedhook01.Student(String) is called!!beforeHookedMethod--" + name);
//                }
//
//                @Override
//                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                    super.afterHookedMethod(param);
//                    XposedBridge.log("com.kanxue.xposedhook01.Student(String) is called!!afterHookedMethod");
//                }
//            });
//            //    public Student(String name2, String id2) {
//            //        this.name = name2;
//            //        this.id = id2;
//            //    }
//
//            XposedHelpers.findAndHookConstructor("com.kanxue.xposedhook01.Student", loadPackageParam.classLoader, String.class, String.class, new XC_MethodHook() {
//                @Override
//                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                    super.beforeHookedMethod(param);
//                    java.lang.Object[] argsobjarray = param.args;
//                    String name = (String) argsobjarray[0];
//                    String id = (String) argsobjarray[1];
//                    XposedBridge.log("com.kanxue.xposedhook01.Student(String,String) is called!!beforeHookedMethod--" + name + "---" + id);
//
//                }
//
//                @Override
//                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                    super.afterHookedMethod(param);
//                    XposedBridge.log("com.kanxue.xposedhook01.Student(String,String) is called!!afterHookedMethod");
//                }
//            });
//
//
//            ClassLoader pathClassLoader= loadPackageParam.classLoader;
//
//            final Class stuClass=pathClassLoader.loadClass("com.kanxue.xposedhook01.Student");
//            XposedBridge.log("StudentClass->"+stuClass);
//
///*            Field teacherField=stuClass.getDeclaredField("teacher");
//
//            //teacherField.setAccessible(true);
//
//            teacherField.set(null,"teacher666");
//
//
//            String teachername1= (String) teacherField.get(null);
//
//            XposedBridge.log("teacherField->"+teachername1);*/
//
//            //(java.lang.Class<?> clazz, java.lang.String fieldName, java.lang.Object value)
//
//
//            XposedHelpers.setStaticObjectField(stuClass,"teacher","teacher888");
//
//            String teachername2= (String) XposedHelpers.getStaticObjectField(stuClass,"teacher");
//
//
//            XposedBridge.log("XposedHelpers.getStaticObjectField->"+teachername2);
//
//
//            /*XposedHelpers.findAndHookConstructor(StudentClass, String.class, String.class, new XC_MethodHook() {
//                @Override
//                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                    super.beforeHookedMethod(param);
//                    java.lang.Object[] argsobjarray = param.args;
//                    String name = (String) argsobjarray[0];
//                    String id = (String) argsobjarray[1];
//                    XposedBridge.log("com.kanxue.xposedhook01.Student(String,String) is called!!beforeHookedMethod--" + name + "---" + id);
//
//                }
//
//                @Override
//                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                    super.afterHookedMethod(param);
//                    XposedBridge.log("com.kanxue.xposedhook01.Student(String,String) is called!!afterHookedMethod");
//                }
//            });
//*/
//            //public Student(String name2, String id2, int age2)
//            XposedHelpers.findAndHookConstructor(StudentClass, String.class, String.class, int.class,String.class,String.class, new XC_MethodHook() {
//                @Override
//                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                    super.beforeHookedMethod(param);
//                    java.lang.Object[] argsobjarray = param.args;
//                    String name = (String) argsobjarray[0];
//                    String id = (String) argsobjarray[1];
//                    int age = (int) (argsobjarray[2]);
//                    argsobjarray[1] = "2050";
//                    argsobjarray[2] = 100;
//
//                    String teacher= (String) argsobjarray[3];
//                    String nickname= (String) argsobjarray[4];
//
//
//                    XposedBridge.log("com.kanxue.xposedhook01.Student(String,String) is called!!beforeHookedMethod--" + name + "---" + id + "--" + age+"---"+teacher+"---"+nickname);
//                }
//
//                @Override
//                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                    super.afterHookedMethod(param);
//
//                    Object thisobj = param.thisObject;
//
//
//             /*       Field nicknameField=stuClass.getDeclaredField("nickname");
//                    XposedBridge.log(stuClass+"--nicknameField->"+nicknameField);
//                    nicknameField.setAccessible(true);
//                    nicknameField.set(thisobj,"bear");*/
//
//                    XposedHelpers.setObjectField(thisobj,"nickname","chick");
//
//
//
//
//                    Object returnobj = param.getResult();
//                    XposedBridge.log(thisobj + "---" + returnobj);
//                    XposedBridge.log("com.kanxue.xposedhook01.Student(String,String,int) is called!!afterHookedMethod");
//                }
//            });
        }

    }
}
