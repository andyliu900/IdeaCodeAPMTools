/*
 * Copyright (C) 2025 Baidu, Inc. All Rights Reserved.
 */
package com.ideacode.apmtools.lib.api

import com.ideacode.apmtools.lib.util.ApmLogX

import com.bytedance.android.bytehook.ByteHook
import com.bytedance.android.bytehook.ByteHook.Config
import com.bytedance.android.bytehook.ByteHook.ConfigBuilder

/**
 * Copyright (C), 2021-2025, 无业游民
 * @ProjectName:    IdeaCodeAPMTools
 * @Package:        com.ideacode.apmtools.lib.api
 * @ClassName:      MemoryMonitorStrategy
 * @Description:
 * @Author:         randysu
 * @CreateDate:     2025/6/11 23:32
 * @UpdateUser:
 * @UpdateDate:     2025/6/11 23:32
 * @UpdateRemark:
 * @Version:        1.0
 */
class MemoryMonitorStrategy : MonitorStrategy {

    companion object {
        const val SUB_TAG = "MemoryMonitorStrategy"
    }

    override fun doAction() {

        ApmLogX.d(SUB_TAG, "doAction")

        // 集成了 Leakcanary，自动进行 debug 环境下java内存检测

        // 监控 native 内存泄漏
        val configBuilder = ConfigBuilder()
        configBuilder.setMode(ByteHook.Mode.AUTOMATIC)
        configBuilder.setShadowhookDebug(true)
        configBuilder.setDebug(true)
        configBuilder.setRecordable(true)

        ByteHook.init(configBuilder.build())
        System.loadLibrary("native_hook")
        hookMallocByBHook()
    }

    external fun hookMallocByBHook() : String

}