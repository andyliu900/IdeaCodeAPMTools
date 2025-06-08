/*
 * Copyright (C) 2025 Baidu, Inc. All Rights Reserved.
 */
package com.ideacode.apmtools.lib.core

import com.ideacode.apmtools.lib.util.ApmLogX

/**
 * Copyright (C), 2021-2025, 无业游民
 * @ProjectName:    IdeaCodeAPMTools
 * @Package:        com.ideacode.apmtools.lib.core
 * @ClassName:      Manager
 * @Description:
 * @Author:         randysu
 * @CreateDate:     2025/6/8 23:03
 * @UpdateUser:
 * @UpdateDate:     2025/6/8 23:03
 * @UpdateRemark:
 * @Version:        1.0
 */
class Manager private constructor() {

    companion object {
        const val SUB_TAG = "Manager"
        val instance: Manager by lazy { Manager() }
    }

    var mConfig: Config? = null
    var mWorkFlag: Boolean = false

    fun setConfig(config: Config) {
        mConfig = config
    }

    fun getConfig(): Config? {
        return mConfig
    }

    fun init() {
        if (mConfig == null) {
            ApmLogX.e(SUB_TAG, "init(), config must not be null!!")
            return
        }

        mConfig?.let {
            if (it.isMonitorMemory) {
                ApmLogX.e(SUB_TAG, "MonitorMemory is open")
            } else if (it.isMonitorCpu) {
                ApmLogX.e(SUB_TAG, "MonitorCpu is open")
            } else if (it.isMonitorCrash) {
                ApmLogX.e(SUB_TAG, "Monitor is open")
            }
        }
    }

    fun terminate() {
        if (mConfig == null) {
            ApmLogX.e(SUB_TAG, "terminate(), config must not be null!!")
            return
        }

        stopWork()
    }

    fun startWork() {
        if (mConfig == null) {
            ApmLogX.e(SUB_TAG, "startWork(), config must not be null!!")
            return
        }

        if (mWorkFlag) {
            ApmLogX.e(SUB_TAG, "the work has started!")
            return
        }

        mWorkFlag = true
        ApmLogX.d(SUB_TAG, "startWork!")

        mConfig?.let {
            if (it.isMonitorMemory) {

            } else if (it.isMonitorCpu) {

            } else if (it.isMonitorCrash) {

            }
        }
    }

    fun stopWork() {
        if (mConfig == null) {
            ApmLogX.e(SUB_TAG, "stopWork(), config must not be null!!")
            return
        }

        mConfig?.let {
            if (it.isMonitorMemory) {

            } else if (it.isMonitorCpu) {

            } else if (it.isMonitorCrash) {

            }
        }
    }

}