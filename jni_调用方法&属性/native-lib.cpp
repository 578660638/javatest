#include <jni.h>
#include <string>
#include <android/log.h>
#include <pthread.h>

JavaVM* javaVmglobal = nullptr;
extern "C" JNIEXPORT jstring JNICALL
Java_com_example_hook_1ndk_1so_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    JavaVM* thisvm = nullptr;

    env->GetJavaVM(&thisvm);
    __android_log_print(4,"test->jni","env->GetJavaVM(&javaVmglobal);->%p","%p",javaVmglobal);
    __android_log_print(4,"test->jni","env->GetJavaVM(&javaVmglobal);->%p","%p",thisvm);

    std::string hello = "Hello from C++";
    //jni创建对象使用函数NewObject来创建java对象，构造方法的方法返回值类型的签名始终为void


    //    jclass  Testjclass = env->FindClass("com/example/hook_ndk_so/Test_reflect");
//    jfieldID publicstaticfield_jfield = env->GetStaticFieldID(Testjclass,"publicstaticfield","Ljava/lang/String;");
//    jstring publicstaticfield_jobject = (jstring)env->GetStaticObjectField(Testjclass,publicstaticfield_jfield);
//    const char* content = env->GetStringUTFChars(publicstaticfield_jobject, nullptr);
//    __android_log_print(4,"test","_jni%s",content);
    return env->NewStringUTF(hello.c_str());
}
void* threadtest(void *args){
    for (int i = 0; i < 10; ++i) {
        __android_log_print(4,"test->jni","jni->%s,%d","jni-onload",i);

    }
    JNIEnv* threadenv = nullptr;
    if (javaVmglobal->GetEnv((void**)&threadenv,JNI_VERSION_1_6)){
        __android_log_print(4,"test->jni","javaVmglobal->GetEnv((void**)&threadenv,JNI_VERSION_1_6)->%s","sucess11");
        //通过jvm获取当前线程的env，jvm是全局的
        javaVmglobal->AttachCurrentThread(&threadenv, nullptr);
        jstring  jstring1 = threadenv->NewStringUTF("threading --create");
        const char* content = threadenv->GetStringUTFChars(jstring1, nullptr);
        __android_log_print(4,"test->jni","jni->%s",content);
        //不用了就释放
        threadenv->ReleaseStringUTFChars(jstring1, content);
    }
    else{
        __android_log_print(4,"test->jni","javaVmglobal->GetEnv((void**)&threadenv,JNI_VERSION_1_6)->%s","failed");
    }


    //一定要写这句话
    pthread_exit(0);
}
//pthread_create(pthread_t* __pthread_ptr, pthread_attr_t const* __attr, void* (*__start_routine)(void*), void*);
JNIEXPORT jint JNI_OnLoad(JavaVM* vm,void* reserved){
    javaVmglobal =vm;
    __android_log_print(4,"test->jni","jni->%s","JNI_Onload is called");
    jint result = 0;
    JNIEnv* env = nullptr;
    if (javaVmglobal->GetEnv((void**)&env,JNI_VERSION_1_6)==JNI_OK){
        __android_log_print(4,"test->jni","jni->%s","vm->GetEnv((void**)&env,JNI_VERSION_1_6) SUCESS");
    }
    pthread_t thread;
    //创建线程
    pthread_create(&thread, nullptr,threadtest, nullptr);
    pthread_join(thread, nullptr);
    result = JNI_VERSION_1_6;
    return result;
}

