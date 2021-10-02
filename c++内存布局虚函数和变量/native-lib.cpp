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
class Cbase{};
//int myadd(int a){
//    int result =0;
//    for(int i=0,i<=a;i++){
//        result=result+i;
//    }
//    return result;
//}
//虚函数的类
//arm64偏移为8
//arm32偏移为4 在int a 前边有一个指针为虚函数指针，这个一定要注意，和虚函数的个数是没有关系的
class VBase{
private:
    int a;
    char b;
public:
    VBase(int a, char b):a(a),b(b){
        __android_log_print(4,"cpp11","VBase(int a, char b):a(a),b(b) %d,b%d",a,b);
    }
    virtual void f(){
        __android_log_print(4,"cpp11","virtual void f()%s","virtual void f()");
    };
    virtual void g(){
        __android_log_print(4,"cpp11","virtual void g()%s","virtual void g()");
    };
    virtual void h(){
        __android_log_print(4,"cpp11","virtual void h()%s","virtual void h()");
    };

};
//
//class CBase{
//    //char类型占一个字节
//    //int占四个字节
//private:
//    int a;
//    char b;
//public:
//    CBase(int a, char b):a(a),b(b){
//        __android_log_print(4,"cpp11"," CBase(int a, char b):a(a),b(b) %d,b%d",a,b);
//    }
//};
extern "C" JNIEXPORT jstring JNICALL Java_com_example_cyyds_MainActivity_stringFromJNI(
        JNIEnv* env,
      jobject /* this */) {
//    int a=10;
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
//    int result1 = [a]{
//        int result =0;
//        for(int i=0;i<=a;i++){
//            result+=a;
//        }
//        __android_log_print(4,"cpp11","lambda result jian %d",result);
//        return result;}();
//    auto autolog = []{
//        __android_log_print(4,"cpp11","i am call ");
//    };
//    autolog();
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
//c++内存分布测试

//    Cbase c1;
////显示1个大小的空间
//    __android_log_print(4,"cpp11","sizeof(nullclass):%d", sizeof(c1));
//    CBase cBase1(100,"b");
//    //对象内存空间补齐
//    __android_log_print(4,"cpp11","sizeof(cBase):%d", sizeof(cBase1));
//    __android_log_print(4,"cpp11","sizeof(cBase):%d", *(int*)&cBase1, sizeof(cBase1));
//    for(int i=0;i<8;i++){
//        unsigned char* array=(unsigned char*)(&cBase1);
//        __android_log_print(4,"cpp11","i%d,value%d", i, array[i]);
//    }
    VBase v1(66,'n');
    __android_log_print(4,"cpp11","sizeof(v1):%d", sizeof(v1));
    for(int i=0;i< sizeof(v1);i++){
        unsigned char* array=(unsigned char*)(&v1);
        __android_log_print(4,"cpp11","Vbase i %d,value  %d", i, array[i]);
    }
    v1.f();
    v1.g();
    v1.h();
    __android_log_print(4,"cpp11","==---==", "-----------");
//c++中虚函数内存布局
#if defined(__arm__)
    unsigned int vtableptr=*(unsigned int*)(&v1);
    unsigned int faddr = *(unsigned int*)vtableptr;
    unsigned int gaddr = *(unsigned int*)(vtableptr+4);
    unsigned int haddr = *(unsigned int*)(vtableptr+8);
     typedef void(*Func)(void*);
    Func ffunc=(Func)faddr;
    Func gfunc=(Func)gaddr;
    Func hfunc=(Func)haddr;
    ffunc(&v1);
    gfunc(&v1);
    hfunc(&v1);
#else
    unsigned long vtableptr=*(unsigned long*)(&v1);
    unsigned long faddr = *(unsigned long *)vtableptr;
    unsigned long gaddr = *(unsigned long *)(vtableptr+8);
    unsigned long haddr =*(unsigned long *)(vtableptr+16);
    //定义一个函数  传地址和参数就行
    typedef void(*Func)(void *);
    Func ffunc=(Func)faddr;
    Func gfunc=(Func)gaddr;
    Func hfunc=(Func)haddr;
    ffunc(&v1);
    gfunc(&v1);
    hfunc(&v1);
#endif
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
