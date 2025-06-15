/*
 * Copyright (C) 2025 Baidu, Inc. All Rights Reserved.
 */
package com.ideacode.apmtools.lib.api

/**
 * Copyright (C), 2021-2025, 无业游民
 * @ProjectName:    IdeaCodeAPMTools
 * @Package:        com.ideacode.apmtools.lib.api
 * @ClassName:      MonitorContext
 * @Description:
 * @Author:         randysu
 * @CreateDate:     2025/6/11 23:33
 * @UpdateUser:
 * @UpdateDate:     2025/6/11 23:33
 * @UpdateRemark:
 * @Version:        1.0
 */
class MonitorContext {

    var strategy: MonitorStrategy? = null

//    fun setStrategy(strategy: MonitorStrategy) {
//        this.strategy = strategy
//    }

    fun startMonitor() {
        strategy?.doAction() ?: throw IllegalStateException("No Monitor strategy set")
    }

}