//javavm是虚拟机在jni层的代表，一个进程只有一个javavm，所有的线程共用一个javavm
//jnienv 表示java调用native语言环境，是一个封装了大量的jni方法的指针，jnienv只在创建它的线程生效，不能跨线程传递，不同的线程之间的jnienv彼此独立
//如果需要访问jni必须要调用attachcurrentthread关联，并使用detachcurrentthread解除链接
//    jint GetVersion()
//    { return functions->GetVersion(this); }
//
//    jclass DefineClass(const char *name, jobject loader, const jbyte* buf,
//                       jsize bufLen)
//    { return functions->DefineClass(this, name, loader, buf, bufLen); }
//
//    jclass FindClass(const char* name)
//    { return functions->FindClass(this, name); }
//
//    jmethodID FromReflectedMethod(jobject method)
//    { return functions->FromReflectedMethod(this, method); }
//
//    jfieldID FromReflectedField(jobject field)
//    { return functions->FromReflectedField(this, field); }
//
//    jobject ToReflectedMethod(jclass cls, jmethodID methodID, jboolean isStatic)
//    { return functions->ToReflectedMethod(this, cls, methodID, isStatic); }
//
//    jclass GetSuperclass(jclass clazz)
//    { return functions->GetSuperclass(this, clazz); }
//
//    jboolean IsAssignableFrom(jclass clazz1, jclass clazz2)
//    { return functions->IsAssignableFrom(this, clazz1, clazz2); }
//
//    jobject ToReflectedField(jclass cls, jfieldID fieldID, jboolean isStatic)
//    { return functions->ToReflectedField(this, cls, fieldID, isStatic); }
//
//    jint Throw(jthrowable obj)
//    { return functions->Throw(this, obj); }
//
//    jint ThrowNew(jclass clazz, const char* message)
//    { return functions->ThrowNew(this, clazz, message); }
//
//    jthrowable ExceptionOccurred()
//    { return functions->ExceptionOccurred(this); }
//
//    void ExceptionDescribe()
//    { functions->ExceptionDescribe(this); }
//
//    void ExceptionClear()
//    { functions->ExceptionClear(this); }
//
//    void FatalError(const char* msg)
//    { functions->FatalError(this, msg); }
//
//    jint PushLocalFrame(jint capacity)
//    { return functions->PushLocalFrame(this, capacity); }
//
//    jobject PopLocalFrame(jobject result)
//    { return functions->PopLocalFrame(this, result); }
//
//    jobject NewGlobalRef(jobject obj)
//    { return functions->NewGlobalRef(this, obj);

//#if defined(__cplusplus)
//    jint DestroyJavaVM()
//    { return functions->DestroyJavaVM(this); }
//    jint AttachCurrentThread(JNIEnv** p_env, void* thr_args)//新建一个线程通过javavm获取到env
//    { return functions->AttachCurrentThread(this, p_env, thr_args); }
//    jint DetachCurrentThread()
//    { return functions->DetachCurrentThread(this); }
//    jint GetEnv(void** env, jint version)  //通过java  vm获取到env
//    { return functions->GetEnv(this, env, version); }
//    jint AttachCurrentThreadAsDaemon(JNIEnv** p_env, void* thr_args)
//    { return functions->AttachCurrentThreadAsDaemon(this, p_env, thr_args); }
//#endif /*__cplusplus*/


extern "C"
JNIEXPORT void JNICALL
Java_com_example_hook_1ndk_1so_MainActivity_newobject(JNIEnv *env, jobject thiz) {
    // TODO: implement newobject()NewObject
    //通过反射获取java中的类
    jclass Testjclass = env->FindClass("com/example/hook_ndk_so/Test_reflect");
    //通过类拿到方法，构造方法，指定多少参数的构造方法
    jmethodID con_mid = env->GetMethodID(Testjclass,"<init>","(Ljava/lang/String;)V");
    //创建字符串
    jstring arg1 = env->NewStringUTF("woshigouzao-->from jni");
    //创建对象--方法1
    jobject testobj = env->NewObject(Testjclass,con_mid,arg1);
    if (testobj!= nullptr){
        __android_log_print(4,"test->jni","jni->%s","newobject SUCESS");
    }



}



