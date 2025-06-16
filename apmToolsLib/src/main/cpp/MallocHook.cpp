#include <cstdlib>
#include <android/log.h>
#include "bytehook.h"

#define TAG "ByteHookExample"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, TAG, __VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, TAG, __VA_ARGS__)

namespace MallocHook {

    // 保存原始 malloc 函数指针
    static void* (*origin_malloc)(size_t) = nullptr;

    // 替代的 malloc 函数
    void* my_malloc(size_t size) {
        void* ptr = origin_malloc(size);  // 调用原始 malloc
        LOGI("[HOOKED] malloc(%zu) = %p", size, ptr);
        return ptr;
    }

    // Hook 安装后的回调
    void HookCallback(bytehook_stub_t stub, int status_code,
                      const char* caller_path, const char* sym_name,
                      void* new_addr, void* orig_addr, void* arg) {
        if (status_code == BYTEHOOK_STATUS_CODE_OK) {
            origin_malloc = reinterpret_cast<void* (*)(size_t)>(orig_addr);
            LOGI("[HOOK SUCCESS] %s from %s", sym_name, caller_path ? caller_path : "unknown");
        } else {
            LOGE("[HOOK FAILED] %s from %s", sym_name, caller_path ? caller_path : "unknown");
        }
    }


    // hook calloc
    static void* (*origin_calloc)(size_t, size_t) = nullptr;

    void* my_calloc(size_t nmemb, size_t size) {
        void* ptr = origin_calloc(nmemb, size);
        LOGI("[HOOKED] calloc(%zu, %zu) = %p", nmemb, size, ptr);
        return ptr;
    }

    // hook mmap
    static void *(*origin_mmap)(void *, size_t, int, int, int, off_t) = nullptr;

    void *my_mmap(void *addr, size_t length, int prot, int flags, int fd, off_t offset) {
        void *result = origin_mmap(addr, length, prot, flags, fd, offset);
        LOGI("[HOOKED] mmap length=%zu -> %p", length, result);
        return result;
    }

    void mmap_hook_callback(bytehook_stub_t stub, int status_code, const char *caller_path,
                            const char *sym_name, void *new_addr, void *orig_addr, void *arg) {
        if (status_code == BYTEHOOK_STATUS_CODE_OK) {
            origin_mmap = (decltype(origin_mmap))orig_addr;
            LOGI("[HOOK SUCCESS] %s", sym_name);
        } else {
            LOGE("[HOOK FAILED] %s", sym_name);
        }
    }

    // 安装 hook
    void Install() {
//        bytehook_init(BYTEHOOK_MODE_AUTOMATIC, true);

        void* handle = bytehook_hook_single(
                nullptr,        // hook 所有调用者模块
                "libc.so",        // hook 所有被调用模块（libc 中的 malloc）
                "malloc",       // 要 hook 的符号
                (void*)my_malloc,
                HookCallback,
                nullptr
        );

        if (handle != nullptr) {
            LOGI("malloc hooked successfully");
        } else {
            LOGE("Failed to hook malloc");
        }

//        bytehook_hook_single(
//                nullptr,
//                "libc.so",
//                "calloc",
//                (void*)my_calloc,
//                [](bytehook_stub_t stub, int status_code, const char* caller_path,
//                   const char* sym_name, void* new_addr, void* orig_addr, void* arg) {
//                    if (status_code == BYTEHOOK_STATUS_CODE_OK) {
//                        origin_calloc = (decltype(origin_calloc))orig_addr;
//                        LOGI("[HOOK SUCCESS] %s", sym_name);
//                    } else {
//                        LOGI("[HOOK FAILED] %s", sym_name);
//                    }
//                },
//                nullptr
//        );

//        void* handle = bytehook_hook_single(
//                nullptr,          // hook 所有调用者
//                "libc.so",        // 目标模块
//                "__mmap2",           // 要 hook 的符号
//                (void *)my_mmap,
//                mmap_hook_callback,
//                nullptr
//        );
//
//        if (handle != nullptr) {
//            LOGI("malloc hooked successfully");
//        } else {
//            LOGE("Failed to hook mmap");
//        }
    }
}