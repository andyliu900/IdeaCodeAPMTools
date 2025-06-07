/*
 * Copyright (C) 2025 Baidu, Inc. All Rights Reserved.
 */
package com.ideacode.apmtools.lib.util

import com.ideacode.apmtools.lib.api.Env
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.FileInputStream
import java.util.concurrent.CompletableFuture

/**
 * Copyright (C), 2021-2025, 无业游民
 * @ProjectName:    IdeaCodeAPMTools
 * @Package:        com.ideacode.apmtools.lib.util
 * @ClassName:      ProcessUtils
 * @Description:
 * @Author:         randysu
 * @CreateDate:     2025/6/7 16:31
 * @UpdateUser:
 * @UpdateDate:     2025/6/7 16:31
 * @UpdateRemark:
 * @Version:        1.0
 */
object ProcessUtils {

    private const val SUB_TAG = "ProcessUtils"
    private const val CMD_LINE_FILE = "/proc/self/cmdline"

    fun getCurrentProcessName(): String {
        val future = CompletableFuture<String>()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = readProcessNameFromFile() ?: fallbackToReflection()
                future.complete(result)
            } catch (e: Exception) {
                future.complete(fallbackToReflection())
            }
        }

        ApmLogX.d(SUB_TAG, "currentProcessName:${future.get()}")
        return future.get()

    }

    private fun readProcessNameFromFile(): String? {
        val buffer = ByteArray(256)
        var length = 0

        FileInputStream(CMD_LINE_FILE).use { fis ->
            var b: Int = 0
            while (length < buffer.size && fis.read().also { b = it } > 0) {
                buffer[length++] = b.toByte()
            }
        }

        return if (length > 0) {
            String(buffer, 0, length, Charsets.UTF_8).trim()
        } else {
            null
        }
    }

    /**
     * 可选：当 /proc/self/cmdline 失败时，尝试其他方式获取进程名
     */
    private fun fallbackToReflection(): String {
        return try {
            Class.forName("android.os.Process")
                .getMethod("myProcessName")
                .invoke(null) as? String ?: "unknown"
        } catch (e: Exception) {
            "unknown"
        }
    }

}