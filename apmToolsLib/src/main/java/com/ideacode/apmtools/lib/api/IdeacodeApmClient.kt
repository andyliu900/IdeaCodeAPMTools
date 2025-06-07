/*
 * Copyright (C) 2025 Baidu, Inc. All Rights Reserved.
 */
package com.ideacode.apmtools.lib.api

import com.ideacode.apmtools.lib.api.Env.getVersionName
import com.ideacode.apmtools.lib.core.Config
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
open class IdeacodeApmClient {

    companion object {
        const val TAG = "IdeacodeApmClient"
    }

    @Volatile
    private var isStart = false

    @Volatile
    private var isAttached = false

    @Synchronized
    fun attach(config: Config) {
        if (isAttached) {
            ApmLogX.e(TAG, "attach ideacode.apm version V" + getVersionName() + " already attached.")
        }
    }

}