extern "C"
JNIEXPORT void JNICALL
Java_com_example_hook_1ndk_1so_MainActivity_getobjectField(JNIEnv *env, jobject thiz) {
    // TODO: implement getobjectField()静态属性字段获取
    // public static String publicstaticfield = "i am public static String publicstaticfield";
    jclass  Testclass = env->FindClass("com/example/hook_ndk_so/Test_reflect");
    jfieldID testfield = env->GetStaticFieldID(Testclass,"publicstaticfield","Ljava/lang/String;");
    jstring pulicstaticfield  = static_cast<jstring>(env->GetStaticObjectField(Testclass,testfield));
    const char*  pulicstaticfield_content = env->GetStringUTFChars(pulicstaticfield, nullptr);
    __android_log_print(4,"test->jni","jni->%s",pulicstaticfield_content);
    //  private static String privateStaticfield = "i am a privatestatic String ";
    jfieldID privatestaticfield_field = env->GetStaticFieldID(Testclass,"privateStaticfield","Ljava/lang/String;");
    jstring privateStaticfield_obj = static_cast<jstring>(env->GetStaticObjectField(Testclass,privatestaticfield_field));
    const char* privatestaticfield_content = env->GetStringUTFChars(privateStaticfield_obj, nullptr);
    __android_log_print(4,"test->jni","jni->%s",privatestaticfield_content);
    //  public String publicnotstaticfield = "i am publicnotstaticfield";
    jfieldID fieldID =  env->GetFieldID(Testclass,"publicnotstaticfield","Ljava/lang/String;");
    env->GetObjectField(Testclass,fieldID);

    // private String privatecnotstaticfield = "i am a private no staticfield";

}
extern "C"
JNIEXPORT void JNICALL
Java_com_example_hook_1ndk_1so_MainActivity_getnostaticobjectField(JNIEnv *env, jobject thiz,jobject test) {
    // TODO: implement getnostaticobjectField() 和java中的访问类属性相比，jni中还需要传入该属性的签名信息，jni不需要进行修改公私有，在jni当中构造函数也是普通函数都使用jmethodid只是签名信息使用<init>

    //  public String publicnotstaticfield = "i am publicnotstaticfield";
    jclass  Testclass = env->FindClass("com/example/hook_ndk_so/Test_reflect");
    jfieldID publicnotstaticfield_field = env->GetFieldID(Testclass,"publicnotstaticfield","Ljava/lang/String;");
    jstring pulicfield  = static_cast<jstring>(env->GetObjectField(test,publicnotstaticfield_field));
    const char*  pulicfield_content = env->GetStringUTFChars(pulicfield, nullptr);
    __android_log_print(4,"test->jni","jni->%s",pulicfield_content);
    //    private String privatecnotstaticfield = "i am a private no staticfield";
    jfieldID privatecnotstaticfield_field = env->GetFieldID(Testclass,"privatecnotstaticfield","Ljava/lang/String;");
    jstring privatefield  = static_cast<jstring>(env->GetObjectField(test,privatecnotstaticfield_field));
    const char*  privatefield_content = env->GetStringUTFChars(privatefield, nullptr);
    __android_log_print(4,"test->jni","jni->%s",privatefield_content);

}

extern "C"
JNIEXPORT jobject JNICALL
Java_com_example_hook_1ndk_1so_MainActivity_callinitjava(JNIEnv *env, jobject thiz) {
    // TODO: implement callinitjava()  调用初始化函数
    jclass Test_reflect = env->FindClass("com/example/hook_ndk_so/Test_reflect");
    jmethodID com_mid = env->GetMethodID(Test_reflect,"<init>","(Ljava/lang/String;Ljava/lang/String;)V");
    jstring arg0 = env->NewStringUTF("i am callinitjava1");
    jstring arg1 = env->NewStringUTF("i am callinitjava2");
    jobject obj = env->NewObject(Test_reflect,com_mid,arg0,arg1);
    return obj;



}
extern "C"
JNIEXPORT void JNICALL
Java_com_example_hook_1ndk_1so_MainActivity_callstaticjava(JNIEnv *env, jobject thiz) {
    // TODO: implement callstaticjava()
    //  public static void publicstaticfunc() 调用静态函数
    jclass Test_reflect = env->FindClass("com/example/hook_ndk_so/Test_reflect");
    jmethodID publicstaticfunc_mid = env->GetStaticMethodID(Test_reflect,"publicstaticfunc","()V");
    env->CallStaticVoidMethod(Test_reflect,publicstaticfunc_mid);
}
extern "C"
JNIEXPORT void JNICALL
Java_com_example_hook_1ndk_1so_MainActivity_callnostaticjava(JNIEnv *env, jobject thiz,jobject obj1) {
    // TODO: implement callnostaticjava() public  void publicnostaticfunc(){//调用非静态函数
    //        Log.i(TAG, "public nostatic publicnostaticfunc: "+flag);
    //    }
    jclass Test_reflect = env->FindClass("com/example/hook_ndk_so/Test_reflect");
    jmethodID publicnostaticfunc_mid =  env->GetMethodID(Test_reflect,"publicnostaticfunc","()V");
     env->CallVoidMethod(obj1,publicnostaticfunc_mid);
}