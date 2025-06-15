/*
 * Copyright (C) 2025 Baidu, Inc. All Rights Reserved.
 */
package com.ideacode.apmtools.lib.api

import com.ideacode.apmtools.lib.api.Env.APM_TAG
import com.ideacode.apmtools.lib.api.Env.getVersionName
import com.ideacode.apmtools.lib.core.Config
import com.ideacode.apmtools.lib.core.Manager
import com.ideacode.apmtools.lib.util.ApmLogX

/**
 * Copyright (C), 2021-2025, 无业游民
 * @ProjectName:    IdeaCodeAPMTools
 * @Package:        com.ideacode.apmtools.lib.api
 * @ClassName:      Client
 * @Description:
 * @Author:         randysu
 * @CreateDate:     2025/6/2 11:59
 * @UpdateUser:
 * @UpdateDate:     2025/6/2 11:59
 * @UpdateRemark:
 * @Version:        1.0
 */
object IdeacodeApmClient {

    private const val SUB_TAG = "IdeacodeApmClient"

    @Volatile
    private var isStart = false

    @Volatile
    private var isAttached = false

    @Synchronized
    fun attach(config: Config) {
        if (isAttached) {
            ApmLogX.e(SUB_TAG, "attach ideacode.apm version V" + getVersionName() + " already attached.")
        }

        isAttached = true
        ApmLogX.i(SUB_TAG, "attach ideacode.apm version V" + getVersionName())
        Manager.instance.setConfig(config)
        Manager.instance.init()

    }

    @Synchronized
    fun detach() {
        if (!isAttached) {
            ApmLogX.e(SUB_TAG, "attach ideacode.apm version V" + getVersionName() + " not ready to attach.")
            return
        }

        isAttached = false
        Manager.instance.terminate()
    }

    @Synchronized
    fun startWork(){
        if (isStart) {
            ApmLogX.e(SUB_TAG, "attach ideacode.apm version V" + getVersionName() + " already start.")
            return;
        }

        isStart = true;
        ApmLogX.d(SUB_TAG, "APM开始任务:startWork")

        Manager.instance.startWork()
    }

}