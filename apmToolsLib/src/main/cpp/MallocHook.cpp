#include <cstdlib>
#include <android/log.h>
#include <dlfcn.h>
#include "bytehook.h"

#define TAG "Ideacode_APM_Tools"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, TAG, __VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, TAG, __VA_ARGS__)

// 封装为命名空间
namespace MallocHook {

    static void *(*origin_malloc)(size_t) = nullptr;

    void *my_malloc(size_t size) {
        void *ptr = origin_malloc(size);
        LOGI("malloc(%zu) = %p", size, ptr);
        return ptr;
    }

    void HookCallback(bytehook_stub_t stub, int status_code,
                      const char *caller_path, const char *sym_name,
                      void *new_addr, void *orig_addr, void *arg) {
        if (status_code == BYTEHOOK_STATUS_CODE_OK) {
            origin_malloc = (void *(*)(size_t))orig_addr;
            LOGI("Hooked %s successfully", sym_name);
        } else {
            LOGE("Failed to hook %s", sym_name);
        }
    }

    // 安装 Hook（供 JNI 调用）
    void Install() {
        bytehook_hook_single(
                nullptr,            // 所有调用者模块
                nullptr,            // 所有被调用模块
                "malloc",           // hook malloc
                (void *)my_malloc,  // 替代函数
                HookCallback,
                nullptr
        );
    }
}