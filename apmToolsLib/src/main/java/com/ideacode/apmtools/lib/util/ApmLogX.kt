/*
 * Copyright (C) 2025 Baidu, Inc. All Rights Reserved.
 */
package com.ideacode.apmtools.lib.util

import android.util.Log
import com.ideacode.apmtools.lib.api.Env

/**
 * Copyright (C), 2021-2025, 无业游民
 * @ProjectName:    IdeaCodeAPMTools
 * @Package:        com.ideacode.apmtools.lib.util
 * @ClassName:      ApmLogX
 * @Description:
 * @Author:         randysu
 * @CreateDate:     2025/6/7 16:24
 * @UpdateUser:
 * @UpdateDate:     2025/6/7 16:24
 * @UpdateRemark:
 * @Version:        1.0
 */
object ApmLogX {

    private const val LOG_FORMATTER = "❖ %s/%s  ❖  %s"

    enum class LogType {
        VERBOSE,
        DEBUG,
        INFO,
        WARN,
        ERROR,
        ASSERT
    }

    @JvmStatic
    fun v(subTag: String, msg: String) {
        showLongLog(Env.APM_TAG, subTag, msg, LogType.VERBOSE)
    }

    @JvmStatic
    fun v(tag: String, subTag: String, msg: String) {
        showLongLog(tag, subTag, msg, LogType.VERBOSE)
    }

    @JvmStatic
    fun d(subTag: String, msg: String) {
        showLongLog(Env.APM_TAG, subTag, msg, LogType.DEBUG)
    }

    @JvmStatic
    fun d(tag: String, subTag: String, msg: String) {
        showLongLog(tag, subTag, msg, LogType.DEBUG)
    }

    @JvmStatic
    fun i(subTag: String, msg: String) {
        showLongLog(Env.APM_TAG, subTag, msg, LogType.INFO)
    }

    @JvmStatic
    fun i(tag: String, subTag: String, msg: String) {
        showLongLog(tag, subTag, msg, LogType.INFO)
    }

    @JvmStatic
    fun w(subTag: String, msg: String) {
        showLongLog(Env.APM_TAG, subTag, msg, LogType.WARN)
    }

    @JvmStatic
    fun w(tag: String, subTag: String, msg: String) {
        showLongLog(tag, subTag, msg, LogType.WARN)
    }

    @JvmStatic
    fun e(subTag: String, msg: String) {
        showLongLog(Env.APM_TAG, subTag, msg, LogType.ERROR)
    }

    @JvmStatic
    fun e(tag: String, subTag: String, msg: String) {
        showLongLog(tag, subTag, msg, LogType.ERROR)
    }

    @JvmStatic
    fun a(subTag: String, msg: String) {
        showLongLog(Env.APM_TAG, subTag, msg, LogType.ASSERT)
    }

    @JvmStatic
    fun a(tag: String, subTag: String, msg: String) {
        showLongLog(tag, subTag, msg, LogType.ASSERT)
    }

    // === Private Implementation ===

    private fun showLongLog(tag: String, subTag: String?, msg: String, type: LogType) {
        val trimmedMsg = msg.trim()
        var index = 0
        val maxLength = 4000
        var sub: String

        while (index < trimmedMsg.length) {
            sub = if (trimmedMsg.length <= index + maxLength) {
                trimmedMsg.substring(index)
            } else {
                trimmedMsg.substring(index, index + maxLength)
            }
            index += maxLength

            val formattedSub = sub.trim()

            val logMessage = if (subTag.isNullOrEmpty()) {
                formattedSub
            } else {
                String.format(LOG_FORMATTER, ProcessUtils.getCurrentProcessName(), subTag,
                    formattedSub)
            }

            when (type) {
                LogType.VERBOSE -> Log.v(tag, logMessage)
                LogType.DEBUG -> Log.d(tag, logMessage)
                LogType.INFO -> Log.i(tag, logMessage)
                LogType.WARN -> Log.w(tag, logMessage)
                LogType.ERROR -> Log.e(tag, logMessage)
                LogType.ASSERT -> Log.wtf(tag, logMessage)
            }
        }
    }

}