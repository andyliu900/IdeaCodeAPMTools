/*
 * Copyright (C) 2025 Baidu, Inc. All Rights Reserved.
 */
package com.ideacode.apmtools.lib.core

/**
 * Copyright (C), 2021-2025, 无业游民
 * @ProjectName:    IdeaCodeAPMTools
 * @Package:        com.ideacode.apmtools.lib.core
 * @ClassName:      Config
 * @Description:
 * @Author:         randysu
 * @CreateDate:     2025/6/7 15:48
 * @UpdateUser:
 * @UpdateDate:     2025/6/7 15:48
 * @UpdateRemark:
 * @Version:        1.0
 */
class Config (
    var debugLogOpen : Boolean = false,
    var isMonitorMemory : Boolean = false,
    var isMonitorCpu : Boolean = false,
    var isMonitorCrash : Boolean = false
) {

    companion object {
        const val TAG = "Config"
    }

    override fun toString(): String {
        return """
            |apm config:
            | debugLogOpen: $debugLogOpen
            | isMonitorMemory: $isMonitorMemory
            | isMonitorCpu: $isMonitorCpu
            | isMonitorCrash: $isMonitorCrash
        """.trimMargin()
    }

}