#include <jni.h>
#include <string>
#include "android/log.h"
extern "C" JNIEXPORT jstring JNICALL
Java_com_example_nostatcfield_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject obj,jobject testobj) {
    // public String publicnotstaticfield = "i am publicnotstaticfield";
    //    private String privatecnotstaticfield = "i am a private no staticfield";
    jclass clazz = env->FindClass("com/example/nostatcfield/Test_reflect");
    jfieldID field_id = env->GetFieldID(clazz,"publicnotstaticfield","Ljava/lang/String;");
    jstring pu_obj = static_cast<jstring>(env->GetObjectField(testobj,field_id));
    const char* publicnotstaticfield = env->GetStringUTFChars(pu_obj, nullptr);
    __android_log_print(4,"test_jni","nostatic->%s",publicnotstaticfield);
    std::string hello = "Hello from C++";
    return env->NewStringUTF(publicnotstaticfield);
}
//    super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//   TextView tv = findViewById(R.id.sample_text);
//        Test_reflect one = new Test_reflect("fieldd");
//        tv.setText(stringFromJNI(one));
extern "C"
JNIEXPORT void JNICALL
Java_com_example_nostatcfield_MainActivity_onCreate(JNIEnv *env, jobject thiz, jobject savedInstanceState) {
    //    // TODO: implement onCreate()
    // protected void onCreate(@Nullable Bundle savedInstanceState)
    jclass AppCompatActivity1 =env->FindClass("androidx/appcompat/app/AppCompatActivity");
    jmethodID oncreate_mid = env->GetMethodID(AppCompatActivity1,"onCreate","(Landroid/os/Bundle;)V");
    //调用父类的方法用这个
    env->CallNonvirtualVoidMethod(thiz,AppCompatActivity1,oncreate_mid,savedInstanceState);
//    Log.i(“test”,"oncreate jni calling");
    jstring teststr = env->NewStringUTF("test");
    jstring oncreate_string = env->NewStringUTF("oncreate jni calling");
    jclass logclass = env->FindClass("android/util/Log");
    jmethodID  log_i_mid = env->GetStaticMethodID(logclass,"i","(Ljava/lang/String;Ljava/lang/String;)I");
    //调用静态返回值类型为int类型的函数，并且参数为两个
    jint  result = env->CallStaticIntMethod(logclass,log_i_mid,teststr,oncreate_string);
    __android_log_print(4,"test_jni","result->%d",result);





}