#include <jni.h>
#include <malloc.h>
#include <elf.h>
#include <android/log.h>

extern "C"
JNIEXPORT void JNICALL
Java_com_ideacode_apmtools_nativelib_NativeLib_testMallocLeak(JNIEnv *env,jobject thiz) {
    void * result = malloc(100*1000*1000);
}