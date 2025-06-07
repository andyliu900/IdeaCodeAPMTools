/*
 * Copyright (C) 2025 Baidu, Inc. All Rights Reserved.
 */
package com.ideacode.apmtools.lib.core

/**
 * Copyright (C), 2021-2025, 无业游民
 * @ProjectName:    IdeaCodeAPMTools
 * @Package:        com.ideacode.apmtools.lib.core
 * @ClassName:      ConfigBuilder
 * @Description:
 * @Author:         randysu
 * @CreateDate:     2025/6/7 15:54
 * @UpdateUser:
 * @UpdateDate:     2025/6/7 15:54
 * @UpdateRemark:
 * @Version:        1.0
 */
class ConfigBuilder private constructor(){

    var debugLogOpen : Boolean = false
    var isMonitorMemory : Boolean = false
    var isMonitorCpu : Boolean = false
    var isMonitorCrash : Boolean = false

    fun setDebugLogOpen(logEnable: Boolean) = apply { this.debugLogOpen = logEnable }
    fun setMonitorMemory(memoryEnable: Boolean) = apply { this.isMonitorMemory = memoryEnable }
    fun setMonitorCpu(cpuEnable: Boolean) = apply { this.isMonitorCpu = cpuEnable }
    fun setMonitorCrash(crashEnable: Boolean) = apply { this.isMonitorCrash = crashEnable }

    fun build(): Config {
        return Config(
            debugLogOpen = debugLogOpen,
            isMonitorMemory = isMonitorMemory,
            isMonitorCpu = isMonitorCpu,
            isMonitorCrash = isMonitorCrash
        )
    }

    companion object {
        fun newBuilder(): ConfigBuilder = ConfigBuilder()
    }
}