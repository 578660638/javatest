#include <jni.h>
#include <string>
#include "android/log.h"


JavaVM* globalvm = nullptr;
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

void onCreate(JNIEnv *env, jobject thiz, jobject savedInstanceState) {
    //    // TODO: implement onCreate()
    // protected void onCreate(@Nullable Bundle savedInstanceState)
    jclass AppCompatActivity1 =env->FindClass("androidx/appcompat/app/AppCompatActivity");
    jmethodID oncreate_mid = env->GetMethodID(AppCompatActivity1,"onCreate","(Landroid/os/Bundle;)V");
    //调用父类的方法用这个
    env->CallNonvirtualVoidMethod(thiz,AppCompatActivity1,oncreate_mid,savedInstanceState);
//    Log.i(“test”,"oncreate jni calling");
    __android_log_print(4,"test_jni","oncreate jni calling");
    jstring teststr = env->NewStringUTF("test");
    jstring oncreate_string = env->NewStringUTF("oncreate jni calling");
    jclass logclass = env->FindClass("android/util/Log");
    jmethodID  log_i_mid = env->GetStaticMethodID(logclass,"i","(Ljava/lang/String;Ljava/lang/String;)I");
    //调用静态返回值类型为int类型的函数，并且参数为两个
    jint  result = env->CallStaticIntMethod(logclass,log_i_mid,teststr,oncreate_string);
    __android_log_print(4,"test_jni","result->%d",result);
}
extern "C"
JNIEXPORT jobject JNICALL
Java_com_example_nostatcfield_MainActivity_callInit(JNIEnv *env, jobject thiz) {
    // TODO: implement callInit()
    // public Test_reflect(String arg1,String arg2)
    jclass initclass = env->FindClass("com/example/nostatcfield/Test_reflect");
    jmethodID init_mid = env->GetMethodID(initclass,"<init>", "(Ljava/lang/String;Ljava/lang/String;)V");
    jstring args1 = env->NewStringUTF("woshi1");
    jstring args2 = env->NewStringUTF("woshi2");
    jobject NEWOBJ = env->NewObject(initclass,init_mid,args1,args2);
    return NEWOBJ;


}

// jint RegisterNatives(jclass clazz, const JNINativeMethod* methods,
//        jint nMethods)
//typedef struct {
//    const char* name;
//    const char* signature;
//    void*       fnPtr;
//} JNINativeMethod;






JNIEXPORT jint JNI_OnLoad(JavaVM *vm,void *reserved){
    globalvm = vm;
    JNIEnv *env = nullptr;
    if(vm->GetEnv((void**)&env,JNI_VERSION_1_6)==JNI_OK){
        __android_log_print(4,"test_jni","vm->GetEnv((void**)&env,JNI_VERSION_1_6)==JNI_OK->%s","vm->GetEnv((void**)&env,JNI_VERSION_1_6)==JNI_OKSUCCESS!!");

    }

    JNINativeMethod jniNativeMethods[] ={
            {"onCreate","(Landroid/os/Bundle;)V",(void*)onCreate}
    };
    __android_log_print(4,"test_jni","vm->GetEnv((void**)&env,JNI_VERSION_1_6)==JNI_OK->%s","vm-_OKSUCCESS!!");
    jclass  mainactivityjclass = env->FindClass("com/example/nostatcfield/MainActivity");
    __android_log_print(4,"test_jni","vm->GetEnv((void**)&env,JNI_VERSION_1_6)==JNI_OK->%s","vOKSUCCESS!!");
    env->RegisterNatives(mainactivityjclass,jniNativeMethods,1);
    __android_log_print(4,"test_jni","vm->GetEnv((void**)&env,JNI_VERSION_1_6)==JNI_OK->%s","SUCCESS!!");
    return JNI_VERSION_1_6;
}

//init写法
extern "C" void _init(void){
    __android_log_print(4,"test_jni","%s","gointo init SUCCESS!!");

}
//initarray写法
__attribute__((constructor,visibility("hidden")))void initarray1(void){
    __android_log_print(4,"test_jni","%s","gointo initarray1 SUCCESS!!");
}
__attribute__((constructor,visibility("hidden")))void initarray2(void){
    __android_log_print(4,"test_jni","%s","gointo initarray2 SUCCESS!!");
}
//流程 init->initarray->onload->jnifunc
//动态注册流程RegisterNatives  8.1
//
// 1--static jint RegisterNatives(JNIEnv* env,jclass java_class, const JNINativeMethod* methods,jint method_count)
//2--const void* ArtMethod::RegisterNative(const void* native_method, bool is_fast)
//3--SetEntryPointFromJni(const void* entrypoint)