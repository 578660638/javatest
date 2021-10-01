#include <jni.h>
#include <string>
#include "android/log.h"
#include "person.h"
#include "student.h"
#include "teacher.h"

void func1(int){
    __android_log_print(4,"cpp11","c:%s","void func(int)");
}

void func1(int *){
    __android_log_print(4,"cpp11","c:%s","void func(int *)" );
}
void swap(int &a,int&b){
    int c = a;
    a=b;
    b=c;
}
void swap(int* a,int* b){
    int c = *a;
    *a=*b;
    *b=c;
}
//int myadd(int a){
//    int result =0;
//    for(int i=0,i<=a;i++){
//        result=result+i;
//    }
//    return result;
//}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_cyyds_MainActivity_stringFromJNI(
        JNIEnv* env,
      jobject /* this */) {
    int a=10;
    //[捕捉列表]（參數）mutable->返回值类型{函数体}
    //规范的表达式
//    auto myadd1 = [](int num )->int{
//        int result =0;
//        for(int i =0;i<=num;i++){
//            result+=i;
//        }
//        return result;};
//    int result = myadd1(a);
//    __android_log_print(4,"cpp11","lambda result %d",result);
//
//    int result1 = [](int num )->int{
//        int result =0;
//        for(int i=0,i<=num;i++){
//        result=result+i;
//    }
//        return result;};
//    int result1 = [](int num )->int{
//        int result =0;
//        for(int i=0;i<=num;i++){
//            result+=num;
//
//        }
//        __android_log_print(4,"cpp11","lambda result %d",result);
//
//        return result;}(10);
//    int result1 = [](int num ){
//        int result =0;
//        for(int i=0;i<=num;i++){
//            result+=num;
//
//        }
//        __android_log_print(4,"cpp11","lambda result jian %d",result);
//
//        return result;}(10);
//简化版捕捉参数[=]捕捉所有作用域中的变量也可以【a,b,c】捕捉这abc三个作用域中的变量
    int result1 = [a]{
        int result =0;
        for(int i=0;i<=a;i++){
            result+=a;
        }
        __android_log_print(4,"cpp11","lambda result jian %d",result);
        return result;}();
    auto autolog = []{
        __android_log_print(4,"cpp11","i am call ");
    };
    autolog();
//      func1(nullptr);//nullptr代表一个空指针，记住NULL的二义性最终被替换成了0
//      int a =10;
//      int b =20;
//      int &c =a;
//      int &d =b;//引用  其实就一个变量的别名只能左值
//      swap(&a,&b);//通过指针进行转换
//    __android_log_print(4,"cpp11","c:%d,d:%d", a,b);
//      swap(c,d);
//    __android_log_print(4,"cpp11","c:%d,d:%d",a, b);
//        char chat_a = 'a'; //一个字节  64位
//        int int_b= 124;//4个字节
//        unsigned int int_c =125;
//        int &int_d = int_b;
//        int *int_e = &int_b;
//        __android_log_print(4,"cpp11","char:%d,int:%d,unsigned %d", sizeof(chat_a), sizeof(int_b),
//                            sizeof(int_c));
//         __android_log_print(4,"cpp11","&int_d:%d,int_e:%d,*int_e:%d", int_d, int_e,*int_e);
//   person man(0,20);
//    __android_log_print(4,"cpp11","man.getage:%d",man.getage());
//
//
//    student stu(0,10,26);
//    __android_log_print(4,"cpp11","stu.getage:%d",stu.getid());
//
//    teacher teacher1(1,30,12);
//    __android_log_print(4,"cpp11","teacher1.getstudent:%d",teacher1.getstudent(&stu));
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
