#include "bytehook.h"

#include <jni.h>
#include <android/log.h>
#include "MallocHook.cpp"

extern "C" JNIEXPORT jstring JNICALL
Java_com_ideacode_apmtools_lib_api_MemoryMonitorStrategy_hookMallocByBHook(
        JNIEnv* env,
        jobject) {
    MallocHook::Install();
}