#include <jni.h>
#include <malloc.h>
#include <elf.h>
#include <android/log.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>

#define TAG "Ideacode_APM_Tools"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, TAG, __VA_ARGS__)

extern "C"
JNIEXPORT void JNICALL
Java_com_ideacode_apmtools_nativelib_NativeLib_testMallocLeak(JNIEnv *env,jobject thiz) {
    void * result = malloc(100*1000*1000);
}